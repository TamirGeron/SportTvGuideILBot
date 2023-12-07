package org.example;

import org.example.data.TelegramBot;
import org.example.data.show;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Functions {

    public static void CreateMessage(ArrayList<ArrayList<show>> days,StringBuilder formattedMessage) {
        for (ArrayList<show> day : days) {
            String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            formattedMessage.append(currentDate).append("\n");
            for (show show : day) {
                // Format each show and append to the message
                formattedMessage.append(show.getTime())
                        .append(" - ")
                        .append(show.getDetail())
                        .append(" - ")
                        .append(show.getChannel())
                        .append("\n");
            }
            formattedMessage.append("\n");
        }
    }

    public static void SendMessage(StringBuilder message) {
        TelegramBot bot = new TelegramBot();

        bot.sendMessage(message);
    }

    public static void scrapeTeleportPage(
            ArrayList<ArrayList<show>> days
    ) {
        // initializing the HTML Document page variable
        Document doc;

        try {
            doc = Jsoup.connect("https://www.telesport.co.il/%D7%A9%D7%99%D7%93%D7%95%D7%A8%D7%99%20%D7%A1%D7%A4%D7%95%D7%A8%D7%98").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Elements products = doc.select(".css-panes .css-pane");

        for(int i=0; i< 2; i++) {
            ArrayList<show> shows = new ArrayList<>();
            Element pane = products.get(i);
            Elements day = pane.select("> div");
            for (int j = 1; j < day.size(); j++) {
                Elements hour = day.get(j).select("> div");
                show show = new show();
                show.setData(hour);
                shows.add(show);
            }
            days.add(shows);
        }
    }

}

