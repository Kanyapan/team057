package com.demo.loanApplication.entity;

import com.demo.loanApplication.enums.LoanPurpose;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "loan_application")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class LoanApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // fullName: string, required, 2–255 chars
    @NotBlank
    @Size(min = 2, max = 255)
    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    // monthlyIncome: number, required, 5000 ≤ value ≤ 5,000,000
    @NotNull
    @DecimalMin(value = "5000.00")
    @DecimalMax(value = "5000000.00")
    @Digits(integer = 13, fraction = 2)
    @Column(name = "monthly_income", nullable = false, precision = 15, scale = 2)
    private BigDecimal monthlyIncome;

    // loanAmount: number, required, 1000 ≤ value ≤ 5,000,000
    @NotNull
    @DecimalMin(value = "1000.00")
    @DecimalMax(value = "5000000.00")
    @Digits(integer = 13, fraction = 2)
    @Column(name = "loan_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal loanAmount;

    // loanPurpose: string, required, must be in the allowed list
    // Stored as VARCHAR, mapped from enum for safety.
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "loan_purpose", nullable = false, length = 50)
    private LoanPurpose loanPurpose;

    // age: number, required, > 0
    @NotNull
    @Min(1)
    @Column(name = "age", nullable = false)
    private Integer age;

    // phoneNumber: string, required, 10 digits only (0–9)
    @NotBlank
    @Pattern(regexp = "^\\d{10}$", message = "Phone must be exactly 10 digits.")
    @Column(name = "phone_number", nullable = false, length = 10)
    private String phoneNumber;

    // email: string, required, valid email
    @NotBlank
    @Email
    @Column(name = "email", nullable = false, length = 320)
    private String email;
}
