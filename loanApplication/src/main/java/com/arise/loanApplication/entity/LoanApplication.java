package com.arise.loanApplication.entity;

import com.arise.loanApplication.enums.LoanPurpose;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "loan_applications")
@Data
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 2–255 characters
    @NotBlank(message = "fullName is required")
    @Size(min = 2, max = 255, message = "fullName must be 2–255 characters")
    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    // 5000 ≤ value ≤ 5,000,000
    @NotNull(message = "monthlyIncome is required")
    @DecimalMin(value = "5000", inclusive = true, message = "monthlyIncome must be ≥ 5000")
    @DecimalMax(value = "5000000", inclusive = true, message = "monthlyIncome must be ≤ 5000000")
    @Digits(integer = 7, fraction = 2, message = "monthlyIncome must have up to 7 integer digits and 2 decimals")
    @Column(name = "monthly_income", nullable = false, precision = 9, scale = 2)
    private BigDecimal monthlyIncome;

    // 1000 ≤ value ≤ 5,000,000
    @NotNull(message = "loanAmount is required")
    @DecimalMin(value = "1000", inclusive = true, message = "loanAmount must be ≥ 1000")
    @DecimalMax(value = "5000000", inclusive = true, message = "loanAmount must be ≤ 5000000")
    @Digits(integer = 7, fraction = 2, message = "loanAmount must have up to 7 integer digits and 2 decimals")
    @Column(name = "loan_amount", nullable = false, precision = 9, scale = 2)
    private BigDecimal loanAmount;

    // must not be empty and in the list of Loan Purpose List
    @NotNull(message = "loanPurpose is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "loan_purpose", nullable = false, length = 50)
    private LoanPurpose loanPurpose;

    // more than 0
    @NotNull(message = "age is required")
    @Min(value = 1, message = "age must be greater than 0")
    @Column(name = "age", nullable = false)
    private Integer age;

    // valid phone format (10 digits) and all numbers 0-9
    @NotBlank(message = "phoneNumber is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "phoneNumber must be exactly 10 digits (0-9)")
    @Column(name = "phone_number", nullable = false, length = 10)
    private String phoneNumber;

    // must be a valid email
    @NotBlank(message = "email is required")
    @Email(message = "email must be a valid email address")
    @Size(max = 254, message = "email must be ≤ 254 characters")
    @Column(name = "email", nullable = false, length = 254)
    private String email;
}
