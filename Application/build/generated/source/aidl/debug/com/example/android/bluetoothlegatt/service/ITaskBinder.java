/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/work/workspace/huami/mywearcompation/BluetoothLeGattSecond/Application/src/main/aidl/com/example/android/bluetoothlegatt/service/ITaskBinder.aidl
 */
package com.example.android.bluetoothlegatt.service;
public interface ITaskBinder extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.example.android.bluetoothlegatt.service.ITaskBinder
{
private static final java.lang.String DESCRIPTOR = "com.example.android.bluetoothlegatt.service.ITaskBinder";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.example.android.bluetoothlegatt.service.ITaskBinder interface,
 * generating a proxy if needed.
 */
public static com.example.android.bluetoothlegatt.service.ITaskBinder asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.example.android.bluetoothlegatt.service.ITaskBinder))) {
return ((com.example.android.bluetoothlegatt.service.ITaskBinder)iin);
}
return new com.example.android.bluetoothlegatt.service.ITaskBinder.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_isTaskRunning:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.isTaskRunning();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_startReBackData:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.startReBackData(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_registerCallback:
{
data.enforceInterface(DESCRIPTOR);
com.example.android.bluetoothlegatt.service.ITaskCallback _arg0;
_arg0 = com.example.android.bluetoothlegatt.service.ITaskCallback.Stub.asInterface(data.readStrongBinder());
this.registerCallback(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_unregisterCallback:
{
data.enforceInterface(DESCRIPTOR);
com.example.android.bluetoothlegatt.service.ITaskCallback _arg0;
_arg0 = com.example.android.bluetoothlegatt.service.ITaskCallback.Stub.asInterface(data.readStrongBinder());
this.unregisterCallback(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.example.android.bluetoothlegatt.service.ITaskBinder
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public boolean isTaskRunning() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isTaskRunning, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void startReBackData(java.lang.String data) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(data);
mRemote.transact(Stub.TRANSACTION_startReBackData, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void registerCallback(com.example.android.bluetoothlegatt.service.ITaskCallback cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void unregisterCallback(com.example.android.bluetoothlegatt.service.ITaskCallback cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_isTaskRunning = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_startReBackData = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_registerCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_unregisterCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
}
public boolean isTaskRunning() throws android.os.RemoteException;
public void startReBackData(java.lang.String data) throws android.os.RemoteException;
public void registerCallback(com.example.android.bluetoothlegatt.service.ITaskCallback cb) throws android.os.RemoteException;
public void unregisterCallback(com.example.android.bluetoothlegatt.service.ITaskCallback cb) throws android.os.RemoteException;
}
