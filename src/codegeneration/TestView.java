package codegeneration;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import pt.iscte.pidesco.extensibility.PidescoView;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserListener;

public class TestView implements PidescoView{
	
	String packageName = null;
	String className = null;
	String modifiers = null;
	
	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {
		// TODO Auto-generated method stub
		//label.setImage(imageMap.get("lol2.png"));
				
		viewArea.setLayout(new RowLayout(SWT.VERTICAL));
		
		//field package
		Label labelPackage = new Label(viewArea, SWT.ABORT);		
		labelPackage.setText("Package: ");		
		Text namePackage = new Text(viewArea,SWT.BORDER);		
		//nameClass.setText("Name Class");
		namePackage.setEditable(false);
		namePackage.setSize(300, 20);
		
		//field name
		Label labelName = new Label(viewArea, SWT.ABORT);		
		labelName.setText("Name: ");		
		Text nameClass = new Text(viewArea,SWT.BORDER);		
		nameClass.setToolTipText("Write your class name");
		
		//combo modifier
		Label labelModifiers = new Label(viewArea, SWT.ABORT);
		labelModifiers.setText("Modifiers: ");
		labelModifiers.setToolTipText("Choose your modifier");	
		Combo combo = new Combo(viewArea, SWT.DROP_DOWN);
		combo.add("");
		combo.add("public");
		combo.add("private");
		combo.add("protected");		    
		
		Button buttonGenerate  = new Button("generate");
		buttonGenerate.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 
	        	className = labelName.getText();
				
				//generate class java
	            //generateJavaCode();
	        	
	         }
	      });
		
		ProjectBrowserListener pb;
		
		
	}	

	protected void generateJavaCode(String packageName, String className, String modifiers) {
		//criar tool (icon na tab) para abrir dialog que pede dados e acrescenta file no diretorio selecionado
		//chamar serviço refresh e depois serviço para abrir classe automatico
		System.out.println(className);	
			
	}
	
	

}
