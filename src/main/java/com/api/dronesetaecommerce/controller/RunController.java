package com.api.dronesetaecommerce.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dronesetaecommerce.service.GeneralService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/run")
public class RunController {
	
	@Autowired
	private GeneralService generalService;
	
	@GetMapping
	public void run() throws InterruptedException {
		do {
			for(int i = 0; i < 5; i ++) {
				generalService.validarPedidos();
				generalService.validarViagens();
				TimeUnit.SECONDS.sleep(5);
			}
			TimeUnit.MINUTES.sleep(1);
		} while (true);
	}
}