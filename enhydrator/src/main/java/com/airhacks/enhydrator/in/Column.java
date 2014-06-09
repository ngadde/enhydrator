package com.airhacks.enhydrator.in;

import java.util.StringTokenizer;

/*
 * #%L
 * enhydrator
 * %%
 * Copyright (C) 2014 Adam Bien
 * %%
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
 * #L%
 */
/**
 *
 * @author airhacks.com
 */
public class Column {

    private int index;
    private String name;
    private String targetSink;
    private String targetObject;
    private Object value;

    private final static String DEFAULT_DESTINATION = "*";

    public Column(int index, String name, Object value) {
        this(index, name, DEFAULT_DESTINATION, value);
    }

    public Column(int index, String name, String destination, Object value) {
        this.index = index;
        this.name = name;
        this.targetSink = destination;
        this.value = value;
    }

    public Column(int index, String name) {
        this(index, name, null);
    }

    public void convertToInteger() {
        if (value == null) {
            return;
        }
        String asString = String.valueOf(value);
        try {
            this.value = Integer.parseInt(asString);
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("Cannot convert column: "
                    + this.name + " with index " + this.index + " and value " + this.value + " to integer");
        }
    }

    public void convertToDouble() {
        if (value == null) {
            return;
        }
        String asString = String.valueOf(value);
        if (asString.isEmpty()) {
            this.value = (double) 0;
            return;
        }
        try {
            this.value = Double.parseDouble(asString);
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("Cannot convert column: "
                    + this.name + " with index " + this.index + " and value ->" + this.value + "<- to double");

        }
    }

    public void convertDMSToDouble() {
        if (value == null) {
            return;
        }
        String asString = String.valueOf(value);
        if (asString.isEmpty()) {
            this.value = (double) 0;
            return;
        }
        StringTokenizer tokenizer = new StringTokenizer(asString, ".");
        int degree = Integer.parseInt(tokenizer.nextToken());
        int minute = Integer.parseInt(tokenizer.nextToken());
        int second = Integer.parseInt(tokenizer.nextToken());
        this.value = (double) degree + (minute / 60d) + (second / 3600d);

    }

    public void convertToBoolean() {
        if (value == null) {
            return;
        }
        String asString = String.valueOf(value);
        this.value = Boolean.parseBoolean(asString);
    }

    public void fillWithValue(String value) {
        this.value = value;
    }

    public void convertToString() {
        if (value == null) {
            return;
        }
        this.value = String.valueOf(value);
    }

    public boolean isNullValue() {
        return this.value == null;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }

    public String getTargetSink() {
        return targetSink;
    }

    public String getTargetObject() {
        return targetObject;
    }

    void setName(String name) {
        this.name = name;
    }

    public void setTargetSink(String targetSink) {
        this.targetSink = targetSink;
    }

    public void setTargetObject(String targetObject) {
        this.targetObject = targetObject;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    boolean isNumber() {
        return this.value instanceof Number;
    }

    boolean isString() {
        return this.value instanceof String;
    }

    @Override
    public String toString() {
        return "Column{" + "index=" + index + ", name=" + name + ", targetSink=" + targetSink + ", targetObject=" + targetObject + ", value=" + value + '}';
    }

}
