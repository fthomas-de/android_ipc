package de.uni_bremen.device.main;

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
import de.uni_bremen.st.model.android.device.AnalysisInformation;
import de.uni_bremen.st.model.android.device.AndroidDevice;
import de.uni_bremen.st.model.android.device.Callgraph;
import de.uni_bremen.st.model.android.device.Category;
import de.uni_bremen.st.model.android.device.Component;
import de.uni_bremen.st.model.android.device.DeviceFactory;
import de.uni_bremen.st.model.android.device.DevicePackage;
import de.uni_bremen.st.model.android.device.DynamicReceiver;
import de.uni_bremen.st.model.android.device.ExplicitIntent;
import de.uni_bremen.st.model.android.device.ImplicitIntent;
import de.uni_bremen.st.model.android.device.IntentCall;
import de.uni_bremen.st.model.android.device.IntentList;
import de.uni_bremen.st.model.android.device.JavaMethod;
import de.uni_bremen.st.model.android.device.impl.CallgraphImpl;

//import de.uni_bremen.st.model.android.device.Method;

public class Main {
	private static final Logger LOG = Logger.getLogger("device.test");

	public static void main(String[] args) {
		LOG.info("Registering EMF stuff.");

		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				"device", new XMIResourceFactoryImpl());
		DevicePackage.eINSTANCE.eClass();

		String u = "/home/fthomas/workspace_ba/android_ipc/adobe/app-release.device";
//		String u = "/home/fthomas/workspace_ba/android_ipc/ri/app-release.device";

		// hier die app-realese.device
		final URI modelURI = URI.createFileURI(u);

		final ResourceSet modelResourceSet = new ResourceSetImpl();
		final Resource modelResource = modelResourceSet.getResource(modelURI,
				true);

		AndroidDevice device = (AndroidDevice) modelResource.getContents().get(
				0);
		// device.getAnalysisInformation().add(arg0) //intentcall
		// -> app -> component raussuchen und daran hängen

		int errors = 0;

		String path = "/home/fthomas/git/android_ipc/epicc_out/";
		String file;

		 file = "#adobereader";
		// file = "AIDL";
		// file = "AidlClient";
		// file = "AidlServer";
		// file = "BroadcastReceiver2"; // dyn rec
		// file = "BroadcastReceiverWithPermission"; //dyn rec
		// file = "IntentFilter";
		// file = "PendingIntent";
//		file = "RandomIntent";
		// file = "SendBroadcast";
		// file = "SendBroadcastWithPermission";
		// file = "StartActivity2";
		// file = "StartBinder";
		// file = "StartLinkedActivity";
		// file = "StartService";
		// file = "ViewImageViaIntent";

		StringParser sp = new StringParser();
		FileParser fp = new FileParser(path + file + ".txt");
		ArrayList<Communication> allCommunications = fp.getCommunications();
		ArrayList<IntentCall> intentCallLst = new ArrayList<IntentCall>();

		if (allCommunications.size() == 0) {
			System.out.println("Error: no communication found");
		} else {
			System.out.println("Found " + allCommunications.size()
					+ " communication objects");
		}

		// ------- Beginn des Durchlaufs -------

		// ------- Lese Kommunikationsobjekt -------
		// intents and its containing data
		for (Communication communication : allCommunications) {
			System.out.println("");
			System.out
					.println("==========================================================");
			System.out.println("");
			System.out.println("processing comunication: " + communication);

			// get component that belongs to the current communication
			EList<Component> allComponents = device.getActiveApp()
					.getComponents();
			String cmpClassName = communication.getMethodClass();
			cmpClassName = cmpClassName.split("\\$")[0];
			System.out.println("suche Komponente: " + cmpClassName);
			// hier: nullpointer bei normalen java klassen!

			Component c = null;
			for (Component item : allComponents) {
				String implCls = item.getImplementationClass();
				ArrayList<String> res = sp.getParts(implCls, "\\.");
				String cls = res.get(1);
				// System.out.println(cls);
				if (cmpClassName.equals(cls)) {
					c = item; // --- komponente zum anhängen der intenlist ---
					break;
				}
			}
			if (c == null) {
				System.out.println("[ERROR] Not found: " + cmpClassName);
				errors++;
				continue;
			}

			// ------- Methode (mit vorhanden check) -------

			EList<AnalysisInformation> aiLst = c.getAnalysisInformation();

			// suche Callgraph in Menge von Analysisinformationen
			Callgraph cg = null;
			for (AnalysisInformation item : aiLst) {
				if (item.getClass().getSimpleName().equals("CallgraphImpl")) {
					cg = (Callgraph) item;
					break;
				} else {
					System.out.println("Name: >"
							+ item.getClass().getSimpleName() + "<");
				}
			}

			EList<JavaMethod> allMethods = cg.getMethods();
			Boolean addMethod = true;
			JavaMethod method = null;
			// Prüfung: ist die Methode schon vorh
			for (JavaMethod item : allMethods) {
				if (item.getName().equals(communication.getMethod())) {
					method = item;
					addMethod = false;
				}
			}
			// TODO Nur zu testzwecken auf false
			addMethod = false;
			if (addMethod) {
				method = DeviceFactory.eINSTANCE.createJavaMethod();
				method.setName(communication.getMethod());

				for (AnalysisInformation item : c.getAnalysisInformation()) {
					if (item.getClass().getSimpleName().equals("CallgraphImpl")) {
						// hier hat man den Callgraphen
						((CallgraphImpl) item).getMethods().add(method);
						System.out.println("adding initial method: "
								+ communication.getMethod() + " in "
								+ cmpClassName);
					}
				}

				// c.getAnalysisInformation() getMethods().add(method); // ist
				// die Methode schon vorh.

			} else {
				System.out.println("allrdy added method: "
						+ communication.getMethod());
			}

			// ------- dynamic receiver -------
			if (communication.getType() != null
					&& !communication.getType().equals("")) {
				System.out.println("dynamic receiver found ("
						+ method.getName() + " in " + cmpClassName + ")");

				DynamicReceiver dynamicReceiver = DeviceFactory.eINSTANCE
						.createDynamicReceiver();
				dynamicReceiver.setRegistration(method);
				dynamicReceiver.setLabel(cmpClassName);
				device.getActiveApp().getComponents().add(dynamicReceiver);
			}

			/*
			 * aufruf über mehrere klassen hinweg - geht noch nicht richtig da
			 * man java klassen noch nicht erkennt sollte sowas wie der ursprung
			 * sein:
			 */

			System.out.println("method parameter: "
					+ communication.getParameter());
			System.out.println("");

			// ------- explicite -------
			if (communication.isExplicite()) {
				System.out.println("intent: explicit");

				for (ArrayList<String> iccValue : communication.getIcc()) {
					// scheme: package / class

					// TODO
					IntentList intentList = DeviceFactory.eINSTANCE
							.createIntentList();
					IntentCall intentCall = DeviceFactory.eINSTANCE
							.createIntentCall();
					intentCall.setCaller(method);

					ExplicitIntent intent = DeviceFactory.eINSTANCE
							.createExplicitIntent();

					// --- package ---
					String pkg = iccValue.get(0);
					System.out.println("package: " + pkg);

					// --- class ---
					String cls = iccValue.get(1).trim();
					cls = sp.getParts(cls, "/").get(1);
					cls = cls.split(" ")[0]; // ? split hinzugefügt ?

					System.out.println("cls: " + cls);
					intent.setComponent(cls);

					intentCall.setIntent(intent);

					// TODO --- callee ---
					EList<Component> componentLst2 = device.getActiveApp()
							.getComponents();
					Component c2 = null;
					for (Component item : allComponents) {
						if (!(item.getImplementationClass() == null)) {
							String[] s = item.getImplementationClass().split(
									"\\.");
							int l = s.length;
							if (s[l - 1].equals(cls)) {
								c2 = item;
								break;
							}
						}
					}
					if (!(c2 == null)) {
						intentCall.getCallee().add(c2);
						System.out.println("added callee: "
								+ c2.getImplementationClass());
					} else {
						System.out.println("no callee added: " + c2
								+ " - was looking for: >" + cls + "<");
					}

					// TODO --- caller ---
					System.out.println("caller: " + c.getLabel());
					JavaMethod caller = null;

					// suche methode
					System.out.println("Suche: " + communication.getMethod());
					for (AnalysisInformation item : c.getAnalysisInformation()) {
						if (item.getClass().getSimpleName()
								.equals("CallgraphImpl")) {
							// hier hat man den Callgraphen
							EList<JavaMethod> calleeMethods = ((CallgraphImpl) item)
									.getMethods();
							for (JavaMethod item_method : calleeMethods) {
								if (item_method.getName().contains(
										communication.getMethod())) {
									// hier hat den caller (die methode)
									caller = item_method;
									System.out.println("found caller: "
											+ caller.getName());
									break;
								}
							}
						}
					}

					intentCall.setCaller(caller);

					intentCallLst.add(intentCall);
					intentList.getCalls().add(intentCall);

					EList<AnalysisInformation> ai = c.getAnalysisInformation();
					if (ai.size() == 0) {
						c.getAnalysisInformation().add(intentList);
					} else {
						boolean done = false;
						// ist in der Liste der AI's schon nen IntentList?
						for (AnalysisInformation item : ai) {
							if (item.getClass().getSimpleName()
									.equals("IntentListImpl")) {
								System.out.println("Found Il: "
										+ item.getClass().getSimpleName());
								((IntentList) item).getCalls().add(intentCall);
								done = true;
								break;
							}
						}

						// wurde nicht gefunden
						if (!done) {
							intentList.getCalls().add(intentCall);
							c.getAnalysisInformation().add(intentList);
						}
					}
				}

				// ------- implicit -------
			} else {
				System.out.println("intent: implicit");
				System.out.println("");
				System.out.println(cmpClassName);
				for (ArrayList<String> iccValue : communication.getIcc()) {
					// scheme: action/category/extras

					IntentList intentList = DeviceFactory.eINSTANCE
							.createIntentList();
					IntentCall intentCall = DeviceFactory.eINSTANCE
							.createIntentCall();
					// TODO
					intentCall.setCaller(method);
					ImplicitIntent intent = DeviceFactory.eINSTANCE
							.createImplicitIntent();

					for (int i = 0; i < iccValue.size(); i++) {
						if (i == 0) {
							// --- action ---
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
							// --- category ---
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
							// --- extras ---
							String name = iccValue.get(i);

							if (name != null) {
								System.out.println("extras: " + name);
								intentCall.getExtras().put(name, name);
							}
						}
					}

					System.out.println("");
					intentCall.setIntent(intent);
					intentCallLst.add(intentCall);
					intentList.getCalls().add(intentCall);

					EList<AnalysisInformation> ai = c.getAnalysisInformation();
					if (ai.size() == 0) {
						System.out
								.println("liste noch leer im impliziten intent branch");
						c.getAnalysisInformation().add(intentList);
					} else {
						boolean done = false;
						System.out.println("else");
						// ist in der Liste der AI's schon nen IntentList?
						for (AnalysisInformation item : ai) {
							System.out.println(item.getClass().getSimpleName());
							if (item.getClass().getSimpleName()
									.equals("IntentListImpl")) {
								System.out.println("Found Il: "
										+ item.getClass().getSimpleName());
								((IntentList) item).getCalls().add(intentCall);
								done = true;
								break;
							}
						}

						// wurde nicht gefunden
						if (!done) {
							intentList.getCalls().add(intentCall);
							c.getAnalysisInformation().add(intentList);
						}
					}
				}
			}

			// System.out.println("got " + intentCallLst.size()
			// + " intent object(s)");
			System.out.println("");

			// add all Analysisinformation to the corresponding
			// if (c != null) {
			// for (IntentCall intentCall : intentCallLst) {
			// // c.getAnalysisInformation().add(intentCall);
			// }
			// }
		}

		System.out.println("");
		System.out.println(">>> " + errors + " Errors found <<<");

		/*
		 * neue resource erzeugen und unter neuem namen speichern, damit diesich
		 * nicht überschreiben/voll schreiben
		 */

		try {
			Map<String, String> saveOptions = new HashMap<>();
			saveOptions.put(XMIResource.OPTION_ENCODING, "UTF-8");
			modelResource.save(saveOptions);

			// final URI newModelURI = URI
			// .createFileURI(u);
			//
			// Resource newResource =
			// modelResourceSet.createResource(newModelURI);
			// modelResource.save(Collections.emptyMap());

		} catch (IOException e) {
			LOG.severe("Failed to save model." + e);
		}

		// nach dem abspeichern ist die ~.device ne "neue" datei
	}
}
