package ru.omgtu.autodetector.autodetector.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import ru.omgtu.autodetector.autodetector.R;
import ru.omgtu.autodetector.autodetector.model.History.CarHistory;
import ru.omgtu.autodetector.autodetector.model.Report;
import ru.omgtu.autodetector.autodetector.network.gibdd.GibddService;
import ru.omgtu.autodetector.autodetector.network.gibdd.NetworkGibddBuilder;
import ru.omgtu.autodetector.autodetector.presenter.HistoryPresenter;
import ru.omgtu.autodetector.autodetector.view.HistoryView;

public class HistoryActivity extends AppCompatActivity implements HistoryView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Intent intent;
        intent = getIntent();
        String vin = intent.getStringExtra("vin");
        String cookies = intent.getStringExtra("cookies");
        String captcha = intent.getStringExtra("captcha");

        Map queryFields = new HashMap<String, String>();
        queryFields.put("vin", vin);
        queryFields.put("captchaWord", captcha);
        queryFields.put("checkType", "history");

        NetworkGibddBuilder.init("http://check.gibdd.ru/");
        GibddService service = NetworkGibddBuilder.getGibddService();

        HistoryPresenter historyPresenter = new HistoryPresenter(this, service, cookies, queryFields);
        historyPresenter.checkHistory();
    }

    private Map<String, String> parseStringArray(int stringArrayResourceId) {
        String[] carTypeArray = getResources().getStringArray(stringArrayResourceId);
        Map<String, String> carType = new HashMap<String, String>();
        for (String entry : carTypeArray) {
            String[] splitResult = entry.split("\\|", 2);
            carType.put(splitResult[0], splitResult[1]);
        }

        return carType;
    }

    @Override
    public void returnHistory(CarHistory carHistory) {

        Report report = (Report)getIntent().getParcelableExtra("report");

        Map<String, String> carType = parseStringArray(R.array.carType);
        LinkedHashMap<String, String> basicInfo = new LinkedHashMap<String, String>();

        basicInfo.put(getResources().getString(R.string.model), carHistory.getRequestResult().getVehicle().getModel());
        basicInfo.put(getResources().getString(R.string.year), carHistory.getRequestResult().getVehicle().getYear());
        basicInfo.put(getResources().getString(R.string.category), carHistory.getRequestResult().getVehicle().getCategory());
        basicInfo.put(getResources().getString(R.string.bodyNumder), carHistory.getRequestResult().getVehicle().getBodyNumber());
        basicInfo.put(getResources().getString(R.string.color), carHistory.getRequestResult().getVehicle().getColor());
        basicInfo.put(getResources().getString(R.string.engVolume), carHistory.getRequestResult().getVehicle().getEngineVolume());
        basicInfo.put(getResources().getString(R.string.power), carHistory.getRequestResult().getVehicle().getPowerHp() +
                getResources().getString(R.string.hp) +
                carHistory.getRequestResult().getVehicle().getPowerKwt() +
                getResources().getString(R.string.kwt));
        basicInfo.put(getResources().getString(R.string.bodyNumder), carHistory.getRequestResult().getVehicle().getBodyNumber());
        basicInfo.put(getResources().getString(R.string.type), carType.get(carHistory.getRequestResult().getVehicle().getType())); // обработка типа
        basicInfo.put(getResources().getString(R.string.pts), carHistory.getRequestResult().getVehiclePassport().getNumber());

        report.setReport("Базовые сведения", basicInfo);

        /*<String, Map<String, String>> r = report.getReport();
        //Map.Entry<String, Map<String, String>>
        Map<String, String> r1 = r.get("Базовые сведения");
        for (Map.Entry<String, String> entry : r1.entrySet()) {
            Log.d("!!!!", entry.getKey() + entry.getValue());
            
        }*/

        Map<String, String> historyInfo = new LinkedHashMap<String, String>();
        Intent intent = new Intent();
        intent.putExtra("report", report);
        intent.putExtra("next", "dtp");
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void returnHistoryError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        setResult(RESULT_CANCELED);
        finish();
    }


}
