
package ru.omgtu.autodetector.autodetector.model.Dtp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarDtp {

    @SerializedName("RequestResult")
    @Expose
    private RequestResult requestResult;
    @SerializedName("vin")
    @Expose
    private String vin;
    @SerializedName("status")
    @Expose
    private Integer status;

    public RequestResult getRequestResult() {
        return requestResult;
    }

    public void setRequestResult(RequestResult requestResult) {
        this.requestResult = requestResult;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
