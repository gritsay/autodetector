package ru.omgtu.autodetector.autodetector.presenter;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.omgtu.autodetector.autodetector.model.Restrict.CarRestricted;
import ru.omgtu.autodetector.autodetector.network.gibdd.GibddService;
import ru.omgtu.autodetector.autodetector.view.RestrictView;

/**
 * Created by denis on 29.12.17.
 */

public class RestrictPresenter {
    private RestrictView restrictView;
    private GibddService  gibddService;
    private String cookies;
    private Map requestFields;

    public RestrictPresenter(RestrictView restrictView, GibddService gibddService, String cookies, Map requestFields){
        this.restrictView = restrictView;
        this.gibddService = gibddService;
        this.cookies = cookies;
        this.requestFields = requestFields;
    }

    public void checkRestrict(){
        gibddService.getRestrict(cookies, requestFields).enqueue(new Callback<CarRestricted>() {
            @Override
            public void onResponse(Call<CarRestricted> call, Response<CarRestricted> response) {

            }

            @Override
            public void onFailure(Call<CarRestricted> call, Throwable t) {

            }
        });
    }


}
