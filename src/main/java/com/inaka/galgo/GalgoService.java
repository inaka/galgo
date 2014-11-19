package com.inaka.galgo;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.IBinder;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.WindowManager;
import android.widget.TextView;

public class GalgoService extends Service {

    private final IBinder mBinder = new LocalBinder();
    private TextView mTextView;
    private GalgoOptions mOptions;

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
    }

    public void displayText(String text) {

        Spannable spannable = new SpannableString(text);
        spannable.setSpan(new BackgroundColorSpan(mOptions.getBackgroundColor()),0, text.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        if(mTextView.getLineCount() > mOptions.getNumberOfLines()) {
            mTextView.setText(spannable);
        } else {
            mTextView.append(spannable);
        }

        mTextView.setTextSize(mOptions.getTextSize());
        mTextView.setTextColor(mOptions.getTextColor());
        mTextView.append("\n");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTextView != null) {
            WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
            wm.removeView(mTextView);
        }
    }
}