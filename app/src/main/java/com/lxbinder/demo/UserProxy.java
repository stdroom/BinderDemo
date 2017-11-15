package com.lxbinder.demo;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import java.util.List;

/**
 * Created by leixun on 17/11/15.
 */

public class UserProxy implements IUserAIDL {
    private IBinder mRemote;

    UserProxy(IBinder remote)
    {
        mRemote = remote;
    }

    @Override
    public IBinder asBinder(){
        return null;
    }

    @Override
    public void addUser(long userId,String userName) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken(DESCRIPTOR);
            data.writeLong(userId);
            data.writeString(userName);
            mRemote.transact(IUserAIDL.TRANSACTION_addUser, data, reply, 0);
            reply.readException();
        }
        finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override
    public List<User> getUserList() throws RemoteException {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.util.List<User> _result;
        try {
            _data.writeInterfaceToken(DESCRIPTOR);
            mRemote.transact(TRANSACTION_getUserList, _data, _reply, 0);
            _reply.readException();
            _result = _reply.createTypedArrayList(User.CREATOR);
        }
        finally {
            _reply.recycle();
            _data.recycle();
        }
        return _result;
    }
}
