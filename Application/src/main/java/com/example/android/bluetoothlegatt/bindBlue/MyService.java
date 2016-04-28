package com.example.android.bluetoothlegatt.bindBlue;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.example.android.bluetoothlegatt.service.ITaskBinder;
import com.example.android.bluetoothlegatt.service.ITaskCallback;

/**
 * Created by jinliang on 16-4-28.
 */
public class MyService extends Service {

    private static final String TAG = "aidltest";

    @Override
    public void onCreate() {
        printf("service create");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        printf("service start id=" + startId);
//        callback(startId);
    }

    @Override
    public IBinder onBind(Intent t) {
        printf("service on bind");
        return mBinder;

//        return mBinder;
    }

    @Override
    public void onDestroy() {
        printf("service on destroy");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        printf("service on unbind");
        return super.onUnbind(intent);
    }

    public void onRebind(Intent intent) {
        printf("service on rebind");

        super.onRebind(intent);
    }

    private void printf(String str) {
        Log.v(TAG, "###################------ " + str + "------");
    }

    void callback(String val) {
        final int N = mCallbacks.beginBroadcast();
        for (int i=0; i<N; i++) {
            try {
                mCallbacks.getBroadcastItem(i).actionPerformed(val);
            }
            catch (RemoteException e) {

            }
        }
        mCallbacks.finishBroadcast();
    }


    /**
     * mBinder绑定
     */
    private final ITaskBinder.Stub mBinder = new ITaskBinder.Stub() {



        public boolean isTaskRunning() {

            return false;
        }


        @Override
        public void startReBackData(String data) throws RemoteException {
            Log.i(TAG, "startReBackData: ");
            toClientData(data);
        }

        public void registerCallback(ITaskCallback cb) {
            if (cb != null) {
                mCallbacks.register(cb);
            }
        }

        public void unregisterCallback(ITaskCallback cb) {
            if(cb != null) {
                mCallbacks.unregister(cb);
            }
        }
    };

    // 回调
    final RemoteCallbackList<ITaskCallback> mCallbacks = new RemoteCallbackList <ITaskCallback>();


    private void toClientData(String data){


        final int N = mCallbacks.beginBroadcast();
        Log.i(TAG, "toClientData: beginBroadCastSize:"+ N);
        for (int i=0; i<N; i++) {
            try {
                mCallbacks.getBroadcastItem(i).actionPerformed("Server处理数据:"+ (Integer.parseInt(data)+10));
            }
            catch (RemoteException e) {

            }
        }
        mCallbacks.finishBroadcast();

    }
}
