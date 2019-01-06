package codegeneration;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.eclipse.swt.widgets.Display;

import pt.iscte.pidesco.extensibility.PidescoServices;
import pt.iscte.pidesco.extensibility.PidescoTool;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;
import utils.JavaFileScanner;
import utils.JavaFileVisitor;

public class GenerateClassTool implements PidescoTool {
			
	private JFrame window;
	private JCheckBox commentsCb;
	private JCheckBox abstractMethodsOption;
	private JCheckBox constructorsOption;
	private JCheckBox mainOption;
	private JCheckBox finalCb;
	private JCheckBox abstractCb;
	private JTextField classNameTxt;
	private JTextField packageTxt;
	private ArrayList<JCheckBox> checkBoxes = null;
	private ProjectBrowserServices browserService;
	private PidescoServices pidescoSrv;
	private String packageValue;
	
	@Override
	public void run(boolean activate) {
			
		final ProjectBrowserServices browser = Activator.getInstance().getBrowserServices();
		
		final PidescoServices pis = Activator.getInstance().getPidescoServices();
		browserService = browser;
		pidescoSrv=pis;
		
		//this is needed because this value is show when user open the window
		getPackageByDefault();
		
		window = createWindow();
		
	}
	
	private JFrame createWindow() {
		
		final JFrame window = new JFrame("Code Generation - Java Class [PA 77529]");
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
		labelPackage.setEnabled(true);
		window.add(labelPackage);

		packageTxt = new JTextField();
		packageTxt.setLocation(132,23);
		packageTxt.setSize(106,20);
		packageTxt.setEnabled(true);
		 
		packageTxt.setText(packageValue); //for default
		window.add(packageTxt);		

		JLabel labelName = new JLabel("Class Name:");
		labelName.setLocation(32,63);
		labelName.setSize(82,20);
		window.add(labelName);

		classNameTxt = new JTextField();
		classNameTxt.setLocation(131,64);
		classNameTxt.setSize(106,20);
		classNameTxt.setToolTipText("<html><b><font color=red>Please enter class name here" + "</font></b></html>");
		window.add(classNameTxt);

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

		abstractCb = new JCheckBox("abstract");
		abstractCb.setLocation(133,183);
		abstractCb.setSize(100,20);
		abstractCb.setBackground(new Color(240,240,240));
		abstractCb.setForeground(new Color(0,0,0));
		
		finalCb = new JCheckBox("final");
		finalCb.setLocation(253,183);
		finalCb.setSize(100,20);
		finalCb.setBackground(new Color(240,240,240));
		finalCb.setForeground(new Color(0,0,0));
		
		// Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(abstractCb);
	    group.add(finalCb);
	    
	    window.add(abstractCb);
		window.add(finalCb);
		
		JLabel methods = new JLabel("Which method stubs would you like to create?");
		methods.setLocation(32,240);
		methods.setSize(302,20);
		methods.setBackground(new Color(240,240,240));
		methods.setForeground(new Color(0,0,0));
		window.add(methods);
		
		mainOption = new JCheckBox("public static void main" );
		mainOption.setLocation(133,272);		
		mainOption.setSize(200,20);
		mainOption.setBackground(new Color(240,240,240));
		mainOption.setForeground(new Color(0,0,0));
		window.add(mainOption);

		constructorsOption = new JCheckBox("Constructors from superclass" );
		constructorsOption.setLocation(133,292);
		constructorsOption.setSize(200,20);
		constructorsOption.setBackground(new Color(240,240,240));
		constructorsOption.setForeground(new Color(0,0,0));
		window.add(constructorsOption);
		
		abstractMethodsOption = new JCheckBox("Inherited abstract methods" );
		abstractMethodsOption.setLocation(133,312);
		abstractMethodsOption.setSize(200,20);
		abstractMethodsOption.setBackground(new Color(240,240,240));
		abstractMethodsOption.setForeground(new Color(0,0,0));
		abstractMethodsOption.setSelected(true);
		window.add(abstractMethodsOption);
		
		JLabel addComments = new JLabel("Do you want to add comments?");
		addComments.setLocation(31,344);
		addComments.setSize(252,20);
		addComments.setEnabled(true);
		addComments.setBackground(new Color(240,240,240));
		addComments.setForeground(new Color(0,0,0));
		window.add(addComments);

		commentsCb = new JCheckBox("Generate comments");
		commentsCb.setLocation(223,344);
		commentsCb.setSize(150,20);
		commentsCb.setBackground(new Color(240,240,240));
		commentsCb.setForeground(new Color(0,0,0));
		window.add(commentsCb);	
		
		checkBoxes  = new ArrayList<JCheckBox>();
	    checkBoxes.add(abstractMethodsOption);
	    checkBoxes.add(commentsCb);
	    checkBoxes.add(abstractCb);
	    checkBoxes.add(constructorsOption);
	    checkBoxes.add(finalCb);
	    checkBoxes.add(mainOption);		
	}
	
	private void createPanelButtons(JFrame window){
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setLocation(175,394);
		cancelBtn.setSize(80,24);
		
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shutdown();
			}
		});
		
		window.add(cancelBtn);

		JButton generateBtn = new JButton("Generate");
		generateBtn.setLocation(268,394);		
		generateBtn.setSize(90,24);
		
		generateBtn.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {	        	 
	        	 generate();
	         }
	      });
		
		window.add(generateBtn);
	}
	
	public void generate() {
		
		packageValue = packageTxt.getText().toLowerCase();
		String nameValue = classNameTxt.getText();
		
		if(packageValue.isEmpty())
			getPackageByDefault();
		
		ArrayList<String> options = new ArrayList<String>();     
		for (JCheckBox checkBox : checkBoxes ) {
	        if (checkBox.isSelected()) {
	            options.add(checkBox.getText());
	        }
	    }	
	  	    
	    //call class javareader
	    CodeGenerationServiceImpl impl = new CodeGenerationServiceImpl(); 	
	     	   
	    if(nameValue.isEmpty())
			JOptionPane.showMessageDialog(window, "There's no class name!");
		else {
			 	    
	 	    impl.createAndSaveFile(options, nameValue, packageValue);
	 	    //close window
			shutdown();	 			
		}
    		
	}
	class PrintVisitor implements JavaFileVisitor {
		public boolean visitPackage(String packageName) {
			packageValue = packageName;
			return true;
		}
	}
	
	private void getPackageByDefault() {
		
	    ProjectBrowserServices browser = Activator.getInstance().getBrowserServices();
		String path = browser.getRootPackage().getFile().toString()+"/src/";
		    
		JavaFileScanner scanner = new JavaFileScanner(path); 
		scanner.accept(new PrintVisitor());	
	}

	public void shutdown() {	
		//Solution from https://stackoverflow.com/questions/5980316/invalid-thread-access-error-with-java-swt
		Display.getDefault().asyncExec(new Runnable() {
		    public void run() {
		    	pidescoSrv.runTool(browserService.REFRESH_TOOL_ID, true);		    	
		    }
		});	
		
		if(window.isActive()) {
			window.setVisible(false);			
		}
	}
}