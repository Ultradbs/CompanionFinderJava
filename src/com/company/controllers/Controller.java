package com.company.controllers;

import com.company.repositories.User;

public class Controller {
    private User repo;

    public Controller(User repo) {
        this.repo = repo;
    }

    public boolean checkLogin(String login){
        return  repo.checkLogin(login);
    }

    public boolean checkPassword(String password) {
        return repo.checkPassword(password);
    }

    public boolean checkType(int id){
        boolean response = repo.checkType(id);
        if(response ==true ){
            return true;
        } else return false;
    }

    public int getUserId(String login, String password) {
        Integer id = repo.getUserId(login, password);
        if(id >0){return id;} else return 0;
    }

    public void newUser(String name, String phone, String type, String bank, String login, String password){
        boolean response = repo.newUser(name, phone, type, bank, login, password);
        if(response == true){
            System.out.println("Account was created");
        } else System.out.println("There something wrong");

    }

    public void newOrder(int driver_id, String start, String end, int price) {
        boolean response = repo.newOrder(driver_id,start,end,price);
        if(response == true) {
            System.out.println("Your trip is published, please wait your fellow traveller");
        } else System.out.println("Wrong");
    }

    public boolean checkStatus(int id) {
        boolean response = repo.checkStatus(id);
        if(response == true){return true;} else{return false;}
    }

    public String listTrip(String start, String end){return  repo.listTrip(start, end);}

    public void fillOrder(int order_id, int user_id) {
        repo.fillOrder(order_id, user_id);
    }


    public void giveFeedbackDriver(int driver_id, int driver_rating) {
        if(repo.giveFeedbackDriver(driver_id,driver_rating)==true ){
            System.out.println("Your feedback is given");
        }else System.out.println("Error");
    }

    public void giveFeedbackUser(int order_id, int user_rating) {
        if(repo.giveFeedbackUser(order_id,user_rating)==true ){
            System.out.println("Your feedback is given");
        }else System.out.println("Error");
    }

    public void changeStatusToFinish(int order_id, int driver_id) {
        repo.changeStatusToFinish( order_id,driver_id);
    }
}

