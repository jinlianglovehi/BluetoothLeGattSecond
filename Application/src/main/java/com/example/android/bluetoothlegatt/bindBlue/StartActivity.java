package com.example.android.bluetoothlegatt.bindBlue;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.bluetoothlegatt.R;
import com.example.android.bluetoothlegatt.service.ITaskBinder;
import com.example.android.bluetoothlegatt.service.ITaskCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jinliang on 16-4-28.
 */
public class StartActivity extends Activity {
    private final static String TAG = StartActivity.class.getName();
    @Bind(R.id.startService)
    EditText startService;
    @Bind(R.id.reback_message)
    TextView rebackMessage;
    @Bind(R.id.hand_result)
    TextView handResult;

    private ITaskBinder mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.test_binder_callback);
        ButterKnife.bind(this);
        Intent intent = new Intent(this, MyService.class);

        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

                Log.i(TAG, "onServiceConnected: ");
                mService = ITaskBinder.Stub.asInterface(service);
                registerCallBack();

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);


    }


    @OnClick(R.id.reback_message)
    public void reBackData() {

        try {
            if (mService != null) {
                Log.i(TAG, "reBackData: to Service Message");
//                String result = startService.getText().toString();
                mService.startReBackData(startService.getText().toString().trim());
            } else {
                Log.i(TAG, "reBackData: mService为空 ");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

    // 注册CallBak 实例
    private void registerCallBack() {

        try {
            if (mService != null) {
                mService.registerCallback(callback);
                Log.i(TAG, "registerMessage: 消息注册成功 ");
            } else {
                Log.i(TAG, "registerCallBack: 注册失败");
            }


        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    // 取消注册


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mService != null) {
            unRegisterCallBack();
        }

        ButterKnife.unbind(this);
    }

    private void unRegisterCallBack() {
        try {

            if (mService != null) {
                Log.i(TAG, "unRegisterCallBack: mService 取消实例");
                mService.unregisterCallback(callback);
            } else {

            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private ITaskCallback callback = new ITaskCallback.Stub() {
        @Override
        public void actionPerformed(String actionId) throws RemoteException {

            handResult.setText(actionId);
            Log.i(TAG, "actionPerformed: Server ReBackNum:" + actionId);
        }
    };


}
