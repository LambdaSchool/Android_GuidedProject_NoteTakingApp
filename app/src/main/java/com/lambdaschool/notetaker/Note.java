package com.lambdaschool.notetaker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Note implements Serializable {
    public static final int NO_ID = -1;

    private String title, content;
    private String id;
    private long timestamp;

    public Note(int id, String title, String content) {
        this.title = title;
        this.content = content;
        this.id = Integer.toString(id);
        this.timestamp = System.currentTimeMillis();
    }

    public Note(String csvString) {
        String[] values = csvString.split(",");
        this.title = values[0];
        this.content = values[1];
        this.id = values[2];

        toCsvString();
    }

    public Note(int id) {
        this.id = Integer.toString(id);
    }

    public Note(JSONObject jsonObject, String name) {
        this.id = name;

        try {
            this.content = jsonObject.getString("content");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.title = jsonObject.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.timestamp = jsonObject.getLong("timestamp");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return Integer.parseInt(id);
    }

    public void setId(int id) {
        this.id = Integer.toString(id);
    }

    public String toCsvString() {
        return this.title.replaceAll(",", "") + "," + this.content.replaceAll(",", "") + "," + this.id;
    }

    public String toJsonString() {
        JSONObject json = new JSONObject();
        try {
            json.put("title", this.title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            json.put("content", this.content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            json.put("timestamp", this.timestamp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}
