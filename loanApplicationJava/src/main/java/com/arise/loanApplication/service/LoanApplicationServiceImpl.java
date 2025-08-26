package com.arise.loanApplication.service;

import com.arise.loanApplication.model.LoanApplicationEntity;
import com.arise.loanApplication.repository.LoanApplicationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanApplicationServiceImpl implements LoanApplicationService {

    private final LoanApplicationRepository repository;

    @Override
    public Page<LoanApplicationEntity> findAll(Pageable pageable,Boolean eligible,String purpose) {
        return repository.findAllPagination(pageable,eligible,purpose);
    }

    @Override
    public LoanApplicationEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("LoanApplication id " + id + " not found"));
    }

    @Override
    public LoanApplicationEntity create(LoanApplicationEntity entity) {
        entity.setId(null);
        return repository.save(entity);
    }

    @Override
    public LoanApplicationEntity update(Long id, LoanApplicationEntity in) {
        LoanApplicationEntity e = findById(id);
        e.setFullName(in.getFullName());
        e.setMonthlyIncome(in.getMonthlyIncome());
        e.setLoanAmount(in.getLoanAmount());
        e.setLoanPurpose(in.getLoanPurpose());
        e.setAge(in.getAge());
        e.setPhoneNumber(in.getPhoneNumber());
        e.setEmail(in.getEmail());
        e.setEligible(in.isEligible());
        e.setReason(in.getReason());
        e.setTimestamp(in.getTimestamp());
        return repository.save(e);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("LoanApplication id " + id + " not found");
        }
        repository.deleteById(id);
    }
}
