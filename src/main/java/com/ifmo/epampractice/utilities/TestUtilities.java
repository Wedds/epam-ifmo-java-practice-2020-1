package com.ifmo.epampractice.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUtilities {
    public static void executeSqlFile(Path pathToFile, Statement statement) {
        try {
            String[] querues = String.join("", Files.readAllLines(pathToFile)).split(";");
            for (String query : querues) {
                if (query.startsWith("--")) {
                    continue;
                }
                try {
                    System.out.println(query);
                    statement.execute(query);
                } catch (SQLException e) {
                    throw new IllegalArgumentException("Cannot to create H2 database.", e);
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot to read SQL instructions from the file.", e);
        }
    }
}
