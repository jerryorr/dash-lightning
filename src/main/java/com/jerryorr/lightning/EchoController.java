package com.jerryorr.lightning;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {
	@RequestMapping("/echo/{msg}")
	public String echo (@PathVariable String msg) {
		return msg;
	}
}
