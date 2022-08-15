package com.fse.estockmarketcompany.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document("company")
public class CompanyAll {
    @Id
    private int companyCode;
    private String companyName;
    private String companyCEO;
    private double companyTurnOver;
    private List<Stock> stockList;
    @Transient
    private String maxStockPrice;
    @Transient
    private String minStockPrice;
    @Transient
    private String avgStockPrice;

    public CompanyAll(Company com, List<Stock> stockList) {
        this.companyCode = com.getCompanyCode();
        this.companyName = com.getCompanyName();
        this.companyCEO = com.getCompanyCEO();
        this.companyTurnOver = com.getCompanyTurnOver();
        if (ObjectUtils.isEmpty(stockList)) {
            this.stockList = stockList;
        }
    }
}
