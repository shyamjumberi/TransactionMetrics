package com.n26.transactionmetrics.util;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Shyam
 *
 */
@Component
public class TransactionMetricsUtil {

	public long getCurrentMilliSeconds() {
		return System.currentTimeMillis();
	}
}
