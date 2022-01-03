package com.example.bookapp;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.bookapp.activites.MainActivity;
import com.example.bookapp.activites.ShowAllBooksActicity;
import com.example.bookapp.models.Book;
import com.example.bookapp.models.Category;

import java.util.ArrayList;
public class MyDataBase extends SQLiteOpenHelper {
    private static String db_name="Book.db";
    private static int db_verison=4;
    private static String book_tabel="BOOKS";
    private static String category_tabel="CATEGORY";
    Context context;

    public MyDataBase(@Nullable Context context) {
        super(context, db_name, null, db_verison);
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String bookQuery="CREATE TABLE "+book_tabel+" (id INTEGER PRIMARY KEY AUTOINCREMENT ,CATEGORYNAME STRING,BOOKNAME STRING,AUTHORNAME STRING,RELEASEYEAR INTERGER,PAGENUMBER INTGER,BOOKIMAGE BLOB,FAVOURITE BOOLEAN )";
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
            Intent intent=new Intent(context, MainActivity.class);
            context.startActivity(intent);
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

    public ArrayList<String> getAllCategroyName() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> categories = new ArrayList<>();
        String query = "SELECT * FROM "+category_tabel;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String categroyName=cursor.getString(1);
            Category c = new Category(categroyName);
            categories.add(c.getCategoryName());
            cursor.moveToNext();
        }
        return categories;
    }
    public void cretaeBook(Book book){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("BOOKNAME",book.getBookName());
        cv.put("AUTHORNAME",book.getAuthoName());
        cv.put("RELEASEYEAR",book.getRelaesYesrs());
        cv.put("PAGENUMBER",book.getPageNumber());
        cv.put("BOOKIMAGE",book.getImage());
        cv.put("CATEGORYNAME",book.getCategoryName());
        cv.put("FAVOURITE",book.isFavourite());
       long result= db.insert(book_tabel,null,cv);
       if(result!=-1){
           Toast.makeText(context, "Book added successfully", Toast.LENGTH_SHORT).show();
           Intent intent=new Intent(context, MainActivity.class);
           context.startActivity(intent);
       }else{
           Toast.makeText(context, "Failed to add book", Toast.LENGTH_SHORT).show();
       }
    }
    public void updateBook(Book book) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("CATEGORYNAME",book.getCategoryName());
        cv.put("BOOKNAME",book.getBookName());
        cv.put("AUTHORNAME",book.getAuthoName());
        cv.put("RELEASEYEAR",book.getRelaesYesrs());
        cv.put("PAGENUMBER",book.getPageNumber());
        cv.put("BOOKIMAGE",book.getImage());
        cv.put("FAVOURITE",book.isFavourite());
        String args[] ={book.getId()+""};
        long result= db.update(book_tabel,cv,"id=?",args);
        if(result!=-1){
            Toast.makeText(context, "Book update successfully", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }else{
            Toast.makeText(context, "Failed to update book", Toast.LENGTH_SHORT).show();
        }
    }
    public ArrayList<Book> getAllBooks(String cat_Name) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Book> books = new ArrayList<>();
        String query = "SELECT * FROM "+book_tabel+" WHERE CATEGORYNAME = '"+cat_Name+"'";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
             int id=cursor.getInt(0);
             String cat=cursor.getString(1);
             String bookName=cursor.getString(2);
             String authoName=cursor.getString(3);
             int relaesYesrs=cursor.getInt(4);
             int pageNumber=cursor.getInt(5);
             byte[] imageContent=cursor.getBlob(6);
             boolean fav=Boolean.parseBoolean(cursor.getString(7));
            Book b = new Book(bookName,relaesYesrs,authoName,pageNumber,cat,fav);
            b.setImage(imageContent);
            books.add(b);
            b.setId(id);
            cursor.moveToNext();
        }
        return books;
    }
    public ArrayList<Book> getfavouriteBooks(int favValue) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Book> books = new ArrayList<>();
        String query = "SELECT * FROM "+book_tabel+" WHERE FAVOURITE = '"+favValue+"'";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id=cursor.getInt(0);
            String cat=cursor.getString(1);
            String bookName=cursor.getString(2);
            String authoName=cursor.getString(3);
            int relaesYesrs=cursor.getInt(4);
            int pageNumber=cursor.getInt(5);
            byte[] imageContent=cursor.getBlob(6);
            boolean fav=Boolean.parseBoolean(cursor.getString(7));
            Book b = new Book(bookName,relaesYesrs,authoName,pageNumber,cat,fav);
            b.setImage(imageContent);
            books.add(b);
            cursor.moveToNext();
        }
        return books;
    }
    public long deleteBook(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String args[] ={id+""};
        long result=db.delete(book_tabel,"id=?",args);
       return result;
    }
}
