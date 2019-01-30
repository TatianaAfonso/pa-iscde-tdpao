package codegenerationext;

import extensibility.CodeGenerationService;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import codegenerationext.Activator;


public class Activator implements BundleActivator {

	private static BundleContext context;
	private static Activator instance;
	private CodeGenerationService codeGenerationService;
	private ProjectBrowserServices browserServices;

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		instance = this;
		
		final ServiceReference<CodeGenerationService> ref1 = context.getServiceReference(CodeGenerationService.class);
		codeGenerationService= context.getService(ref1);
		
		final ServiceReference<ProjectBrowserServices> ref2 = context.getServiceReference(ProjectBrowserServices.class);
		browserServices = context.getService(ref2);
		
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

	public ProjectBrowserServices getBrowserServices() {
		return browserServices;
	}
	
	
}
