package com.aeroplaneBooking.Dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRateDto {
	private BigDecimal baseAmount;

	private BigDecimal taxAmount;

	private BigDecimal totalAmount;
}
