package com.fast_ad.fast_ad.Models;

import org.json.JSONArray;

public class Places {
    String key;
    String name;
    String zone;
    JSONArray tarif;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public JSONArray getTarif() {
        return tarif;
    }

    public void setTarif(JSONArray tarif) {
        this.tarif = tarif;
    }
}
