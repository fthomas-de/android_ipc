package de.uni_bremen.device.main;

//TODO pro intent einen neuen ipc call

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import ba_parse.Communication;
import ba_parse.FileParser;
import ba_parse.StringParser;
import de.uni_bremen.st.model.android.device.Action;
import de.uni_bremen.st.model.android.device.AndroidDevice;
import de.uni_bremen.st.model.android.device.Category;
import de.uni_bremen.st.model.android.device.Component;
import de.uni_bremen.st.model.android.device.DeviceFactory;
import de.uni_bremen.st.model.android.device.DevicePackage;
import de.uni_bremen.st.model.android.device.DynamicReceiver;
import de.uni_bremen.st.model.android.device.ExplicitIntent;
import de.uni_bremen.st.model.android.device.ImplicitIntent;
import de.uni_bremen.st.model.android.device.Intent;
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
				.createFileURI("/home/fthomas/workspace_ba/android_ipc/bcr2/app-release.device");

		final ResourceSet modelResourceSet = new ResourceSetImpl();
		final Resource modelResource = modelResourceSet.getResource(modelURI,
				true);

		AndroidDevice device = (AndroidDevice) modelResource.getContents().get(
				0);
		// device.getAnalysisInformation().add(arg0) //intentcall
		// -> app -> component raussuchen und daran hängen

		String path = "/home/fthomas/git/android_ipc/epicc_out/";
		String file;

		// file = "AIDL";
		// file = "AidlClient";
		// file = "AidlServer";
		file = "BroadcastReceiver2"; // dyn rec
		// file = "BroadcastReceiverWithPermission"; //dyn rec
		// file = "IntentFilter";
		// file = "PendingIntent";
		// file = "RandomIntent";
		// file = "SendBroadcast";
		// file = "SendBroadcastWithPermission";
		// file = "StartActivity";
		// file = "StartBinder";
//		 file = "StartLinkedActivity";
		// file = "StartService";
		// file = "ViewImageViaIntent";

		StringParser sp = new StringParser();
		FileParser fp = new FileParser(path + file + ".txt");
		ArrayList<Communication> clst = fp.getCommunications();
		ArrayList<IntentCall> ipcLst = new ArrayList<IntentCall>();

		if (clst.size() == 0) {
			System.out.println("no communication found");
		}

		// intents and its containing data
		for (Communication communication : clst) {
			System.out.println("processing: " + communication);

			// get component that belongs to the current communication
			EList<Component> componentLst = device.getActiveApp()
					.getComponents();
			String cmp = communication.getMethodClass();
			System.out.println("matche: " + cmp); //TODO nullpointer bei normalen java klassen!
			Component c = null;
			for (Component item : componentLst) {
				String implCls = item.getImplementationClass();
				ArrayList<String> res = sp.getParts(implCls, "\\.");
				String cls = res.get(1);

				if (cmp.equals(cls)) {
					c = item;
					break;
				}
			}
			
			// method
			Method method = DeviceFactory.eINSTANCE.createMethod();
			method.setName(communication.getMethod());
			c.getMethods().add(method);
			System.out.println("initial method: " + communication.getMethod()
					+ " in " + communication.getMethodClass());

			// dynamic receiver
			if (communication.getType() != null
					&& !communication.getType().equals("")) {
				System.out.println("dynamic receiver found ("
						+ method.getName() + " in " + cmp + ")");

				DynamicReceiver dynamicReceiver = DeviceFactory.eINSTANCE
						.createDynamicReceiver();
				dynamicReceiver.setRegistration(method);
				dynamicReceiver.setLabel(cmp);
				device.getActiveApp().getComponents().add(dynamicReceiver);

			}

			// TODO aufruf über mehrere klassen hinweg - geht noch nicht richtig
			// da man java klassen noch nicht erkennt
			// sollte sowas wie der ursprung sein:
			System.out.println("method parameter: "
					+ communication.getParameter());
			System.out.println("");

			if (communication.isExplicite()) { // explicit
				System.out.println("intent: explicit");

				for (ArrayList<String> iccValue : communication.getIcc()) {
					// scheme: package / class

					IntentCall ipc = DeviceFactory.eINSTANCE.createIntentCall();
					ipc.setCaller(method);

					ExplicitIntent intent = DeviceFactory.eINSTANCE
							.createExplicitIntent();

					// package
					String pkg = iccValue.get(0);
					System.out.println("package: " + pkg);

					// class
					String cls = iccValue.get(1).trim();
					cls = sp.getParts(cls, "/").get(1);

					System.out.println("cls: " + cls);
					intent.setComponent(cls);

					c.getAnalysisInformation().add(intent);
					ipc.setIntent(intent);
					ipcLst.add(ipc);
				}

			} else { // implicit
				System.out.println("intent: implicit");
				System.out.println("");

				for (ArrayList<String> iccValue : communication.getIcc()) {
					// scheme: action/category/extras

					IntentCall ipc = DeviceFactory.eINSTANCE.createIntentCall();
					ipc.setCaller(method);

					ImplicitIntent intent = DeviceFactory.eINSTANCE
							.createImplicitIntent();

					for (int i = 0; i < iccValue.size(); i++) {
						if (i == 0) {
							// action
							String name = iccValue.get(i);

							if (name != null) {
								Action action = DeviceFactory.eINSTANCE
										.createAction();
								action.setName(name);
								System.out.println("action: " + name);
								intent.setAction(action);
								device.getActions().add(action);
							}

						} else if (i == 1) {
							// category
							String name = iccValue.get(i);

							if (name != null) {
								Category category = DeviceFactory.eINSTANCE
										.createCategory();
								category.setName(name);
								System.out.println("category: " + name);
								intent.setCategory(category);
								device.getCategories().add(category);
							}

						} else if (i == 2) {
							// extras
							String name = iccValue.get(i);

							if (name != null) {
								System.out.println("extras: " + name);
								ipc.getExtras().put(name, name);
								// TODO map entry erzeugen mit key/value ???
							}
						}
					}
					
					System.out.println("");
					c.getAnalysisInformation().add(intent);
					ipc.setIntent(intent);
					ipcLst.add(ipc);
				}
			}

			System.out.println("got " + ipcLst.size() + " intent object(s)");
			System.out.println("");

			// add all Analysisinformation to the corresponding
			if (c != null) {
				for (IntentCall ipc : ipcLst) {
					c.getAnalysisInformation().add(ipc);
				}
			}
		}

		// neue resource erzeugen und unter neuem namen speichern, damit die
		// sich nicht überschreiben/voll schreiben

		try {
			Map<String, String> saveOptions = new HashMap<>();
			saveOptions.put(XMIResource.OPTION_ENCODING, "UTF-8");
			modelResource.save(saveOptions);
		} catch (IOException e) {
			LOG.severe("Failed to save model." + e);
		}

		// nach dem abspeichern ist die ~.device ne "neue" datei
	}
}
