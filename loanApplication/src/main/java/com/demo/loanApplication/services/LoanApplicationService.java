package com.demo.loanApplication.services;

import com.demo.loanApplication.entity.LoanApplicationEntity;
import com.demo.loanApplication.repository.LoanApplicationRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoanApplicationService {
    private final LoanApplicationRepository loanApplicationRepository;

    public List<LoanApplicationEntity> findAll(){
      return   loanApplicationRepository.findAll();
    }
}
