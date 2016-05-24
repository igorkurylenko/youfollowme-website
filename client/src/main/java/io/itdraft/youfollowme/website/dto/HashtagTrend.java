package io.itdraft.youfollowme.website.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class HashtagTrend implements IsSerializable, Trend {
    private String hashtag;
    private int frequency;
    private long date;

    public HashtagTrend() {
        this.hashtag = "";
        this.frequency = 0;
        this.date = System.currentTimeMillis();
    }

    public HashtagTrend(String hashtag, int frequency, long date) {
        this.hashtag = hashtag;
        this.frequency = frequency;
        this.date = date;
    }

    public String getDisplayName() {
        return hashtag;
    }

    public double getWeight() {
        return getFrequency();
    }

    public int getFrequency() {
        return frequency;
    }

    public long getDate() {
        return date;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String toString() {
        return getDisplayName();
    }
}
