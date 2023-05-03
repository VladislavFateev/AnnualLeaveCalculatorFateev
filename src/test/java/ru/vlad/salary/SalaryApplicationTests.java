package ru.vlad.salary;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.vlad.salary.model.VacationPayData;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class SalaryApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testWithDates() throws Exception {
		VacationPayData payData = new VacationPayData();
		payData.setAveragePayment(new BigDecimal(10000));
		payData.setStartDate(LocalDate.of(2023, 10, 10));
		payData.setEndDate(LocalDate.of(2023, 10, 20));

		String requestBody = objectMapper.writeValueAsString(payData);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("http://localhost:8095/api/vacation-pay/calculate")
				.accept(MediaType.APPLICATION_JSON).content(requestBody)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals("196", response.getContentAsString());
	}

	@Test
	public void testWithDaysCount() throws Exception {
		VacationPayData payData = new VacationPayData();
		payData.setAveragePayment(new BigDecimal(10000));
		payData.setVacationDays(10);

		String requestBody = objectMapper.writeValueAsString(payData);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("http://localhost:8095/api/vacation-pay/calculate")
				.accept(MediaType.APPLICATION_JSON).content(requestBody)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals("280", response.getContentAsString());
	}
}
