package com.jerryorr.lightning.chart;

import java.time.LocalDate;

import javax.annotation.Resource;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Simple Spring MVC controller for changing some sample application-specific
 * data
 * 
 * @author jerryorr
 */
@Controller
public class ChartDataController {
	@Resource
	ChartDataDAO chartDAO;

	@RequestMapping("/chart")
	public String chart(ModelMap model) {
		model.addAttribute("dateValues", chartDAO.getDateValues());

		return "chart-form";
	}

	@RequestMapping(value = "/chart/{date}/up", method = RequestMethod.POST)
	public String up(@PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
		chartDAO.increment(date);
		return "redirect:/chart";
	}

	@RequestMapping(value = "/chart/{date}/down", method = RequestMethod.POST)
	public String down(@PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
		chartDAO.decrement(date);
		return "redirect:/chart";
	}
}
