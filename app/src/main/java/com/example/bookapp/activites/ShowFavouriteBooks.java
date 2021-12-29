package com.example.bookapp.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bookapp.Adapters.AllBookAdapter;
import com.example.bookapp.MyDataBase;
import com.example.bookapp.R;
import com.example.bookapp.models.Book;
import com.example.bookapp.models.Category;

import java.util.ArrayList;

public class ShowFavouriteBooks extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_favourite_books);
        int favValue;
        MyDataBase myDataBase=new MyDataBase(ShowFavouriteBooks.this);
        RecyclerView rv =findViewById(R.id.RV_all_favBooks);
        Book book=new Book();
        if(book.isFavourite()){
            favValue=0;
        }else{
            favValue=1;
        }
        ArrayList<Book> books=myDataBase.getfavouriteBooks(favValue);
        AllBookAdapter myadapter=new AllBookAdapter(books);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(myadapter);
    }
}