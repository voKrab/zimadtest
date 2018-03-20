package com.vokrab.zimadtest;

import android.app.Application;
import android.widget.Toast;

import com.vokrab.zimadtest.model.DataObject;
import com.vokrab.zimadtest.model.ItemType;
import com.vokrab.zimadtest.model.SimpleActionListener;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by oleg on 20.03.2018.
 */

public class ZimadTestApplication extends Application
{
    private static String BASE_URL = "http://kot3.com/";
    private static ServerAPI serverAPI;
    private Retrofit retrofit;
    private Object communicationObject;
    private Map <ItemType, DataObject > data;

    @Override
    public void onCreate ()
    {
        super.onCreate ();

        retrofit = new Retrofit.Builder ()
                .baseUrl ( BASE_URL )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();
        serverAPI = retrofit.create ( ServerAPI.class );

        data = new HashMap <ItemType, DataObject > ();
    }

    public static ServerAPI getApi ()
    {
        return serverAPI;
    }

    public void getData (final ItemType type, final SimpleActionListener listener )
    {
        final DataObject dataObject = data.get ( type );
        if ( dataObject == null )
        {
            serverAPI.getData ( type == ItemType.CAT ? "cat" : "dog" ).enqueue (new Callback<DataObject>()
            {
                @Override
                public void onResponse ( Call< DataObject > call, Response< DataObject > response )
                {
                    if ( response.isSuccessful () )
                    {
                        DataObject loadedDataObject = response.body ();
                        if ( loadedDataObject != null )
                        {
                            data.put ( type, loadedDataObject );
                            listener.onAction ( loadedDataObject );
                        } else
                        {
                            Toast.makeText (ZimadTestApplication.this, getString ( R.string.error ), Toast.LENGTH_SHORT ).show ();
                        }
                    } else
                    {
                        Toast.makeText (ZimadTestApplication.this, getString ( R.string.error ), Toast.LENGTH_SHORT ).show ();
                    }
                }
                @Override
                public void onFailure ( Call < DataObject > call, Throwable t )
                {
                    Toast.makeText (ZimadTestApplication.this, getString ( R.string.error ), Toast.LENGTH_SHORT ).show ();
                }
            });
        } else
        {
            listener.onAction ( dataObject );
        }
    }

    public void setCommunicationObject ( Object newObject )
    {
        this.communicationObject = newObject;
    }

    public Object getCommunicationObject ()
    {
        return communicationObject;
    }
}
