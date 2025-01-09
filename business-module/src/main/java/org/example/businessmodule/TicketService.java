package org.example.businessmodule;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.businessmodule.dto.TicketWithEventWrite;
import org.example.businessmodule.dto.TicketWrite;
import org.example.businessmodule.exception.TicketServiceNotAvailable;
import org.example.businessmodule.model.Person;
import org.example.businessmodule.model.Ticket;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

//@Stateless
public class TicketService {
    private final static String SPRING_SERVICE_URL = "http://localhost:80/TMA/api/v2/tickets";
    private static final String CONSUL_URL = "http://localhost:8500/v1/catalog/service/ticketService";

    private static String getServiceUrl() throws IOException {
        try {
            URL url = new URL(CONSUL_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                System.err.println("Ошибка запроса к Consul: " + conn.getResponseCode());
                return null;
            }

            // Читаем JSON-ответ от Consul
            Scanner scanner = new Scanner(conn.getInputStream());
            String jsonResponse = scanner.useDelimiter("\\A").next();
            scanner.close();


            // Парсим JSON с Gson
            JsonArray nodes = JsonParser.parseString(jsonResponse).getAsJsonArray();
            if (nodes.size() > 0) {
                JsonObject firstNode = nodes.get(0).getAsJsonObject();
                String address = firstNode.get("ServiceAddress").getAsString();
                int port = firstNode.get("ServicePort").getAsInt();
//                return "http://" + address + ":" + port + SPRING_SERVICE_URL;
                return "http://" + address + ":" + "57962" + SPRING_SERVICE_URL;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Object saveTicket(TicketWrite ticket)  {
        String address = null;
        try {
            address = getServiceUrl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (Client client = ClientBuilder.newClient()) {
            Response response = client.target(SPRING_SERVICE_URL)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(ticket, MediaType.APPLICATION_JSON));

            if (response.getStatus() == 201) return response.readEntity(Ticket.class);
            else throw new TicketServiceNotAvailable(response.readEntity(String.class));
        }
    }

    public static Object saveTickets(TicketWithEventWrite ticket, int num) {
        List<Integer> ids;
        String address = null;
        try {
            address = getServiceUrl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Client client = ClientBuilder.newClient()) {
            Response response = client.target(SPRING_SERVICE_URL + "/bulk/" + num)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(ticket, MediaType.APPLICATION_JSON));

            if (response.getStatus() == 201) ids = (List<Integer>) response.readEntity(Object.class);
            else throw new TicketServiceNotAvailable(response.readEntity(String.class));
        }

        return ids;
    }

    public static void deleteTickets(int id) {
        String address = null;
        try {
            address = getServiceUrl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Client client = ClientBuilder.newClient()) {
            Response response = client.target(SPRING_SERVICE_URL + "/bulk/" + id)
                    .request(MediaType.APPLICATION_JSON)
                    .delete();
            if (response.getStatus() != 204) throw new TicketServiceNotAvailable(response.readEntity(String.class));
        }
    }

    public static Integer findTicketsByEventId(int id) {
        String address = null;
        try {
            address = getServiceUrl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String s = SPRING_SERVICE_URL + "/events/" + id;
        try (Client client = ClientBuilder.newClient()) {
            Response response = client.target(s)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if (response.getStatus() == 200) return response.readEntity(Integer.class);
            else if (response.getStatus() == 404) return null;
            else throw new TicketServiceNotAvailable(response.readEntity(String.class));
        }
    }
    public static TicketWithEventWrite findTicket(int id) {
        String address = null;
        try {
            address = getServiceUrl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String s = SPRING_SERVICE_URL + "/" + id;
        try (Client client = ClientBuilder.newClient()) {
            Response response = client.target(s)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if (response.getStatus() == 200) return response.readEntity(TicketWithEventWrite.class);
            else if (response.getStatus() == 404) return null;
            else throw new TicketServiceNotAvailable(response.readEntity(String.class));
        }
    }
    public static Person findPerson(int id) {
        String address = null;
        try {
            address = getServiceUrl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String s = SPRING_SERVICE_URL + "/people/" + id;
        try (Client client = ClientBuilder.newClient()) {
            Response response = client.target(s)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if (response.getStatus() == 200) return response.readEntity(Person.class);
            else if (response.getStatus() == 404) return null;
            else throw new TicketServiceNotAvailable(response.readEntity(String.class));
        }
    }
}
