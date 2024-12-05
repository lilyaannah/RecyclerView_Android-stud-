package com.example.kr_recycleview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovieDatabase {

    private static final String DB_URL = "jdbc:mysql://Localserver:3306/movies";
    private static final String USER = "root";
    private static final String PASSWORD = "testserver12345";
    public  MovieDatabase(){
        main();
    }
    public static void main() {
        // Пример добавления фильма
        addMovie("Inception", "Sci-Fi", 2010, 8.8, "https://www.example.com/inception", "https://www.example.com/inception_poster.jpg");
        addMovie("The Shawshank Redemption", "Drama", 1994, 9.3, "https://www.example.com/shawshank", "https://www.example.com/shawshank_poster.jpg");
        // Добавьте другие фильмы по аналогии
    }

    private static void addMovie(String title, String genre, int releaseYear, double rating, String watchLink, String posterLink) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            String query = "INSERT INTO movies (title, genre, release_year, rating, watch_link, poster_link) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, title);
                preparedStatement.setString(2, genre);
                preparedStatement.setInt(3, releaseYear);
                preparedStatement.setDouble(4, rating);
                preparedStatement.setString(5, watchLink);
                preparedStatement.setString(6, posterLink);

                preparedStatement.executeUpdate();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
