package extensibility;

import java.util.ArrayList;

/**
 ** Interface needed to be used to implement code generation.
 **/
public interface CodeGenerationService {
		
	/**
	 * Create, save and open the file created.
	 * This method use java editor service for these actions.
	 * @param options, represents all options that add on the class java.
	 * @param className, represents the name of file.
	 * @param packageName, represents the name of package where the file is saved.
	 * 
	 */
	void createAndSaveFile(ArrayList<String> options, String className,String packageName);
	
}
