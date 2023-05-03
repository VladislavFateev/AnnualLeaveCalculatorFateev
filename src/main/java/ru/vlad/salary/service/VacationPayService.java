package ru.vlad.salary.service;

import ru.vlad.salary.model.VacationPayData;

import java.math.BigDecimal;

public interface VacationPayService {
    BigDecimal calculateVacationPayByData(VacationPayData vocationPayData);
}
