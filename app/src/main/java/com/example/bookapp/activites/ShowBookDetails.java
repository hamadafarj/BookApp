package com.example.bookapp.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookapp.MyDataBase;
import com.example.bookapp.R;
import com.example.bookapp.models.Book;
import com.example.bookapp.models.Category;

public class ShowBookDetails extends AppCompatActivity {
    public static Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book_details);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.delete_icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView bookName, authorName, relaseYear, pageNumber, category;
        Button button;
        ImageView book_Img;
        Bitmap imageContent;
        bookName = findViewById(R.id.BookNameTxt);
        authorName = findViewById(R.id.AuthorNameTxt);
        relaseYear = findViewById(R.id.RelaseYearTxt);
        pageNumber = findViewById(R.id.PageNumberTxt);
        category = findViewById(R.id.CategoryNameTxt);
        book_Img = findViewById(R.id.imageView);
        button = findViewById(R.id.button2);


        bookName.setText(book.getBookName());
        authorName.setText(book.getAuthoName());
        relaseYear.setText(book.getRelaesYesrs()+"");
        pageNumber.setText(book.getPageNumber()+"");
         category.setText(book.getCategoryName());
        imageContent= BitmapFactory.decodeByteArray(book.getImage(),0,book.getImage().length);
        book_Img.setImageBitmap(imageContent);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent=new Intent(getApplicationContext(),EditBookDetails.class);
                EditBookDetails.book = book;
            startActivity(editIntent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        MyDataBase myDataBase=new MyDataBase(getApplicationContext());
        long res = myDataBase.deleteBook(book.getId());
        if(res!=-1){
            Toast.makeText(this, "Book update successfully", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }else{
            Toast.makeText(this, "Failed to update book", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);

    }
}