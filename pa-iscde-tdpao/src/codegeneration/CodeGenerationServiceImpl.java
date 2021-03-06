package codegeneration;

import java.io.File;

import org.eclipse.swt.widgets.Display;

import extensibility.CodeGenerationService;
import pt.iscte.pidesco.extensibility.PidescoServices;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

/*
 * This is the class that implements CodeGenerationService.
 * Returns the string to be written to the file.
 * 
 */
public class CodeGenerationServiceImpl implements CodeGenerationService{
	
	StringBuilder fileTxt = new StringBuilder();
	final static String DEFAULT = "package-info";

	public CodeGenerationServiceImpl() {
		super();
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

	private String generateCode(String packageName, String className, boolean addConstructors, boolean addMethodMain,
			boolean addComments, boolean abstractClass, boolean finalClass) {		
		fileTxt.append("package "+packageName+";\n");
		
		if(addComments) {
			generate_Comments(fileTxt);
		}
		
		fileTxt.append("public ");
		
		if(abstractClass) {
			fileTxt.append("abstract ");
		}
		else if(finalClass) {
			fileTxt.append("final ");
		}
		
		fileTxt.append("class "+className+"{\n");		
		
		//add other options that user choose before
		if(addConstructors) {
			generate_Constructor(className,fileTxt);
		}
		
		if(addMethodMain) {
			generate_void_main(fileTxt);
		}
		
		fileTxt.append("\n}");	
		
		return fileTxt.toString();
	
	}
	
	private String generateCode(String namePackage,String path) {
		
		fileTxt.append("/**\n*\n*/\n/**\n* @author ");		
		String userName = System.getProperty("user.name");
		fileTxt.append(userName);
		fileTxt.append("\n*\n*/\n");
		
		path = path.replace("\\", ".");
		fileTxt.append("package "+path+"."+namePackage+";\n");
		
		return fileTxt.toString();
	}

	@Override
	public void createPackage(boolean package_info, String packageName, String absolutePath) {
		
		JavaEditorServices editor = Activator.getInstance().getJavaEditorServices();

		if(package_info) {
			int indexOfSrc = absolutePath.indexOf("src")+4;
			String srcPath = absolutePath.substring(indexOfSrc, absolutePath.length());
			String code = generateCode(packageName,srcPath);
			File classFile = FileGenerator.createFileInfo(DEFAULT, packageName,absolutePath); //name of class and name of package
			FileGenerator.writeToFile(classFile, code, editor);		
			FileGenerator.openFile(editor, classFile);
		}else {
			FileGenerator.createNewPackage(packageName,absolutePath,null);
		}	
		
		PidescoServices pidescoSrv = Activator.getInstance().getPidescoServices();
		//Solution from https://stackoverflow.com/questions/5980316/invalid-thread-access-error-with-java-swt
		Display.getDefault().asyncExec(new Runnable() {
			    public void run() {
			    	ProjectBrowserServices browserService = Activator.getInstance().getBrowserServices();
					pidescoSrv.runTool(browserService .REFRESH_TOOL_ID, true);		    	
			    }
			});	
	}
	
	
	@Override
	public void createAndSaveFile(boolean addConstructors, boolean addMethodMain, boolean addComments, 
			boolean isAbstractClass, boolean isFinalClass, String className, String packageName,String absolutePath) {
		
		ProjectBrowserServices browser = Activator.getInstance().getBrowserServices();

		if(absolutePath==null) {
			absolutePath=browser.getRootPackage().getFile().toString()+"/src/";
		}
		
		String code = generateCode(packageName, className, addConstructors, addMethodMain, addComments, isAbstractClass, isFinalClass);
		
		File classFile = FileGenerator.createFile(className,packageName,absolutePath);	
		
		JavaEditorServices editor = Activator.getInstance().getJavaEditorServices();
		FileGenerator.writeToFile(classFile, code, editor);		
		FileGenerator.openFile(editor, classFile);
		
	}	
}