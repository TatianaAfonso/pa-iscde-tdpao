package generator;

import java.io.File;
import java.util.ArrayList;

import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;

public class JavaReader {
	
	private String packageName;
	private String className;
	private ArrayList<String> options;
	private final StringBuilder fileTxt = new StringBuilder();
	private File file;
	
	public JavaReader(JavaEditorServices jes) {
		
		file = jes.getOpenedFile();
		
	}
	public JavaReader(String packageName, String className, ArrayList<String> options) {
		super();
		this.packageName = packageName;
		this.className = className;
		this.options = options;		
		
		
		generateSignatureClass(packageName, className, options);
	}
	
	private void generateSignatureClass(String packageName, String className, ArrayList<String> options) {
		
		if(packageName!="") {
			fileTxt.append("package "+packageName+";\n");
		}
		
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
		generateClassOptions(className, options, fileTxt);
		
		fileTxt.append("\n}");	
						
	}
	
	private void generateClassOptions(String className, ArrayList<String> options,StringBuilder fileTxt) {
		
		if(options.contains("Constructors from superclass")) {
			generate_Constructor(className,fileTxt);
		}
		
		if(options.contains("public static void main")) {
			generate_void_main(fileTxt);
		}	
	}

	private void generate_void_main(StringBuilder sb) {

		fileTxt.append("\n\tpublic static void main(String[] args) {");
		fileTxt.append("\n\t// TODO Auto-generated constructor stub\n\t}");	
	}
	
	public void generate_Comments(StringBuilder fileTxt){		
		
		fileTxt.append("/**\n*\n*/\n/**\n* @author ");
		//TODO append name of author
		fileTxt.append("${User}");
		fileTxt.append("\n*\n*/\n");
		
	}
	
	public void generate_Constructor(String className, StringBuilder fileTxt){
		
		fileTxt.append("\n\tpublic "+className+"(){");
		fileTxt.append("\n\t// TODO Auto-generated constructor stub\n\t}");
		
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public ArrayList<String> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<String> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		System.out.println(fileTxt.toString());
		return fileTxt.toString();
	}
	
}
