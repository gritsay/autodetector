package ru.omgtu.autodetector.autodetector.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gritsay on 16.12.17.
 */

public class Captcha {
   @SerializedName("cookies")
   private String cookies;
   @SerializedName("captcha")
   private Bitmap captcha;

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public Bitmap getCaptcha() {
        return captcha;
    }

    public void setCaptcha(Bitmap captcha) {
        this.captcha = captcha;
    }
}
