package ru.omgtu.autodetector.autodetector.presenter;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.omgtu.autodetector.autodetector.model.Wanted.CarWanted;
import ru.omgtu.autodetector.autodetector.network.gibdd.GibddService;
import ru.omgtu.autodetector.autodetector.view.WantedView;

/**
 * Created by denis on 29.12.17.
 */

public class WantedPresenter {
    private WantedView wantedView;
    private GibddService gibddService;
    private String cookies;
    private Map requestFields;

    public WantedPresenter(WantedView wantedView, GibddService gibddService, String cookies, Map requestFields){
        this.wantedView = wantedView;
        this.gibddService = gibddService;
        this.cookies = cookies;
        this.requestFields = requestFields;
    }

    public void checkWanted(){
        gibddService.getWanted(cookies, requestFields).enqueue(new Callback<CarWanted>() {
            @Override
            public void onResponse(Call<CarWanted> call, Response<CarWanted> response) {

            }

            @Override
            public void onFailure(Call<CarWanted> call, Throwable t) {

            }
        });
    }
}
