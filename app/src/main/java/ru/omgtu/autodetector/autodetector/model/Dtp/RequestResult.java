
package ru.omgtu.autodetector.autodetector.model.Dtp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestResult {

    @SerializedName("errorDescription")
    @Expose
    private String errorDescription;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("Accidents")
    @Expose
    private List<Accident> accidents = null;

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<Accident> getAccidents() {
        return accidents;
    }

    public void setAccidents(List<Accident> accidents) {
        this.accidents = accidents;
    }

}
