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

public class ShowAllBooksActicity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_books);
        Intent intent=getIntent();
        Category category=(Category) intent.getSerializableExtra("cat");
        Toast.makeText(this, category.getCategoryName(), Toast.LENGTH_SHORT).show();
        MyDataBase myDataBase=new MyDataBase(ShowAllBooksActicity.this);
        RecyclerView rv =findViewById(R.id.RV_all_Book);
        ArrayList<Book>books=myDataBase.getAllBooks(category.getCategoryName());
        AllBookAdapter myadapter=new AllBookAdapter(books);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(myadapter);
    }
}