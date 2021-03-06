package com.example.bookapp.activites;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.bookapp.Adapters.AllBookAdapter;
import com.example.bookapp.Adapters.AllCategoryAdapter;
import com.example.bookapp.MyDataBase;
import com.example.bookapp.R;
import com.example.bookapp.models.Book;
import com.example.bookapp.models.Category;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CreateBookActicvity extends AppCompatActivity {
    ImageView img;
    byte[]imageContent;
    ImageView favBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_book_acticvity);
        Spinner dropdown;
        EditText bookName,authorName,relaesYear,pageNumber;
        Button button;
        bookName=findViewById(R.id.editText_BookName);
        authorName=findViewById(R.id.editText_AuthorName);
        pageNumber=findViewById(R.id.editText_PageNumber);
        relaesYear=findViewById(R.id.editText_ReleaseYear);
        dropdown = findViewById(R.id.spinner3);
        favBtn=findViewById(R.id.favButton);
        img=findViewById(R.id.profile_image);
        button=findViewById(R.id.btnCreateBook);
        MyDataBase myDataBase=new MyDataBase(CreateBookActicvity.this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, myDataBase.getAllCategroyName());
        dropdown.setAdapter(adapter);
        Book myBook=new Book();
        if(myBook.isFavourite()){
           favBtn.setBackground(this.getDrawable(R.drawable.full_favorite));
        }else {
           favBtn.setBackground(this.getDrawable(R.drawable.empty_favorite));
        }
       favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myBook.setFavourite(!myBook.isFavourite());
                changeStatus(myBook.isFavourite());
            }
        });
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
                String selectedVal=dropdown.getSelectedItem().toString();
                boolean fav=myBook.isFavourite();
                Book book=new Book(BookName,ReleaesYear,AuthorName,PageNumber,selectedVal,fav);
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
    public void changeStatus(boolean status){
        if(status){
           favBtn.setBackground(this.getDrawable(R.drawable.full_favorite));
        }else {
          favBtn.setBackground(this.getDrawable(R.drawable.empty_favorite));
        }
    }
}