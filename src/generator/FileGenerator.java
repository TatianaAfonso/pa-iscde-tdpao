package generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import codegeneration.Activator;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

public class FileGenerator {
	
	public static File createFile(String filename) {
		final ProjectBrowserServices browser = Activator.getInstance().getBrowserServices();

		String path = browser.getRootPackage().getFile().toString();
		File file = new File(path, filename + ".java");

		try {
			file.createNewFile();
			
		} catch (IOException e) {
			System.out.println(e);
		}

		return file;
	}

	public static void writeToFile(File file, String code) throws IOException {
		
		//final JavaEditorServices editor = Activator.getInstance().getJavaEditorServices();
		
		//editor.setText(file, code);
	    //editor.saveFile(file);
	   
		//Activator.getInstance().getPidescoServices().runTool("pt.iscte.pidesco.projectbrowser.refresh", true);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
		    writer.write(code);	  
		    writer.close();
		}	
		
	}

	public static void openFile(File classFile) {
		/*final JavaEditorServices editor = Activator.getInstance().getJavaEditorServices();		
		editor.openFile(classFile);	*/
	}

}
