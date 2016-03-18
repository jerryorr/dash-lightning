package com.jerryorr.lightning.chart;

import java.time.LocalDate;

import org.springframework.context.ApplicationEvent;

/**
 * Simple custom Spring event indicating that the chart data has been updated
 * 
 * @author jerryorr
 */
public class ChartDataUpdatedEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	private LocalDate date;
	private Integer newValue;

	public ChartDataUpdatedEvent(Object source, LocalDate date, Integer newValue) {
		super(source);
		this.date = date;
		this.newValue = newValue;
	}

	public LocalDate getDate() {
		return date;
	}

	public Integer getNewValue() {
		return newValue;
	}

}
