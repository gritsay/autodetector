package ru.omgtu.autodetector.autodetector.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

import ru.omgtu.autodetector.autodetector.R;
import ru.omgtu.autodetector.autodetector.model.History.CarHistory;
import ru.omgtu.autodetector.autodetector.network.gibdd.GibddService;
import ru.omgtu.autodetector.autodetector.network.gibdd.NetworkGibddBuilder;
import ru.omgtu.autodetector.autodetector.presenter.HistoryPresenter;
import ru.omgtu.autodetector.autodetector.view.HistoryView;
import ru.omgtu.autodetector.autodetector.view.MainView;

public class HistoryActivity extends AppCompatActivity implements HistoryView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Intent intent;
        intent = getIntent();
        String cookies = intent.getStringExtra("cookies");
        String captcha = intent.getStringExtra("captcha");

        Map queryFields = new HashMap<String, String>();
        queryFields.put("vin", "XTA21099023102979");
        queryFields.put("captchaWord", captcha);
        queryFields.put("checkType", "history");

        NetworkGibddBuilder.init("http://check.gibdd.ru/");
        GibddService service = NetworkGibddBuilder.getGibddService();

        HistoryPresenter historyPresenter = new HistoryPresenter(this, service, cookies, queryFields);
        historyPresenter.checkHistory();
    }

    @Override
    public void returnHistory(CarHistory carHistory) {

    }
}
