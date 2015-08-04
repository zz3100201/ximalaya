package com.zz.zhufengtest.app.model.discoverRecomend;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhangzhao11 on 2015/7/30.
 * User:zz
 * Date:2015/7/30
 */
public class EditorRecommendAlbumsList {
    /**
     * "albumId": 344497,
     "coverLarge": "http://fdfs.xmcdn.com/group6/M02/35/45/wKgDhFTg4w_SDkc9AAT-fXngGBY184_mobile_large.jpg",
     "title": "黑先生在麦田咖啡馆",
     "tags": "民谣,80后,文艺",
     "tracks": 117,
     "playsCounts": 917714,
     "isFinished": 0,
     "trackId": 7898099,
     "trackTitle": "几米：音乐与绘本的美好邂逅"
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
    public void parseJSON(JSONObject jsonObject) throws JSONException {
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
        return "EditorRecommendAlbumsList{" +
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
