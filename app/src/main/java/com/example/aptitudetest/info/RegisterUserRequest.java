package com.example.aptitudetest.info;

import com.google.gson.annotations.SerializedName;

public class RegisterUserRequest {

    @SerializedName("name")
    private String name;
    @SerializedName("phone")
    private String phoneno;
    @SerializedName("job_role_id")
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
