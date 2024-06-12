package com.example.miniuper.Data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Const {

    public static final String Key_Users="Users";

    public static final String Key_Customers="Customers";
    public static final String Key_Drivers="Drivers";
    public static final String Key_Employees="Employees";
    public static final String Key_AcceptedForDriver="AcceptedForDriver";
    public static final String Key_Trip_UnAccepted="All  UnAccepted Trip ";
    public static final String Key_Trip_UnAcceptedCustomer="All Trip For Customer ";
    public static final String Key_Trip_Accepted="Trip Accepted";
    public static final String Key_Trip_AcceptedForCustomer="All  Accepted Trip For Customer";
    public static final String Key_CarsNotAssignedYet="Cars Not Assigned Yet";
    public static final String Key_CarsThatAssigned="Cars that Assigned ";
    public static final String Key_Complains="Complains";
    public  static FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    public static DatabaseReference ref= FirebaseDatabase.getInstance().getReference();

    public static FirebaseAuth auth=FirebaseAuth.getInstance();
}
