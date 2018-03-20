package com.vokrab.zimadtest.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vokrab.zimadtest.R;
import com.vokrab.zimadtest.model.ItemObject;
import com.vokrab.zimadtest.model.ToDetailsItemObject;

/**
 * Created by oleg on 19.03.2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter < RecyclerViewAdapter.ViewHolder >
{
    private ItemObject[] data;
    private static RecyclerViewClickListener itemListener;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView catImageView;
        public TextView positionTextView;
        public TextView titleTextView;

        public ViewHolder ( View view )
        {
            super ( view );

            this.catImageView = view.findViewById ( R.id.catImageView );
            this.positionTextView = view.findViewById ( R.id.positionTextView );;
            this.titleTextView = view.findViewById ( R.id.titleTextView );;
        }
    }

    public RecyclerViewAdapter ( ItemObject[] data, RecyclerViewClickListener itemListener )
    {
        this.data = data;
        this.itemListener = itemListener;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType )
    {
        View v = LayoutInflater.from ( parent.getContext () ).inflate ( R.layout.row_layout, parent, false );
        ViewHolder vh = new ViewHolder ( v );
        return vh;
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, final int position )
    {
        final ItemObject currentItem = data[position];
        Picasso.get ().load ( currentItem.getUrl() ).resize (100, 100 ).placeholder ( R.mipmap.ic_launcher ).centerCrop ().into ( holder.catImageView );
        holder.positionTextView.setText ( "" + ( position + 1 ) );
        holder.titleTextView.setText ( currentItem.getTitle () );
        holder.itemView.setOnClickListener ( new View.OnClickListener ()
        {
            @Override
            public void onClick ( View view )
            {
                ToDetailsItemObject toDetailsView = new ToDetailsItemObject ( currentItem.getUrl (), currentItem.getTitle (), position );
                itemListener.recyclerViewListClicked ( view, position, toDetailsView );
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return data.length;
    }
}

