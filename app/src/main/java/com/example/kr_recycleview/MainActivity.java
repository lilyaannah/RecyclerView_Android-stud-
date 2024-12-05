package com.example.kr_recycleview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Создаем или открываем базу данных для чтения
        DBHelper dbHelper = new DBHelper(this);

        // Пример загрузки данных из базы данных SQLite
        List<Movie> movieList = loadMoviesFromDatabase(dbHelper);

        // Находим RecyclerView в макете
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // Создаем и настраиваем адаптер
        MovieAdapter movieAdapter = new MovieAdapter(movieList, this);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<Movie> loadMoviesFromDatabase(DBHelper dbHelper) {
        // Загружаем данные из базы данных (здесь просто заглушка)
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("Movie 1", "Action", 2020, 4.5, "evengers",
                "https://example.com/streaming_link1","Описание филма какое0то"));

        movieList.add(new Movie("Movie 2", "Comedy", 2021, 3.8, "joker",
                "https://example.com/streaming_link2","Описание филма какое0то"));
        movieList.add(new Movie("Movie 3", "Drama", 2019, 4.2, "lord_rings",
                "https://example.com/streaming_link3","Описание филма какое0то"));


        return movieList;
    }

}