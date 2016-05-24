package io.itdraft.youfollowme.website.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DomainTrend implements IsSerializable, Trend {
    private String domain;
    private int frequency;
    private long date;

    public DomainTrend() {
        this.domain = "";
        this.frequency = 0;
        this.date = System.currentTimeMillis();
    }

    public DomainTrend(String domain, int frequency, long date) {
        this.domain = domain;
        this.frequency = frequency;
        this.date = date;
    }

    public String getDisplayName() {
        return domain;
    }

    public int getFrequency() {
        return frequency;
    }

    public double getWeight() {
        return getFrequency();
    }

    public long getDate() {
        return date;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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
