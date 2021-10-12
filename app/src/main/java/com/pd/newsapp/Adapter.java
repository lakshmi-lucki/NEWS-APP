package com.pd.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<ModalClass> modalClassArrayList;

    public Adapter(Context context, ArrayList<ModalClass> modalClassArrayList) {
        this.context = context;
        this.modalClassArrayList = modalClassArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.layout_item, null, false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // set the data
        holder.cardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( context, Webview.class );
                intent.putExtra( "url", modalClassArrayList.get( position ).getUrl() );
                context.startActivity( intent );
            }
        } );
        holder.mtime.setText( "Published at:-" + modalClassArrayList.get( position ).getPublishedAt() );
        holder.mauthor.setText( modalClassArrayList.get( position ).getAuthor() );
        holder.mheading.setText( modalClassArrayList.get( position ).getTitle() );
        holder.mcontent.setText( modalClassArrayList.get( position ).getDescription() );
        Glide.with( context ).load( modalClassArrayList.get( position ).getUrlToImage() ).into( holder.imageView );


    }

    @Override
    public int getItemCount() {
        return modalClassArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mheading, mcontent, mauthor, mtime;
        CardView cardView;
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            mheading = itemView.findViewById( R.id.mainheading );
            mcontent = itemView.findViewById( R.id.content );
            mauthor = itemView.findViewById( R.id.author );
            mtime = itemView.findViewById( R.id.time );
            imageView = itemView.findViewById( R.id.imagview );
            cardView = itemView.findViewById( R.id.cardview );


        }
    }
}
