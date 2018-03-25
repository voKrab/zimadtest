package com.vokrab.zimadtest.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vokrab.zimadtest.R;
import com.vokrab.zimadtest.ZimadTestApplication;
import com.vokrab.zimadtest.model.ToDetailsItemObject;

/**
 * Created by oleg on 19.03.2018.
 */

public class ItemDetailsFragment extends BaseFragment
{
    private ImageView itemImageView;
    private TextView positionTextView;
    private TextView titleTextView;
    private ToDetailsItemObject item;

    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle toLoad )
    {
        View view = inflater.inflate ( R.layout.details_layout, null );

        itemImageView = view.findViewById ( R.id.catImageView );
        positionTextView = view.findViewById ( R.id.positionTextView );;
        titleTextView = view.findViewById ( R.id.titleTextView );

        return view;
    }

    private void addListeners ()
    {

    }

    private void updateComponents ()
    {
        Picasso.get ().load ( item.getUrl() ).resize (512, 512 ).placeholder ( R.mipmap.ic_launcher ).centerCrop ().into ( itemImageView );
        positionTextView.setText ( "" + ( item.getPosition () + 1 ) );
        titleTextView.setText ( item.getTitle () );
    }

    @Override
    protected void clearFields ()
    {

    }

    @Override
    protected void init ()
    {
        ZimadTestApplication app = ( ZimadTestApplication ) controller.getApplication ();
        item = ( ToDetailsItemObject ) app.getCommunicationObject ();
        updateComponents ();
    }

    @Override
    public void onPause ()
    {
        super.onPause();
    }

    @Override
    public void onResume ()
    {
        super.onResume ();
    }
}
