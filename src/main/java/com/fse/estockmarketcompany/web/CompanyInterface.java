package com.fse.estockmarketcompany.web;

import com.fse.estockmarketcompany.exception.CommonInternalException;
import com.fse.estockmarketcompany.model.CompanyAll;
import com.fse.estockmarketcompany.model.request.CompanyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/company")
public interface CompanyInterface {

    @GetMapping("/getping")
    ResponseEntity<String> getping();

    @GetMapping("/getAllCompanyForDropDown")
    ResponseEntity<List<Map<String, String>>> getAllCompanyForDropDown();

    @PostMapping("/register")
    ResponseEntity<Map<String, String>> register(@RequestBody CompanyRequest request) throws CommonInternalException;

    @GetMapping("/info/{companyCode}")
    ResponseEntity<CompanyAll> info(@PathVariable("companyCode") int companyCode) throws CommonInternalException;

    @GetMapping("/getall")
    ResponseEntity<List<CompanyAll>> getAllCompanyInfo();

    @DeleteMapping("/delete/{companyCode}")
    ResponseEntity<Map<String, String>> delete(@PathVariable("companyCode") int companyCode) throws CommonInternalException;
}
