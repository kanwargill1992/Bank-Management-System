package bank.management.system;

import java.io.*;
import java.sql.*;

public class Conn {

    Connection c;
    Statement s;

    public Conn() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(".env"));
            String line;
            String[] envVariables = new String[3];
            int index = 0;

            while ((line = reader.readLine()) != null && index < 3) {
                String[] parts = line.split("=");
                if (parts.length >= 2) {
                    envVariables[index++] = parts[1];
                }
            }

            String dbUrl = envVariables[0];
            String dbUser = envVariables[1];
            String dbPassword = envVariables[2];
            c = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            s = c.createStatement();
            System.out.println("Connected to Database");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public Statement getStatement() {
        return s;
    }

    public void close() {
        try {
            if (s != null) {
                s.close();
            }
            if (c != null) {
                c.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
