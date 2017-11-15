package com.lxbinder.demo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by leixun on 17/11/15.
 */

public class Book implements Parcelable{
    private long bookId;
    private String bookName;


    @Override
    public int describeContents() {
        return 0;
    }

    public Book(Parcel in){
        bookId = in.readLong();
        bookName = in.readString();
    }

    public Book(long bookId,String bookName){
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public static Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel parcel) {
            return new Book(parcel);
        }

        @Override
        public Book[] newArray(int i) {
            return new Book[i];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(bookId);
        parcel.writeString(bookName);
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
