package org.example.data;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class TelegramBot {

    String token = "6671599139:AAFy6Okb2PraNn9ovIKmunwxcgcreZAgDxY";
    String chat_id = "409046767";
    private Client client;
    public void sendMessage(StringBuilder message) {

        client = ClientBuilder.newClient();
        WebTarget baseTarget = client.target("https://api.telegram.org/bot{token}")
                .resolveTemplate("token", this.token);

        try {
            baseTarget.path("sendMessage")
                    .queryParam("chat_id", chat_id)
                    .queryParam("text", message)
                    .request()
                    .get();


        } catch (Exception e) {
            System.err.println("Couldn't successfully send message, " + e.getMessage());
            e.printStackTrace();
        }
    }
}
