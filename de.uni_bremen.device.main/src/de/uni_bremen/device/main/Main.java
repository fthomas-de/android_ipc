package de.uni_bremen.device.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import de.uni_bremen.st.model.android.device.AndroidDevice;
import de.uni_bremen.st.model.android.device.DevicePackage;

public class Main {
	private static final Logger LOG = Logger.getLogger("device.test");

	public static void main(String[] args) {
		LOG.info("Registering EMF stuff.");
		
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("device", new XMIResourceFactoryImpl());
		DevicePackage.eINSTANCE.eClass();

		final URI modelURI = URI.createFileURI("test.device");

		final ResourceSet modelResourceSet = new ResourceSetImpl();
		final Resource modelResource = modelResourceSet.getResource(modelURI, true);

		AndroidDevice device = (AndroidDevice)modelResource.getContents().get(0);

		// ... "your code here"
		
		try {
			Map<String, String> saveOptions = new HashMap<>();
			saveOptions.put(XMIResource.OPTION_ENCODING, "UTF-8");
			modelResource.save(saveOptions);
		} catch (IOException e) {
			LOG.severe("Failed to save model." + e);
		}
	}

}
