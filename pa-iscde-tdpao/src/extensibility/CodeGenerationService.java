package extensibility;

/**
 ** Interface needed to be used to implement code generation.
 **/
public interface CodeGenerationService {
		
	/**
	 * Create, save and open the file created.
	 * This method use java editor service for these actions.
	 * @param addConstructors
	 * @param addMethodMain
	 * @param addComments
	 * @param isAbstractClass
	 * @param isFinalClass
	 * @param className, represents the name of file.
	 * @param packageName, represents the name of package where the file is saved
	 * @param source, path where class is saved.
	 * 
	 * All options are: 
	 * 					Constructors from superclass - by default is false
	 * 					public static void main - by default is false
	 * 					Generate comments - by default is false
	 * 					abstract - by default is false
	 * 					final - by default is false
	 * 
	 */
	void createAndSaveFile(boolean addConstructors, boolean addMethodMain, boolean addComments, boolean isAbstractClass, boolean isFinalClass, String className,String packageName, String source);

	/**
	 * Create a new package with the option for create a class package-info too
	 * @param package_info, if true create a class if not, only create a package
	 * @param packageName, name of package that you want create
	 * @param source, path that you wnat create a new package
	 * 
	 */
	void createPackage(boolean package_info, String packageName, String source);
	
}
