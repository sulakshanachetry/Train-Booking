package org.example.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

public class Tickets {

    @Getter @Setter
    private String ticketId;
    @Getter @Setter
    private String userName;
    @Getter @Setter
    private String source;
    @Getter @Setter
    private String destination;
    @Getter @Setter
    private String dateOfTravel;
    @Getter @Setter
    private Train train;

    public Tickets(){}

    @JsonIgnore
    public String getTicketInfo()
    {
        return String.format("Ticket Id %s belongs to user %s from %s to %s on %s", ticketId,userName,source,destination,dateOfTravel);
    }

}
