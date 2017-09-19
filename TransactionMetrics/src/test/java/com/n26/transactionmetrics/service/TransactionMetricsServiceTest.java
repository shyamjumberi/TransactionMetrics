/**
 * 
 */
package com.n26.transactionmetrics.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionMetricsServiceTest {
	
	private static final String ERROR_CODE 		= "204";
	private static final String SUCCESS_CODE 	= "201";
	
	@Autowired
	@InjectMocks
	private TransactionMetricsService transactionMetricsService;
	
	@Mock
	private TransactionMetricsRepository mockTransactionMetricsRepository;
	
	@Mock
	private TransactionMetricsUtil mockTransactionMetricsUtil;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void saveTransaction_transactionOlderThan60Seconds() {
		final TransactionMetricsRequest request = new TransactionMetricsRequest();
		request.setAmount(100);
		request.setTimestamp(1505845205668l);
		Mockito.when(mockTransactionMetricsUtil.getCurrentMilliSeconds()).thenReturn(1505845974223l);
		final TransactionMetricsResponse response = transactionMetricsService.saveTransaction(request);
		assertEquals(ERROR_CODE, response.getStatusCode());
	}

	@Test
	public void saveTransaction_success() {
		final TransactionMetricsRequest request = new TransactionMetricsRequest();
		request.setAmount(100);
		request.setTimestamp(1505845948978l);
		final Transaction transaction  = new Transaction();
		transaction.setId(1l);
		transaction.setAmount(100d);
		transaction.setTimestamp(1505845948978l);
		Mockito.when(mockTransactionMetricsUtil.getCurrentMilliSeconds()).thenReturn(1505845974223l);
		Mockito.when(mockTransactionMetricsRepository.save(any(Transaction.class))).thenReturn(transaction);
		final TransactionMetricsResponse response = transactionMetricsService.saveTransaction(request);
		assertEquals(SUCCESS_CODE, response.getStatusCode());
	}
	
	@Test
	public void getTransacations_transactionsGreaterThan60Seconds() {
		Mockito.when(mockTransactionMetricsUtil.getCurrentMilliSeconds()).thenReturn(1505845974223l);
		Mockito.when(mockTransactionMetricsRepository.findByTimestampIsGreaterThanEqualAndTimestampIsLessThanEqual(anyLong(), anyLong())).thenReturn(null);
		final TransactionMetrics metrics = transactionMetricsService.getTransacations();
		assertNotNull(metrics);
		assertEquals(Double.valueOf(0d), Double.valueOf(metrics.getSum()));
		assertEquals(Double.valueOf(0d), Double.valueOf(metrics.getCount()));
	}
	
	@Test
	public void getTransacations_transactionsLessThan60Seconds() {
		Mockito.when(mockTransactionMetricsUtil.getCurrentMilliSeconds()).thenReturn(1505847266033l);
		final List<Transaction> transactionList = new ArrayList<>();
		final Transaction firstTransaction = new Transaction();
		firstTransaction.setId(1l);
		firstTransaction.setAmount(100d);
		firstTransaction.setTimestamp(1505847245700l);
		transactionList.add(firstTransaction);
		final Transaction secTransaction = new Transaction();
		secTransaction.setId(1l);
		secTransaction.setAmount(300d);
		secTransaction.setTimestamp(1505847226963l);
		transactionList.add(secTransaction);
		
		Mockito.when(mockTransactionMetricsRepository.findByTimestampIsGreaterThanEqualAndTimestampIsLessThanEqual(anyLong(), anyLong())).thenReturn(transactionList);
		final TransactionMetrics metrics = transactionMetricsService.getTransacations();
		assertNotNull(metrics);
		assertEquals(Double.valueOf(400d), Double.valueOf(metrics.getSum()));
		assertEquals(Double.valueOf(200d), Double.valueOf(metrics.getAvg()));
		assertEquals(Double.valueOf(100d), Double.valueOf(metrics.getMin()));
		assertEquals(Double.valueOf(300d), Double.valueOf(metrics.getMax()));
		assertEquals(Double.valueOf(2d), Double.valueOf(metrics.getCount()));
	}

}
