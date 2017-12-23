package ru.omgtu.autodetector.autodetector.presenter;

import android.telecom.Call;
import android.util.Log;

import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;
import ru.omgtu.autodetector.autodetector.model.Capture;
import ru.omgtu.autodetector.autodetector.network.gibdd.GibddService;
import ru.omgtu.autodetector.autodetector.network.gibdd.NetworkGibddBuilder;
import ru.omgtu.autodetector.autodetector.view.MainView;

/**
 * Created by gritsay on 16.12.17.
 */

public class MainPresenter {
    MainView mainView;


  public   void checkCapture(String url) {
      NetworkGibddBuilder.init("http://check.gibdd.ru");
      NetworkGibddBuilder.init("http://check.gibdd.ru/");
      GibddService service = NetworkGibddBuilder.getGibddService();
      service.getCapture().enqueue(new Callback<Capture>() {
          @Override
          public void onResponse(retrofit2.Call<Capture> call, Response<Capture> response) {
              Log.d("!!!!", response.toString());
             //поменять на какртинку на приянтую через запрос
              mainView.returnCapture(new Capture());

          }

          @Override
          public void onFailure(retrofit2.Call<Capture> call, Throwable t) {
              Log.d("!!!!", t.toString());
          }
      });

  }
}
