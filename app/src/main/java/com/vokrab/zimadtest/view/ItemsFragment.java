package com.vokrab.zimadtest.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vokrab.zimadtest.R;
import com.vokrab.zimadtest.ZimadTestApplication;
import com.vokrab.zimadtest.model.DataObject;
import com.vokrab.zimadtest.model.ItemObject;
import com.vokrab.zimadtest.model.ItemType;
import com.vokrab.zimadtest.model.SimpleActionListener;
import com.vokrab.zimadtest.model.ToDetailsItemObject;

import java.util.List;

/**
 * Created by oleg on 19.03.2018.
 */

public class ItemsFragment extends BaseFragment implements RecyclerViewClickListener
{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private int scrollPosition;
    private ItemType type;

    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle toLoad )
    {
        View view = inflater.inflate ( R.layout.items_layout, null );

        mRecyclerView = ( RecyclerView ) view.findViewById ( R.id.recyclerView );
        mLayoutManager = new LinearLayoutManager ( controller );
        mRecyclerView.setLayoutManager ( mLayoutManager );

        ZimadTestApplication app = ( ZimadTestApplication ) controller.getApplication ();
        app.getData ( type, new SimpleActionListener ()
        {
            @Override
            public void onAction ( DataObject data )
            {
                updateComponents ( data );
            }
        });
        if ( scrollPosition != 0 )
        {
            scrollTo ( scrollPosition );
        }
        addListeners ();

        return view;
    }

    private void addListeners ()
    {
        mRecyclerView.addOnItemTouchListener ( new RecyclerView.SimpleOnItemTouchListener ()
        {

        });
    }

    protected ItemType getType ()
    {
        return ItemType.CAT;
    }

    public int getScrollPosition ()
    {
        int result = 0;
        if ( mLayoutManager != null )
        {
            result = mLayoutManager.findFirstCompletelyVisibleItemPosition ();
        }
        return result;
    }

    public void scrollTo ( final int newScrollPosition )
    {
        mRecyclerView.post ( new Runnable()
        {
            public void run ()
            {
                mLayoutManager.scrollToPositionWithOffset ( newScrollPosition,0 );
                scrollPosition = 0;
            }
        });
    }

    private void updateComponents ( DataObject data )
    {
        List <ItemObject> dataObject = data.getData ();
        ItemObject[] arrayData = new ItemObject[dataObject.size ()];
        mAdapter = new RecyclerViewAdapter ( dataObject.toArray ( arrayData ), this );
        mRecyclerView.setAdapter ( mAdapter );
    }

    @Override
    protected void clearFields ()
    {

    }

    @Override
    protected void init ()
    {

    }

    public void setup ( int newScrollPosition, ItemType type )
    {
        scrollPosition = newScrollPosition;
        this.type = type;
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

    @Override
    public void recyclerViewListClicked ( View v, int position, ToDetailsItemObject item )
    {
//        Toast.makeText ( controller, "POSITION: " + item.getPosition (), Toast.LENGTH_SHORT ).show ();
        ZimadTestApplication app = ( ZimadTestApplication ) controller.getApplication ();
        app.setCommunicationObject ( item );
        controller.showDetailsView ();

    }
}
