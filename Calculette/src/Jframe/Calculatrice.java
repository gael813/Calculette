package Jframe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculatrice extends JFrame {
	
	private JPanel container = new JPanel();
	// Table of elements in calculator
	String[] tab_string = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "=", "C", "+", "-", "*", "/"};
	// Button for each elements
	JButton[] tab_button = new JButton[tab_string.length];
	private JLabel screen = new JLabel();
	Dimension size = new Dimension(50,40);
	Dimension size2 = new Dimension(50, 30);
	double number;
	boolean operatorClic = false, update = false;
	String operator = "";
	
	public Calculatrice() {
		this.setTitle("Calculette");
		this.setSize(480, 520);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		initComposant();
		this.setContentPane(container);
		this.setVisible(true);
	}
	
	
	private void initComposant() {
		// Font definition
		Font font = new Font("Arial", Font.BOLD ,20);
		screen = new JLabel("0");
		screen.setFont(font);
		// Right align informations
		screen.setHorizontalAlignment(JLabel.RIGHT);
		screen.setPreferredSize(new Dimension(420, 20));
		JPanel operator = new JPanel();
		operator.setPreferredSize(new Dimension(150, 450));
		JPanel number = new JPanel();
		number.setPreferredSize(new Dimension(165, 400));
		JPanel panScreen = new JPanel();
		panScreen.setPreferredSize(new Dimension(440, 30));
		
		// Browse the initialized array
		// and attribute to buttons
		for (int i = 0; i < tab_string.length; i++) {
			tab_button[i] = new JButton(tab_string[i]);
			tab_button[i].setPreferredSize(size);
			switch(i) {
				//For each elements is not at the end of table and is not a number, then comportment definition with listener
			case 11 :
				tab_button[i].addActionListener(new EgalListener());
				number.add(tab_button[i]);
				break;
			case 12 :
				tab_button[i].setForeground(Color.red);
				tab_button[i].addActionListener(new ResetListener());
				number.add(tab_button[i]);
				break;
			case 13 :
				tab_button[i].addActionListener(new AdditionnalListener());
				tab_button[i].setPreferredSize(size2);
			case 14 :
				tab_button[i].addActionListener(new SubstractListener());
				tab_button[i].setPreferredSize(size2);
			case 15 :
				tab_button[i].addActionListener(new MultiListener());
				tab_button[i].setPreferredSize(size2);
			case 16 :
				tab_button[i].addActionListener(new DivisionListener());
				tab_button[i].setPreferredSize(size2);
			default :
				number.add(tab_button[i]);
				tab_button[i].addActionListener(new NumberListener());
				break;
			}
		}
		panScreen.add(screen);
		panScreen.setBorder(BorderFactory.createLineBorder(Color.black));
		container.add(panScreen, BorderLayout.NORTH);
		container.add(number, BorderLayout.CENTER);
		container.add(operator, BorderLayout.EAST);
	}
		
	
	// Equals Button case 11
	class EgalListener implements ActionListener {
		public void actionPerformed(ActionEvent Arg0) {
			calcul();
			update = true;
			operatorClic = false;
		}
	}
	
	// Reset Button case 12
	class ResetListener implements ActionListener {
		public void actionPerformed(ActionEvent Arg0) {
			operatorClic = false;
			update = true;
			number = 0;
			operator = "";
			screen.setText("");
		}
	}
	
	// Additionnal Button case 13
	class AdditionnalListener implements ActionListener {
		public void actionPerformed(ActionEvent Arg0) {
			if (operatorClic) {
				calcul();
				screen.setText(String.valueOf(number));
			}
			else {
				number = Double.valueOf(screen.getText()).doubleValue();
				operatorClic = true;
			}
			operator = "+";
			update = true;
		}
	}
	
	// Substract Button case 14
	class SubstractListener implements ActionListener {
		public void actionPerformed(ActionEvent Arg0) {
			if (operatorClic) {
				calcul();
				screen.setText(String.valueOf(number));
			}
			else {
				number = Double.valueOf(screen.getText()).doubleValue();
				operatorClic = true;
			}
			operator = "-";
			update = true;
		}
	}
	
	// Multi Button case 15
	class MultiListener implements ActionListener {
		public void actionPerformed(ActionEvent Arg0) {
			if (operatorClic) {
				calcul();
				screen.setText(String.valueOf(number));
			}
			else {
				number = Double.valueOf(screen.getText()).doubleValue();
				operatorClic = true;
			}
			operator = "*";
			update = true;
		}
	}
	
		
	// Divison Button case 16
	class DivisionListener implements ActionListener {
		public void actionPerformed(ActionEvent Arg0) {
			if (operatorClic) {
				calcul();
				screen.setText(String.valueOf(number));
			}
			else {
				number = Double.valueOf(screen.getText()).doubleValue();
				operatorClic = true;
			}
			operator = "/";
			update = true;
		}
	}	

	// Stock the numbers 
	class NumberListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Display the additional number in the label
			String str = ((JButton)e.getSource()).getText();
			if (update) {
				update = false;
			}
			else {
				if (!screen.getText().equals("0"))
					str = screen.getText() + str;
			}
			screen.setText(str);
		}
	}
	
	// Calcul() Method
	private void calcul() {
		if (operator.equals("+")) {
			number = number + Double.valueOf(screen.getText()).doubleValue();
			screen.setText(String.valueOf(number));
		}
		if (operator.equals("-")) {
			number = number - Double.valueOf(screen.getText()).doubleValue();
			screen.setText(String.valueOf(number));
		}
		if (operator.equals("*")) {
			number = number * Double.valueOf(screen.getText()).doubleValue();
			screen.setText(String.valueOf(number));
		}
		if (operator.equals("/")) {
			try {
				number = number / Double.valueOf(screen.getText()).doubleValue();
				screen.setText(String.valueOf(number));
			}
			catch (ArithmeticException e) {
				screen.setText("0");
			}
		}
	}
}