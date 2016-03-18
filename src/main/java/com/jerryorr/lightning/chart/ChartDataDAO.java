package com.jerryorr.lightning.chart;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Simple DAO storing some sample application-specific data in memory
 * 
 * @author jerryorr
 */
@Component
public class ChartDataDAO {
	@Resource
	ApplicationEventPublisher publisher;

	private static final ConcurrentMap<LocalDate, Integer> store = new ConcurrentHashMap<>();

	static {
		store.put(LocalDate.of(2016, 3, 15), 3);
		store.put(LocalDate.of(2016, 3, 16), 6);
		store.put(LocalDate.of(2016, 3, 17), 5);
		store.put(LocalDate.of(2016, 3, 18), 3);
		store.put(LocalDate.of(2016, 3, 19), 5);
		store.put(LocalDate.of(2016, 3, 20), 9);
		store.put(LocalDate.of(2016, 3, 21), 8);
	}

	public List<DateValue> getDateValues() {
		return store.entrySet().stream().map(entry -> new DateValue(entry.getKey(), entry.getValue()))
				.sorted((o1, o2) -> o1.getDate().compareTo(o2.getDate())).collect(Collectors.toList());
	}

	public void increment(LocalDate date) {
		Integer newval = store.compute(date, (d, current) -> current == null ? 1 : current + 1);
		
		// Notice we aren't updating Dash directly; we're raising events, which
		// any number of event listeners may be observing.
		publisher.publishEvent(new ChartDataUpdatedEvent(this, date, newval));
	}

	public void decrement(LocalDate date) {
		Integer newval = store.compute(date, (d, current) -> current == null ? -1 : current - 1);
		publisher.publishEvent(new ChartDataUpdatedEvent(this, date, newval));
	}
}
