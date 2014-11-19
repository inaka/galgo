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
}
