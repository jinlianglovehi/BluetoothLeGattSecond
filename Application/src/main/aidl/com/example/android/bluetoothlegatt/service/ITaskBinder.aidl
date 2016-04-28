// ITaskBinder.aidl
package com.example.android.bluetoothlegatt.service;

// Declare any non-default types here with import statements
import com.example.android.bluetoothlegatt.service.ITaskCallback;
interface ITaskBinder {

        boolean isTaskRunning();
        void startReBackData();
        void registerCallback(in ITaskCallback cb);
        void unregisterCallback(in ITaskCallback cb);

}
