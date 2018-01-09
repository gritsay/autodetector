package ru.omgtu.autodetector.autodetector.presenter;

import android.util.Log;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.omgtu.autodetector.autodetector.model.Dtp.CarDtp;
import ru.omgtu.autodetector.autodetector.network.gibdd.GibddService;
import ru.omgtu.autodetector.autodetector.view.DtpView;

/**
 * Created by denis on 29.12.17.
 */

public class DtpPresenter {
    private DtpView dtpView;
    private GibddService gibddService;
    private String cookies;
    private Map requestFields;

    public DtpPresenter(DtpView dtpView, GibddService gibddService, String cookies, Map requestFields){
        this.dtpView = dtpView;
        this.gibddService = gibddService;
        this.cookies = cookies;
        this.requestFields = requestFields;
    }

    public void checkDtp() {
        gibddService.getDtp(cookies, requestFields).enqueue(new Callback<CarDtp>() {
            @Override
            public void onResponse(Call<CarDtp> call, Response<CarDtp> response) {
                Log.d("!!!!", response.toString());
                Log.d("!!!!", Integer.toString(response.body().getRequestResult().getAccidents().size()));
                dtpView.returnDtp(response.body());

            }

            @Override
            public void onFailure(Call<CarDtp> call, Throwable t) {

            }
        });
    }
}
