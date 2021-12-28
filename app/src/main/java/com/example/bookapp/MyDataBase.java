package com.example.bookapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.bookapp.models.Book;
import com.example.bookapp.models.Category;

import java.util.ArrayList;
public class MyDataBase extends SQLiteOpenHelper {
    private static String db_name="Book.db";
    private static int db_verison=2;
    private static String book_tabel="BOOKS";
    private static String category_tabel="CATEGORY";
    Context context;

    public MyDataBase(@Nullable Context context) {
        super(context, db_name, null, db_verison);
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String bookQuery="CREATE TABLE "+book_tabel+" (id INTEGER PRIMARY KEY AUTOINCREMENT ,CATEGORYNAME STRING,BOOKNAME STRING,AUTHORNAME STRING,RELEASEYEAR INTERGER,PAGENUMBER INTGER,BOOKIMAGE BLOB)";
        String categoryQuery="CREATE TABLE "+category_tabel+" (CATEGORYID INTEGER PRIMARY KEY AUTOINCREMENT ,CATEGORYNAME STRING)";
        sqLiteDatabase.execSQL(bookQuery);
        sqLiteDatabase.execSQL(categoryQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String bookQuery="DROP TABLE IF EXISTS "+book_tabel;
        String categoryQuery="DROP TABLE IF EXISTS "+category_tabel;
        sqLiteDatabase.execSQL(bookQuery);
        sqLiteDatabase.execSQL(categoryQuery);
    }
    public void cretaeCategroy(Category category){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("CATEGORYNAME",category.getCategoryName());
        long result= db.insert(category_tabel,null,cv);
        if(result!=-1){
            Toast.makeText(context, "Category added successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Failed to add Category", Toast.LENGTH_SHORT).show();
        }
    }
    public ArrayList<Category> getAllCategroy() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM "+category_tabel;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id=cursor.getInt(0);
            String categroyName=cursor.getString(1);
            Category c = new Category(id,categroyName);
            categories.add(c);
            cursor.moveToNext();
        }
        return categories;
    }
    public void cretaeBook(Book book){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
       // cv.put("id",book.getId());
        cv.put("BOOKNAME",book.getBookName());
        cv.put("AUTHORNAME",book.getAuthoName());
        cv.put("RELEASEYEAR",book.getRelaesYesrs());
        cv.put("PAGENUMBER",book.getPageNumber());
        cv.put("BOOKIMAGE",book.getImage());
       long result= db.insert(book_tabel,null,cv);
       if(result!=-1){
           Toast.makeText(context, "Book added successfully", Toast.LENGTH_SHORT).show();
       }else{
           Toast.makeText(context, "Failed to add book", Toast.LENGTH_SHORT).show();
       }
    }
    public void updateBook(Book book) {
        boolean result=true;
        SQLiteDatabase db =getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("BOOKNAME",book.getBookName());
        cv.put("AUTHORNAME",book.getAuthoName());
        cv.put("RELEASEYEAR",book.getRelaesYesrs());
        cv.put("PAGENUMBER",book.getPageNumber());
        cv.put("BOOKIMAGE",book.getImage());
        String args[] ={book.getId()+""};
        db.update(book_tabel,cv,"id=?",args);
    }
    public ArrayList<Book> getAllBooks() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Book> books = new ArrayList<>();
        String query = "SELECT * FROM "+book_tabel;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
             int id=cursor.getInt(0);
             String bookName=cursor.getString(1);
             int relaesYesrs=cursor.getInt(2);
             String authoName=cursor.getString(3);
             int pageNumber=cursor.getInt(4);
            Book b = new Book(bookName,relaesYesrs,authoName,pageNumber);
            books.add(b);
            cursor.moveToNext();
        }
        return books;
    }
    public void deleteBook(String id) {
        SQLiteDatabase db = getWritableDatabase();
        String args[] ={id};
        db.delete(book_tabel,"id=?",args);
    }
//    public void deleteAllBooks() {
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("DELETE FROM "+table_name+";");
//    }
}
