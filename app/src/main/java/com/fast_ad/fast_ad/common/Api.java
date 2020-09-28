package com.fast_ad.fast_ad.common;

public class Api {
    public static String TOKEN = "YdlXM42mVhj5dqR4N1e0IxmghV2Sn";
    public static String U_Identifiant = "oJxfI-yIoWNLFqFg1pCcRNiOahFBTv7_";


    private static String URL = "http://fastserviceapp.com/api/web/v1/";

    // Server user login url
    public static String URL_LOGIN = URL+"users/connecte_useraccount";

    // Server user register url
    public static String URL_REGISTER = URL+"users/create_useraccount";

    public static String URL_CONFIRMATION = URL+"users/confirm_useraccount";


    public static String URL_MENU_DATA = URL+"services/get_info";

    public static String URL_ADDRESS = URL+"adresses/get_info";

    public static String URL_PROFIL = URL+"update-profil";



}
