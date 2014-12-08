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
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

public class GalgoService extends Service {

    private final IBinder mBinder = new LocalBinder();
    private TextView mTextView;
    private GalgoOptions mOptions;
    private final Queue<String> mLines = new ArrayDeque<>();

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

    public void displayText(String text) {
        mLines.add(text);
        if (mLines.size() > mOptions.numberOfLines) {
            mLines.poll();
        }

        redraw(mLines);
    }

    private void redraw(Collection<String> texts) {
        mTextView.setTextSize(mOptions.textSize);
        mTextView.setTextColor(mOptions.textColor);

        Spannable spannable = new SpannableString(TextUtils.join("\n", texts));
        spannable.setSpan(new BackgroundColorSpan(mOptions.backgroundColor), 0, spannable.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        mTextView.setText(spannable);
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