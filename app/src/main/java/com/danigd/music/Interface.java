package com.danigd.music;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by daniel on 22/11/17.
 */

public interface Interface {

    @GET("/band/78?api_key=20ed3295-53e1-4b9c-969d-db57e196a5af")
    Call<BandsClass> getArtists();
   // Call<List<Bands>> listaBandas(@Path("bandas") String bandas);
   @GET("/band/419?api_key=20ed3295-53e1-4b9c-969d-db57e196a5af")
   Call<BandsClass> getArtists2();
}
