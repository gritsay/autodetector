package ru.omgtu.autodetector.autodetector.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import ru.omgtu.autodetector.autodetector.R;
import ru.omgtu.autodetector.autodetector.model.DamageMap;
import ru.omgtu.autodetector.autodetector.network.gibdd.GibddService;
import ru.omgtu.autodetector.autodetector.network.gibdd.NetworkGibddBuilder;
import ru.omgtu.autodetector.autodetector.presenter.DamageMapPresenter;
import ru.omgtu.autodetector.autodetector.view.DamageMapView;

public class DamageMapActivity extends AppCompatActivity implements DamageMapView{

    ImageView ivDamageMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damage_map);

        ivDamageMap = (ImageView) findViewById(R.id.ivDamageMap);

        Intent intent = getIntent();
        String damagePoint = intent.getStringExtra("damagePoint");

        NetworkGibddBuilder.init("http://check.gibdd.ru/");
        GibddService service = NetworkGibddBuilder.getGibddService();

        DamageMapPresenter damageMapPresenter = new DamageMapPresenter(this, service, damagePoint);
        damageMapPresenter.getDamageMap();
    }

    @Override
    public void returnDamageMap(DamageMap damageMap) {
        ivDamageMap.setImageBitmap(damageMap.getDamageMap());
    }
}
