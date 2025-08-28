package org.example.services;

import org.example.entities.Tickets;
import org.example.entities.Train;
import org.example.entities.User;
import org.example.util.UserServiceUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.*;

public class UserBookingService {

    //private User user;
    private List<User> userList;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String USERS_PATH = "E:/java/IRCTC/app/src/main/java/org/example/localDb/Users.json";


    public UserBookingService() throws IOException {
        loadUserListFromFile();
    }

    public void loadUserListFromFile() throws IOException {
        userList = objectMapper.readValue(new File(USERS_PATH), new TypeReference<List<User>>() {
        });
    }

    public Optional<User> loginUser(String name, String password) {
        return userList.stream()
                .filter(user1 ->
                        user1.getName().equals(name) &&
                                UserServiceUtil.checkPassword(password, user1.getHashedPassword())
                )
                .findFirst();
    }

    public Optional<User> registerUser(String name, String password) {
        try {
            boolean exists = userList.stream().anyMatch(u -> name.equals(u.getName()));
            if (exists) {
                System.out.println("Username already exists. Please choose another one.");
                return Optional.empty();
            }

            User newUser = new User(name, UserServiceUtil.hashPassword(password), new ArrayList<>(), UUID.randomUUID().toString());
            userList.add(newUser);
            saveUserListToFile();
            return Optional.of(newUser);
        } catch (IOException ex) {
            return Optional.empty();
        }

    }

    public void saveUserListToFile() throws IOException {
        objectMapper.writeValue(new File(USERS_PATH), userList);
    }

    public void fetchBookings(User user) {
        user.printTickets();
    }

    public List<Train> getTrains(String source, String dest) {
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, dest);
        } catch (IOException ex) {
            return new ArrayList<>();
        }
    }

    public Boolean bookTrainSeats(User user, Train train, int row, int col) {
        try{
            TrainService trainService= new TrainService();
            List<List<Integer>>seats = train.getSeats();

           if(row<0 || col <0 || row>=seats.size() || col>=seats.get(0).size()){
            return Boolean.FALSE;
           }

           if(seats.get(row).get(col)==0){
               seats.get(row).set(col,1);
               train.setSeats(seats);
               trainService.updateTrain(train);

               Tickets ticket= new Tickets();
               ticket.setSource(train.getStations().getFirst());
               ticket.setDestination(train.getStations().getLast());
               ticket.setTrain(train);
               ticket.setUserName(user.getName());
               ticket.setDateOfTravel("2025-09-01");
               ticket.setTicketId(UUID.randomUUID().toString().substring(0, 8));
               user.getTicketsBooked().add(ticket);

               updateUserList(user);
                return Boolean.TRUE;
           }else{

               return Boolean.FALSE;
           }

        } catch (IOException e) {
            return Boolean.FALSE;
        }
    }

    public void updateUserList( User toBeUpdatedUser) throws IOException
    {
        for( int i=0;i<userList.size();i++){
            if(userList.get(i).getUserId().equals(toBeUpdatedUser.getUserId())){
                userList.set(i,toBeUpdatedUser);
            }
        }
        saveUserListToFile();
    }

    public List<List<Integer>>  fetchSeats(Train t){
        return t.getSeats();
    }

    public Train getTrainByTrainNo(Integer TrainNo){
        try {
            TrainService trainService = new TrainService();
            return trainService.getTrain(TrainNo);
        } catch (IOException ex) {
            return null;
        }
    }

    public Boolean cancelBooking( User user, String ticketId) throws IOException
    {
            if(ticketId==null || ticketId.isEmpty()){
                System.out.println("Ticket ID cannot be null or empty.");
                return Boolean.FALSE;
            }
            boolean ticketRemoved = user.getTicketsBooked().removeIf(ticket->ticket.getTicketId().equals(ticketId));
        if(ticketRemoved) {

            updateUserList(user);
            System.out.println("Ticket with ID " + ticketId + " has been canceled.");
            return true;
        }else{
            System.out.println("No ticket found with ID " + ticketId);
            return false;
        }
    }
}


