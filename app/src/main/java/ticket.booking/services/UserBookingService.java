package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mindrot.jbcrypt.BCrypt;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserBookingService {
    private User user;

    private ObjectMapper objectMapper = new ObjectMapper();
    private List<User> userList;

    private static final String USER_PATH = "app/src/main/java/ticket.booking/localDb/User.json";

    public UserBookingService(User user) throws IOException {
        this.user = user;
        loadUser();
    }

    public UserBookingService() throws IOException{
        loadUser();
    }

    public List<User> loadUser() throws IOException {
        File users = new File(USER_PATH);
        return objectMapper.readValue(users, new TypeReference<List<User>>(){});
    }

    public boolean loginUser(){
        Optional<User> userFound = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword((user.getPassword()), user1.getHashedPassword());
        }).findFirst();
        return userFound.isPresent();
    }

    public boolean signUp(User user1){
        try {
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile()throws IOException{
        File file = new File(USER_PATH);
        objectMapper.writeValue(file, userList);
    }

    public void fetchBooking(){
        user.printTickets();
    }

    public void cancelBooking(String ticketId){
       //        todo : complete it by myself....
    }

}
