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
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.IBinder;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.WindowManager;
import android.widget.TextView;

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

    public class LocalBinder extends Binder {
        public GalgoService getService() {
            return GalgoService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        this.mOptions = intent.getExtras().getParcelable(Galgo.ARG_OPTIONS);
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

    public void displayText(String text, Integer priority) {

        Spannable spannable = new SpannableString(text);
        spannable.setSpan(new BackgroundColorSpan(mOptions.backgroundColor),0, text.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        int textColor;
        switch (priority){
            case ERROR:
                textColor = mOptions.errorTextColor;
                break;
            default:
                textColor = mOptions.textColor;
                break;
        }
        spannable.setSpan(new ForegroundColorSpan(textColor),0,text.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        if(mTextView.getLineCount() > mOptions.numberOfLines) {
            //TODO make this in scrolling mode only
            mTextView.setText(mTextView.getEditableText().delete(0, mTextView.getText().toString().split("\n", 2)[0].length()+1));
            mTextView.append(spannable);
        } else {
            mTextView.append(spannable);
        }

        mTextView.setTextSize(mOptions.textSize);
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