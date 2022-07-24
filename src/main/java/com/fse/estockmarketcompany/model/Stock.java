package com.fse.estockmarketcompany.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
public class Stock {
    private int stockId;
    private int companyCode;
    private String stockName;
    private double price;
    private Timestamp stockDateTime;
}
