package com.arise.loanApplication.model;

import java.util.List;

public record LoansResponse(
    List<LoanApplicationEntity> application,
    int page,
    int totalpage
){}
