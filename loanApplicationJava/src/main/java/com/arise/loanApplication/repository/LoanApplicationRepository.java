package com.arise.loanApplication.repository;

import com.arise.loanApplication.model.LoanApplicationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplicationEntity, Long> {
    Page<LoanApplicationEntity> findAll(Pageable pageable);

    @Query(
            value = """
        SELECT * FROM loan_applications
        WHERE (:eligible IS NULL OR eligible = :eligible)
          AND (:purpose IS NULL OR loan_purpose = :purpose)
        """,
            countQuery = """
        SELECT count(*) FROM loan_applications
        WHERE (:eligible IS NULL OR eligible = :eligible)
          AND (:purpose IS NULL OR loan_purpose = :purpose)
        """,
            nativeQuery = true
    )
        Page<LoanApplicationEntity> findAllPagination(Pageable pageable,@Param("eligible")Boolean eligible,@Param("purpose")String purpose);

}
