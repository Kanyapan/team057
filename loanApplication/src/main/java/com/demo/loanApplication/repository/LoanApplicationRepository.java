package com.demo.loanApplication.repository;

import com.demo.loanApplication.entity.LoanApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplicationEntity,Long> {
}
