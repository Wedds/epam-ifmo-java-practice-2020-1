package com.ifmo.epampractice.controller;

import com.ifmo.epampractice.service.DatabaseSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter pw = resp.getWriter();
        try {
            DatabaseSource dbs = DatabaseSource.getInstance();
            Connection conn = dbs.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT email FROM users");

            while(rs.next()){
                pw.println("<b>" + rs.getString("email") + "</b></br>");
            }

            pw.print("");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        pw.print("<h1>Hello Servlet</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        super.doPost(req, resp);
    }

}
