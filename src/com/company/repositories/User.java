package com.company.repositories;

import com.company.database.IDB;

import java.sql.*;

public class User implements IUser{
    private final IDB db;

    public User(IDB db) {
        this.db = db;
    }

    @Override
    public boolean checkType(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT type FROM accounts WHERE user_id = ? ";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String response = rs.getString("type");
                if(response.equals( "driver")){return true;}


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean checkLogin(String login) {
        Connection con = null;
        try {

            con = db.getConnection();
            String sql = "SELECT user_id FROM accounts WHERE login = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,login);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
                /*int id =  rs.getInt("user_id");
                if(id>0){return true;}*/
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }



    @Override
    public boolean checkPassword(String password) {
        Connection con = null;
        try {

            con = db.getConnection();
            String sql = "SELECT user_id FROM accounts WHERE password = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,password);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Integer getUserId(String login, String password) {
        Connection con = null;
        try {

            con = db.getConnection();
            String sql = "SELECT user_id FROM accounts WHERE login = ? AND password = ? ";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1,login);
            st.setString(2,password);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id =  rs.getInt("user_id");

                return id;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public Boolean newUser(String name, String phone, String type, String bank, String login, String password) {
        Connection con = null;
        try {

            con = db.getConnection();
            String sql = "insert into accounts (type, name, phone, bank, login, password) values (?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1,type);
            st.setString(2,name);
            st.setString(3,phone);
            st.setString(4,bank);
            st.setString(5,login);
            st.setString(6,password);

            st.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }



    @Override
    public boolean newOrder(int driver_id, String start, String end, int price) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "insert into orders (driver_id, start_point, end_point, price, status) values (?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,driver_id);
            st.setString(2,start);
            st.setString(3,end);
            st.setInt(4,price);
            st.setString(5,"search");

            st.execute();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean checkStatus(int id) {
        Connection con = null;
        try {

            con = db.getConnection();
            String sql = "SELECT status FROM orders WHERE driver_id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String response =  rs.getString("status");
                if(response.equals("search")){
                    return true;
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public String listTrip(String start, String end) {
        Connection  con =null;
        try {
            con = db.getConnection();
            String sql = "SELECT order_id, driver_id, price FROM orders where start_point=? and end_point=? and status='search'" ;
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,start);
            st.setString(2,end);

            ResultSet rs = st.executeQuery();
            System.out.println("List of Orders: ");
            while (rs.next()) {
                System.out.println( "[ Order ID: " + rs.getInt("order_id")
                        +" | Driver ID: "+ rs.getInt("driver_id")+" | Price: "+ rs.getInt("price")+"]");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return " ";
    }

    @Override
    public String fillOrder(int order_id, int user_id) {
        Connection con = null; int status=2;
        try {
            con = db.getConnection();
            String sql = "update orders set customer_id = ?, status='trip' where order_id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,user_id);
            st.setInt(2,order_id);
            st.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return "Wasn't Done";
    }

    @Override
    public String getUserInfo(int id) {
        Connection  con =null;
        try {
            con = db.getConnection();
            String sql = "SELECT name, phone FROM accounts where user_id=?" ;
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                System.out.println( "Name: "+ rs.getString("name")+", Phone: "+rs.getString("phone")+
                         "\nYou may contact if you wish");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return " ";
    }

    @Override
    public boolean giveFeedbackDriver(int driver_id, int driver_rating) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "update orders set customer_rate = ? where driver_id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,driver_rating);
            st.setInt(2,driver_id);

            st.execute();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean giveFeedbackUser(int order_id, int user_rating) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "update orders set driver_rate = ? where order_id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,user_rating);
            st.setInt(2,order_id);

            st.execute();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }


    @Override
    public boolean changeStatusToFinish(int driver_id, int order_id) {
        Connection  con =null;
        try {
            con = db.getConnection();
            String sql = "SELECT driver_rate, customer_rate FROM orders where driver_id=? or order_id=?" ;
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1,driver_id);
            st.setInt(2,order_id);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                if(rs.getInt("driver_rate")>0 && rs.getInt("customer_rate")>0);
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return false;


}}



