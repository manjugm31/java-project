package com.kn.fromuser2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCScannerCRUD {
    private static final String URL = "jdbc:mysql://localhost:3306/JDBC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    private static final String INSERTING = "insert into product values(?,?,?);";
    private static final String UPDATING = "update product set name=? where id=?;";
    private static final String DELETING = "delete from product where id=?;"; 
    static Connection con = null;
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("driver registered successfully");
            con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        System.out.println("choose any 1 from below option-");
        System.out.println("press 1 for insertion");
        System.out.println("press 2 for update");
        System.out.println("press 3 for delete");
        System.out.println("press 4 for get result");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                insert();
                break;
            case 2:
                update();
                break;
            case 3:
                delete();
                break;
            case 4:
                getresult();
                break;
        }
    }

    private static void getresult() {
    }

    private static void delete() {
        try {
            PreparedStatement pstm = con.prepareStatement(DELETING);

            System.out.println("enter id=");
            int id = scan.nextInt();

            pstm.setInt(1, id);

            int count = pstm.executeUpdate();
            System.out.println(count + " record(s) deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void update() {
        try {
            PreparedStatement pstm = con.prepareStatement(UPDATING);

            System.out.println("enter name=");
            String name = scan.next();
            System.out.println("enter id=");
            int id = scan.nextInt();
            pstm.setString(1, name);
            pstm.setInt(2, id);

            int count = pstm.executeUpdate();
            System.out.println(count + " record updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insert() {
        try {
            PreparedStatement pstm = con.prepareStatement(INSERTING);
            System.out.println("enter id=");
            int id = scan.nextInt();
            System.out.println("enter name=");
            String name = scan.next();
            System.out.println("enter cost=");
            int cost = scan.nextInt();
            pstm.setInt(1, id);
            pstm.setString(2, name);
            pstm.setInt(3, cost);
            int count = pstm.executeUpdate();
            System.out.println(count + " record inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
