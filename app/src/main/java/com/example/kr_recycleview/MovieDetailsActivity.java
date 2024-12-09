package com.example.kr_recycleview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MovieDetailsActivity extends AppCompatActivity {

    public void streamingToMovie() {

        // Инициализация компонентов UI
        TextView descriptionTextView = findViewById(R.id.detailsDescriptionTextView);
        Button linkButton = findViewById(R.id.detailsLinkButton);

        // Здесь передайте данные из объекта Movie
        String movieDescription = "Данный фильм повествует о возникновения анти героя по имени Джокер...";
        String movieUrl = "https://youtu.be/mj4dVlxhfA4?si=Zzby_tANwsJp9ss0"; // пример ссылки

        // Устанавливаем описание фильма
        descriptionTextView.setText(movieDescription);

        // Прикрепляем слушатель на кнопку для открытия ссылки
        linkButton.setOnClickListener(v -> {
            Uri.parse(Uri.encode(movieUrl, ":/"));
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(movieUrl));
            startActivity(browserIntent);
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        streamingToMovie();

        Intent intent = getIntent();
        if (intent != null) {
            Movie movie = (Movie) intent.getSerializableExtra("movie");

            if (movie != null) {

                TextView titleTextView = findViewById(R.id.detailsTitleTextView);
                TextView genreTextView = findViewById(R.id.detailsGenreTextView);
                TextView yearTextView = findViewById(R.id.detailsYearTextView);
                TextView ratingTextView = findViewById(R.id.detailsRatingTextView);
                TextView descriptionTextView = findViewById(R.id.detailsDescriptionTextView);
                ImageView imageView = findViewById(R.id.detailsImageView);


                titleTextView.setText("Title: " + movie.getTitle());
                genreTextView.setText("Genre: " + movie.getGenre());
                yearTextView.setText("Year: " + String.valueOf(movie.getYear()));
                ratingTextView.setText("Rating: " + String.valueOf(movie.getRating()));
                descriptionTextView.setText("Description: " + movie.getDescription());


                int imageResourceId = getResources().getIdentifier(movie.getImageName(), "drawable", getPackageName());
                imageView.setImageResource(imageResourceId);
            }
        }
    }
}
