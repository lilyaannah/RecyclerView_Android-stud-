package com.example.kr_recycleview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


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
