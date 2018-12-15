package codegeneration;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import pt.iscte.pidesco.extensibility.PidescoTool;

public class GenerateClassTool implements PidescoTool {
	
	private JFrame window;

	@Override
	public void run(boolean activate) {
		window = createWindow();
	}
	
	private JFrame createWindow() {
		
		final JFrame window = new JFrame("Code Generation [PA 77529]");
		window.setLayout(null);
		window.setSize(400, 500);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocation(300 , 0);
		
		createPaneltoFields(window);
		createPanelButtons(window);
		
		window.setVisible(true);
		
		return window;
	}

	private void createPaneltoFields(JFrame window) {
		
		JLabel labelPackage = new JLabel("Package:");
		labelPackage.setLocation(32,23);
		labelPackage.setSize(82,20);
		labelPackage.setEnabled(false);
		window.add(labelPackage);

		JTextField packageTxt = new JTextField();
		packageTxt.setLocation(132,23);
		packageTxt.setSize(106,20);
		packageTxt.setEnabled(false);
		packageTxt.setBackground(new Color(240,240,240));
		packageTxt.setForeground(new Color(0,0,0));
		window.add(packageTxt);

		JLabel labelName = new JLabel("Name:");
		labelName.setLocation(32,63);
		labelName.setSize(82,20);
		window.add(labelName);

		JTextField nameTxt = new JTextField();
		nameTxt.setLocation(131,64);
		nameTxt.setSize(106,20);
		window.add(nameTxt);

		JLabel labelModifiers = new JLabel("Modifiers:");
		labelModifiers.setLocation(32,144);
		labelModifiers.setSize(82,20);
		labelModifiers.setEnabled(true);
		labelModifiers.setBackground(new Color(240,240,240));
		labelModifiers.setForeground(new Color(0,0,0));
		window.add(labelModifiers);

		JRadioButton publicChoose = new JRadioButton("public");
		publicChoose.setLocation(133,144);
		publicChoose.setSize(88,20);
		publicChoose.setEnabled(false);
		publicChoose.setBackground(new Color(240,240,240));
		publicChoose.setForeground(new Color(0,0,0));
		publicChoose.setSelected(true);
		window.add(publicChoose);

		JCheckBox abstractCb = new JCheckBox("abstract");
		abstractCb.setLocation(133,183);
		abstractCb.setSize(100,20);
		abstractCb.setBackground(new Color(240,240,240));
		abstractCb.setForeground(new Color(0,0,0));
		window.add(abstractCb);

		JCheckBox finalCb = new JCheckBox("final");
		finalCb.setLocation(253,183);
		finalCb.setSize(100,20);
		finalCb.setBackground(new Color(240,240,240));
		finalCb.setForeground(new Color(0,0,0));
		window.add(finalCb);

		JLabel methods = new JLabel("Which method stubs would you like to create?");
		methods.setLocation(32,240);
		methods.setSize(302,20);
		methods.setEnabled(true);
		methods.setBackground(new Color(240,240,240));
		methods.setForeground(new Color(0,0,0));
		window.add(methods);
		
		JCheckBox main = new JCheckBox("public static void main" );
		main.setLocation(133,272);		
		main.setSize(200,20);
		main.setEnabled(true);
		main.setBackground(new Color(240,240,240));
		main.setForeground(new Color(0,0,0));
		window.add(main);

		JCheckBox constructors = new JCheckBox("Constructors from superclass" );
		constructors.setLocation(133,292);
		constructors.setSize(200,20);
		constructors.setEnabled(true);
		constructors.setBackground(new Color(240,240,240));
		constructors.setForeground(new Color(0,0,0));
		window.add(constructors);
		
		JCheckBox abstractMethods = new JCheckBox("Inherited abstract methods" );
		abstractMethods.setLocation(133,312);
		abstractMethods.setSize(200,20);
		abstractMethods.setEnabled(true);
		abstractMethods.setBackground(new Color(240,240,240));
		abstractMethods.setForeground(new Color(0,0,0));
		window.add(abstractMethods);
		
		JLabel addComments = new JLabel("Do you want to add comments?");
		addComments.setLocation(31,344);
		addComments.setSize(252,20);
		addComments.setEnabled(true);
		addComments.setBackground(new Color(240,240,240));
		addComments.setForeground(new Color(0,0,0));
		window.add(addComments);

		JCheckBox commentsCb = new JCheckBox("Generate comments");
		commentsCb.setLocation(223,344);
		commentsCb.setSize(150,20);
		commentsCb.setEnabled(true);
		commentsCb.setBackground(new Color(240,240,240));
		commentsCb.setForeground(new Color(0,0,0));
		window.add(commentsCb);			
		
	}

	
	private JPanel createPanelWithFields() {
		
		JPanel panelFields = new JPanel();
		panelFields.setBackground(Color.LIGHT_GRAY);
		
		JLabel labelPackage = new JLabel("Package: ");
		JTextField packageName = new JTextField();
		packageName.setEnabled(false);	
				
		panelFields.add(labelPackage);
		panelFields.add(packageName);
		
		return panelFields;
		
	}

	
	private void createPanelButtons(JFrame window){
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setLocation(175,394);
		cancelBtn.setSize(80,24);
		
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO close window
			}
		});
		
		window.add(cancelBtn);

		JButton generateBtn = new JButton("Generate");
		generateBtn.setLocation(268,394);		
		generateBtn.setSize(90,24);
		
		generateBtn.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {	        	 
	        	//TODO this method create class java
	         }
	      });
		
		window.add(generateBtn);
	}

}
