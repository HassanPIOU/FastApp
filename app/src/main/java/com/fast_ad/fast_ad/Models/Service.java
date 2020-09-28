package com.fast_ad.fast_ad.Models;

import org.json.JSONArray;


public class Service {
    String key;
    String name;
    String hint;
    String logo;
    int menu;
    int actif;
    String adresse_collect;
    String adresse_livraison;
    JSONArray sous_menu;


    public Service() {
    }

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

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }

    public JSONArray getSous_menu() {
        return sous_menu;
    }

    public void setSous_menu(JSONArray sous_menu) {
        this.sous_menu = sous_menu;
    }

    public int getActif() {
        return actif;
    }

    public void setActif(int actif) {
        this.actif = actif;
    }

    public String getAdresse_collect() {
        return adresse_collect;
    }

    public void setAdresse_collect(String adresse_collect) {
        this.adresse_collect = adresse_collect;
    }

    public String getAdresse_livraison() {
        return adresse_livraison;
    }

    public void setAdresse_livraison(String adresse_livraison) {
        this.adresse_livraison = adresse_livraison;
    }
}
