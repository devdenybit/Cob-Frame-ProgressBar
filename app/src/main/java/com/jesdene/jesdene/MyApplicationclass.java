
package com.jesdene.jesdene;

import android.content.res.Resources;
import com.jesdene.jesdenias.Applicationclass;


public class MyApplicationclass extends Applicationclass {


    private static MyApplicationclass myApp;
    public static Resources resource;

    @Override
    public void onCreate() {
        super.onCreate();

        resource = getResources();
        myApp = this;


    }




}

