package ticket.booking;


import ticket.booking.entities.User;
import ticket.booking.services.UserBookingService;
import ticket.booking.util.UserServiceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class  App {

    public static void main(String[] args) {


        System.out.println("Running ticket booking app");
        Scanner sc = new Scanner(System.in);
        int option = 0;
        UserBookingService userBookingService;
        try{
            userBookingService = new UserBookingService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while(option != 7){
            System.out.println("Select an option: ");
            System.out.println("1: Signup");
            System.out.println("2: Login");
            System.out.println("3: Fetch booking");
            System.out.println("4: Search Train");
            System.out.println("5: Book a seat");
            System.out.println("6: cancel my booking");
            System.out.println("7: Exit");
            option = sc.nextInt();
        }

        switch (option){
            case 1:
                System.out.println("Enter your name : ");
                String name = sc.next();
                System.out.println("Enter your password : ");
                String password = sc.next();
                User signupUser = new User(name, password,
                        UserServiceUtil.hashPassword(password),
                        new ArrayList<>(),
                        UUID.randomUUID().toString());
                userBookingService.signUp(signupUser);
                break;

            case 2:
                System.out.println("Enter your name: ");
                String nameLogin = sc.next();
                System.out.println("Enter your password: ");
                String passwordLogin = sc.next();
                User loginUser = new User(nameLogin, passwordLogin,
                        UserServiceUtil.hashPassword(passwordLogin),
                        new ArrayList<>(),
                        UUID.randomUUID().toString());

                try{
                    userBookingService = new UserBookingService(loginUser);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;

            case 3:
                System.out.println("Fetch bookings..");
                userBookingService.fetchBooking();
                break;
            case 4:
                System.out.println("Enter source Station: ");
                String source = sc.next();
                System.out.println("Enter destination Station: ");
                String destination = sc.next();
                userBookingService.fetchTrains(source, destination);


        }
    }
        }
