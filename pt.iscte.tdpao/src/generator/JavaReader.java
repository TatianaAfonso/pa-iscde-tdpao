package generator;

import java.util.ArrayList;

public interface JavaReader {
	
	void generate_SignatureClass(String packageName, String className, ArrayList<String> options);

	void generate_ClassOptions(String className, ArrayList<String> options, StringBuilder fileTxt);

	void generate_void_main(StringBuilder sb);
	
	void generate_Comments(StringBuilder fileTxt);
	
	void generate_Constructor(String className, StringBuilder fileTxt);
}
