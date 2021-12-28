package com.example.bookapp.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bookapp.Adapters.AllBookAdapter;
import com.example.bookapp.Adapters.AllCategoryAdapter;
import com.example.bookapp.MyDataBase;
import com.example.bookapp.R;

public class ShowAllCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_category);
        MyDataBase myDataBase=new MyDataBase(ShowAllCategory.this);
        RecyclerView rv =findViewById(R.id.RV_all_Category);
        AllCategoryAdapter myadapter=new AllCategoryAdapter(myDataBase.getAllCategroy(),getApplicationContext());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(myadapter);
    }
}