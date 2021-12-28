package com.example.bookapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp.R;
import com.example.bookapp.models.Book;
import com.example.bookapp.models.Category;

import java.util.ArrayList;

public class AllBookAdapter extends RecyclerView.Adapter<AllBookAdapter.ViewHolder> {
    ArrayList<Book> data=new ArrayList<>();
    Context context;

    public AllBookAdapter(ArrayList<Book> data, Context context) {
        this.data = data;
        this.context = context;
    }
    @NonNull
    @Override
    public AllBookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.book_item,null,false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book b=data.get(position);
        holder.tv_BookName.setText(b.getBookName());
        holder.tv_AuthorName.setText(b.getAuthoName());
        holder.tv_RelaesYeaer.setText(b.getRelaesYesrs()+"");
//        holder.tv_PageNumber.setText(b.getPageNumber()+"");
        Log.d("hamada","rrrrr"+b);
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView tv_BookName,tv_AuthorName,tv_RelaesYeaer,tv_PageNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_BookName=itemView.findViewById(R.id.tv_BookName);
            tv_AuthorName=itemView.findViewById(R.id.tv_AuthorName);
            tv_RelaesYeaer=itemView.findViewById(R.id.tv_RelaesYeaer);
           // tv_PageNumber=itemView.findViewById(R.id.tv_PageNumber);
        }
    }
}
