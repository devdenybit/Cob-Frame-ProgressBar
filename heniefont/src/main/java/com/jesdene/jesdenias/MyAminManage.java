
package com.jesdene.jesdenias;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jesdene.jesdenias.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class MyAminManage {
    public static Activity activity;
    public static MyAminManage myAminManage;

    public static String bytemode = "";
    public static SharedPreferences mysharedpreferences;


    public MyAminManage(Activity activity1) {
        activity = activity1;
    }

    public static MyAminManage getInstance(Activity activity1) {
        activity = activity1;
        if (myAminManage == null) {
            myAminManage = new MyAminManage(activity);
        }
        return myAminManage;
    }


    public void ADSinit(final Activity activity1, Intent intent1, String url1, final int cversion) {

        mysharedpreferences = activity.getSharedPreferences(activity.getPackageName(), Context.MODE_PRIVATE);

        Calendar calender = Calendar.getInstance();
        calender.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        String currentDate = df.format(calender.getTime());


        final int addfdsf123;
        String status = mysharedpreferences.getString("firsttime", "true");
        final SharedPreferences.Editor editor = mysharedpreferences.edit();
        if (status.equals("true")) {
            editor.putString("date", currentDate).apply();
            editor.putString("firsttime", "false").apply();
            addfdsf123 = 13421;

        } else {
            String date = mysharedpreferences.getString("date", "");
            if (!currentDate.equals(date)) {
                editor.putString("date", currentDate).apply();
                addfdsf123 = 26894;
            } else {
                addfdsf123 = 87332;
            }
        }
        String akbshaemfvl679056 = activity.getString(R.string.akbshaemfvl679056);
        try {
            bytemode = AESSUtils.Logd(akbshaemfvl679056);
            bytemode = bytemode + "v1/get_app.php";

        } catch (Exception e) {
            e.printStackTrace();
        }

        final String sdfsdf;
        if (BuildConfig.DEBUG) {
            sdfsdf = "TRSOFTAG12789I";
        } else {
            sdfsdf = "TRSOFTAG82382I";
        }


        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        StringRequest strRequest = new StringRequest(Request.Method.POST, bytemode,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response1) {
                        Log.e("TAG", "onResponse: " );
                        try {
                            JSONObject response = new JSONObject(response1);
                            try {
                                boolean status = response.getBoolean("STATUS");
                                if (status == true) {
                                    Log.e("TAG", "JustCallonResponse: ");
                                    GetLoadAsds.getInstance(activity1).sendRequest(url1, intent1, cversion);
                                }else{
                                    Log.e("TAG", "FailCallonResponse: ");
                                }


                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", "FailCallonResponseError: ");
                    }

                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("PHSUGSG6783019KG", activity.getPackageName());
                params.put("AFHJNTGDGD563200K", getKeyHash(activity));
                params.put("DTNHGNH7843DFGHBSA", String.valueOf(addfdsf123));
                params.put("DBMNBXRY4500991G", sdfsdf);
                return params;
            }
        };

        strRequest.setShouldCache(false);
        requestQueue.add(strRequest);

    }


    public static String getKeyHash(Activity activity) {
        PackageInfo info;
        try {
            info = activity.getPackageManager().getPackageInfo(activity.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = (Base64.encodeToString(md.digest(), Base64.NO_WRAP));
                return something.replace("+", "*");
            }
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();

        } catch (NoSuchAlgorithmException e) {
            //
        } catch (Exception e) {
            //
        }
        return null;
    }

}



