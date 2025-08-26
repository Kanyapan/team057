package com.arise.loanApplication.service;

import com.arise.loanApplication.model.LoanApplicationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoanApplicationService {
    Page<LoanApplicationEntity> findAll(Pageable pageable,Boolean eligible,String purpose);
    LoanApplicationEntity findById(Long id);
    LoanApplicationEntity create(LoanApplicationEntity entity);
    LoanApplicationEntity update(Long id, LoanApplicationEntity entity);
    void delete(Long id);
}
