package org.example.entities;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)

public class User {
    private String name;
    private String password;
    private String hashedPassword;
    private List<Tickets> ticketsBooked;
    private String userId;

    public User(String name,String password,String hashedPassword, List<Tickets> ticketsBooked, String userId)
    {
        this.name = name;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.ticketsBooked = ticketsBooked;
        this.userId = userId;
    }

    public User(){}

    public String getName()
    {
        return name;
    }

    public String getPassword()
    {
        return password;
    }

    public String getHashedPassword()
    {
        return hashedPassword;
    }

    public List<Tickets> getTicketsBooked() {
        return ticketsBooked;
    }

    public String getUserId()
    {
        return userId;
    }

    public void printTickets()
    {
        if (ticketsBooked.isEmpty()) {
            System.out.println("No tickets booked yet.");
            return;
        }
        for (Tickets tickets : ticketsBooked) {
            System.out.println(tickets.getTicketInfo());
        }
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setTicketsBooked(List<Tickets> ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
