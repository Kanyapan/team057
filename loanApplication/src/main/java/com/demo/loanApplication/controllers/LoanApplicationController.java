package com.demo.loanApplication.controllers;

import com.demo.loanApplication.entity.LoanApplicationEntity;
import com.demo.loanApplication.services.LoanApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class LoanApplicationController {
    private final LoanApplicationService loanApplicationService;
    @GetMapping("/loans")
    public ResponseEntity<List<LoanApplicationEntity>> findAll(){
        List<LoanApplicationEntity> loans = loanApplicationService.findAll();
        return ResponseEntity.ok(loans);
    }

}
