package ru.omgtu.autodetector.autodetector.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import ru.omgtu.autodetector.autodetector.R;
import ru.omgtu.autodetector.autodetector.model.Captcha;
import ru.omgtu.autodetector.autodetector.network.gibdd.GibddService;
import ru.omgtu.autodetector.autodetector.network.gibdd.NetworkGibddBuilder;
import ru.omgtu.autodetector.autodetector.presenter.CaptchaPresenter;
import ru.omgtu.autodetector.autodetector.view.CaptchaView;

public class CaptchaActivity extends AppCompatActivity implements CaptchaView, View.OnClickListener{
    ImageView ivCaptcha;
    EditText etCaptcha;
    Button btn;
    String cookies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captcha);

        ivCaptcha = (ImageView) findViewById(R.id.ivCaptcha);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        etCaptcha = (EditText) findViewById(R.id.etCaptcha);

        NetworkGibddBuilder.init("http://check.gibdd.ru/");
        GibddService service = NetworkGibddBuilder.getGibddService();
        CaptchaPresenter presenter = new CaptchaPresenter(this, service);
        presenter.checkCapture();

    }


    @Override
    public void returnCaptcha(Captcha captcha) {
        ivCaptcha.setImageBitmap(captcha.getCaptcha());
        cookies = captcha.getCookies();
        // выводим пользователю для ввода

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("cookies", cookies);
        intent.putExtra("captcha", etCaptcha.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
