/**
 * 
 */
package com.n26.transactionmetrics.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.n26.transactionmetrics.dto.TransactionMetrics;
import com.n26.transactionmetrics.dto.TransactionMetricsRequest;
import com.n26.transactionmetrics.dto.TransactionMetricsResponse;
import com.n26.transactionmetrics.service.TransactionMetricsService;

/**
 * @author Shyam
 *
 */
@RestController
public class TransactionMetricsController {
	
	private static final Logger LOG = Logger.getLogger("TransactionMetricsController");
	
	@Autowired
	private TransactionMetricsService service;
	
	@PostMapping(value = "/transactions")
	public ResponseEntity<TransactionMetricsResponse> saveTransaction(@RequestBody TransactionMetricsRequest request) {
		final long startTime = System.currentTimeMillis();
		final TransactionMetricsResponse response = service.saveTransaction(request);
		if(LOG.isInfoEnabled()) {
			LOG.info("saveTransaction executed in "+(System.currentTimeMillis()-startTime) + " milliseconds");
		}
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@GetMapping(value = "/statistics")
	public ResponseEntity<TransactionMetricsResponse> getStatistics() {
		final long startTime = System.currentTimeMillis();
		final TransactionMetrics transactionMetrics = service.getTransacations();
		if(LOG.isInfoEnabled()) {
			LOG.info("getStatistics executed in "+(System.currentTimeMillis()-startTime) + " milliseconds");
		}
		return new ResponseEntity(transactionMetrics, HttpStatus.OK);
	}
}
