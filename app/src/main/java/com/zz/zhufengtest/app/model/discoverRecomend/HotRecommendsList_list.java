package com.zz.zhufengtest.app.model.discoverRecomend;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhangzhao11 on 2015/7/30.
 * User:zz
 * Date:2015/7/30
 */
public class HotRecommendsList_list {
    /**
     * "albumId": 343042,
     "coverLarge": "http://fdfs.xmcdn.com/group6/M0A/E0/7E/wKgDhFUc5qbjBmDbAAAps8N-LbI468_mobile_large.jpg",
     "title": "重点关注",
     "tags": "东广新闻台",
     "tracks": 572,
     "playsCounts": 100781,
     "isFinished": 0,
     "trackId": 7991015,
     "trackTitle": "“电梯吃人”事故,谁来负责？"
     * @param jsonObject
     */
    private int albumId;
    private String coverLarge;
    private String title;
    private String tags;
    private int tracks;
    private int playsCounts;
    private int isFinished;
    private int trackId;
    private String trackTitle;

    public void parse(JSONObject jsonObject) throws JSONException {
        albumId = jsonObject.getInt("albumId");
        coverLarge = jsonObject.getString("coverLarge");
        title = jsonObject.getString("title");
        tags = jsonObject.getString("tags");
        tracks = jsonObject.getInt("tracks");
        playsCounts = jsonObject.getInt("playsCounts");
        isFinished = jsonObject.getInt("isFinished");
        trackId = jsonObject.getInt("trackId");
        trackTitle = jsonObject.getString("trackTitle");

    }

    public int getAlbumId() {
        return albumId;
    }

    public String getCoverLarge() {
        return coverLarge;
    }

    public int getIsFinished() {
        return isFinished;
    }

    public int getPlaysCounts() {
        return playsCounts;
    }

    public String getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }

    public int getTrackId() {
        return trackId;
    }

    public int getTracks() {
        return tracks;
    }

    public String getTrackTitle() {
        return trackTitle;
    }

    @Override
    public String toString() {
        return "HotRecommendsList_list{" +
                "albumId=" + albumId +
                ", coverLarge='" + coverLarge + '\'' +
                ", title='" + title + '\'' +
                ", tags='" + tags + '\'' +
                ", tracks=" + tracks +
                ", playsCounts=" + playsCounts +
                ", isFinished=" + isFinished +
                ", trackId=" + trackId +
                ", trackTitle='" + trackTitle + '\'' +
                '}';
    }
}
