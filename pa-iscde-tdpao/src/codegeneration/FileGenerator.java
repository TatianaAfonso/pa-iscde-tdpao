package codegeneration;

import java.io.File;
import java.io.IOException;
import org.eclipse.swt.widgets.Display;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

/*
 * 
 * Class that calls the java editor service for write, save and open the file
 * 
 * Is used in class that generate code and file (GenerateClassTool.java)
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

	public static File createFileInfo(String filename, String packageName,String absolutePath) {

		ProjectBrowserServices browser = Activator.getInstance().getBrowserServices();
		
		if(absolutePath==null)
			absolutePath = browser.getRootPackage().getFile().toString()+"/src/";		
				
		String path = createNewPackage(packageName, absolutePath, filename);
		
		System.out.println("create filename * "+ filename + " * in "+ path);
		File file = createNewFile(path,filename);
	
		return file;
	}	
	
	private static File createNewFile(String path, String filename) {
		File file = new File(path+"/",filename+".java");
		try {
			file.createNewFile();	
			System.out.println("file created.");
		} catch (IOException e) {
			System.out.println("Error creating a new file.");
			System.out.println(e);
		}
		return file;
	}

	public static String createNewPackage(String packageName,String absolutePath,String filename) {
				
		System.out.println("* createNewPackage *");
		
		File dir = new File(absolutePath+"/"+packageName);
		if (!dir.exists()) {
			//Cria novo package retorna true or false
            if (dir.mkdir()) {
                System.out.println("New Package created.");  	    			
            } else {
                System.out.println("Error creating a new package.");
            }
        }
		return dir.getAbsolutePath();			
	}

	public static File createFile(String filename, String packageName, String absolutePath) {
		File dir = new File(absolutePath+"/", filename + ".java");
		//Verificar package		
		//Verifica a existencia da diretoria
		if (!dir.exists()) {
			//Cria novo package retorna true or false
            if (dir.mkdir()) {
                System.out.println("New Package created.");  	    			
            } else {
                System.out.println("Error creating a new package.");
            }
        }
		createNewFile(absolutePath, filename);
		return dir;
	}
}