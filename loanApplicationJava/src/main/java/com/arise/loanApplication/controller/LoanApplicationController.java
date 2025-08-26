package com.arise.loanApplication.controller;

import com.arise.loanApplication.model.LoanApplicationEntity;
import com.arise.loanApplication.model.LoansResponse;
import com.arise.loanApplication.service.LoanApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LoanApplicationController {

    private final LoanApplicationService service;

    @GetMapping("/loans")
    public ResponseEntity<LoansResponse> getAllLoans(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) Boolean eligible,
            @RequestParam(required = false) String purpose
    ) {
        Pageable pageable = PageRequest.of(page-1, limit);
        Page<LoanApplicationEntity> result = service.findAll(pageable, eligible, purpose);

        LoansResponse response = new LoansResponse(
                result.getContent(),
                page,
                result.getTotalPages()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/v1/loans/{applicationId}")
    public LoanApplicationEntity getOne(@PathVariable Long applicationId) {
        return service.findById(applicationId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoanApplicationEntity submit(@Valid @RequestBody LoanApplicationEntity body) {
        return service.create(body);
    }

    @PutMapping("/{id}")
    public LoanApplicationEntity update(@PathVariable Long id,
                                        @Valid @RequestBody LoanApplicationEntity body) {
        return service.update(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
