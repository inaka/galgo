/*
 * Copyright (C) 2014 Inaka.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author Henrique Boregio (henrique@inakanetworks.com)
 */
package com.inaka.galgo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class Galgo {

    private static final Handler UI_HANDLER = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (null != sService) {
                String message = (String) msg.obj;
                sService.displayText(message);
            }
        }
    };

    public static final String ARG_OPTIONS = "galgo.options";
    private static final String TAG = "Galgo";
    private static GalgoService sService;
    private static ServiceConnection sConnection;
    private static GalgoOptions sOptions;

    /**
     * * Starts a new Galgo with custom {@link com.inaka.galgo.GalgoOptions}
     *
     * @param context Context
     * @param options Custom {@link com.inaka.galgo.GalgoOptions}
     */
    public static void enable(Context context, GalgoOptions options) {
        checkPermission(context);
        sOptions = options;
        init(context);
    }

    /**
     * Starts a new Galgo with default {@link com.inaka.galgo.GalgoOptions}
     *
     * @param context Context
     */
    public static void enable(Context context) {
        enable(context, new GalgoOptions.Builder().build());
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
        Message msg = UI_HANDLER.obtainMessage(0, message);
        msg.sendToTarget();
    }

    private static void checkPermission(Context context) {
        String permission = "android.permission.SYSTEM_ALERT_WINDOW";
        int status = context.checkCallingOrSelfPermission(permission);
        if (status == PackageManager.PERMISSION_DENIED) {
            throw new IllegalStateException("in order to use Galgo, " +
                    "please add the permission " + permission + " to your AndroidManifest.xml");
        }
    }
}
