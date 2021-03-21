package com.company.repositories;

public interface IUser {


    boolean checkLogin(String login);

    boolean checkPassword(String password);

    Integer getUserId(String login, String password);

    Boolean newUser(String name, String phone, String type, String bank, String login, String password);

    boolean checkType(int id);

    boolean newOrder(int driver_id, String start, String end, int price);

    boolean checkStatus(int id);

    String listTrip(String start, String end);

    String fillOrder(int order_id, int user_id);

    String getUserInfo(int id);

    boolean giveFeedbackDriver(int driver_id,int driver_rating);
    boolean giveFeedbackUser(int order_id,int user_rating);

    boolean changeStatusToFinish(int driver_id, int order_id);
}
