#!/usr/bin/env bash
java -Xmx4g -jar /opt/epicc-0.1/epicc-0.1.jar -apk ~/git/android_ipc/apps/#facebook/app/app-release.apk -android-directory ~/git/android_ipc/apps/#facebook/dare_output/retargeted/app-release -cp /opt/epicc-0.1/android.jar -icc-study ~/git/android_ipc/apps/#facebook/
mv ~/git/android_ipc/apps/#facebook/app-release.txt ~/git/android_ipc/icc_out/#facebook.txt