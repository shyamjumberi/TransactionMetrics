/**
 * 
 */
package com.n26.transactionmetrics.dto;

import java.io.Serializable;

/**
 * @author Shyam
 *
 */
public class TransactionMetricsRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 176483519003255553L;

	private double amount;
	private long timestamp;
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("TransactionMetricsRequest [amount=");
		builder.append(amount);
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append("]");
		return builder.toString();
	}
}
