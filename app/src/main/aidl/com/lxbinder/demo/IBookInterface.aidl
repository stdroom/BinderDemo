// IBookInterface.aidl
package com.lxbinder.demo;
import com.lxbinder.demo.Book;
// Declare any non-default types here with import statements

interface IBookInterface {
    void addBook(long bookId,String bookName);
    List<Book> getBook();
}
