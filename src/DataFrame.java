import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Thien Nguyen
 * DataFrame reads a csv file then produce a table based on the file.
 *
 */
public class DataFrame {
	//firstLine string contains the first line of the file, which is the headers
	private String firstLine;
	
	//String array contains all the header names
	private String[] headerNames;
	
	//numOfCols contain the number of columns
	private int numOfCols;
	
	//numOfRows contain the number of rows
	private int numOfRows;
	
	//2D array spreadSheet represents the data table
	private double[][] spreadSheet;
	
	/**
	 * Constructor for DataFrame which read the file then creates a 1 array contains
	 * the headers and 2D array contains the data
	 * @param filename is the name of the file
	 * @throws IOException is in the case file is not found.
	 */
	public DataFrame(String filename) throws IOException {
		//Scanner class s is used to read the file 
		Scanner s = new Scanner(new File(filename));
		
		//Assigned the first line of the file to firstLine
		firstLine = s.nextLine();
		//headerNames is then used to store the results from splitting firstLine.
		headerNames = firstLine.trim().split("[, ]+");
		//numOfCols is achieved from headerNames length
		numOfCols = headerNames.length;
		numOfRows = 0;
		
		//initialize spreadSheet 2D array with 100 rows and with numOfCol columns
		spreadSheet = new double[100][numOfCols];
		
		//looping through the entire file for data
		while (s.hasNext()) {
			String temp = s.nextLine();
			//Splitting temp by the comma delimiter
			String[] str = temp.trim().split("[, ]+");
			//nums array contains the double that has the size of numOfCols
			double[] nums = new double[numOfCols];
			//loop though to parse all strings into double
			for (int i = 0; i < numOfCols; i++) {
				nums[i] = Double.parseDouble(str[i]);
				
				//assigned the data into each column
				spreadSheet[numOfRows][i] = nums[i];
			}
			//update number of rows through each iteration
			numOfRows++;
		}

	}
	
	/**
	 * getCoumnByIndex use the the index of spreadSheet column and add it to a DataSeries
	 * object for analysis
	 * @param col is the index number 
	 * @return collection 
	 */
	public DataSeries getColumnByIndex(int col) {
		//Initialize DataSeries called collection which uses the headerNames index
		DataSeries collection = new DataSeries(headerNames[col]);
		//iterate through all the rows and add data to collection
		for (int row = 0; row < numOfRows; row++) {
			collection.add(spreadSheet[row][col]);
		}
		return (collection);

	}
	
	@Override
	/**
	 * Overriding toString() to output data table
	 */
	public String toString() {
		
		/**
		 * The first part is to determine spacing between the header. We
		 * establish this through cycling through headerNames for the word with the most
		 * characters, this will be the minimum spacing. Add 3 more spaces for better visualization
		 */
		String formatter;
		int number;
		String longestWord = headerNames[0];
		for (int i = 0; i < headerNames.length; i++) {
			if (longestWord.length() < headerNames[i].length()) {
				longestWord = headerNames[i];
			}
		}
		number = longestWord.length();
		//formatter contain the spacing between data
		formatter = "%-"+ (number+3) + "s";
		
		//header contain the strings for header
		String header = "";
		//body contain the rest of the table
		String body = "";
		//looping through headerNames to create header string along with the proper spacing
		for (int i = 0; i < numOfCols; i++) {
			header = header + String.format(formatter, headerNames[i])+"";
		}
		
		/**
		 * Setting the limit of 10 rows. If the data is less than 10 rows then use its numOfRows for limit
		 * or else use 10 as the limit.
		 */
		int limit = 0;
		if (numOfRows < 10) {
			limit = numOfRows;
		} else
			limit = 10;
		for (int row = 0; row < limit; row++) {
			for (int col = 0; col < numOfCols; col++) {
				body = body + String.format(formatter, spreadSheet[row][col])+"";
			}
			body = body + "\n";
		}
		return (header + "\n" + body);
	}
	
	//getter method to get numOfRows
	public int getNumOfRows() {
		return numOfRows;
	}
	
	//getter method to get numOfCols
	public int getNumOfCols() {
		return numOfCols;
	}
	
	//getter method to get headerNames 
	public String[] getHeaderNames() {
		return headerNames;
	}

}
