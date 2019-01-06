package generator;

import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;

public class JavaReaderImpl implements JavaReader{
	
	private StringBuilder fileTxt = new StringBuilder();
	private String packageName;
	private String className;
	private ArrayList<String> options;	
	
	public StringBuilder getFileTxt() {
		return fileTxt;
	}

	public JavaReaderImpl(String packageName, String className, ArrayList<String> options) {
		super();
		this.packageName = packageName;
		this.className = className;
		this.options = options;
		
		generate_SignatureClass(packageName, className, options);		
	}

	@Override
	public void generate_SignatureClass(String packageName, String className, ArrayList<String> options) {
		
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
		
	}

	@Override
	public void generate_ClassOptions(String className, ArrayList<String> options, StringBuilder fileTxt) {
		if(options.contains("Constructors from superclass")) {
			generate_Constructor(className,fileTxt);
		}
		
		if(options.contains("public static void main")) {
			generate_void_main(fileTxt);
		}	
		
		options.clear();
	}

	@Override
	public void generate_void_main(StringBuilder sb) {
		fileTxt.append("\n\tpublic static void main(String[] args) {");
		fileTxt.append("\n\t// TODO Auto-generated constructor stub\n\t}");			
	}

	@Override
	public void generate_Comments(StringBuilder fileTxt) {
		fileTxt.append("/**\n*\n*/\n/**\n* @author ");
		
		String userName = System.getProperty("user.name");
		fileTxt.append(userName);
		fileTxt.append("\n*\n*/\n");		
	}

	@Override
	public void generate_Constructor(String className, StringBuilder fileTxt) {
		fileTxt.append("\n\tpublic "+className+"(){");
		fileTxt.append("\n\t// TODO Auto-generated constructor stub\n\t}");		
	}

	public String errorDialog(){
		String error = "";
		if(className.isEmpty())
			error = "There's no class name!";
		
		return error;
	}

	

}
	
	