package com.fse.estockmarketcompany.service.impl;

import com.fse.estockmarketcompany.common.CommonConstant;
import com.fse.estockmarketcompany.exception.CommonInternalException;
import com.fse.estockmarketcompany.model.Company;
import com.fse.estockmarketcompany.model.CompanyAll;
import com.fse.estockmarketcompany.model.request.CompanyRequest;
import com.fse.estockmarketcompany.repository.CompanyAllRepository;
import com.fse.estockmarketcompany.repository.CompanyRepository;
import com.fse.estockmarketcompany.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository repo;

    @Autowired
    private CompanyAllRepository mongoRepo;

    @Override
    public Map<String, String> register(CompanyRequest request) throws CommonInternalException {
        if (!ObjectUtils.isEmpty(request.getCompanyCEO())
                && !ObjectUtils.isEmpty(request.getCompanyName())
                && !ObjectUtils.isEmpty(request.getCompanyTurnOver())) {
            if (request.getCompanyTurnOver() >= 100000000) {
                Company com = repo.save(new Company(request));
                mongoRepo.save(new CompanyAll(com, null));
                Map<String, String> mp = CommonConstant.getSuccessMapResponse();
                mp.put("companyId", String.valueOf(com.getCompanyCode()));
                return mp;
            } else {
                throw new CommonInternalException(
                        "Company turn over should be greater than 10CR", HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new CommonInternalException(
                    "Kindly fill all required fields", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public CompanyAll info(int companyCode) throws CommonInternalException {
        Optional<CompanyAll> data = mongoRepo.findById(companyCode);
        if (data.isPresent()) {
            return data.get();
        } else {
            throw new CommonInternalException(
                    "Company details not found", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<CompanyAll> getAllCompanyInfo() {
        return mongoRepo.findAll();
    }
}
