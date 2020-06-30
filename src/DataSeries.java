import java.util.Arrays;

/**
 * @author Thien nguyen
 * DataSeries represent a column of data with a label
 *
 */
public class DataSeries implements IBasicStats {
	//label contain the label of the set of data
	private String label;
	//data array contain the data value with a size of 100
	private double[] data = new double[100];
	//sortedData array contain the sorted values with ascending order
	private double[] sortedData;
	//count contain the total number of data
	private int count = 0;
	
	private double sum;
	private double mean;
	private double min;
	private double max;
	
	/**
	 * Constructor of DataSeries which label the data collection
	 * @param name is the name of the series
	 */
	public DataSeries(String name) {
		this.label = name;
	}
	
	/**
	 * add() method add the value into the data array and update the count 
	 * @param value
	 */
	public void add(double value) {
		data[count] = value;
		count +=1;
	}

	/**
	 * size() return the size of the data size, which will be count
	 * @return count
	 */
	public int size() {
		return count;
	}

	@Override
	/**
	 * getMin() return the minimum of the data set. The data set is sorted and the first
	 * element is the minimum 
	 */
	public double getMin() {
		sortedData = Arrays.copyOf(data, count);
		Arrays.sort(sortedData);
		min = sortedData[0];
		return min;
	}
	 /**
	  * getMax() return the maximum of the data set. The last non null value of the sorted data 
	  * is the maximum
	  */
	@Override
	public double getMax() {
		max = sortedData[count - 1];
		return max;
	}

	@Override
	/**
	 * getSum() return the summation of all data in the data set
	 */
	public double getSum() {
		sum = 0;
		for (int i = 0; i < count; i++) {
			sum = sum + data[i];
		}
		return sum;
	}

	@Override
	/**
	 * getMean() return the average of the data 
	 */
	public double getMean() {
		mean = sum / count;
		return mean;
	}
	
	@Override
	/**
	 * Overriding toString() to produce an output for DataSeries. Using 10 rows as the limit
	 * if count is less than 10 the count is the limit
	 */
	public String toString() {
		String strData = "";
		int limit = 0;
		if (count < 10) {
			limit = count;
		} else
			limit = 10;
		for (int j = 0; j < limit; j++) {
			strData = strData + data[j] + " ";
		}
		return (label + " [" + strData.trim()+ "]");
	}

}
