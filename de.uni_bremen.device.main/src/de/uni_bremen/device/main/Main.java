package de.uni_bremen.device.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
import de.uni_bremen.st.model.android.device.AnalysisInformation;
import de.uni_bremen.st.model.android.device.AndroidDevice;
import de.uni_bremen.st.model.android.device.Category;
import de.uni_bremen.st.model.android.device.Component;
import de.uni_bremen.st.model.android.device.DeviceFactory;
import de.uni_bremen.st.model.android.device.DevicePackage;
import de.uni_bremen.st.model.android.device.DynamicReceiver;
import de.uni_bremen.st.model.android.device.ExplicitIntent;
import de.uni_bremen.st.model.android.device.ImplicitIntent;
import de.uni_bremen.st.model.android.device.IntentCall;
import de.uni_bremen.st.model.android.device.IntentList;
import de.uni_bremen.st.model.android.device.Method;

public class Main {
	private static final Logger LOG = Logger.getLogger("device.test");

	public static void main(String[] args) {
		LOG.info("Registering EMF stuff.");

		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				"device", new XMIResourceFactoryImpl());
		DevicePackage.eINSTANCE.eClass();

		String u = "/home/fthomas/workspace_ba/android_ipc/ri/app-release.device";
		
		// hier die app-realese.device
		final URI modelURI = URI
				.createFileURI(u);

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
//		file = "BroadcastReceiver2"; // dyn rec
//		 file = "BroadcastReceiverWithPermission"; //dyn rec
		// file = "IntentFilter";
//		 file = "PendingIntent";
		 file = "RandomIntent";
		// file = "SendBroadcast";
		// file = "SendBroadcastWithPermission";
//		 file = "StartActivity2";
		// file = "StartBinder";
//		 file = "StartLinkedActivity";
		// file = "StartService";
		// file = "ViewImageViaIntent";

		StringParser sp = new StringParser();
		FileParser fp = new FileParser(path + file + ".txt");
		ArrayList<Communication> allCommunications = fp.getCommunications();
		ArrayList<IntentCall> intentCallLst = new ArrayList<IntentCall>();

		if (allCommunications.size() == 0) {
			System.out.println("no communication found");
		}
		
		// intents and its containing data
		for (Communication communication : allCommunications) {
			System.out.println("processing: " + communication);

			// get component that belongs to the current communication
			EList<Component> componentLst = device.getActiveApp()
					.getComponents();
			String cmp = communication.getMethodClass();
			System.out.println("matche: " + cmp); // nullpointer bei normalen java klassen!
			Component c = null;
			for (Component item : componentLst) {
				String implCls = item.getImplementationClass();
				ArrayList<String> res = sp.getParts(implCls, "\\.");
				String cls = res.get(1);

				if (cmp.equals(cls)) {
					c = item;
					break;
				}
			} // hier habe ich die Komponente an die dann der IntentCall gehängt wird
			
			// method
			Method method = DeviceFactory.eINSTANCE.createMethod();
			method.setName(communication.getMethod());
			c.getMethods().add(method); // ist die Methode schon vorh.
			
			
//			EList<Method> mL = c.getMethods();
//			Boolean isIn = false;
//			for(Method m : mL) {
//				if(m.getName().equals(method.getName())){
//					isIn = true;
//				}
//			}
//			System.out.println(isIn);
//			if (!isIn) {
//				c.getMethods().add(method);
//			}
			
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

			// aufruf über mehrere klassen hinweg - geht noch nicht richtig
			// da man java klassen noch nicht erkennt
			// sollte sowas wie der ursprung sein:
			System.out.println("method parameter: "
					+ communication.getParameter());
			System.out.println("");

			if (communication.isExplicite()) { // explicit
				System.out.println("intent: explicit");

				for (ArrayList<String> iccValue : communication.getIcc()) {
					// scheme: package / class
					
					//TODO 
					IntentList intentList = DeviceFactory.eINSTANCE.createIntentList();
					IntentCall intentCall = DeviceFactory.eINSTANCE.createIntentCall();
					intentCall.setCaller(method);

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
					
					//c.getAnalysisInformation().add(intent);
					intentCall.setIntent(intent);
					
					//TODO callee
					EList<Component> componentLst2 = device.getActiveApp()
							.getComponents();
					Component c2 = null;
					for (Component item : componentLst) {

						if (item.getLabel().equals(cls)) {
							c2 = item;
							break;
						}
					}
					intentCall.getCallee().add(c2);
					
					intentCallLst.add(intentCall);
					intentList.getCalls().add(intentCall);
					//c.getAnalysisInformation().add(intentList); //TODO schon vorhanden?
					
					EList<AnalysisInformation> ai = c.getAnalysisInformation();
					if(ai.size() == 0){
						c.getAnalysisInformation().add(intentList);
					}else {
						IntentList il = (IntentList) ai.get(0);
						il.getCalls().add(intentCall);
					}
				}

			} else { // implicit
				System.out.println("intent: implicit");
				System.out.println("");

				for (ArrayList<String> iccValue : communication.getIcc()) {
					// scheme: action/category/extras

					IntentList intentList = DeviceFactory.eINSTANCE.createIntentList();
					IntentCall intentCall = DeviceFactory.eINSTANCE.createIntentCall();
					//TODO
					intentCall.setCaller(method);

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
								intentCall.getExtras().put(name, name);
								// TODO map entry erzeugen mit key/value ???
							}
						}
					}
					
					System.out.println("");
					//c.getAnalysisInformation().add(intent);
					intentCall.setIntent(intent);
					intentCallLst.add(intentCall);
					intentList.getCalls().add(intentCall);
					//c.getAnalysisInformation().add(intentList);
					
					EList<AnalysisInformation> ai = c.getAnalysisInformation();
					if(ai.size() == 0){
						c.getAnalysisInformation().add(intentList);
					}else {
						IntentList il = (IntentList) ai.get(0);
						il.getCalls().add(intentCall);
					}
					
				}
			}

			System.out.println("got " + intentCallLst.size() + " intent object(s)");
			System.out.println("");

			// add all Analysisinformation to the corresponding
			if (c != null) {
				for (IntentCall intentCall : intentCallLst) {
					//c.getAnalysisInformation().add(intentCall);
				}
			}
		}

		// neue resource erzeugen und unter neuem namen speichern, damit die
		// sich nicht überschreiben/voll schreiben

		try {
			Map<String, String> saveOptions = new HashMap<>();
			saveOptions.put(XMIResource.OPTION_ENCODING, "UTF-8");
			modelResource.save(saveOptions);
			
//			final URI newModelURI = URI
//					.createFileURI(u);
//			
//			Resource newResource = modelResourceSet.createResource(newModelURI);
//			modelResource.save(Collections.emptyMap());
			
		} catch (IOException e) {
			LOG.severe("Failed to save model." + e);
		}

		// nach dem abspeichern ist die ~.device ne "neue" datei
	}
}
