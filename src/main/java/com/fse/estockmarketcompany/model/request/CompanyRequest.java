package com.fse.estockmarketcompany.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CompanyRequest {
    private Integer companyCode;
    private String companyName;
    private String companyCEO;
    private double companyTurnOver;
}
