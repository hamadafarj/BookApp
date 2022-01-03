package com.example.bookapp.activites;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bookapp.MyDataBase;
import com.example.bookapp.R;
import com.example.bookapp.models.Book;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;

public class EditBookDetails extends AppCompatActivity{
    ImageView editBookImage;
    byte[]imageContent;
    Bitmap showImage;
    int id;
    static Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book_details);
        TextView bookName,authorName,relaseYear,pageNumber;
        Button UpdateButton;
        Spinner dropdown;

        MyDataBase myDataBase=new MyDataBase(EditBookDetails.this);

        bookName=findViewById(R.id.BookNameEditTxt);
        authorName=findViewById(R.id.AuthorNameEditTxt);
        relaseYear=findViewById(R.id.RelaseYearEditTxt);
        pageNumber=findViewById(R.id.PageNamberEditTxt);
        UpdateButton=findViewById(R.id.button3);
        dropdown = findViewById(R.id.spinner);


        id=book.getId();
        editBookImage=findViewById(R.id.Edit_profile_image);

        bookName.setText(book.getBookName());
        authorName.setText(book.getAuthoName());
        relaseYear.setText(book.getRelaesYesrs()+"");
        pageNumber.setText(book.getPageNumber()+"");

        imageContent = book.getImage() ;
        showImage= BitmapFactory.decodeByteArray(book.getImage(),0,book.getImage().length);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, myDataBase.getAllCategroyName());
        dropdown.setAdapter(adapter);

        editBookImage.setImageBitmap(showImage);
        editBookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });


        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String BookName=bookName.getText().toString();
                String AuthorName=authorName.getText().toString();
                int ReleaesYear=Integer.parseInt(relaseYear.getText().toString());
                int PageNumber=Integer.parseInt(pageNumber.getText().toString());
                String selectedVal=dropdown.getSelectedItem().toString();;
                Book editBookDetails=new Book(BookName,ReleaesYear,AuthorName,PageNumber,selectedVal);
                editBookDetails.setImage(imageContent);
                editBookDetails.setId(id);
                myDataBase.updateBook(editBookDetails);
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
                editBookImage.setImageBitmap(bitmap);
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