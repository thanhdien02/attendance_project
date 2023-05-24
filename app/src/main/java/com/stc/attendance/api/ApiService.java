package com.stc.attendance.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stc.attendance.model.ChamCong;
import com.stc.attendance.model.TaiKhoan;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    // http://localhost:8080/rest/tai-khoan

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.0.92:8080/rest/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("tai-khoan")
    Call<List<TaiKhoan>> getAllUsers();

    @POST("tai-khoan")
    Call<TaiKhoan> createUser(@Body TaiKhoan user);

    @GET("tai-khoan/{id}")
    Call<TaiKhoan> getUserById(@Path("id") int id);

    @PUT("tai-khoan/{id}")
    Call<TaiKhoan> updateUser(@Path("id") int id, @Body TaiKhoan user);

    @DELETE("tai-khoan/{id}")
    Call<TaiKhoan> deleteUser(@Path("id") int id);


    @GET("cham-cong")
    Call<List<ChamCong>> getAllChamCong();

    @POST("cham-cong")
    Call<ChamCong> createChamCong(@Body ChamCong chamCong);

    @GET("cham-cong/{id}")
    Call<ChamCong> getAttendanceById(@Path("id") int id);

    @PUT("cham-cong/{id}")
    Call<ChamCong> updateAttendance(@Path("id") int id, @Body ChamCong chamCong);

    @DELETE("cham-cong/{id}")
    Call<ChamCong> deleteAttendance(@Path("id") int id);

}