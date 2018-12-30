// IBookManager.aidl
package cn.stu.cusview.ruiz.customeviewdemo.aidl;

// Declare any non-default types here with import statements

import cn.stu.cusview.ruiz.customeviewdemo.aidl.Book;

interface IBookManager {
   List<Book> getbooks();
    void addBook(in Book book);
}
