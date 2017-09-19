/**
 * 
 */
package com.n26.transactionmetrics.dto;

import java.io.Serializable;

/**
 * @author Shyam
 *
 */
public class TransactionMetricsResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2186836635604695008L;

	private String statusCode;

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TransactionMetricsResponse [statusCode=");
		builder.append(statusCode);
		builder.append("]");
		return builder.toString();
	}
}
