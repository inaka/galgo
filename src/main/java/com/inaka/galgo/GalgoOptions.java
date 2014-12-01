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

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

public final class GalgoOptions implements Parcelable {


    public final int numberOfLines;
    public final int backgroundColor;
    public final int textColor;
	public final int errorTextColor;
    public final int textSize;
	public final boolean saveToTextFile;

    /**
     * Contains options for Galgo. Defines
     * @param builder
     */
    private GalgoOptions(Builder builder) {
        numberOfLines = builder.numberOfLines;
        backgroundColor = builder.backgroundColor;
        textColor = builder.textColor;
        errorTextColor = builder.errorTextColor;
        textSize = builder.textSize;
		saveToTextFile = builder.saveToTextFile;
    }

    /**
     * Builder for {@link com.inaka.galgo.GalgoOptions}
     */
    public static class Builder {
        private int numberOfLines = 10;
        private int backgroundColor = 0xD993d2b9;
        private int textColor = Color.WHITE;
        private int errorTextColor = Color.RED;
        private int textSize = 10;
		private boolean saveToTextFile = false;

        /**
         *
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
         * @param color
         * @return
         */
        public Builder backgroundColor(int color) {
            backgroundColor = color;
            return this;
        }

        /**
         * Sets the text color of the log messages
         * @param color
         * @return
         */
        public Builder textColor(int color) {
            textColor = color;
            return this;
        }
        
        /**
         * Sets the text color of messages logged as errors
         * @param errorColor
         * @return
         */
        public Builder errorTextColor(int errorColor) {
            errorTextColor = errorColor;
            return this;
        }

        /**
         * Sets the text size of the messages
         * @param size
         * @return
         */
        public Builder textSize(int size) {
            ensurePositiveInt(size, "text size must be > 0");
            textSize = size;
            return this;
        }

        /**
         * Sets saving to a text file to true
         * @param save
         * @return
         */
		public Builder saveToTextFile(boolean save) {
			saveToTextFile = save;
			return this;
		}

        /**
         * Creates a {@link com.inaka.galgo.GalgoOptions} with the customized parameters
         * @return
         */
        public GalgoOptions build() {
            return new GalgoOptions(this);
        }
    }

    // Parcelable implementation

    private GalgoOptions(Parcel source) {
        numberOfLines = source.readInt();
        backgroundColor = source.readInt();
        textColor = source.readInt();
        errorTextColor = source.readInt();
        textSize = source.readInt();
        saveToTextFile = (source.readByte() != 0);
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
        dest.writeInt(errorTextColor);
        dest.writeInt(textSize);
        dest.writeByte((byte) (saveToTextFile ? 1:0));
    }

    private static void ensurePositiveInt(int value, String msg) {
        if (value <= 0) {
            throw new IllegalArgumentException(msg);
        }
    }
}

