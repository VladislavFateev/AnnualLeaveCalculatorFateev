package ru.vlad.salary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlad.salary.model.VacationPayData;
import ru.vlad.salary.service.VacationPayService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/vacation-pay")
@RequiredArgsConstructor
public class VacationPayController {


    private final VacationPayService vacationPayService;

    @GetMapping(value = "/calculate", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BigDecimal> calculateVacationPay(@RequestBody VacationPayData vacationPayData) {
        return ResponseEntity.ok(vacationPayService.calculateVacationPayByData(vacationPayData));
    }

}
