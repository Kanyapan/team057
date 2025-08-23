package com.arise.controller;

import com.arise.model.LoanApplicationEntity;
import com.arise.service.LoanApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan-applications")
@RequiredArgsConstructor
public class LoanApplicationController {

    private final LoanApplicationService service;

    @GetMapping
    public List<LoanApplicationEntity> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public LoanApplicationEntity getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoanApplicationEntity create(@Valid @RequestBody LoanApplicationEntity body) {
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
