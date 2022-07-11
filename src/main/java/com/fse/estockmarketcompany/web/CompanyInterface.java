package com.fse.estockmarketcompany.web;

import com.fse.estockmarketcompany.model.request.CompanyRequest;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/company")
public interface CompanyInterface {

    @GetMapping("/getping")
    ResponseEntity<String> getping();

    @PostMapping("/register")
    ResponseEntity<Map<String, String>> register(@RequestBody CompanyRequest request);
}
