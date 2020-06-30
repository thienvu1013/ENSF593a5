import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Thien Nguyen
 * 
 * TextAnalysisGUI represent the graphic user interface. This class
 * will handle user input and output uses TextAnalyzer. This class extends JFrame
 * and ActionListener.
 */
public class TextAnalysisGUI extends JFrame implements ActionListener{
	
	/**
	 * Button class is added to represent the analyze and clear buttons.
	 */
	private JButton analyzeButton;
	private JButton clearButton;
	
	/**
	 * JLabel class is initialized to help user identify the the right place to input
	 */
	private JLabel enterText;
	private JLabel resultText;
	
	/**
	 * JTextArea class is used to record and display input and output for user
	 * text is for recording the input
	 * result is for displaying the result
	 */
	private JTextArea text;
	private JTextArea result;
	
	/**
	 * TextAnalyzer is initialize for the analysis of texts.
	 */
	private TextAnalyzer textAz;
	
	/**
	 * TextAnalysisGUI constructor
	 * @param title contains the name of the GUI
	 */
	public TextAnalysisGUI(String title) {
		
		//Analyze button is created and added ActionListener
		analyzeButton  = new JButton("ANALYZE"); 
		analyzeButton.addActionListener(this);
		
		//Clear button is created and added ActionListener
		clearButton = new JButton("CLEAR");
		clearButton.addActionListener(this);
		
		//Labels are added along with its alignment
		enterText = new JLabel("Enter text here");
		enterText.setHorizontalAlignment(JLabel.CENTER);
		resultText = new JLabel("Results");
		resultText.setHorizontalAlignment(JLabel.CENTER);
		
		//TextArea added with the desired size. LineWrap is set to true so the text doesn't run over
		text  = new JTextArea("",10,20);
		text.setLineWrap(true);
		result = new JTextArea("",10,20);
		result.setLineWrap(true);
		
		//Added scroll bar for ease of use and review
		JScrollPane vbar = new JScrollPane(text);
		
		//Labels are added to the label panel. Grid layout was used because we wanted equal spacing between these labels
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(0,2));
		labelPanel.add(enterText);
		labelPanel.add(resultText);
		
		//TextAreas are added textPanel. GridBag layout was used because we want to have more control over its design
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0,0,0,3);
		
		//Scroll bar added to text
		textPanel.add(vbar,c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0,3,0,0);
		textPanel.add(result,c);
		
		//Buttons are added to buttonPanel. Grid layout was used because the button can be the same size.
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0,2));
		buttonPanel.add(analyzeButton);
		buttonPanel.add(clearButton);
		
		//All panel is added into a Container object using BorderLayout for organization
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add("North",labelPanel);
		contentPane.add("Center",textPanel);
		contentPane.add("South",buttonPanel);
		
		//Setting close operation, size, and title
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(550,250);
		setTitle(title);
		setVisible(true);
		
		
	}
	
	@Override
	/**
	 * actionPerformed used to handle events that can happen with the GUI.
	 */
	public void actionPerformed(ActionEvent e) {
		
		/**
		 * In the event the user select the analyze button, TextAnalyzer is instantiated and the text
		 * from within the text TextArea will be analyze and the result will be displayed in the result TextArea
		 */
		if (e.getSource()== analyzeButton) {
			textAz = new TextAnalyzer(text.getText());
			result.setText(textAz.toString());
		}
		
		/**
		 * In the event the user select the clear button, all values within the text and result TextArea will be cleared
		 */
		if (e.getSource() == clearButton) {
			text.selectAll();
			text.setText("");
			result.selectAll();
			result.setText("");
		}
		
	}
	
	public static void main(String[] args) {
		//Start of the program
		new TextAnalysisGUI("Text Analysis GUI");

	}



}
