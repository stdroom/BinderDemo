package com.lxbinder.demo;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by leixun on 17/11/15.
 */

abstract class UserStub extends Binder implements IUserAIDL {
    public UserStub(){
        this.attachInterface(this,DESCRIPTOR);
    }

    @Override
    public IBinder asBinder(){
        return this;
    }


    public static IUserAIDL asInterface(IBinder obj){
        if(obj == null){
            return null;
        }
        android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if(iin!=null && (iin instanceof IUserAIDL)){
            return (IUserAIDL)iin;
        }
        return new UserProxy(obj);
    }



    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case TRANSACTION_addUser:
                data.enforceInterface(DESCRIPTOR);
                long userId = data.readLong();
                String name = data.readString();
                reply.writeNoException();
                addUser(userId,name);
                return true;
            case TRANSACTION_getUserList:
                data.enforceInterface(DESCRIPTOR);
                java.util.List<User> _result = this.getUserList();
                reply.writeNoException();
                reply.writeTypedList(_result);
                return true;
            default:
                break;
        }
        return super.onTransact(code, data, reply, flags);
    }

}