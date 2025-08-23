package com.arise.service;

import com.arise.model.LoanApplicationEntity;

import java.util.List;

public interface LoanApplicationService {
    List<LoanApplicationEntity> findAll();
    LoanApplicationEntity findById(Long id);
    LoanApplicationEntity create(LoanApplicationEntity entity);
    LoanApplicationEntity update(Long id, LoanApplicationEntity entity);
    void delete(Long id);
}
