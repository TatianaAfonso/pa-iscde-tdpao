package codegeneration;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pt.iscte.pidesco.extensibility.PidescoServices;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private ProjectBrowserServices browserServices;
	private PidescoServices pidescoServices;
	private static Activator instance;	
	private static JavaEditorServices service;

	private JavaEditorServices services;	

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		instance = this;
		
		final ServiceReference<ProjectBrowserServices> ref1 = context.getServiceReference(ProjectBrowserServices.class);
		browserServices = context.getService(ref1);

		final ServiceReference<JavaEditorServices> ref2 = context.getServiceReference(JavaEditorServices.class);
		services = context.getService(ref2);
		
		final ServiceReference<PidescoServices> ref3 = context.getServiceReference(PidescoServices.class);
		pidescoServices= context.getService(ref3);			
		
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

	public ProjectBrowserServices getBrowserServices() {
		return browserServices;
	}

	public JavaEditorServices getJavaEditorServices() {
		return services;
	}

	public PidescoServices getPidescoServices() {
		return pidescoServices;
	}

	public static JavaEditorServices getService() {
		return service;
	}

	public static void setService(JavaEditorServices service) {
		Activator.service = service;
	}
	
}
