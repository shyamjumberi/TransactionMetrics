/**
 * 
 */
package com.n26.transactionmetrics.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.transactionmetrics.dto.TransactionMetrics;
import com.n26.transactionmetrics.dto.TransactionMetricsRequest;
import com.n26.transactionmetrics.dto.TransactionMetricsResponse;
import com.n26.transactionmetrics.model.Transaction;
import com.n26.transactionmetrics.repository.TransactionMetricsRepository;
import com.n26.transactionmetrics.util.TransactionMetricsUtil;

/**
 * @author Shyam
 *
 */
@Service
public class TransactionMetricsService {
	
	private static final String ERROR_CODE 		= "204";
	private static final String SUCCESS_CODE 	= "201";
	
	@Autowired
	private TransactionMetricsRepository transactionMetricsRepository;
	
	@Autowired
	private TransactionMetricsUtil transactionMetricsUtil;
	
	/**
	 * @param transactionMetricsRepository the transactionMetricsRepository to set
	 */
	public void setTransactionMetricsRepository(TransactionMetricsRepository transactionMetricsRepository) {
		this.transactionMetricsRepository = transactionMetricsRepository;
	}

	/**
	 * @param transactionMetricsUtil the transactionMetricsUtil to set
	 */
	public void setTransactionMetricsUtil(TransactionMetricsUtil transactionMetricsUtil) {
		this.transactionMetricsUtil = transactionMetricsUtil;
	}

	public TransactionMetricsResponse saveTransaction(final TransactionMetricsRequest request) {
		final TransactionMetricsResponse response = new TransactionMetricsResponse();
		final long initialSeconds = TimeUnit.MILLISECONDS.toSeconds(request.getTimestamp());
		final long currentSeconds = TimeUnit.MILLISECONDS.toSeconds(transactionMetricsUtil.getCurrentMilliSeconds());
		final long diffSeconds = currentSeconds - initialSeconds;
		if(diffSeconds <= 60) {
			final Transaction transaction = new Transaction();
			transaction.setAmount(request.getAmount());
			transaction.setTimestamp(request.getTimestamp());
			transactionMetricsRepository.save(transaction);
			response.setStatusCode(SUCCESS_CODE);
		} else {
			response.setStatusCode(ERROR_CODE);
		}
		return response;
	}
	
	public TransactionMetrics getTransacations() {
		final long currentMilliSeconds = transactionMetricsUtil.getCurrentMilliSeconds();
		final long last60Seconds = currentMilliSeconds - 60000;
		final List<Transaction> transactionList = transactionMetricsRepository.findByTimestampIsGreaterThanEqualAndTimestampIsLessThanEqual(last60Seconds, currentMilliSeconds);
		double amount = 0, sum = 0, avg = 0, max = 0, min = 0, count = 0;
		if(transactionList != null && transactionList.size() > 0) {
			min = transactionList.get(0).getAmount();
			int transactionsCount = transactionList.size();
			for(Transaction transaction :  transactionList) {
				amount = transaction.getAmount();
				sum = sum + amount ;
				if(amount < min) {
					min =  amount;
				}
				if(amount > max) {
					max = amount;
				}
			}
			avg = sum / transactionsCount;
			count = transactionsCount;
		}
		final TransactionMetrics metrics = new TransactionMetrics();
		metrics.setSum(sum);
		metrics.setAvg(avg);
		metrics.setMax(max);
		metrics.setMin(min);
		metrics.setCount(count);
		return metrics;
	}
}
