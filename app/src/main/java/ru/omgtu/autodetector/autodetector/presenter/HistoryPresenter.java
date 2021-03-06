package ru.omgtu.autodetector.autodetector.presenter;

import android.util.Log;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.omgtu.autodetector.autodetector.model.History.CarHistory;
import ru.omgtu.autodetector.autodetector.network.gibdd.GibddService;
import ru.omgtu.autodetector.autodetector.view.HistoryView;

/**
 * Created by denis on 23.12.17.
 */

public class HistoryPresenter {
    private HistoryView historyView;
    private GibddService gibddService;
    private String cookies;
    private Map requestFields;

    public HistoryPresenter(HistoryView historyView, GibddService gibddService, String cookies, Map requestFields){
        this.historyView = historyView;
        this.gibddService = gibddService;
        this.cookies = cookies;
        this.requestFields = requestFields;
    }

    public void checkHistory(){
        gibddService.getHistory(cookies, requestFields).enqueue(new Callback<CarHistory>() {
            @Override
            public void onResponse(Call<CarHistory> call, Response<CarHistory> response) {
                if (response.body().getStatus() != 200){
                    Log.d("!!!!", response.body().getMessage());
                    historyView.returnHistoryError(response.body().getMessage());
                }
                else historyView.returnHistory(response.body());
            }

            @Override
            public void onFailure(Call<CarHistory> call, Throwable t) {

            }
        });

    }
}
