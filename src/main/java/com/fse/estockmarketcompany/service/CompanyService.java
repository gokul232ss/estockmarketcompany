package com.fse.estockmarketcompany.service;

import com.fse.estockmarketcompany.model.request.CompanyRequest;

import java.util.Map;

public interface CompanyService {
    Map<String, String> register(CompanyRequest request);
}
