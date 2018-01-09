package ru.omgtu.autodetector.autodetector.network.gibdd;

import android.util.Log;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.omgtu.autodetector.autodetector.model.Capture;
import ru.omgtu.autodetector.autodetector.model.Dtp.CarDtp;
import ru.omgtu.autodetector.autodetector.model.History.CarHistory;
import ru.omgtu.autodetector.autodetector.model.Restrict.CarRestricted;
import ru.omgtu.autodetector.autodetector.model.Wanted.CarWanted;

/**
 * Created by gritsay on 16.12.17.
 */

public interface GibddService {
    @GET("/proxy/captcha.jpg")
    Call<ResponseBody> getCapture();

    @FormUrlEncoded
    @POST("/proxy/check/auto/history")
    Call<CarHistory> getHistory(@Header("Cookie") String cookie, @FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("/proxy/check/auto/restrict")
    Call<CarRestricted> getRestrict(@Header("Cookie") String cookie, @FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("/proxy/check/auto/wanted")
    Call<CarWanted> getWanted(@Header("Cookie") String cookie, @FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("/proxy/check/auto/dtp")
    Call<CarDtp> getDtp(@Header("Cookie") String cookie, @FieldMap Map<String, String> fields);

    @GET("/proxy/check/auto/dtp/damages.png")
    Call<ResponseBody> getDamageMap(@Query("map") String map);
}
