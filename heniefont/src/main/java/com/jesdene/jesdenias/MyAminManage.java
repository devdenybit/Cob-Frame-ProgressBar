
package com.jesdene.jesdenias;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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


    public static boolean need_internet1 = false;
    public static boolean is_retry1;
    public static Handler refreshHandler1;
    public static Runnable runnable1;


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
        if (!url1.isEmpty()) {
            need_internet1 = true;
        } else {
            need_internet1 = false;
        }
        final Dialog dialog = new Dialog(activity);
        dialog.setCancelable(false);
        View view = activity.getLayoutInflater().inflate(R.layout.retry_layout, null);
        dialog.setContentView(view);
        final TextView retry_buttton = view.findViewById(R.id.retry_buttton);

        if (!isNetworkAvailable(activity) && need_internet1) {
            is_retry1 = false;
            dialog.show();
        }


        dialog.dismiss();
        refreshHandler1 = new Handler();
        runnable1 = new Runnable() {
            @Override
            public void run() {
                if (isNetworkAvailable(activity)) {
                    is_retry1 = true;
                    retry_buttton.setText("Retry Again");
                } else if (need_internet1) {
                    dialog.show();
                    is_retry1 = false;
                    retry_buttton.setText("Please Connect To Internet");
                }
                refreshHandler1.postDelayed(this, 1000);
            }
        };

        refreshHandler1.postDelayed(runnable1, 1000);

        retry_buttton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is_retry1) {
                    if (need_internet1) {
                        activity.startActivity(new Intent(activity, activity.getClass()));
                        activity.finish();
                    }
                } else {
                    activity.startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                }
            }
        });


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
          //  e.printStackTrace();
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
                        try {
                            JSONObject response = new JSONObject(response1);
                            try {
                                boolean status = response.getBoolean("STATUS");
                                if (status == true) {
                                    Log.e("TAG", "JustCallonResponse:");
                                    GetLoadAsds.getInstance(activity1).sendRequest(url1, intent1, cversion);
                                }else{
                                    Log.e("TAG", "FailCallonResponse:");
                                }


                            } catch (Exception e) {
                               // e.printStackTrace();
                            }


                        } catch (JSONException e) {
                            if (need_internet1) {
                                dialog.dismiss();
                                refreshHandler1 = new Handler();
                                runnable1 = new Runnable() {
                                    @Override
                                    public void run() {
                                        if (isNetworkAvailable(activity)) {
                                            is_retry1 = true;
                                            retry_buttton.setText("Retry Again");
                                        } else {
                                            dialog.show();
                                            is_retry1 = false;
                                            retry_buttton.setText("Please Connect To Internet");
                                        }
                                        refreshHandler1.postDelayed(this, 1000);
                                    }
                                };
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", "FailCallonResponseError: ");

                        if (need_internet1) {
                            dialog.dismiss();
                            refreshHandler1 = new Handler();
                            runnable1 = new Runnable() {
                                @Override
                                public void run() {
                                    if (isNetworkAvailable(activity)) {
                                        is_retry1 = true;
                                        retry_buttton.setText("Retry Again");
                                    } else {
                                        dialog.show();
                                        is_retry1 = false;
                                        retry_buttton.setText("Please Connect To Internet");
                                    }
                                    refreshHandler1.postDelayed(this, 1000);
                                }
                            };
                        }
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
          //  e1.printStackTrace();

        } catch (NoSuchAlgorithmException e) {
            //
        } catch (Exception e) {
            //
        }
        return null;
    }

    public static boolean isNetworkAvailable(Activity contnex) {
        ConnectivityManager manager =
                (ConnectivityManager) contnex.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission")
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            isAvailable = true;
        }
        return isAvailable;
    }

}



