package com.jerryorr.lightning.jmx;

public class MemoryUsage {
	private Long used;
	private Long max;

	public MemoryUsage(Long used, Long max) {
		super();
		this.used = used;
		this.max = max;
	}

	public Long getUsed() {
		return used;
	}

	public Long getMax() {
		return max;
	}

}
