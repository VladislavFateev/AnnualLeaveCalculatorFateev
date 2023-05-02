package ru.vlad.salary.service;

import ru.vlad.salary.model.HolidayPayData;

import java.math.BigDecimal;

public interface HolidayPayService {
    BigDecimal calculateHolidayPayByData(HolidayPayData holidayPayData);
}
