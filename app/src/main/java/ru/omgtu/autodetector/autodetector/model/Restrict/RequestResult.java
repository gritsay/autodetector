
package ru.omgtu.autodetector.autodetector.model.Restrict;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestResult {

    @SerializedName("records")
    @Expose
    private List<Record> records = null;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("error")
    @Expose
    private Integer error;

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

}
