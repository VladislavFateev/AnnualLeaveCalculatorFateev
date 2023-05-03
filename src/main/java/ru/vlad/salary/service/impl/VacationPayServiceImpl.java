package ru.vlad.salary.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vlad.salary.model.VacationPayData;
import ru.vlad.salary.service.VacationPayService;
import ru.vlad.salary.util.DateUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class VacationPayServiceImpl implements VacationPayService {

    private final DateUtil dateUtil;

    private static final BigDecimal MONTH_COUNT = new BigDecimal(12);
    private static final BigDecimal AVERAGE_DAYS_COUNT = new BigDecimal("29.3");

    @Override
    public BigDecimal calculateVacationPayByData(VacationPayData vacationPayData) {
        if (vacationPayData.getVacationDays() != null) {
            return calculatePayment(vacationPayData.getAveragePayment(), new BigDecimal(vacationPayData.getVacationDays()));
        }
        if (vacationPayData.getEndDate() != null && vacationPayData.getStartDate() != null) {
            long leaveDuration = dateUtil.calculateWorkingDays(vacationPayData.getStartDate(), vacationPayData.getEndDate());
            return calculatePayment(vacationPayData.getAveragePayment(), new BigDecimal(leaveDuration));
        }
        throw new IllegalArgumentException("Vacation pay data is incorrect");
    }

    private BigDecimal calculatePayment(BigDecimal averagePayment, BigDecimal leaveDays) {
        return averagePayment
                .divide(MONTH_COUNT.multiply(AVERAGE_DAYS_COUNT), RoundingMode.HALF_UP)
                .multiply(leaveDays);
    }
}
