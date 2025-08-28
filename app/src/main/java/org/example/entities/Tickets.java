package org.example.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

public class Tickets {

    @Getter @Setter
    private String ticketId;
    @Getter @Setter
    private String userId;
    @Getter @Setter
    private String source;
    @Getter @Setter
    private String destination;
    @Getter @Setter
    private String dateOfTravel;
    @Getter @Setter
    private Train train;

    public Tickets(){}

//    public Tickets(String ticketId,String userId,String source, String destination,String dateOfTravel,Train train){
//        this.ticketId=ticketId;
//        this.userId = userId;
//        this.source = source;
//        this.destination=destination;
//        this.dateOfTravel=dateOfTravel;
//        this.train=train;
//    }

    @JsonIgnore
    public String getTicketInfo()
    {
        return String.format("Ticket Id %s belongs to user %s from %s to %s on %s", ticketId,userId,source,destination,dateOfTravel);
    }

}
