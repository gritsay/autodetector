package ru.omgtu.autodetector.autodetector.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

/**
 * Created by denis on 09.01.18.
 */

public class DamageMap {
    @SerializedName("damageMap")
    private Bitmap damageMap;

    public Bitmap getDamageMap() {
        return damageMap;
    }

    public void setDamageMap(Bitmap damageMap) {
        this.damageMap = damageMap;
    }

}
