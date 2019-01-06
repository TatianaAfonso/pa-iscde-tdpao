package codegeneration;

import java.io.File;
import java.io.IOException;

import org.eclipse.swt.widgets.Display;

import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

public class FileGenerator{
	
	final static ProjectBrowserServices browser = Activator.getInstance().getBrowserServices();
	
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

	public static File createFile(String filename, String packageName) {
		//ProjectBrowserServices serviço da janela de navegação pidesco		
				String path = browser.getRootPackage().getFile().toString()+"/src/";
				//Verificar package
				File dir = new File(path+packageName);
				//Verifica a existencia da diretoria
				if (!dir.exists()) {
					//Cria novo package retorna true or false
		            if (dir.mkdir()) {
		                System.out.println("Novo Package criado.");
		            } else {
		                System.out.println("Erro a criar novo package.");
		            }
		        }
				
				File file = new File(dir.getAbsolutePath()+"/", filename + ".java");
				try {
					file.createNewFile();			
				} catch (IOException e) {
					System.out.println(e);
				}

				return file;
	}


}