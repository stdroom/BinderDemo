package com.lxbinder.demo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by leixun on 17/11/15.
 */

public class User implements Parcelable{

    private long userId;
    private String userName;

    public User(long userId,String userName){
        this.userId = userId;
        this.userName = userName;
    }

    public User(Parcel in){
        userId = in.readLong();
        userName = in.readString();
    }

    public static Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel parcel) {
            return new User(parcel);
        }

        @Override
        public User[] newArray(int i) {
            return new User[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(userId);
        parcel.writeString(userName);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
