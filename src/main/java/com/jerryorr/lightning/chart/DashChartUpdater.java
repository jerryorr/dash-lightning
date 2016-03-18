package com.jerryorr.lightning.chart;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.jerryorr.lightning.dash.Chart;
import com.jerryorr.lightning.dash.Chart.Series;
import com.jerryorr.lightning.dash.Push;

@Component
public class DashChartUpdater {
	@Resource
	ChartDataDAO graphDAO;
	
	@Resource
	Push push;
	
	// from src/main/resources/application.properties
	@Value("${dash.chart}")
	private String chartUrl;
	
	@EventListener
	@Async
	public void onApplicationEvent(ChartDataUpdatedEvent event) {
		List<DateValue> datevals = graphDAO.getDateValues();

		Chart chart = new Chart().title("Some Chart Title");
		Series series = chart.series("Some Date/Value Thing");

		datevals.stream().forEach(
				dateval -> series.point(dateval.getDate().format(DateTimeFormatter.ISO_DATE), dateval.getValue()));

		push.start().url(chartUrl).chart(chart).push();
	}

}
