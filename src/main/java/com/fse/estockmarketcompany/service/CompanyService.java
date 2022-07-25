package com.fse.estockmarketcompany.service;

import com.fse.estockmarketcompany.exception.CommonInternalException;
import com.fse.estockmarketcompany.model.CompanyAll;
import com.fse.estockmarketcompany.model.Stock;
import com.fse.estockmarketcompany.model.request.CompanyRequest;

import java.util.List;
import java.util.Map;

public interface CompanyService {
    Map<String, String> register(CompanyRequest request) throws CommonInternalException;

    CompanyAll info(int companyCode) throws CommonInternalException;

    List<CompanyAll> getAllCompanyInfo();

    void updateCompanyStockDetail(Stock stock);

    Map<String, String> delete(int companyCode) throws CommonInternalException;
}
