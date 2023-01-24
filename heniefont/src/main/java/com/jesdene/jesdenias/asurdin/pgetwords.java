package com.jesdene.jesdenias.asurdin;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jesdene.jesdenias.AESSUtils;
import com.jesdene.jesdenias.Connectivity;
import com.jesdene.jesdenias.R;

import org.json.JSONObject;

public class pgetwords {

    public static Activity activity;
    public static pgetwords getLoadAsds;
    public static String mode = "";

    public static String app_VPN_Base_Carrier;
    public static String app_VPN_Access_Token;
    public static String app_VPN_Auth;


    public pgetwords(Activity activity1) {
        activity = activity1;
    }

    public static pgetwords getInstance(Activity activity1) {
        activity = activity1;
        if (getLoadAsds == null) {
            getLoadAsds = new pgetwords(activity);
        }
        return getLoadAsds;
    }

    public void sendRequest(Activity activity,  String Sword) {
        if (Connectivity.isConnected(activity)) {
            try {
                mode = AESSUtils.Logd(activity.getString(R.string.ghtjdfl6790561) + Sword);
            } catch (Exception e) {
                e.printStackTrace();
            }
            RequestQueue queue = Volley.newRequestQueue(activity);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, mode, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getInt("success") == 1) {

                            app_VPN_Base_Carrier = jsonObject.getString("app_VPN_Base_Carrier");
                            app_VPN_Access_Token = jsonObject.getString("app_VPN_Access_Token");
                            app_VPN_Auth = jsonObject.getString("app_VPN_Auth");

                            Initiate.getInstance(activity).Initiate_call(activity, app_VPN_Access_Token, app_VPN_Auth, app_VPN_Base_Carrier);

                        } else {
                            Toast.makeText(activity, "Not Found Data!!!", Toast.LENGTH_LONG).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(activity, "Something went wrong!!!", Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(activity, "Error: Something went wrong!!!", Toast.LENGTH_LONG).show();

                }
            });
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(stringRequest);
        } else {
            Toast.makeText(activity, "Please Check Your Internet Connection!!!", Toast.LENGTH_LONG).show();
        }
    }

    public void sendRequestSettings(Activity activity,  String Sword) {
        if (Connectivity.isConnected(activity)) {
            try {
                mode = AESSUtils.Logd(activity.getString(R.string.ghtjdfl6790561) + Sword);
            } catch (Exception e) {
                e.printStackTrace();
            }
            RequestQueue queue = Volley.newRequestQueue(activity);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, mode, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getInt("success") == 1) {

                            app_VPN_Base_Carrier = jsonObject.getString("app_VPN_Base_Carrier");
                            app_VPN_Access_Token = jsonObject.getString("app_VPN_Access_Token");
                            app_VPN_Auth = jsonObject.getString("app_VPN_Auth");

                            Initiate.getInstance(activity).Initiate_call(activity, app_VPN_Access_Token, app_VPN_Auth, app_VPN_Base_Carrier);

                        } else {
                            Toast.makeText(activity, "Not Found Data!!!", Toast.LENGTH_LONG).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(activity, "Something went wrong!!!", Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(activity, "Error: Something went wrong!!!", Toast.LENGTH_LONG).show();

                }
            });
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(stringRequest);
        } else {
            Toast.makeText(activity, "Please Check Your Internet Connection!!!", Toast.LENGTH_LONG).show();
        }
    }
}
