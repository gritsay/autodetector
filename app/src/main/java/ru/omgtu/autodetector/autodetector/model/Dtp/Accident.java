
package ru.omgtu.autodetector.autodetector.model.Dtp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Accident {

    @SerializedName("AccidentDateTime")
    @Expose
    private String accidentDateTime;
    @SerializedName("VehicleModel")
    @Expose
    private String vehicleModel;
    @SerializedName("VehicleDamageState")
    @Expose
    private String vehicleDamageState;
    @SerializedName("RegionName")
    @Expose
    private String regionName;
    @SerializedName("AccidentNumber")
    @Expose
    private String accidentNumber;
    @SerializedName("AccidentType")
    @Expose
    private String accidentType;
    @SerializedName("VehicleMark")
    @Expose
    private String vehicleMark;
    @SerializedName("DamagePoints")
    @Expose
    private List<String> damagePoints = null;
    @SerializedName("VehicleYear")
    @Expose
    private String vehicleYear;

    public String getAccidentDateTime() {
        return accidentDateTime;
    }

    public void setAccidentDateTime(String accidentDateTime) {
        this.accidentDateTime = accidentDateTime;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleDamageState() {
        return vehicleDamageState;
    }

    public void setVehicleDamageState(String vehicleDamageState) {
        this.vehicleDamageState = vehicleDamageState;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getAccidentNumber() {
        return accidentNumber;
    }

    public void setAccidentNumber(String accidentNumber) {
        this.accidentNumber = accidentNumber;
    }

    public String getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(String accidentType) {
        this.accidentType = accidentType;
    }

    public String getVehicleMark() {
        return vehicleMark;
    }

    public void setVehicleMark(String vehicleMark) {
        this.vehicleMark = vehicleMark;
    }

    public List<String> getDamagePoints() {
        return damagePoints;
    }

    public void setDamagePoints(List<String> damagePoints) {
        this.damagePoints = damagePoints;
    }

    public String getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(String vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

}
