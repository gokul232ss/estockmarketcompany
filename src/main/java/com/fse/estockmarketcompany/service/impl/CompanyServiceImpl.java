package com.fse.estockmarketcompany.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fse.estockmarketcompany.common.CommonConstant;
import com.fse.estockmarketcompany.exception.CommonInternalException;
import com.fse.estockmarketcompany.model.Company;
import com.fse.estockmarketcompany.model.CompanyAll;
import com.fse.estockmarketcompany.model.Stock;
import com.fse.estockmarketcompany.model.request.CompanyRequest;
import com.fse.estockmarketcompany.repository.CompanyAllRepository;
import com.fse.estockmarketcompany.repository.CompanyRepository;
import com.fse.estockmarketcompany.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository repo;

    @Autowired
    private CompanyAllRepository mongoRepo;

    @Autowired
    private ObjectMapper ob;

    @Autowired
    private RabbitMqServiceImpl rabbitMqService;

    @Value("${com.fse.exchange}")
    private String fseExchange;

    @Value("${com.fse.company.routingkey}")
    private String routingKey;

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
            CompanyAll dataValue = data.get();
            List<Double> priceList = dataValue.getStockList()
                    .stream().map(Stock::getPrice).collect(Collectors.toList());
            dataValue.setMaxStockPrice(!ObjectUtils.isEmpty(priceList) ?
                    String.valueOf(Collections.max(priceList)) : "");
            dataValue.setMinStockPrice(!ObjectUtils.isEmpty(priceList) ?
                    String.valueOf(Collections.min(priceList)) : "");
            Collections.sort(priceList);
            dataValue.setMaxStockPrice(!ObjectUtils.isEmpty(priceList) ?
                    String.valueOf(priceList.get(priceList.size() / 2)) : "");
            return dataValue;
        } else {
            throw new CommonInternalException(
                    "Company details not found", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<CompanyAll> getAllCompanyInfo() {
        return mongoRepo.findAll();
    }

    @Override
    public void updateCompanyStockDetail(Stock stock) {
        Optional<CompanyAll> data = mongoRepo.findById(stock.getCompanyCode());
        if (data.isPresent()) {
            CompanyAll company = data.get();
            if (company.getStockList() == null) {
                company.setStockList(new ArrayList<>());
            }
            stock.setStockDateTime(
                    CommonConstant.getUIDateFormatIfNotNull(
                            new Timestamp(Long.parseLong(stock.getStockDateTime()))));
            company.getStockList().add(stock);
            mongoRepo.save(company);
        }
    }

    @Override
    public Map<String, String> delete(int companyCode) {
        mongoRepo.deleteById(companyCode);
        repo.findById(companyCode);
        Map<String, String> map = CommonConstant.getSuccessMapResponse();
        map.put("companyCode", String.valueOf(companyCode));
        rabbitMqService.publishMessage(fseExchange, routingKey, map);
        return map;
    }

    @Override
    public List<Map<String, String>> getAllCompanyForDropDown() {
        return repo.findAll().stream().map(d -> {
            Map<String, String> map = new HashMap<>();
            map.put("companyCode", String.valueOf(d.getCompanyCode()));
            map.put("companyName", d.getCompanyName());
            return map;
        }).collect(Collectors.toList());
    }
}
