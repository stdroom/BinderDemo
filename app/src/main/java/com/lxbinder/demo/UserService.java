package com.lxbinder.demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leixun on 17/11/15.
 */

public class UserService extends Service {
    private ArrayList<User> mUsers = new ArrayList<>();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private UserStub mBinder  = new UserStub() {
        @Override
        public void addUser(long userId,String userName) throws RemoteException {
            Log.d("UserService",":"+mUsers.size());
            mUsers.add(new User(userId,userName));
        }

        @Override
        public List<User> getUserList() throws RemoteException {
            return mUsers;
        }
    };
}
