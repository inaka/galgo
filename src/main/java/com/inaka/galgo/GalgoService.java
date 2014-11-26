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

import android.app.Service;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class GalgoService extends Service {
	
	/**
     * Priority constant for the displayText method; use defined text color.
     */
    public static final int INFO = 1;
    /**
     * Priority constant for the displayText method; use defined error text color.
     */
    public static final int ERROR = 2;

    private final IBinder mBinder = new LocalBinder();
    private TextView mTextView;
    private GalgoOptions mOptions;
    private FileOutputStream out;
    private PrintStream p;


    public class LocalBinder extends Binder {
        public GalgoService getService() {
            return GalgoService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        this.mOptions = (GalgoOptions) intent.getExtras().get(Galgo.ARG_OPTIONS);
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mTextView = new TextView(this);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.addView(mTextView, params);

        Resources res = getResources();
        String dirName = res.getString(R.string.app_name);
        File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), dirName);
        path.mkdirs();
        File file = new File(path, "Log.txt");

        try {
            out = new FileOutputStream(file, true);
            p = new PrintStream(out, true);
        } catch(FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void displayText(String text, Integer priority) {

        Spannable spannable = new SpannableString(text);
        spannable.setSpan(new BackgroundColorSpan(mOptions.getBackgroundColor()),0, text.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        int textColor;
        switch (priority){
            case ERROR:
                textColor = mOptions.getErrorTextColor();
                break;
            default:
                textColor = mOptions.getTextColor();
                break;
        }
        spannable.setSpan(new ForegroundColorSpan(textColor),0,text.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        if(mTextView.getLineCount() > mOptions.getNumberOfLines()) {
            mTextView.setText(spannable);
        } else {
            mTextView.append(spannable);
        }

        mTextView.setTextSize(mOptions.getTextSize());
        mTextView.append("\n");

        if(mOptions.getLogToTextFile()) {
            p.print(text + "\n");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTextView != null) {
            WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
            wm.removeView(mTextView);
        }
        if(out != null) {
            try {
                p.close();
                out.close();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}