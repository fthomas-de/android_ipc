#!/usr/bin/env bash
java -Xmx8g -jar /opt/epicc-0.1/epicc-0.1.jar -apk ~/git/android_ipc/apps/#whatsapp/app/app-release.apk -android-directory ~/git/android_ipc/apps/#whatsapp/dare_output/retargeted/app-release -cp /opt/epicc-0.1/android.jar -icc-study ~/git/android_ipc/apps/#whatsapp/
mv ~/git/android_ipc/apps/#whatsapp/app-release.txt ~/git/android_ipc/icc_out/#whatsapp.txt
