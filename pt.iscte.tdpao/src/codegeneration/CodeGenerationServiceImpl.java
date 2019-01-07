package codegeneration;

import java.io.File;
/*
 * this class implements the service CodeGenerationService;
 * 
 * is call for any other project that we want use:
 * @packageName is the name of package that we want add the class file
 * @className is the name of class file that we created
 * @options are all options in the window into my project that create comments, modifiers and others
 * 
 */
import java.util.ArrayList;

import extensibility.CodeGenerationService;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;

public class CodeGenerationServiceImpl implements CodeGenerationService{
	
	StringBuilder fileTxt = new StringBuilder();
	String packageName;
	String className;
	ArrayList<String> options;	

	public CodeGenerationServiceImpl() {
		super();
	}

	public void generate_ClassOptions(String className, ArrayList<String> options, StringBuilder fileTxt) {
		if(options.contains("Constructors from superclass")) {
			generate_Constructor(className,fileTxt);
		}
		
		if(options.contains("public static void main")) {
			generate_void_main(fileTxt);
		}	
		
		options.clear();
	}

	public void generate_void_main(StringBuilder sb) {
		fileTxt.append("\n\tpublic static void main(String[] args) {");
		fileTxt.append("\n\t// TODO Auto-generated constructor stub\n\t}");			
	}

	public void generate_Comments(StringBuilder fileTxt) {
		fileTxt.append("/**\n*\n*/\n/**\n* @author ");
		
		String userName = System.getProperty("user.name");
		fileTxt.append(userName);
		fileTxt.append("\n*\n*/\n");		
	}

	public void generate_Constructor(String className, StringBuilder fileTxt) {
		fileTxt.append("\n\tpublic "+className+"(){");
		fileTxt.append("\n\t// TODO Auto-generated constructor stub\n\t}");		
	}
	
	private String generateCode(String packageName, String className, ArrayList<String> options) {
				
		fileTxt.append("package "+packageName+";\n");
		
		if(options.contains("Generate comments")) {
			generate_Comments(fileTxt);
		}
		
		fileTxt.append("public ");
		
		if(options.contains("abstract")) {
			fileTxt.append("abstract ");
		}
		else if(options.contains("final")) {
			fileTxt.append("final ");
		}
		
		fileTxt.append("class "+className+"{\n");		
		
		//add other options that user choose before
		generate_ClassOptions(className, options, fileTxt);
		
		fileTxt.append("\n}");	
		
		return fileTxt.toString();
		
	}

	@Override
	public void createAndSaveFile(ArrayList<String> options, String nameValue,String packageValue) {
		//create java file here
		String code = generateCode(packageValue, nameValue, options);
		
 	    File classFile = FileGenerator.createFile(nameValue,packageValue);	  			
		JavaEditorServices editor = Activator.getInstance().getJavaEditorServices();
		FileGenerator.writeToFile(classFile, code, editor);		
		FileGenerator.openFile(editor, classFile);
	}
}
	
	