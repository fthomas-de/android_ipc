package de.uni_bremen.device.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import ba_parse.Communication;
import ba_parse.FileParser;
import de.uni_bremen.st.model.android.device.Action;
import de.uni_bremen.st.model.android.device.Category;
import de.uni_bremen.st.model.android.device.DeviceFactory;
import de.uni_bremen.st.model.android.device.DevicePackage;
import de.uni_bremen.st.model.android.device.ExplicitIntent;
import de.uni_bremen.st.model.android.device.ImplicitIntent;
import de.uni_bremen.st.model.android.device.IntentCall;
import de.uni_bremen.st.model.android.device.Method;

public class Main {
	private static final Logger LOG = Logger.getLogger("device.test");

	public static void main(String[] args) {
		LOG.info("Registering EMF stuff.");

		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				"device", new XMIResourceFactoryImpl());
		DevicePackage.eINSTANCE.eClass();

		// hier die app-realese.device
		final URI modelURI = URI
				.createFileURI("/home/fthomas/workspace_ba/android_ipc/slinkedactivity1/app-release.device");

		final ResourceSet modelResourceSet = new ResourceSetImpl();
		final Resource modelResource = modelResourceSet.getResource(modelURI,
				true);

		//AndroidDevice device = (AndroidDevice) modelResource.getContents().get(0);

		String path = "/home/fthomas/git/android_ipc/epicc_out/";
		String file;

//		 file = "ACT_Android";
//		 file = "AIDL";
//		 file = "AidlClient";
//		 file = "AidlServer";
//		 file = "BroadcastReceiver";
//		 file = "BroadcastReceiverWithPermission2";
//		 file = "IntentFilter";
//		 file = "PendingIntent4";
//		 file = "RandomIntent";
//		 file = "SendBroadcast";
//		 file = "SendBroadcastWithPermission";
//		 file = "StartActivity2";
//		 file = "StartBinder";
		 file = "StartLinkedActivity";
//		 file = "StartService";
//		 file = "ViewImageViaIntent";

		FileParser fp = new FileParser(path + file + ".txt");
		ArrayList<Communication> clst = fp.getCommunications();

		if (clst.size() == 0) {
			System.out.println("Keine Kommunikation gefunden");
		}

		// a communication contains all information about one call with all
		// possible
		// intents and its containing data
		for (Communication communication : clst) {
			IntentCall ipc = DeviceFactory.eINSTANCE.createIntentCall();
			System.out.println("Processing: " + communication);

			Method method = DeviceFactory.eINSTANCE.createMethod();
			method.setName(communication.getMethod());
			//TODO dynamic receiver
			System.out.println("initial method: " + communication.getMethod() + " in " + communication.getMethodClass());
			//TODO aufruf über mehrere klassen hinweg 
			ipc.setCaller(method);

			if (communication.isExplicite()) {
				// explicit
				System.out.println("intent: explicit");
				
				for(ArrayList<String> iccValue : communication.getIcc()){
					ExplicitIntent intent = DeviceFactory.eINSTANCE
							.createExplicitIntent();
				
					// package
					String pkg = iccValue.get(0);
					System.out.println("package: " + pkg);

					// class
					String cls = iccValue.get(1).trim();

					String[] s = cls.split("/");
					int size = s.length;
					cls = s[size - 1];

					System.out.println("cls: " + cls);
					intent.setComponent(cls);

					ipc.setIntent(intent); 
				}

			} else {
				// implicit
				System.out.println("intent: implicit");
				
				for (ArrayList<String> iccValue : communication.getIcc()) { // action/category/extras
					ImplicitIntent intent = DeviceFactory.eINSTANCE
							.createImplicitIntent();

					for (int i = 0; i < iccValue.size(); i++) {
						// action
						if (i == 0) {
							Action action = DeviceFactory.eINSTANCE
									.createAction();
							String name = iccValue.get(i);
							action.setName(name);
							System.out.println("action: " + name);
							intent.setAction(action);

							// category
						} else if (i == 1) {
							Category category = DeviceFactory.eINSTANCE
									.createCategory();
							String name = iccValue.get(i);
							category.setName(name);
							System.out.println("category: " + name);
							intent.setCategory(category);

							// extras
						} else if (i == 2) {
							// TODO extras hinzufügen?
							String extra = iccValue.get(i);
							System.out.println("extras: " + extra);
						}
					}
					ipc.setIntent(intent);
				}
			}
		}

		try {
			Map<String, String> saveOptions = new HashMap<>();
			saveOptions.put(XMIResource.OPTION_ENCODING, "UTF-8");
			modelResource.save(saveOptions);
		} catch (IOException e) {
			LOG.severe("Failed to save model." + e);
		}
	}
}
