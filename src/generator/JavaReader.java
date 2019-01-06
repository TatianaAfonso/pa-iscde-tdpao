package generator;

import java.util.ArrayList;

public interface JavaReader {
	
	void generateSignatureClass(String packageName, String className, ArrayList<String> options);

	void generateClassOptions(String className, ArrayList<String> options, StringBuilder fileTxt);

	void generate_void_main(StringBuilder sb);
	
	void generate_Comments(StringBuilder fileTxt);
	
	void generate_Constructor(String className, StringBuilder fileTxt);
}
