package com.jerryorr.lightning.dash;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple fluent POJO for building chart data in Dash's expected format
 * 
 * @author jerryorr
 */
public class Chart {
	private String title;
	private List<Series> datasequences = new ArrayList<>();

	public Chart title(String title) {
		this.title = title;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public List<Series> getDatasequences() {
		return datasequences;
	}

	public Series series(String title) {
		Series series = new Series(title);
		datasequences.add(series);
		return series;
	}

	public static JSON json(Chart chart) {
		return new JSON(chart);
	}

	public static class Series {
		private String title;
		private List<Point> datapoints = new ArrayList<>();

		public Series(String title) {
			super();
			this.title = title;
		}

		public Series point(Object title, Number value) {
			this.datapoints.add(new Point(title, value));
			return this;
		}

		public String getTitle() {
			return title;
		}

		public List<Point> getDatapoints() {
			return datapoints;
		}

	}

	public static class Point {
		private Object title;
		private Number value;

		public Point(Object title, Number value) {
			super();
			this.title = title;
			this.value = value;
		}

		public Object getTitle() {
			return title;
		}

		public Number getValue() {
			return value;
		}
	}

	public static final class JSON {
		private Chart graph;

		private JSON(Chart graph) {
			this.graph = graph;
		}

		public Chart getGraph() {
			return graph;
		}
	}
}
