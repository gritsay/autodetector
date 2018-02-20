package ru.omgtu.autodetector.autodetector.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import ru.omgtu.autodetector.autodetector.model.Dtp.CarDtp;
import ru.omgtu.autodetector.autodetector.model.History.CarHistory;

/**
 * Created by denis on 08.02.18.
 */

public class Report implements Parcelable{


    Map<String, HashMap<String, String>> report = new HashMap<String, HashMap<String, String>>();


    protected Report(Parcel in) {
        in.readMap(report, LinkedHashMap.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeMap(report);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Report> CREATOR = new Creator<Report>() {
        @Override
        public Report createFromParcel(Parcel in) {
            return new Report(in);
        }

        @Override
        public Report[] newArray(int size) {
            return new Report[size];
        }
    };

    public void setReport(String key, LinkedHashMap<String, String> value){
        report.put(key, value);
    }


    public Map<String, HashMap<String, String>> getReport() {
        return report;
    }
    public Report(){
    }
}
