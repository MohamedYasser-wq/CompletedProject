package com.example.miniuper.Data;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefrence {

    private static final String PREF_NAME = "UserPreferences";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static final String KEY_USER_Type = "USER_Type";
    private static final String KEY_Car_model = "Car_Model";
    private static final String KEY_Car_Year = "Car_Year";
    private static final String KEY_Car_Color = "Car_Color";
    private static final String KEY_Car_PlateNumber = "Car_PlateNumber";

    private static final String KEY_Driver_Name = "Driver_Name";
    private static final String KEY_Driver_Phone = "Driver_Phone";
    private static final String KEY_Driver_Email = "Driver_Email";
    private static final String KEY_Date = "Key_Date";
    private static final String KEY_Car_Password = "Driver_Pass";


    private static final String KEY_startPoint = "startPoint";
    private static final String KEY_DriverId = "Key_Id";
    private static final String KEY_Time = "Time__";

    public static void init(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
    }

    public static void setUserType(String type) {
        editor.putString(KEY_USER_Type, type).apply();
    }

    public static String getUserType() {
        return sharedPreferences.getString(KEY_USER_Type, "");
    }


    public static void setCarModel(String model) {
        editor.putString(KEY_Car_model, model).apply();
    }

    public static String getCarModel() {
        return sharedPreferences.getString(KEY_Car_model, "");
    }

    public static void set_Car_Year(String Year) {
        editor.putString(KEY_Car_Year, Year).apply();
    }

    public static String get_Car_Year() {
        return sharedPreferences.getString(KEY_Car_Year, "");
    }

    public static void setCar_PlateNumber(String plateNumber) {
        editor.putString(KEY_Car_PlateNumber, plateNumber).apply();
    }

    public static String getCar_PlateNumber() {
        return sharedPreferences.getString(KEY_Car_PlateNumber, "");
    }

    public static void setCar_color(String color) {
        editor.putString(KEY_Car_Color, color).apply();
    }

    public static String getCar_Color() {
        return sharedPreferences.getString(KEY_Car_Color, "");
    }




    public static void setDriverName(String name) {
        editor.putString(KEY_Driver_Name, name).apply();
    }

    public static String getDriverName() {
        return sharedPreferences.getString(KEY_Driver_Name, "");
    }

    public static void set_Driver_Phone(String phone) {
        editor.putString(KEY_Driver_Phone, phone).apply();
    }

    public static String get_Driver_Phone() {
        return sharedPreferences.getString(KEY_Driver_Phone, "");
    }

    public static void set_Driver_Password (String pass) {
        editor.putString(KEY_Car_Password, pass).apply();
    }

    public static String getDriver_Password() {
        return sharedPreferences.getString(KEY_Car_Password, "");
    }

    public static void set_Driver_Email (String email) {
        editor.putString(KEY_Driver_Email, email).apply();
    }

    public static String get_Driver_Email() {
        return sharedPreferences.getString(KEY_Driver_Email, "");
    }

    public static void set_Date (String date) {
        editor.putString(KEY_Date, date).apply();
    }

    public static String get_Date() {
        return sharedPreferences.getString(KEY_Date, "");
    }






















/////////////////////////////
public static void set_Driver_id (String id) {
    editor.putString(KEY_DriverId, id).apply();
}

    public static String getDriver_id() {
        return sharedPreferences.getString(KEY_DriverId, "");
    }

//    public static void set_Driver_Email (String email) {
//        editor.putString(KEY_Driver_Email, email).apply();
//    }
//
//    public static String get_Driver_Email() {
//        return sharedPreferences.getString(KEY_Driver_Email, "");
//    }
//
//    public static void set_Date (String date) {
//        editor.putString(KEY_Date, date).apply();
//    }
//
//    public static String get_Date() {
//        return sharedPreferences.getString(KEY_Date, "");
//    }
//
//
//
//
//
//







}
