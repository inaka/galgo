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

import java.io.Serializable;

public final class GalgoOptions implements Serializable {

    private final int numberOfLines;
    private final int backgroundColor;
    private final int textColor;
    private final int textSize;

    /**
     * Contains options for Galgo. Defines
     * @param builder
     */
    private GalgoOptions(Builder builder) {
        numberOfLines = builder.numberOfLines;
        backgroundColor = builder.backgroundColor;
        textColor = builder.textColor;
        textSize = builder.textSize;
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getTextSize() {
        return textSize;
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
         * Creates a {@link com.inaka.galgo.GalgoOptions} with the customized parameters
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
}
