package com.jerryorr.lightning.chart;

import java.time.LocalDate;

public class DateValue {
	public LocalDate date;
	public Integer value;

	public DateValue(LocalDate date, Integer value) {
		super();
		this.date = date;
		this.value = value;
	}

	public LocalDate getDate() {
		return date;
	}

	public Integer getValue() {
		return value;
	}
}