package com.fse.estockmarketcompany.web;

import com.fse.estockmarketcompany.exception.CommonInternalException;
import com.fse.estockmarketcompany.model.CompanyAll;
import com.fse.estockmarketcompany.model.request.CompanyRequest;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/company")
public interface CompanyInterface {

    @GetMapping("/getping")
    ResponseEntity<String> getping();

    @PostMapping("/register")
    ResponseEntity<Map<String, String>> register(@RequestBody CompanyRequest request) throws CommonInternalException;

    @GetMapping("/info/{companyCode}")
    ResponseEntity<CompanyAll> info(@PathVariable("companyCode") int companyCode) throws CommonInternalException;

    @GetMapping("/getall")
    ResponseEntity<List<CompanyAll>> getAllCompanyInfo();
}
