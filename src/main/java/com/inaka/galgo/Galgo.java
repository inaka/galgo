package com.inaka.galgo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

public class Galgo {

    public static final String ARG_OPTIONS = "galgo.options";
    private static final String TAG = "Galgo";
    private static GalgoService sService;
    private static ServiceConnection sConnection;
    private static GalgoOptions sOptions;

    /**
     * * Starts a new Galgo with custom {@link com.inaka.galgo.GalgoOptions}
     * @param context Context
     * @param options Custom {@link com.inaka.galgo.GalgoOptions}
     */
    public static void enable(Context context, GalgoOptions options) {
        sOptions = options;
        init(context);
    }

    /**
     * Starts a new Galgo with default {@link com.inaka.galgo.GalgoOptions}
     * @param context Context
     */
    public static void enable(Context context) {
        sOptions = new GalgoOptions.Builder().build();
        init(context);
    }

    private static void init(Context context) {

        sConnection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName className, IBinder service) {
                GalgoService.LocalBinder binder = (GalgoService.LocalBinder) service;
                sService = binder.getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName c) {
            }
        };

        // start a new service with our options
        Intent intent = new Intent(context, GalgoService.class);
        intent.putExtra(ARG_OPTIONS, sOptions);
        context.bindService(intent, sConnection, Context.BIND_AUTO_CREATE);
    }

    public static void disable(Context context) {
        context.unbindService(sConnection);
    }

    /**
     * Logs a String message to the screen. This String will be overlayed on top of the
     * UI elements currently displayed on screen. As a side effect, this message will
     * also be logged to the standard output via {@link android.util.Log}.
     *
     * @param message String to be displayed
     */
    public static void log(String message) {
        Log.i(TAG, message);
        if(sService != null) {
            sService.displayText(message);
        }
    }
}
