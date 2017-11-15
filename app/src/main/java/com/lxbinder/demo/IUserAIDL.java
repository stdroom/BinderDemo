package com.lxbinder.demo;

import android.os.RemoteException;

import java.util.List;

/**
 * Created by leixun on 17/11/15.
 */

interface IUserAIDL extends android.os.IInterface{
    final String DESCRIPTOR = "com.lxbinder.demo.IUserAIDL";
    int TRANSACTION_addUser = 0;
    int TRANSACTION_getUserList = 1;
    void addUser(long userId,String userName) throws RemoteException;
    List<User> getUserList() throws RemoteException;
}
