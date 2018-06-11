package com.example.harshita.jobwidgetapp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Job {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("position_title")
    @Expose
    public String positionTitle;
    @SerializedName("organization_name")
    @Expose
    private String organizationName;
    @SerializedName("rate_interval_code")
    @Expose
    private String rateIntervalCode;
    @SerializedName("minimum")
    @Expose
    private Integer minimum;
    @SerializedName("maximum")
    @Expose
    private Integer maximum;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("locations")
    @Expose
    private List<String> locations = null;
    @SerializedName("url")
    @Expose
    private String url;
}