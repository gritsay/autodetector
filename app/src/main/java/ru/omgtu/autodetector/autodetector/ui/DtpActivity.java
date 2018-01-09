package ru.omgtu.autodetector.autodetector.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.omgtu.autodetector.autodetector.R;
import ru.omgtu.autodetector.autodetector.model.Dtp.CarDtp;
import ru.omgtu.autodetector.autodetector.network.gibdd.GibddService;
import ru.omgtu.autodetector.autodetector.network.gibdd.NetworkGibddBuilder;
import ru.omgtu.autodetector.autodetector.presenter.DtpPresenter;
import ru.omgtu.autodetector.autodetector.view.DtpView;

public class DtpActivity extends AppCompatActivity implements DtpView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dtp);

        Intent intent;
        intent = getIntent();
        String cookies = intent.getStringExtra("cookies");
        String captcha = intent.getStringExtra("captcha");

        Map queryFields = new HashMap<String, String>();
        queryFields.put("vin", "KMHHM61DP2U077462");
        queryFields.put("captchaWord", captcha);
        queryFields.put("checkType", "aiusdtp");

        NetworkGibddBuilder.init("http://check.gibdd.ru/");
        GibddService service = NetworkGibddBuilder.getGibddService();

        DtpPresenter dtpPresenter = new DtpPresenter(this, service, cookies, queryFields);
        dtpPresenter.checkDtp();
    }

    @Override
    public void returnDtp(CarDtp carDtp) {
        List<String> damagePoint = carDtp.getRequestResult().getAccidents().get(0).getDamagePoints();
        String point = TextUtils.join("", damagePoint);

        Intent intent = new Intent(this, DamageMapActivity.class);
        intent.putExtra("damagePoint", point);
        startActivity(intent);
    }
}
