package com.example.android.bluetoothlegatt.service;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattServerCallback;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import com.example.android.bluetoothlegatt.idata.IBlueData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jinliang on 16-4-19.
 */
public class BLEServerProDB extends Activity {

    private static final String characteristicUUID = "";
    private BluetoothGattCharacteristic character;
    private BluetoothManager bluetoothManager;

    private BluetoothGattService service;
    private static final String serviceUUID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 创建 bluetoothServer 蓝牙服务器端知识
         */
        // step1:创建一个特征
        BluetoothGattCharacteristic character = new BluetoothGattCharacteristic(
                UUID.fromString(characteristicUUID),BluetoothGattCharacteristic.PROPERTY_NOTIFY,
                BluetoothGattCharacteristic.PERMISSION_READ);



        // step2 创建一个server
        service = new BluetoothGattService(UUID.fromString(serviceUUID),
                BluetoothGattService.SERVICE_TYPE_PRIMARY);

        service.addCharacteristic(character);

//        service.addService(newService)  也可以加入一个新的server

        //step3  获取blueManager

        bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);



       final  List<BluetoothDevice>  connectedDevices  = new ArrayList<>();

        BluetoothGattServer gattServer = bluetoothManager.openGattServer(this, new BluetoothGattServerCallback() {
            @Override
            public void onConnectionStateChange(BluetoothDevice device, int status, int newState) {

                connectedDevices.add(device);
                super.onConnectionStateChange(device, status, newState);
            }

            @Override
            public void onServiceAdded(int status, BluetoothGattService service) {

                // TODO: 16-4-22 添加设备


                super.onServiceAdded(status, service);
            }

            @Override
            public void onCharacteristicReadRequest(BluetoothDevice device, int requestId, int offset, BluetoothGattCharacteristic characteristic) {
                super.onCharacteristicReadRequest(device, requestId, offset, characteristic);

            }

            /**
             * 读取写入的数据信息
             * @param device
             * @param requestId
             * @param characteristic
             * @param preparedWrite
             * @param responseNeeded
             * @param offset
             * @param value
             */
            @Override
            public void onCharacteristicWriteRequest(BluetoothDevice device, int requestId, BluetoothGattCharacteristic characteristic, boolean preparedWrite, boolean responseNeeded, int offset, byte[] value) {


                super.onCharacteristicWriteRequest(device, requestId, characteristic, preparedWrite, responseNeeded, offset, value);
            }

            @Override
            public void onDescriptorReadRequest(BluetoothDevice device, int requestId, int offset, BluetoothGattDescriptor descriptor) {
                super.onDescriptorReadRequest(device, requestId, offset, descriptor);
            }

            @Override
            public void onDescriptorWriteRequest(BluetoothDevice device, int requestId, BluetoothGattDescriptor descriptor, boolean preparedWrite, boolean responseNeeded, int offset, byte[] value) {

                super.onDescriptorWriteRequest(device, requestId, descriptor, preparedWrite, responseNeeded, offset, value);
            }

            @Override
            public void onExecuteWrite(BluetoothDevice device, int requestId, boolean execute) {
                super.onExecuteWrite(device, requestId, execute);
            }

            @Override
            public void onNotificationSent(BluetoothDevice device, int status) {
                super.onNotificationSent(device, status);
            }

            @Override
            public void onMtuChanged(BluetoothDevice device, int mtu) {
                super.onMtuChanged(device, mtu);
            }
        });


        gattServer.addService(service);


        // BluetoothDevice device,  BluetoothGattCharacteristic characteristic, boolean confirm  jinxing
//        gattServer.notifyCharacteristicChanged(connectedDevices.get(0),,false);
//        gattServer.notifyCharacteristicChanged()



//        gattServer.notifyCharacteristicChanged();

        /**
         *   GattServer  中比较关键的调用方法。
         */
//        gattServer.connect();
//        gattServer.cancelConnection();
//        gattServer.getConnectedDevices();
//        gattServer.getService();
//
//        gattServer.sendResponse();
//
//        gattServer.getConnectionState();





    }


    /**
     * 中央设备的影响过程
     */
    private void initBluetoothGatt(){

        //step01
        BluetoothManager bluetoothManager =(BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);

        //step02
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();

        // 一样获取adapter
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        //step03   搜索设备
        /**
         * 第一套方案
         */
        bluetoothAdapter.startLeScan(new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {

            }
        });

        /**
         * 第二套方案
         */
        UUID[] uuids = new UUID[2];
        uuids[0] = UUID.fromString("01");

        // 假设搜索到的device设备
        final List<BluetoothDevice> listDevices = new ArrayList<>();
        bluetoothAdapter.startLeScan(uuids, new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
                 listDevices.add(device);
            }
        });



        //
        BluetoothDevice currentConnectedDevice = listDevices.get(0);
        try {
            currentConnectedDevice.createRfcommSocketToServiceRecord(UUID.fromString("huhu"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        currentConnectedDevice.connectGatt(this, false, new BluetoothGattCallback() {
            @Override
            public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                super.onConnectionStateChange(gatt, status, newState);
            }

            @Override
            public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                super.onServicesDiscovered(gatt, status);
            }

            @Override
            public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                super.onCharacteristicRead(gatt, characteristic, status);

                BluetoothGattService  gattService =   gatt.getService(UUID.fromString(""));

                BluetoothGattCharacteristic characteristic1 =   gattService.getCharacteristic(UUID.fromString(""));

                characteristic1.setValue("huafei".getBytes());

                // client to server 端写数据
                gatt.writeCharacteristic(characteristic1);

            }

            @Override
            public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {

                /**
                 *   BluetoothGattCharacteristy : 特征中的描述.
                 *
                 */

                // 获取传输的数据的字节
                byte[] bytes= characteristic.getValue();

                // 获取描述集合信息  从特征中获取描述集合
                List<BluetoothGattDescriptor>  bluetoothGattDescriptorList = characteristic.getDescriptors();


                 characteristic.setValue("");
                characteristic.getDescriptor(UUID.fromString(""));

                //向特征中添加描述   可以添加多个的描述
                characteristic.addDescriptor(new BluetoothGattDescriptor(UUID.fromString(""),BluetoothGattDescriptor.PERMISSION_WRITE));


                /**
                 * 测试描述的方法信息
                 */
                BluetoothGattDescriptor descriptor = new BluetoothGattDescriptor(UUID.fromString(""), BluetoothGattDescriptor.PERMISSION_READ);


                // 获取描述所属的特征
                descriptor.getCharacteristic() ;

                descriptor.getValue();
                descriptor.getPermissions();
                descriptor.getUuid();
                //
                descriptor.setValue(new String("").getBytes());

                try {
                    descriptor.wait(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.onCharacteristicWrite(gatt, characteristic, status);
            }

            @Override
            public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                super.onCharacteristicChanged(gatt, characteristic);
            }

            @Override
            public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                super.onDescriptorRead(gatt, descriptor, status);
            }

            @Override
            public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                super.onDescriptorWrite(gatt, descriptor, status);
            }

            @Override
            public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
                super.onReliableWriteCompleted(gatt, status);
            }

            @Override
            public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
                super.onReadRemoteRssi(gatt, rssi, status);
            }

            @Override
            public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
                super.onMtuChanged(gatt, mtu, status);
            }
        });





    }


    private void  bluetoothOPTFileTransfer(IBlueData iBlueData){

        BluetoothGattServer gattServer = bluetoothManager.openGattServer(this, new BluetoothGattServerCallback() {
            @Override
            public void onConnectionStateChange(BluetoothDevice device, int status, int newState) {


                super.onConnectionStateChange(device, status, newState);
            }

            @Override
            public void onServiceAdded(int status, BluetoothGattService service) {

                // TODO: 16-4-22 添加设备


                super.onServiceAdded(status, service);
            }

            @Override
            public void onCharacteristicReadRequest(BluetoothDevice device, int requestId, int offset, BluetoothGattCharacteristic characteristic) {
                super.onCharacteristicReadRequest(device, requestId, offset, characteristic);


            }

            /**
             * 读取写入的数据信息
             * @param device
             * @param requestId
             * @param characteristic
             * @param preparedWrite
             * @param responseNeeded
             * @param offset
             * @param value
             */
            @Override
            public void onCharacteristicWriteRequest(BluetoothDevice device, int requestId, BluetoothGattCharacteristic characteristic, boolean preparedWrite, boolean responseNeeded, int offset, byte[] value) {


                super.onCharacteristicWriteRequest(device, requestId, characteristic, preparedWrite, responseNeeded, offset, value);
            }

            @Override
            public void onDescriptorReadRequest(BluetoothDevice device, int requestId, int offset, BluetoothGattDescriptor descriptor) {
                super.onDescriptorReadRequest(device, requestId, offset, descriptor);
            }

            @Override
            public void onDescriptorWriteRequest(BluetoothDevice device, int requestId, BluetoothGattDescriptor descriptor, boolean preparedWrite, boolean responseNeeded, int offset, byte[] value) {

                super.onDescriptorWriteRequest(device, requestId, descriptor, preparedWrite, responseNeeded, offset, value);
            }

            @Override
            public void onExecuteWrite(BluetoothDevice device, int requestId, boolean execute) {
                super.onExecuteWrite(device, requestId, execute);
            }

            @Override
            public void onNotificationSent(BluetoothDevice device, int status) {
                super.onNotificationSent(device, status);
            }

            @Override
            public void onMtuChanged(BluetoothDevice device, int mtu) {
                super.onMtuChanged(device, mtu);
            }
        });




    }
}
