package com.example.bookapp.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bookapp.Adapters.AllBookAdapter;
import com.example.bookapp.MyDataBase;
import com.example.bookapp.R;

public class ShowAllBooksActicity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_books);
        MyDataBase myDataBase=new MyDataBase(ShowAllBooksActicity.this);
        RecyclerView rv =findViewById(R.id.RV_all_Book);
        AllBookAdapter myadapter=new AllBookAdapter(myDataBase.getAllBooks(),getApplicationContext());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(myadapter);
    }
}