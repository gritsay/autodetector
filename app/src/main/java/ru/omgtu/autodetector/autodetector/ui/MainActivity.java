package ru.omgtu.autodetector.autodetector.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.regex.Pattern;

import ru.omgtu.autodetector.autodetector.R;
import ru.omgtu.autodetector.autodetector.model.Captcha;
import ru.omgtu.autodetector.autodetector.model.Report;
import ru.omgtu.autodetector.autodetector.network.gibdd.GibddService;
import ru.omgtu.autodetector.autodetector.network.gibdd.NetworkGibddBuilder;
import ru.omgtu.autodetector.autodetector.presenter.CaptchaPresenter;
import ru.omgtu.autodetector.autodetector.view.CaptchaView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CaptchaView {

    EditText etVin;
    Button btnCheck;
    final int REQUEST_CODE_CAPTCHA = 1;
    final int REQUEST_CODE_HISTORY = 2;
    final int REQUEST_CODE_DTP = 3;
    final int REQUEST_CODE_WANTED = 4;
    final int REQUEST_CODE_RESTRICT = 5;
    String captcha = "";
    String cookies = "";
    Report report = new Report();

    //Dialog captchaDialog;
    ImageView ivCaptchaDialog;
    EditText etCaptcha;
    Button btnCaptcha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*captchaDialog = new Dialog(MainActivity.this);
        captchaDialog.setTitle("Введите капчу");
        captchaDialog.setContentView(R.layout.capatcha_dialog);

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.capatcha_dialog, (ViewGroup)findViewByid(R.id.toast_layout));
        etCaptcha = (EditText) findViewById(R.id.etCaptchaDialog);
        btnCaptcha = (Button) findViewById(R.id.btnCaptchaDialog);
        ivCaptchaDialog = (ImageView) findViewById(R.id.ivDialog);
*/




        etVin = (EditText) findViewById(R.id.etVin);
        btnCheck = (Button) findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(this);
        TextWatcher twDoc = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Log.d("!!!!", etVin.getText().toString());
                String documentType = checkWithRegex(etVin.getText().toString());
                switch (documentType){
                    case "vin":
                        btnCheck.setEnabled(true);
                        break;
                    default:
                        btnCheck.setEnabled(false);
                        break;
                }
            }
        };
        etVin.addTextChangedListener(twDoc);
        etVin.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
                        (i == KeyEvent.KEYCODE_ENTER)){
                    btnCheck.callOnClick();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        //return super.onCreateDialog(id);
        AlertDialog.Builder captchaDialog = new AlertDialog.Builder(this);
        captchaDialog.

    }



    private void getCaptcha(/*int requestCode*/){
        /*Log.d("!!!!", "22");
        Intent intent = new Intent(this, CaptchaActivity.class);
        startActivityForResult(intent, requestCode);*/
        NetworkGibddBuilder.init("http://check.gibdd.ru/");
        GibddService service = NetworkGibddBuilder.getGibddService();
        CaptchaPresenter presenter = new CaptchaPresenter(MainActivity.this, service);
        presenter.checkCapture();

    }

    @Override
    public void onClick(View view) {
        /*String documentType = checkWithRegex(etVin.getText().toString());
        switch (documentType){
            case "vin":*/
                /*Intent intent = new Intent(this, CaptchaActivity.class);
                startActivityForResult(intent, REQUEST_CODE_HISTORY);*/
               /* getCaptcha(REQUEST_CODE_HISTORY);
                break;
        }*/

        //Intent intent = new Intent(this, CaptchaActivity.class);
        //startActivityForResult(intent, REQUEST_CODE_HISTORY);


        getCaptcha();
        //captchaDialog.show();
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
                /*case REQUEST_CODE_CAPTCHA:
                    //report = data.getParcelableExtra("report");
                    String next = data.getStringExtra("next");
                    Log.d("!!!!", next);
                    switch (next){
                        case "dtp":
                            Log.d("!!!!", next);
                            getCaptcha(REQUEST_CODE_DTP);
                            break;
                        case "wanted":
                            getCaptcha(REQUEST_CODE_WANTED);
                            break;
                        case "restrict":
                            getCaptcha(REQUEST_CODE_RESTRICT);
                            break;
                        case "end":
                            break;
                    }*/
                case REQUEST_CODE_HISTORY:
                    captcha = data.getStringExtra("captcha");
                    cookies = data.getStringExtra("cookies");
                    Intent intentHistory = new Intent(this, HistoryActivity.class);
                    intentHistory.putExtra("vin", etVin.getText().toString());
                    intentHistory.putExtra("captcha", captcha);
                    intentHistory.putExtra("cookies", cookies);
                    intentHistory.putExtra("report", report);
                    startActivityForResult(intentHistory, REQUEST_CODE_CAPTCHA);
                    break;
                case REQUEST_CODE_DTP:
                    captcha = data.getStringExtra("captcha");
                    cookies = data.getStringExtra("cookies");
                    Intent intentDtp = new Intent(this, DtpActivity.class);
                    intentDtp.putExtra("vin", etVin.getText().toString());
                    intentDtp.putExtra("captcha", captcha);
                    intentDtp.putExtra("cookies", cookies);
                    intentDtp.putExtra("report", report);
                    startActivityForResult(intentDtp, REQUEST_CODE_CAPTCHA);
                case REQUEST_CODE_WANTED:
                    captcha = data.getStringExtra("captcha");
                    cookies = data.getStringExtra("cookies");
                    Intent intentWanted = new Intent(this, WantedActivity.class);
                    intentWanted.putExtra("vin", etVin.getText().toString());
                    intentWanted.putExtra("captcha", captcha);
                    intentWanted.putExtra("cookies", cookies);
                    intentWanted.putExtra("report", report);
                    startActivityForResult(intentWanted, REQUEST_CODE_CAPTCHA);
                case REQUEST_CODE_RESTRICT:
                    captcha = data.getStringExtra("captcha");
                    cookies = data.getStringExtra("cookies");
                    Intent intentRestrict = new Intent(this, RestrictActivity.class);
                    intentRestrict.putExtra("vin", etVin.getText().toString());
                    intentRestrict.putExtra("captcha", captcha);
                    intentRestrict.putExtra("cookies", cookies);
                    intentRestrict.putExtra("report", report);
                    startActivityForResult(intentRestrict, REQUEST_CODE_CAPTCHA);
            }
        }

    }

    @Override
    public void returnCaptcha(Captcha captcha) {
        ivCaptchaDialog.setImageBitmap(captcha.getCaptcha());
        //cookies = captcha.getCookies();
        //Log.d("!!!", captcha.getCaptcha().);
        captchaDialog.show();

    }


}
