package codegenerationext;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import extensibility.CodeGenerationService;
import pt.iscte.pidesco.extensibility.PidescoView;

public class TestView implements PidescoView{
	
	String packageName = null;
	String className = null;
	
	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {
					
		viewArea.setLayout(new RowLayout(SWT.VERTICAL));
		
		//field package
		Label labelPackage = new Label(viewArea, SWT.ABORT);		
		labelPackage.setText("Package: ");		
		Text namePackage = new Text(viewArea,SWT.BORDER);		
		namePackage.setEditable(true);
		namePackage.setSize(300, 20);
		
		//field name
		Label labelName = new Label(viewArea, SWT.ABORT);		
		labelName.setText("Class Name: ");		
		Text nameClass = new Text(viewArea,SWT.BORDER);		
		nameClass.setToolTipText("Write your class name");

		Button buttonGenerate  = new Button(viewArea,SWT.PUSH);
		buttonGenerate.setText("Generate");
		buttonGenerate.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
	        	
	        	String classValue = nameClass.getText();
	        	String packageValue = namePackage.getText();
	        
				Display.getDefault().asyncExec(new Runnable() {
				    public void run() {
				    	ArrayList<String> options = new ArrayList<String>();    
						CodeGenerationService srv = Activator.getInstance().getCodeGenerationService();	
						srv.createAndSaveFile(options,classValue, packageValue);
				    }
				});	
				
	         }
	      });	
		
	}	
}
