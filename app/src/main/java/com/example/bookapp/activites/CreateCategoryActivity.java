package com.example.bookapp.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bookapp.MyDataBase;
import com.example.bookapp.R;
import com.example.bookapp.models.Category;

public class CreateCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);
        EditText categoryName=findViewById(R.id.editText_CategoryName);
        Button button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDataBase myDataBase=new MyDataBase(CreateCategoryActivity.this);
                String CategoryName=categoryName.getText().toString();
                Log.d("hamada",CategoryName);
                Category category=new Category(CategoryName);
                myDataBase.cretaeCategroy(category);
            }
        });
    }
}