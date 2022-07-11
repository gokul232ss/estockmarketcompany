package com.fse.estockmarketcompany.web.impl;

import com.fse.estockmarketcompany.model.request.CompanyRequest;
import com.fse.estockmarketcompany.service.CompanyService;
import com.fse.estockmarketcompany.web.CompanyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CompanyController implements CompanyInterface {

    @Autowired
    private CompanyService service;


    @Override
    public ResponseEntity<String> getping() {
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, String>> register(CompanyRequest request) {
        return new ResponseEntity<>(service.register(request), HttpStatus.CREATED);
    }
}
