package codegenerationext;

import java.util.Collection;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
import pt.iscte.pidesco.projectbrowser.model.SourceElement;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserListener;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

public class TestView implements PidescoView{
		
	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {
					
		viewArea.setLayout(new RowLayout(SWT.VERTICAL));
		
		//field source
		Label labelSource = new Label(viewArea, SWT.ABORT);		
		labelSource.setText("Source: ");	
		
		Text sourceTxt = new Text(viewArea,SWT.BORDER);		
		sourceTxt.setEditable(false);
		sourceTxt.setSize(500, 20);
		sourceTxt.setVisible(false);
		labelSource.setVisible(false);
		
		Text hiddenSource = new Text(viewArea,SWT.BORDER);		
		hiddenSource.setEditable(false);
		hiddenSource.setSize(600, 20);
		hiddenSource.setVisible(false);
				
		//field package
		Label labelPackage = new Label(viewArea, SWT.ABORT);		
		labelPackage.setText("Package: ");	
		
		Text namePackage = new Text(viewArea,SWT.BORDER);		
		namePackage.setEditable(false);
		namePackage.setSize(300, 20);
		
		//field name
		Label labelName = new Label(viewArea, SWT.ABORT);		
		labelName.setText("Class Name: ");	
		
		Text nameClass = new Text(viewArea,SWT.BORDER);		
		nameClass.setToolTipText("Write your class name");
		nameClass.setEditable(true);
		namePackage.setEditable(true);
		
		Button createClass = new Button(viewArea,SWT.CHECK);
		Button createPackage = new Button(viewArea,SWT.CHECK);
		createClass.setSelection(true);
		
		ProjectBrowserServices browser = Activator.getInstance().getBrowserServices();
		ProjectBrowserListener listener = new ProjectBrowserListener() {
			@Override
			public void selectionChanged(Collection<SourceElement> selection) {
				SourceElement element = selection.iterator().next();
				if(element.isPackage()) {
					sourceTxt.setText(element.getName());
					hiddenSource.setText(element.getFile().getAbsolutePath());
					namePackage.setText(element.getName());
				}else {
					System.out.println("it's not a package valid.");
				}				
			}				
			@Override
			public void doubleClick(SourceElement element) {
				if(element.isPackage()) {
					sourceTxt.setText(element.getName());
					hiddenSource.setText(element.getFile().getAbsolutePath());
					namePackage.setText(element.getName());
				}else {
					System.out.println("it's not a package valid.");
				}
			}		
		};
		browser.addListener(listener);	

		CodeGenerationService srv = Activator.getInstance().getCodeGenerationService();
		
		Button buttonGenerateClass  = new Button(viewArea,SWT.PUSH);
		buttonGenerateClass.setText("Generate class");
		buttonGenerateClass.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
	        	
	        	String classValue = nameClass.getText();
	        	String packageValue = namePackage.getText();
	        
				Display.getDefault().asyncExec(new Runnable() {
				    public void run() {
				    	
				    	String source = null;
				    	if(!classValue.isEmpty()) {
				    		
				    		source = hiddenSource.getText();
				    		
							srv.createAndSaveFile(false, false, false, true, false, classValue, packageValue,source);
							nameClass.setText("");
							namePackage.setText("");
							sourceTxt.setText("");
							hiddenSource.setText("");
				    	}				    	
				    }
				});					
	         }
	      });
		
		Button buttonGeneratePackage  = new Button(viewArea,SWT.PUSH);
		buttonGeneratePackage.setText("Generate package");
		buttonGeneratePackage.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
	        	
	        	String packageValue = namePackage.getText();
	        	String source = hiddenSource.getText();
	        
				Display.getDefault().asyncExec(new Runnable() {
				    public void run() {	
				    	if(!packageValue.isEmpty() && !source.isEmpty()) {
				    		srv.createPackage(false, packageValue, source);
							namePackage.setText("");
							hiddenSource.setText("");
							sourceTxt.setText("");
				    	}else {
				    		System.out.println("You need select a source from project browser and choose a name for your package.");
				    	}
				    }
				});	
				
	         }
	      });
		
		buttonGenerateClass.setEnabled(true);
		buttonGeneratePackage.setEnabled(false);
		
		createClass.setText("Create Class");
		createClass.addSelectionListener(new SelectionAdapter() {
	        @Override
	        public void widgetSelected(SelectionEvent event) {
	            Button btn = (Button) event.getSource();
	            if(btn.getSelection()) {
	            	createPackage.setSelection(false);
	            	nameClass.setEditable(true);
	            	namePackage.setEditable(true);
	            	namePackage.setText("");
	            	buttonGeneratePackage.setEnabled(false);
	            	buttonGenerateClass.setEnabled(true);
	            	sourceTxt.setVisible(false);
	            	labelSource.setVisible(false);
	            	
	            }else {
	            	createPackage.setSelection(true);
	            	nameClass.setEditable(false);
	            	namePackage.setEditable(true);
	            	nameClass.setText("");
	            	sourceTxt.setVisible(true);
	            	labelSource.setVisible(true);
	            }
	        }
	    });
		
		createClass.setSelection(true);
		
		createPackage.setText("Create Package");
		createPackage.addSelectionListener(new SelectionAdapter() {
	        @Override
	        public void widgetSelected(SelectionEvent event) {
	            Button btn = (Button) event.getSource();
	           // System.out.println(btn.getSelection());
	            if(btn.getSelection()) {
	            	createClass.setSelection(false);
	            	nameClass.setEditable(false);
	            	namePackage.setEditable(true);
	            	buttonGenerateClass.setEnabled(false);
	            	buttonGeneratePackage.setEnabled(true);
	            	sourceTxt.setVisible(true);
	            	labelSource.setVisible(true);
	            }else {
	            	createClass.setSelection(true);
	            	nameClass.setEditable(true);
	            	namePackage.setEditable(false);
	            	buttonGenerateClass.setEnabled(true);
	            	buttonGeneratePackage.setEnabled(false);
	            	sourceTxt.setVisible(false);
	            	labelSource.setVisible(false);
	            }
	        }
	    });
		
		
	}	
}
