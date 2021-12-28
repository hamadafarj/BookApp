package com.example.bookapp.activites;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.bookapp.MyDataBase;
import com.example.bookapp.R;
import com.example.bookapp.models.Book;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CreateBookActicvity extends AppCompatActivity {
    ImageView img;
    byte[]imageContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_book_acticvity);

        //ImageView img;
        EditText bookName,authorName,relaesYear,pageNumber;
        Button button;
        bookName=findViewById(R.id.editText_BookName);
        authorName=findViewById(R.id.editText_AuthorName);
        pageNumber=findViewById(R.id.editText_PageNumber);
        relaesYear=findViewById(R.id.editText_ReleaseYear);
        img=findViewById(R.id.profile_image);
        button=findViewById(R.id.btnCreateBook);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDataBase myDataBase=new MyDataBase(CreateBookActicvity.this);
                String BookName=bookName.getText().toString();
                String AuthorName=authorName.getText().toString();
                int ReleaesYear=Integer.parseInt(relaesYear.getText().toString());
                int PageNumber=Integer.parseInt(pageNumber.getText().toString());
                Book book=new Book(BookName,ReleaesYear,AuthorName,PageNumber);
                book.setImage(imageContent);
                myDataBase.cretaeBook(book);
            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode==100 && resultCode==RESULT_OK){
            Uri imgUri=intent.getData();
            try {
                InputStream inputStream= getContentResolver().openInputStream(imgUri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
                imageContent=getByte(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public byte[] getByte(Bitmap bitmap){
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream);
        return  stream.toByteArray();
    }
}