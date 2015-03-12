#!/usr/bin/env bash
java -jar /opt/epicc-0.1/epicc-0.1.jar -apk ~/git/android_ipc/apps/RandomIntent/app/app-release.apk -android-directory ~/git/android_ipc/apps/RandomIntent/dare_output/retargeted/app-release -cp /opt/epicc-0.1/android.jar -icc-study ~/git/android_ipc/apps/RandomIntent/
mv ~/git/android_ipc/apps/RandomIntent/app-release.txt ~/git/android_ipc/icc_out/RandomIntent.txt