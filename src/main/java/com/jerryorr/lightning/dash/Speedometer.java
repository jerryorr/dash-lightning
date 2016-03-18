package com.jerryorr.lightning.dash;

/**
 * Simple immutable POJO and builder for building chart data in Dash's expected
 * format. See TomcatStatsController for example usages;
 * 
 * @author jerryorr
 */
public class Speedometer {
	private Number value;
	private Number start;
	private Number end;
	private String formatted;

	private Speedometer() {
	}

	public Number getValue() {
		return value;
	}

	public Number getStart() {
		return start;
	}

	public Number getEnd() {
		return end;
	}

	public String getFormatted() {
		return formatted;
	}

	public static final Builder build() {
		return new Builder();
	}

	public static final class Builder {
		private Speedometer s = new Speedometer();

		private Builder() {
		}

		public Builder value(Number v) {
			s.value = v;
			return this;
		}

		public Builder start(Number v) {
			s.start = v;
			return this;
		}

		public Builder end(Number v) {
			s.end = v;
			return this;
		}

		public Builder formatted(String v) {
			s.formatted = v;
			return this;
		}

		public Speedometer done() {
			return s;
		}
	}
}
