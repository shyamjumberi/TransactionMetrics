/**
 * 
 */
package com.n26.transactionmetrics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.n26.transactionmetrics.model.Transaction;
import java.lang.Long;
import java.util.List;

/**
 * @author Shyam
 *
 */
public interface TransactionMetricsRepository extends JpaRepository<Transaction, Long> {
	List<Transaction> findByTimestampIsGreaterThanEqualAndTimestampIsLessThanEqual(Long oldTimeStamp, Long newTimeStamp);
}
