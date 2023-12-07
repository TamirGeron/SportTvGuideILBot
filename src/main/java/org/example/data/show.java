package org.example.data;

import org.jsoup.select.Elements;

import java.util.Map;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class show {
    private String Channel;
    private String SportType;
    private String Time;
    private String Detail;

    public String getChannel() {
        return Channel;
    }

    public String getTime() {
        return Time;
    }

    public String getSportType() {
        return SportType;
    }

    public String getDetail() {
        return Detail;
    }

    public void setData(Elements show) {
        this.Channel = (show.get(1).text());
        this.Time = (show.get(2).text());
        String Detail = show.get(3).text();
        if (Detail.contains(":")) {
            String[] parts = Detail.split(": ", 2);
            this.SportType = parts[0];
            this.Detail = Detail;
        }
    }

}