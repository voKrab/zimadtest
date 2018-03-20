package com.vokrab.zimadtest;

import com.vokrab.zimadtest.model.DataObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by oleg on 20.03.2018.
 */

public interface ServerAPI
{
    @GET ( "xim/api.php" )
    Call < DataObject > getData ( @Query ( "query" ) String dataType );
}
