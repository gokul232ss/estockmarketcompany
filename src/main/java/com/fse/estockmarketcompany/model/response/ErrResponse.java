package com.fse.estockmarketcompany.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrResponse {

    String errCode;
    String message;
    long timestamp;

    public ErrResponse(String errCode, String message) {
        this.errCode = errCode;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }
}