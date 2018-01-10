package ru.omgtu.autodetector.autodetector.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ru.omgtu.autodetector.autodetector.R;
import ru.omgtu.autodetector.autodetector.model.Captcha;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etVin;
    Button btnCheck;
    final int REQUEST_CODE_CAPTCHA = 1;
    final int REQUEST_CODE_CHECK = 2;
    String captcha = "";
    String cookies = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etVin = (EditText) findViewById(R.id.etVin);
        btnCheck = (Button) findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String documentType = checkWithRegex(etVin.getText().toString());
        switch (documentType){
            case "vin":
                Intent intent = new Intent(this, CaptchaActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CAPTCHA);
                break;
        }
    }

    private String checkWithRegex(String number){
        String documentType = "";
        Pattern p = Pattern.compile("^(?<wmi>[A-HJ-NPR-Z\\d]{3})(?<vds>[A-HJ-NPR-Z\\d]{5})(?<check>[\\dA-HJ-NPR-Z])(?<vis>(?<year>[A-HJ-NPR-Z\\d])(?<plant>[A-HJ-NPR-Z\\d])(?<seq>[A-HJ-NPR-Z\\d]{6}))$");
        if (p.matcher(number).matches()) documentType = "vin";
        p = Pattern.compile("/^[A-Za-z]\\d{3}[A-Za-z]{2}\\d{2,3}/\n");
        if (p.matcher(number).matches()) documentType = "regPlate";
        p = Pattern.compile("/^\\d{2}[A-Za-z]{2}\\d{6}/\n");
        if (p.matcher(number).matches()) documentType = "regCert";
        p = Pattern.compile("/^((8|\\+7)[\\- ]?)(\\(?\\d{3}\\)?[\\- ]?)\\d{7}$/");
        if (p.matcher(number).matches()) documentType = "phoneNum";

        return documentType;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            switch (requestCode){
                case REQUEST_CODE_CAPTCHA:
                    captcha = data.getStringExtra("captcha");
                    cookies = data.getStringExtra("cookies");

                    Intent intent = new Intent(this, DtpActivity.class);
                    intent.putExtra("vin", etVin.getText().toString());
                    intent.putExtra("captcha", captcha);
                    intent.putExtra("cookies", cookies);
                    startActivityForResult(intent, REQUEST_CODE_CHECK);
                    break;
                case REQUEST_CODE_CHECK:
                    /*Intent intent1 = new Intent(this, HistoryActivity.class);
                    intent1.putExtra("vin", etVin.getText().toString());
                    intent1.putExtra("captcha", captcha);
                    intent1.putExtra("cookies", cookies);
                    startActivityForResult(intent1, REQUEST_CODE_CHECK);*/
            }
        }

    }
}
