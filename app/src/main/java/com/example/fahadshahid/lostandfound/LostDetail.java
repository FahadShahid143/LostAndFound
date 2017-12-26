package com.example.fahadshahid.lostandfound;

import com.example.fahadshahid.lostandfound.models.Lost;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Fahad Shahid on 11/14/2017.
 */

public interface LostDetail {
    @GET("losts")
    Call<ArrayList<Lost>> getLostList();

    /*@GET("lost/{lost}")
    Call<Lost> getlost(@Path("lost") int id);*/

    @POST("losts")
    @FormUrlEncoded
    Call<Lost> savelost(@Field("lost_item_name") String item_name,
                      @Field("lost_item_description") String item_description,
                      @Field("lost_date") String lost_date,
                      @Field("person_name") String person_name,
                      @Field("person_phoneNo") String person_phoneNo,
                      @Field("person_address") String person_address
    );


    @PUT("losts/{lost}")
    @FormUrlEncoded
    Call<Lost> updatelost(@Path("lost") String id,
                        @Field("lost_item_name") String item_name,
                        @Field("lost_item_description") String item_description,
                        @Field("lost_date") String lost_date,
                        @Field("person_name") String person_name,
                        @Field("person_phoneNo") String person_phoneNo,
                        @Field("person_address") String person_address
    );


    @POST("losts")
    Call<Lost> saveLost(@Body Lost lost);

    @DELETE("losts/{lost}")
    Call<String> deletelost(@Path("lost") String id);
}
