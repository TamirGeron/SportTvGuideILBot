package org.example;

import java.util.ArrayList;

import org.example.data.show;

public class Main {

    public static void main(String[] args) {
        ArrayList<ArrayList<show>> days = new ArrayList<>();
        Functions.scrapeTeleportPage(days);
        StringBuilder formattedMessage = new StringBuilder();
        Functions.CreateMessage(days,formattedMessage);
        System.out.println(formattedMessage);
        Functions.SendMessage(formattedMessage);
    }


}
