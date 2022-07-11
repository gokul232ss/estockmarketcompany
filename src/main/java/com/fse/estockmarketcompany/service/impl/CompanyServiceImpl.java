package com.fse.estockmarketcompany.service.impl;

import com.fse.estockmarketcompany.model.Company;
import com.fse.estockmarketcompany.model.request.CompanyRequest;
import com.fse.estockmarketcompany.repository.CompanyRepository;
import com.fse.estockmarketcompany.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository repo;

    @Override
    public Map<String, String> register(CompanyRequest request) {
        repo.save(new Company(request));
        Map<String, String> mp = new HashMap<>();
        mp.put("message", "success");
        return mp;
    }
}
