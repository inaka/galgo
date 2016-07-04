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

import android.os.Parcel;
import android.os.Parcelable;

public final class GalgoOptions implements Parcelable {

    public final int numberOfLines;
    public final int backgroundColor;
    public final int textColor;
    public final int textSize;

    /**
     * Contains options for Galgo. Defines
     *
     * @param builder
     */
    private GalgoOptions(Builder builder) {
        numberOfLines = builder.numberOfLines;
        backgroundColor = builder.backgroundColor;
        textColor = builder.textColor;
        textSize = builder.textSize;
    }

    /**
     * Builder for {@link com.inaka.galgo.GalgoOptions}
     */
    public static class Builder {
        private int numberOfLines = 10;
        private int backgroundColor = 0xD993d2b9;
        private int textColor = 0xFFFFFFFF;
        private int textSize = 10;

        /**
         * @param n number of lines
         * @return
         */
        public Builder numberOfLines(int n) {
            ensurePositiveInt(n, "number of lines must be > 0");
            numberOfLines = n;
            return this;
        }

        /**
         * Sets the background color of the log messages
         *
         * @param color
         * @return
         */
        public Builder backgroundColor(int color) {
            backgroundColor = color;
            return this;
        }

        /**
         * Sets the text color of the log messages
         *
         * @param color
         * @return
         */
        public Builder textColor(int color) {
            textColor = color;
            return this;
        }

        /**
         * Sets the text size of the messages
         *
         * @param size
         * @return
         */
        public Builder textSize(int size) {
            ensurePositiveInt(size, "text size must be > 0");
            textSize = size;
            return this;
        }

        /**
         * Creates a {@link com.inaka.galgo.GalgoOptions} with the customized parameters
         *
         * @return
         */
        public GalgoOptions build() {
            return new GalgoOptions(this);
        }
    }

    private static void ensurePositiveInt(int value, String msg) {
        if (value <= 0) {
            throw new IllegalArgumentException(msg);
        }
    }

    // Parcelable implementation
    private GalgoOptions(Parcel source) {
        numberOfLines = source.readInt();
        backgroundColor = source.readInt();
        textColor = source.readInt();
        textSize = source.readInt();
    }

    public static final Creator<GalgoOptions> CREATOR = new Creator<GalgoOptions>() {
        @Override
        public GalgoOptions createFromParcel(Parcel source) {
            return new GalgoOptions(source);
        }

        @Override
        public GalgoOptions[] newArray(int size) {
            return new GalgoOptions[size];
        }
    };

    @Override
    public int describeContents() {
        return 0; // No special content.
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(numberOfLines);
        dest.writeInt(backgroundColor);
        dest.writeInt(textColor);
        dest.writeInt(textSize);
    }

}
