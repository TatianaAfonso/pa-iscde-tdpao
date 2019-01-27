package codegeneration;

import java.io.File;
import java.io.IOException;
import org.eclipse.swt.widgets.Display;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

/*
 * 
 * this class calls the java editor service for write, save and open the file
 * and she is used in class that generate code and file (GenerateClassTool.java)
 */
public class FileGenerator{
			
	public static void writeToFile(File file, String code,JavaEditorServices editor) {
		Display.getDefault().asyncExec(new Runnable() {
		    public void run() {
		    	editor.setText(file, code);
		    	editor.saveFile(file);
		    }
		});				
	}

	public static void openFile(JavaEditorServices editor, File file) {
		Display.getDefault().asyncExec(new Runnable() {
		    public void run() {		    	
		    	editor.openFile(file);
		    }
		});		
	}

	public static File createFile(String filename, String packageName,String absolutePath) {

		ProjectBrowserServices browser = Activator.getInstance().getBrowserServices();
		
		if(absolutePath==null)
			absolutePath = browser.getRootPackage().getFile().toString()+"/src/";
		
		System.out.println("absolutePath: "+absolutePath);
		
		File dir = createNewPackage(packageName, absolutePath);
		
		System.out.println("dir.getAbsolutePath(): "+dir.getAbsolutePath());
		File file = new File(dir.getAbsolutePath()+"/", filename + ".java");
		try {
			file.createNewFile();			
		} catch (IOException e) {
			System.out.println(e);
		}
	
		return file;
	}	
	
	public static File createNewPackage(String packageName,String path) {
					
		path = path +"/";
		//Verificar package
		File dir = new File(path+"/"+packageName);
		//Verifica a existencia da diretoria
		if (!dir.exists()) {
			//Cria novo package retorna true or false
            if (dir.mkdir()) {
                System.out.println("New Package created.");                
            } else {
                System.out.println("Error creating a new package.");
            }
        }
		return dir;	
	}
}