package com.jerryorr.lightning;

import java.text.DecimalFormat;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jerryorr.lightning.dash.Speedometer;
import com.jerryorr.lightning.jmx.JmxService;
import com.jerryorr.lightning.jmx.MemoryUsage;

/**
 * REST endpoints for some sample Tomcat stats
 *  
 * @author jerryorr
 */
@RestController
public class TomcatStatsController {
	@Resource
	JmxService jmxService;
	
	@RequestMapping(value = "stats/memory", method = RequestMethod.GET)
	@ResponseBody
	public String memory() throws Exception {
		MemoryUsage memory = jmxService.getMemory();
		
		return toMB(memory.getUsed());
	}
	
	@RequestMapping(value = "stats/memory/speedometer", method = RequestMethod.GET)
	@ResponseBody
	public Speedometer memorySpeedometer() throws Exception {
		MemoryUsage memory = jmxService.getMemory();
		
		return Speedometer.build()
				.start(0).end(memory.getMax())
				.value(memory.getUsed())
				.formatted(toMB(memory.getUsed())).done();
	}
	
	private String toMB(Number b) {
		return new DecimalFormat("#,##0.## MB").format(b.doubleValue() / 1_000_000);
	}
}
