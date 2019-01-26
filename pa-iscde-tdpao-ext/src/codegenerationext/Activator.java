package codegenerationext;

import extensibility.CodeGenerationService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import codegenerationext.Activator;


public class Activator implements BundleActivator {

	private static BundleContext context;
	private static Activator instance;
	private CodeGenerationService codeGenerationService;

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		instance = this;
		
		final ServiceReference<CodeGenerationService> ref1 = context.getServiceReference(CodeGenerationService.class);
		codeGenerationService= context.getService(ref1);
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
	
	public static Activator getInstance() {
		return instance;
	}

	public CodeGenerationService getCodeGenerationService() {
		return codeGenerationService;
	}

	
	
}
