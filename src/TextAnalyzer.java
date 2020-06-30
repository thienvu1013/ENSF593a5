
/**
 * @author Thien Nguyen
 * 
 * TextAnalyzer is used to analyze a String to determines the number of words and 
 * sentences contained within that String.
 *
 */
public class TextAnalyzer {
	//numOfSentences is used to stored the number of sentences
	private int numOfSentences;
	//numOfWords is used to stored the number of words
	private int numOfWords;
	
	/**
	 * Constructor for TextAnalyzer class. It takes in the string and
	 * produce the number of sentences and words in that string
	 * @param text contains the String that will be analyzed
	 */
	public TextAnalyzer(String text) {
		//totalWords is a temporary variable used to store the number of words
		int totalWords = 0;
		
		/**An array called sentences was created to store all sentences from text.
		 * text was first trimmed of white spaces and then split on ".?!" because these
		 * symbol indicates the end of a sentence
		 */
		String [] sentences = text.trim().split("[.?!]");
		
		//numOfSentences is achieve through finding the length of sentences
		numOfSentences = sentences.length;

		/**
		 * Iterate through each stored sentences
		 * First sentences are modified so that anything that are not "-", spaces or letters are replaced with ""
		 * Then the sentences are split on spaces to review the total number of words.
		 */
		for(String str:sentences) {
			String newStr = str.trim().replaceAll("[^-\\w\\s]+", "");
			String [] words =newStr.trim().split(" ");
			totalWords = totalWords + words.length;
		}
		
		numOfWords = totalWords;
	}
	
	//getter function used to get numOfSentences
	public int getnumOfSentences() {
		return numOfSentences;
	}
	
	//getter function used to get numOfWords
	public int getnumOfWords() {
		return numOfWords;
	}
	
	@Override
	//Overriding toString() to print out the results	
	public String toString() {
		return("Total number of sentences: "+ getnumOfSentences()+"\n"+"Total number of words: "+ getnumOfWords());
	}

}
