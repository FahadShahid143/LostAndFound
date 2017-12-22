package com.example.fahadshahid.lostandfound.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Fahad Shahid on 11/14/2017.
 */

public class Lost {


    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("lost_item_name")
    @Expose
    private String item_name;

    @SerializedName("lost_item_description")
    @Expose
    private String item_description;

    @SerializedName("lost_date")
    @Expose
    private String lost_date;

    @SerializedName("person_name")
    @Expose
    private String person_name;

    @SerializedName("person_phoneNo")
    @Expose
    private String person_phoneNo;

    @SerializedName("person_address")
    @Expose
    private String person_address;

    public Lost(Integer id, String item_name, String item_description, String lost_date, String person_name, String person_phoneNo, String person_address) {
        this.id = id;
        this.item_name = item_name;
        this.item_description = item_description;
        this.lost_date = lost_date;
        this.person_name = person_name;
        this.person_phoneNo = person_phoneNo;
        this.person_address = person_address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getLost_date() {
        return lost_date;
    }

    public void setLost_date(String lost_date) {
        this.lost_date = lost_date;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_phoneNo() {
        return person_phoneNo;
    }

    public void setPerson_phoneNo(String person_phoneNo) {
        this.person_phoneNo = person_phoneNo;
    }

    public String getPerson_address() {
        return person_address;
    }

    public void setPerson_address(String person_address) {
        this.person_address = person_address;
    }
}
