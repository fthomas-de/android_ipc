#!/usr/bin/env bash
java -jar /opt/epicc-0.1/epicc-0.1.jar -apk ~/Dokumente/BA/android_ipc/apps/AidlClient/app/app-release.apk -android-directory ~/Dokumente/BA/android_ipc/apps/AidlClient/dare_output/retargeted/app-release -cp /opt/epicc-0.1/android.jar -icc-study ~/Dokumente/BA/android_ipc/apps/AidlClient/
mv ~/Dokumente/BA/android_ipc/apps/AidlClient/app-release.txt ~/Dokumente/BA/android_ipc/icc_out/AidlClient.txt