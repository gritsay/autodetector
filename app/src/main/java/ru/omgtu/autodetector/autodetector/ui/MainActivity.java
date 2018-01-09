package ru.omgtu.autodetector.autodetector.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.omgtu.autodetector.autodetector.R;
import ru.omgtu.autodetector.autodetector.model.Capture;
import ru.omgtu.autodetector.autodetector.network.gibdd.GibddService;
import ru.omgtu.autodetector.autodetector.network.gibdd.NetworkGibddBuilder;
import ru.omgtu.autodetector.autodetector.presenter.MainPresenter;
import ru.omgtu.autodetector.autodetector.view.DtpView;
import ru.omgtu.autodetector.autodetector.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener{
    ImageView ivCaptcha;
    EditText etCaptcha;
    Button btn;
    String cookies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivCaptcha = (ImageView) findViewById(R.id.ivCaptcha);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        etCaptcha = (EditText) findViewById(R.id.etCaptcha);

        NetworkGibddBuilder.init("http://check.gibdd.ru/");
        GibddService service = NetworkGibddBuilder.getGibddService();
        MainPresenter presenter = new MainPresenter(this, service);
        presenter.checkCapture();

    }


    @Override
    public void returnCapture(Capture capture) {
        ivCaptcha.setImageBitmap(capture.getCaptcha());
        cookies = capture.getCookies();
        // выводим пользователю для ввода

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, DtpActivity.class);
        intent.putExtra("cookies", cookies);
        intent.putExtra("captcha", etCaptcha.getText().toString());
        startActivity(intent);
    }
}
