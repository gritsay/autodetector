package ru.omgtu.autodetector.autodetector.presenter;

import android.graphics.BitmapFactory;
import android.util.Log;

import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;
import ru.omgtu.autodetector.autodetector.model.Captcha;
import ru.omgtu.autodetector.autodetector.network.gibdd.GibddService;
import ru.omgtu.autodetector.autodetector.view.CaptchaView;

/**
 * Created by gritsay on 16.12.17.
 */

public class CaptchaPresenter {
    private CaptchaView mainView;
    private GibddService gibddService;

    public CaptchaPresenter(CaptchaView mainView, GibddService gibddService){
        this.mainView = mainView;
        this.gibddService = gibddService;
    }


  public void checkCapture(/*String url*/) {
      gibddService.getCapture().enqueue(new Callback<ResponseBody>() {
          @Override
          public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {
              Log.d("!!!", response.toString());
              Log.d("!!!!", response.headers().get("Set-Cookie"));

              Captcha captcha = new Captcha();
              captcha.setCookies(response.headers().get("Set-Cookie"));
              if (response.body() != null){
                  captcha.setCaptcha(BitmapFactory.decodeStream(response.body().byteStream()));
              }
              //поменять на какртинку на приянтую через запрос
              mainView.returnCaptcha(captcha);


          }

          @Override
          public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
              Log.d("!!!!", t.toString());
          }
      });

  }
}
