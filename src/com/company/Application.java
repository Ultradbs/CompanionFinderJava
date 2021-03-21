package com.company;

import com.company.controllers.Controller;

import java.util.Scanner;

public class Application {
    private final Controller controller;
    private final Scanner scanner;


    public Application(Controller controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
    }

    public void start() {
        int user_id;
        boolean check = true;
        while (check) {
            while(true) {
                showMenu();
                try {
                    int option = scanner.nextInt();
                    if (option == 1) {
                        user_id = getUserId();
                        if(checkType(user_id)==true){
                            newOrder(user_id);
                            boolean checkStatus = true;
                            while(checkStatus) {
                                checkStatus(user_id);
                                if(checkStatus(user_id)==false){
                                    checkStatus = false;
                                }}
                            System.out.println("User was found");//We get customer'id and show his contact info
                            System.out.println("Rate your partner from 1-5");
                            int rating = scanner.nextInt();
                            giveFeedbackDriver(user_id, rating);
                            changeStatusToFinish(0,user_id);

                        } else {
                            User();
                            System.out.println("Choose driver by order_id");//we get driver's id then show his contacts
                            int choose = scanner.nextInt();
                            fillOrder(choose, user_id);
                            System.out.println("Rate your partner from 1-5");
                            int rating = scanner.nextInt();
                            giveFeedbackUser(choose, rating);
                            changeStatusToFinish(choose,0);


                        }


                    } else if (option == 2) {
                        newUser();
                    } else {
                        check = false;
                    }
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }



        }}


    public void changeStatusToFinish(int order_id, int driver_id){
        controller.changeStatusToFinish(order_id, driver_id);
    }

    public void giveFeedbackDriver(int driver_id, int driver_rating) {
       controller.giveFeedbackDriver(driver_id, driver_rating);
    }

    public void giveFeedbackUser(int order_id, int user_rating) {
        controller.giveFeedbackUser(order_id, user_rating);
    }

    public void showMenu(){
        System.out.println("Enter option (1-3): ");
        System.out.println("1.Log in");
        System.out.println("2.Registration");
        System.out.println("3.Exit");
    }


    public void User(){
        System.out.println("Enter your current address:");
        String start = scanner.next();
        System.out.println("Enter your end address");
        String end = scanner.next();
        listTrip(start, end);

    }

    public  void fillOrder(int order_id, int user_id){
        controller.fillOrder(order_id, user_id);
    }

    public String listTrip(String start, String end){
        String response = controller.listTrip(start,end);
        return response;
    }

    public boolean checkType(int id){
        if(controller.checkType(id)==true){
            return true;
        } else {
            return false;
        }
    }



    public boolean checkLogin(String login){
        if(controller.checkLogin(login) == true){
            return true;
        } else return  false;
    }

    public boolean checkPassword(String password){
        if(controller.checkPassword(password)==true){
            return true;
        }else return false;
    };

    public boolean checkAccount(String login, String password){
        boolean log, pass;
        log = checkLogin(login);
        pass = checkPassword(password);
        if(log == true && pass ==true){
            return true;
        } else return false;
    }

    public boolean checkStatus(int id){
        if (controller.checkStatus(id)==true){
            return true;
        }else
        return false;
    }

    public int getUserId(){
        boolean check = true;
        String login = null; String password = null;
        while (check) {
            System.out.println("Enter your login");
            login = scanner.next();
            System.out.println("Enter your password");
            password = scanner.next();
            if(checkAccount(login, password)){
                check = false;
            } else System.out.println("Some mistake in your input");
        }
        int id = controller.getUserId(login, password);
        return id;
    }



    public void newUser(){
        System.out.println("Enter your name");
        String name = scanner.next();
        System.out.println(("Enter your phone"));
        String phone = scanner.next();
        System.out.println("Your position, driver or user");
        String type = scanner.next();
        System.out.println("Number of your bank card");
        String bank = scanner.next();
        boolean check = true;
        String login = null, input;
        while(check) {
            System.out.println("Create your login");
             input = scanner.next();
             login= input;
            if(checkLogin(login) == false){
                System.out.println("Your login is unique!");
                check = false;
            } else System.out.println("Create unique login");
        }
        System.out.println("Create your password");
        String password = scanner.next();
         controller.newUser(name, phone, type, bank, login, password);

    }

    public void newOrder(int driver_id){
        System.out.println("Enter your current address:");
        String start = scanner.next();
        System.out.println("Enter your end address");
        String end = scanner.next();
        boolean check = true;
        int price = 0, Iprice;
        while(check) {
            System.out.println("Your price: must be less or equal 500tg");
             Iprice = scanner.nextInt();
            if(Iprice >=0 && Iprice <=500){
                price = Iprice;
                check = false;
            } else System.out.println("AGAIN: Your price must be less or equal 500tg");
        }

        controller.newOrder(driver_id, start, end, price);

    }



}

