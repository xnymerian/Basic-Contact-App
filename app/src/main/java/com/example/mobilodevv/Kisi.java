package com.example.mobilodevv;

import java.io.Serializable;

public class Kisi implements Serializable {
    String adsoyad;
    String telefonno;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefonno() {
        return telefonno;
    }

    public void setTelefonno(String telefonno) {
        this.telefonno = telefonno;
    }

    public String getAdsoyad() {
        return adsoyad;
    }

    public void setAdsoyad(String adsoyad) {
        this.adsoyad = adsoyad;
    }
}
