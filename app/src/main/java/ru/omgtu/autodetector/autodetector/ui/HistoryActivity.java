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

        Map<String, String> carType = parseStringArray(R.array.carType);
        //Log.d("!!!!", carType.get("23"));
        Map<String, String> basicInfo = new LinkedHashMap<String, String>();
        basicInfo.put("Марка, модель: ", carHistory.getRequestResult().getVehicle().getModel());
        basicInfo.put("Год выпуска: ", carHistory.getRequestResult().getVehicle().getYear());
        basicInfo.put("Категория транспортного средства: ", carHistory.getRequestResult().getVehicle().getCategory());
        basicInfo.put("Кузов: ", carHistory.getRequestResult().getVehicle().getBodyNumber());
        basicInfo.put("Цвет: ", carHistory.getRequestResult().getVehicle().getColor());
        basicInfo.put("Рабочий объем (см3): ", carHistory.getRequestResult().getVehicle().getEngineVolume());
        basicInfo.put("Мощность: ", carHistory.getRequestResult().getVehicle().getPowerHp() + " л.с. / " +
                carHistory.getRequestResult().getVehicle().getPowerKwt() + " кВт");
        basicInfo.put("№ двигателя: ", carHistory.getRequestResult().getVehicle().getBodyNumber());
        basicInfo.put("Тип: ", carType.get(carHistory.getRequestResult().getVehicle().getType())); // обработка типа
        basicInfo.put("ПТС: ", carHistory.getRequestResult().getVehiclePassport().getNumber());
        for (Map.Entry<String, String> entry : basicInfo.entrySet()) {
            Log.d("!!!!", entry.getKey() + entry.getValue());
            
        }

        Map<String, String> historyInfo = new LinkedHashMap<String, String>();


        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void returnHistoryError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        setResult(RESULT_CANCELED);
        finish();
    }
}
