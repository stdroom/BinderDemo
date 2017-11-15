package com.lxbinder.demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leixun on 17/11/15.
 */

public class BookService extends Service{
    private ArrayList<Book> books = new ArrayList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private IBookInterface.Stub mBinder = new IBookInterface.Stub() {
        @Override
        public void addBook(long bookId, String bookName) throws RemoteException {
            books.add(new Book(bookId,bookName));
        }

        @Override
        public List<Book> getBook() throws RemoteException {
            return books;
        }
    };

}
