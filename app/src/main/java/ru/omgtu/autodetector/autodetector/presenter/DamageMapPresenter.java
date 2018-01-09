package ru.omgtu.autodetector.autodetector.presenter;

import android.graphics.BitmapFactory;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.omgtu.autodetector.autodetector.model.DamageMap;
import ru.omgtu.autodetector.autodetector.network.gibdd.GibddService;
import ru.omgtu.autodetector.autodetector.view.DamageMapView;

/**
 * Created by denis on 09.01.18.
 */

public class DamageMapPresenter {
    private DamageMapView damageMapView;
    private GibddService gibddService;
    private String map;

    public DamageMapPresenter(DamageMapView damageMapView, GibddService gibddService, String map){
        this.damageMapView = damageMapView;
        this.gibddService = gibddService;
        this.map = map;
    }

    public void getDamageMap(){
        gibddService.getDamageMap(map).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                DamageMap  damageMap = new DamageMap();
                damageMap.setDamageMap(BitmapFactory.decodeStream(response.body().byteStream()));
                damageMapView.returnDamageMap(damageMap);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
