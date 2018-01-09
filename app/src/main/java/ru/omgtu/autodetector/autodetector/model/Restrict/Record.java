
package ru.omgtu.autodetector.autodetector.model.Restrict;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {

    @SerializedName("regname")
    @Expose
    private String regname;
    @SerializedName("osnOgr")
    @Expose
    private String osnOgr;
    @SerializedName("gid")
    @Expose
    private String gid;
    @SerializedName("tsyear")
    @Expose
    private String tsyear;
    @SerializedName("tsVIN")
    @Expose
    private String tsVIN;
    @SerializedName("codDL")
    @Expose
    private Integer codDL;
    @SerializedName("dateogr")
    @Expose
    private String dateogr;
    @SerializedName("ogrkod")
    @Expose
    private String ogrkod;
    @SerializedName("tsmodel")
    @Expose
    private String tsmodel;
    @SerializedName("tsKuzov")
    @Expose
    private String tsKuzov;
    @SerializedName("codeTo")
    @Expose
    private Integer codeTo;
    @SerializedName("dateadd")
    @Expose
    private String dateadd;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("regid")
    @Expose
    private String regid;
    @SerializedName("divtype")
    @Expose
    private String divtype;
    @SerializedName("divid")
    @Expose
    private String divid;

    public String getRegname() {
        return regname;
    }

    public void setRegname(String regname) {
        this.regname = regname;
    }

    public String getOsnOgr() {
        return osnOgr;
    }

    public void setOsnOgr(String osnOgr) {
        this.osnOgr = osnOgr;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getTsyear() {
        return tsyear;
    }

    public void setTsyear(String tsyear) {
        this.tsyear = tsyear;
    }

    public String getTsVIN() {
        return tsVIN;
    }

    public void setTsVIN(String tsVIN) {
        this.tsVIN = tsVIN;
    }

    public Integer getCodDL() {
        return codDL;
    }

    public void setCodDL(Integer codDL) {
        this.codDL = codDL;
    }

    public String getDateogr() {
        return dateogr;
    }

    public void setDateogr(String dateogr) {
        this.dateogr = dateogr;
    }

    public String getOgrkod() {
        return ogrkod;
    }

    public void setOgrkod(String ogrkod) {
        this.ogrkod = ogrkod;
    }

    public String getTsmodel() {
        return tsmodel;
    }

    public void setTsmodel(String tsmodel) {
        this.tsmodel = tsmodel;
    }

    public String getTsKuzov() {
        return tsKuzov;
    }

    public void setTsKuzov(String tsKuzov) {
        this.tsKuzov = tsKuzov;
    }

    public Integer getCodeTo() {
        return codeTo;
    }

    public void setCodeTo(Integer codeTo) {
        this.codeTo = codeTo;
    }

    public String getDateadd() {
        return dateadd;
    }

    public void setDateadd(String dateadd) {
        this.dateadd = dateadd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid;
    }

    public String getDivtype() {
        return divtype;
    }

    public void setDivtype(String divtype) {
        this.divtype = divtype;
    }

    public String getDivid() {
        return divid;
    }

    public void setDivid(String divid) {
        this.divid = divid;
    }

}
