package codegeneration;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pt.iscte.pidesco.extensibility.PidescoServices;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private static Activator instance;
	private ProjectBrowserServices browserServices;
	private PidescoServices pidescoServices;
	private JavaEditorServices editor;

	public void start(BundleContext bundleContext) throws Exception {
		
		Activator.context = bundleContext;
		instance = this;
		
		final ServiceReference<ProjectBrowserServices> ref1 = context.getServiceReference(ProjectBrowserServices.class);
		browserServices = context.getService(ref1);

		final ServiceReference<JavaEditorServices> ref2 = context.getServiceReference(JavaEditorServices.class);
		editor = context.getService(ref2);
		
		final ServiceReference<PidescoServices> ref3 = context.getServiceReference(PidescoServices.class);
		pidescoServices= context.getService(ref3);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

	public ProjectBrowserServices getBrowserServices() {
		return browserServices;
	}

	public JavaEditorServices getJavaEditorServices() {
		return editor;
	}
	
	public PidescoServices getPidescoServices() {
		return pidescoServices;
	}
	
	public static Activator getInstance() {
		return instance;
	}

}
