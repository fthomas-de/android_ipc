package ba_parse;

import java.util.ArrayList;

// zu testzecken
public class Main {

	public static void main(String[] args) {
		
		String path = "/home/fthomas/git/android_ipc/epicc_out/";
		String file;
	
		file = "ACT_Android";
//		file = "AIDL";
//		file = "AidlClient";
//		file = "AidlServer";
//		file = "BroadcastReceiver";
//		file = "BroadcastReceiverWithPermission2";
//		file = "IntentFilter";
//		file = "PendingIntent4";
//		file = "RandomIntent";
//		file = "SendBroadcast";
//		file = "SendBroadcastWithPermission";
//		file = "StartActivity2";
//		file = "StartBinder";
//		file = "StartLinkedActivity";
//		file = "StartService";
//		file = "StartLinkedActivity2"; //TODO quelle des verlinkten aufrufs (util)
//		file = "ViewImageViaIntent";
		
		FileParser fp = new FileParser(path + file + ".txt");
		ArrayList<Activity> activities = fp.getActivities();
		ArrayList<Service> services = fp.getServices();
		ArrayList<Receiver> receiver = fp.getReceiver();
		ArrayList<Communication> communication = fp.getCommunications();

		System.out.println("Activities: " + activities);
		System.out.println("Services: " + services);
		System.out.println("Receiver: " + receiver);
		System.out.println("Commnunications: " + communication);
	}
}
