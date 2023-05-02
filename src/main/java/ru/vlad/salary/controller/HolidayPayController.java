package ru.vlad.salary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlad.salary.model.HolidayPayData;
import ru.vlad.salary.service.HolidayPayService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/holiday-pay")
@RequiredArgsConstructor
public class HolidayPayController {


    private final HolidayPayService holidayPayService;

    @GetMapping(value = "/calculate", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BigDecimal> calculateHolidayPay(@RequestBody HolidayPayData holidayPayData) {
        return ResponseEntity.ok(holidayPayService.calculateHolidayPayByData(holidayPayData));
    }

}
