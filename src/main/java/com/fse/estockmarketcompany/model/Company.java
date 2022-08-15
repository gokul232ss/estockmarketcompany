package com.fse.estockmarketcompany.model;

import com.fse.estockmarketcompany.model.request.CompanyRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyCode;
    private String companyName;
    private String companyCEO;
    private double companyTurnOver;

    public Company(CompanyRequest request) {
        if (request.getCompanyCode() != null) {
            this.companyCode = request.getCompanyCode();
        }
        this.companyCEO = request.getCompanyCEO();
        this.companyName = request.getCompanyName();
        this.companyTurnOver = Double.parseDouble(request.getCompanyTurnOver());
    }
}
