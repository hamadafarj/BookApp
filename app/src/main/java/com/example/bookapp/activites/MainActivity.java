package com.example.bookapp.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bookapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createBook,createCategory,libaray,favirite;
        createBook=findViewById(R.id.btn_CreateBook);
        createCategory=findViewById(R.id.btn_CreateCategory);
        libaray=findViewById(R.id.btn_libary);
        createBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), CreateBookActicvity.class);
                startActivity(intent);
            }
        });
        createCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), CreateCategoryActivity.class);
                startActivity(intent);
            }
        });
        libaray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent=new Intent(getApplicationContext(), ShowAllBooksActicity.class);
                Intent intent=new Intent(getApplicationContext(), ShowAllCategory.class);
                startActivity(intent);
            }
        });
    }
}