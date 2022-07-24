package com.fse.estockmarketcompany.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommonInternalException extends Exception {
    HttpStatus code;

    String messageLocal;

    public CommonInternalException(String messageLocal, HttpStatus code) {
        this.messageLocal = messageLocal;
        this.code = code;
    }

}
