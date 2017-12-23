package ru.omgtu.autodetector.autodetector.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.omgtu.autodetector.autodetector.R;
import ru.omgtu.autodetector.autodetector.model.Capture;
import ru.omgtu.autodetector.autodetector.network.gibdd.GibddService;
import ru.omgtu.autodetector.autodetector.network.gibdd.NetworkGibddBuilder;
import ru.omgtu.autodetector.autodetector.presenter.MainPresenter;
import ru.omgtu.autodetector.autodetector.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainPresenter presenter = new MainPresenter();

    }


    @Override
    public void returnCapture(Capture capture) {
        // выводим пользователю для ввода

    }
}
