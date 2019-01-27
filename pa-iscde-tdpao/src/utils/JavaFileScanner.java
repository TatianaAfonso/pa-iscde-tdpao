package utils;
import java.io.File;
import java.util.ArrayList;

public class JavaFileScanner {

	private File basedir;
	
	public JavaFileScanner(String path) {
		basedir = new File(path);
		assert basedir.exists() && basedir.isDirectory();
	}
	
	public String accept(JavaFileVisitor v) {
		PackageStack stack = new PackageStack();
		return visit(basedir, v, stack);		
	}
	
	/*public String acceptAll(JavaFileVisitor v) {
		PackageStack stack = new PackageStack();
		return visitAll(basedir, v, stack);		
	}
	*/
	private static String visit(File dir, JavaFileVisitor v, PackageStack stack)   {		
		
		// for every package v.visitPackage(...) should be invoked 			
		for(File f : dir.listFiles()) {	
							
			if(!f.isFile()) {						
				stack.push(f.getName());
				v.visitPackage(stack.packageName());					
				stack.pop();
				break;				
			}
		}
		
		return stack.packageName();
			
	}
	
	/*private static String visitAll(File dir, JavaFileVisitor v, PackageStack stack)   {		
		
		String path = dir.getPath();
		System.out.println("* visitAll *");
		System.out.println(path);
		
		// for every package v.visitPackage(...) should be invoked 			
		for(File f : dir.listFiles()) {								
			if(!f.isFile()) {						
				stack.push(f.getName());
				v.visitPackage(stack.packageName());					
				stack.pop();	
				if(f.isDirectory() && path!=f.getPath()) {
					dir = new File(f.getPath());
					System.out.println("dir: "+dir);
					visitAll(dir, v, stack);					
				}
			}
		}
		return path;
	}*/
	 
	public static String removeTypeFile(File f) {
		String[] split = f.getName().split("\\.");
		return split[0];
	}
	
	private static class PackageStack {
		private ArrayList<String> stack = new ArrayList<>();
		
		public void push(String e) {
			stack.add(e);
		}
		
		public String pop() {
			return stack.remove(stack.size()-1);
		}
		
		public boolean isDefault() {
			return stack.isEmpty();
		}
		
		public String packageName() {
			return String.join(".", stack);
		}
	}
	
}
