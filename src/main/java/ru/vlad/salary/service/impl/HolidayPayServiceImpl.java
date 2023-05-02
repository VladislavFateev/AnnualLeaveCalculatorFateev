package ru.vlad.salary.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vlad.salary.model.HolidayPayData;
import ru.vlad.salary.service.HolidayPayService;
import ru.vlad.salary.util.DateUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class HolidayPayServiceImpl implements HolidayPayService {

    private final DateUtil dateUtil;

    private static final BigDecimal MONTH_COUNT = new BigDecimal(12);
    private static final BigDecimal AVERAGE_DAYS_COUNT = new BigDecimal("29.3");

    @Override
    public BigDecimal calculateHolidayPayByData(HolidayPayData holidayPayData) {
        if (holidayPayData.getHolidayDays() != null) {
            return calculatePayment(holidayPayData.getAveragePayment(), new BigDecimal(holidayPayData.getHolidayDays()));
        }
        if (holidayPayData.getEndDate() != null && holidayPayData.getStartDate() != null) {
            long leaveDuration = dateUtil.calculateWorkingDays(holidayPayData.getStartDate(), holidayPayData.getEndDate());
            return calculatePayment(holidayPayData.getAveragePayment(), new BigDecimal(leaveDuration));
        }
        throw new IllegalArgumentException("Holiday pay data is incorrect");
    }

    private BigDecimal calculatePayment(BigDecimal averagePayment, BigDecimal leaveDays) {
        return averagePayment
                .divide(MONTH_COUNT.multiply(AVERAGE_DAYS_COUNT), RoundingMode.HALF_UP)
                .multiply(leaveDays);
    }
}
