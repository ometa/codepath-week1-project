package org.ometa.instagrammer.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by devin on 10/22/15.
 */
public class Photo {
    private String username;
    private String caption;
    private String type;
    private String imageUrl;
    private String locationName;
    private String userPictureUrl;
    private String link;
    private int likesCount;
    private int imageHeight;
    private double createdTime;
    private float locationLatitude = 0;
    private float locationLongitude = 0;

    public Photo(JSONObject photoJSON) {
        try {
            type = photoJSON.getString("type");
            link = photoJSON.getString("link");
            createdTime = photoJSON.getDouble("created_time");
            caption = photoJSON.getJSONObject("caption").getString("text");
            likesCount = photoJSON.getJSONObject("likes").getInt("count");
            username = photoJSON.getJSONObject("user").getString("username");
            userPictureUrl = photoJSON.getJSONObject("user").getString("profile_picture");
            imageUrl = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
            imageHeight = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getInt("height");

            if (photoJSON.optJSONObject("location") != null) {
                locationName = photoJSON.getJSONObject("location").getString("name");
                locationLatitude = Float.parseFloat(photoJSON.getJSONObject("location").getString("latitude"));
                locationLongitude = Float.parseFloat(photoJSON.getJSONObject("location").getString("longitude"));
            }
        } catch (JSONException e) {
            // do we want this here?
            e.printStackTrace();
        }
    }


    // ------------------------------------------------

    public float getLocationLatitude() {
        return locationLatitude;
    }

    public float getLocationLongitude() {
        return locationLongitude;
    }

    public String getLocationName() {
        return locationName;
    }


    public String getUserPictureUrl() {
        return userPictureUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getLink() {
        return link;
    }

    public String getCaption() {
        return caption;
    }

    public String getType() {
        return type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public double getCreatedTime() {
        return createdTime;
    }
}
