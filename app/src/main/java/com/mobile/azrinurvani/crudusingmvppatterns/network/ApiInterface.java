package com.mobile.azrinurvani.crudusingmvppatterns.network;

import com.mobile.azrinurvani.crudusingmvppatterns.model.NoteModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("save.php")
    Call<NoteModel>saveNote(
            @Field("title")String title,
            @Field("note")String note,
            @Field("color")int color
    );

    @GET("notes.php")
    Call<List<NoteModel>>getNote();


    @FormUrlEncoded
    @POST("update.php")
    Call<NoteModel>updateNote(
            @Field("id")int id,
            @Field("title")String title,
            @Field("note")String note,
            @Field("color")int color
    );


    @FormUrlEncoded
    @POST("delete.php")
    Call<NoteModel>deleteNote(@Field("id")int id);
}
