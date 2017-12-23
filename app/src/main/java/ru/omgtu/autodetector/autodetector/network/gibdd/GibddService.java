package ru.omgtu.autodetector.autodetector.network.gibdd;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import ru.omgtu.autodetector.autodetector.model.Capture;

/**
 * Created by gritsay on 16.12.17.
 */

public interface GibddService {

    @GET("/proxy/captcha.jpg")
    Call<Capture> getCapture();


}
