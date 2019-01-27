package codegeneration;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.eclipse.swt.widgets.Display;

import pt.iscte.pidesco.extensibility.PidescoServices;
import pt.iscte.pidesco.extensibility.PidescoTool;
import pt.iscte.pidesco.projectbrowser.model.SourceElement;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserListener;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

public class GeneratePackageTool implements PidescoTool {

	private ProjectBrowserServices browserService;
	private PidescoServices pidescoSrv;
	ProjectBrowserListener listener;
	private JFrame window;
	private JTextField sourceTxt;
	private JTextField nameTxt;
	private JCheckBox packageInfo;
	private JTextField pathHidden;
	ProjectBrowserServices browser;
	
	@Override
	public void run(boolean activate) {
	
		browser = Activator.getInstance().getBrowserServices();
		final PidescoServices pis = Activator.getInstance().getPidescoServices();
		browserService = browser;
		pidescoSrv=pis;		
		
		window = createWindow(browser);
		
		listener= new ProjectBrowserListener() {
			@Override
			public void selectionChanged(Collection<SourceElement> selection) {
				SourceElement element = selection.iterator().next();
				if(element.isPackage()) {
					sourceTxt.setText(element.getName());
					pathHidden.setText(element.getFile().getAbsolutePath());
				}else {
					JOptionPane.showMessageDialog(window, "Select a package only!");
				}				
			}				
			@Override
			public void doubleClick(SourceElement element) {
				if(element.isPackage()) {
					sourceTxt.setText(element.getName());
					pathHidden.setText(element.getFile().getAbsolutePath());
				}else {
					JOptionPane.showMessageDialog(window, "Select a package only!");
				}
			}		
		};
		browser.addListener(listener);			
	}
	
	private JFrame createWindow(ProjectBrowserServices browser) {
		
		final JFrame window = new JFrame("Code Generation - Java Package [PA 77529]");
		window.setLayout(null);
		window.setSize(415, 230);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocation(700 , 200);
		
		createPaneltoFields(window,browser);	
		createPanelButtons(window);	
		
		window.setVisible(true);		
		
		return window;	
	}
	
	private void createPanelButtons(JFrame window) {
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setLocation(180,154);
		cancelBtn.setSize(80,24);
	
		cancelBtn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			shutdown();
		}
	});
	
	window.add(cancelBtn);
	
	JButton generateBtn = new JButton("Finish");
		generateBtn.setLocation(273,154);		
		generateBtn.setSize(90,24);
		
		generateBtn.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {	        	 
	        	 generate();
	         }		
	     });		
		window.add(generateBtn);
	}
	
	protected void generate() {
		
		String sourceFolder = sourceTxt.getText().toLowerCase();
		String packageName = nameTxt.getText();
		String absolutePath = pathHidden.getText();		
		
		CodeGenerationServiceImpl impl = new CodeGenerationServiceImpl();		 
		 
		if(sourceFolder.isEmpty())
			JOptionPane.showMessageDialog(window, "There's no source folder selected!");
		else {
			if(packageName.isEmpty())
				JOptionPane.showMessageDialog(window, "There's no package name!");
			else {
						
				if(packageInfo.isSelected()) {
					System.out.println("absolutePath: "+absolutePath);
					impl.createPackage(true, packageName,absolutePath);
				}else {
					impl.createPackage(false, packageName,absolutePath);//name of package
				}
								
			 	//close window
				shutdown();	 			
			}
		 }		
	}
	
	private void createPaneltoFields(JFrame window, ProjectBrowserServices browser) {
		
		JLabel source = new JLabel("Source folder:");
		source.setLocation(32,23);
		source.setSize(82,20);
		source.setEnabled(true);
		window.add(source);
		
		sourceTxt = new JTextField();
		sourceTxt.setLocation(132,23);
		sourceTxt.setSize(240,20);
		sourceTxt.setEnabled(true);		
		window.add(sourceTxt);	
		
		JLabel labelName = new JLabel("Name:");
		labelName.setLocation(32,63);
		labelName.setSize(82,20);
		window.add(labelName);
	
		nameTxt = new JTextField();
		nameTxt.setLocation(131,64);
		nameTxt.setSize(106,20);
		nameTxt.setToolTipText("<html><b><font color=red>Please enter package name here" + "</font></b></html>");
		window.add(nameTxt);
	
		packageInfo = new JCheckBox("Create package-info.java");
		packageInfo.setLocation(32,103);
		packageInfo.setSize(200,20);
		packageInfo.setBackground(new Color(240,240,240));
		packageInfo.setForeground(new Color(0,0,0)); 
		window.add(packageInfo);		
		
		pathHidden = new JTextField();
		pathHidden.setLocation(32,123);
		pathHidden.setSize(330,20);
		pathHidden.setEnabled(false);
		pathHidden.hide();
		window.add(pathHidden);
	}
	
	public void shutdown() {	
		//Solution from https://stackoverflow.com/questions/5980316/invalid-thread-access-error-with-java-swt
		Display.getDefault().asyncExec(new Runnable() {
			    public void run() {
			    	pidescoSrv.runTool(browserService.REFRESH_TOOL_ID, true);		    	
			    }
			});	
		
		
			
		browser.removeListener(listener);
		
		if(window.isActive()) {
			window.setVisible(false);			
		}
	}
}
