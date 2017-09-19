/**
 * 
 */
package com.n26.transactionmetrics.dto;

import java.io.Serializable;

/**
 * @author Shyam
 *
 */
public class TransactionMetrics implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6084260865193292973L;
	private double sum;
	private double avg;
	private double max;
	private double min;
	private double count;
	/**
	 * @return the sum
	 */
	public double getSum() {
		return sum;
	}
	/**
	 * @param sum the sum to set
	 */
	public void setSum(double sum) {
		this.sum = sum;
	}
	/**
	 * @return the avg
	 */
	public double getAvg() {
		return avg;
	}
	/**
	 * @param avg the avg to set
	 */
	public void setAvg(double avg) {
		this.avg = avg;
	}
	/**
	 * @return the max
	 */
	public double getMax() {
		return max;
	}
	/**
	 * @param max the max to set
	 */
	public void setMax(double max) {
		this.max = max;
	}
	/**
	 * @return the min
	 */
	public double getMin() {
		return min;
	}
	/**
	 * @param min the min to set
	 */
	public void setMin(double min) {
		this.min = min;
	}
	/**
	 * @return the count
	 */
	public double getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(double count) {
		this.count = count;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("TransactionMetrics [sum=");
		builder.append(sum);
		builder.append(", avg=");
		builder.append(avg);
		builder.append(", max=");
		builder.append(max);
		builder.append(", min=");
		builder.append(min);
		builder.append(", count=");
		builder.append(count);
		builder.append("]");
		return builder.toString();
	}
}
