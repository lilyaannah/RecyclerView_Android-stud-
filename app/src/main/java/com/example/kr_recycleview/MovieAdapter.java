package com.example.kr_recycleview;
import com.example.kr_recycleview.Movie;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

    public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> implements Filterable {
        private List<Movie> movieList;
        private List<Movie> filteredMovieList; // Список для фильтрации
        private Context context;

        public MovieAdapter(List<Movie> movieList, Context context) {
            this.movieList = movieList;
            this.filteredMovieList = new ArrayList<>(movieList); // Изначально совпадает с оригинальным списком
            this.context = context;
        }

        @NonNull
        @Override
        public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
            return new MovieViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
            Movie movie = filteredMovieList.get(position);  // Изменили на filteredMovieList

            holder.titleTextView.setText(movie.getTitle());
            holder.genreTextView.setText(movie.getGenre());
            holder.yearTextView.setText(String.valueOf(movie.getYear()));
            holder.ratingTextView.setText(String.valueOf(movie.getRating()));

            // Урезаем описание до 50 символов (или другой длины)
            String truncatedDescription = movie.getDescription();
            if (truncatedDescription.length() > 50) {
                truncatedDescription = truncatedDescription.substring(0, 50) + "...";
            }
            holder.descriptionTextView.setText(truncatedDescription);

            // Получаем идентификатор ресурса изображения по его имени
            int imageResourceId = context.getResources().getIdentifier(movie.getImageName(), "drawable", context.getPackageName());
            holder.imageView.setImageResource(imageResourceId);

            // Добавляем обработчик клика на элемент списка
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Создаем Intent для перехода к MovieDetailsActivity
                    Intent intent = new Intent(context, MovieDetailsActivity.class);
                    // Передаем объект Movie в Intent
                    intent.putExtra("movie", movie);
                    // Запускаем активность
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return filteredMovieList.size(); // Используем filteredMovieList
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    String query = constraint.toString().toLowerCase().trim();
                    List<Movie> filtered = new ArrayList<>();

                    if (query.isEmpty()) {
                        filtered.addAll(movieList); // Когда поиск пустой, показываем весь список
                    } else {
                        // Разделяем строку запроса на отдельные жанры
                        String[] genres = query.split("\\s*,\\s*|\\s+");  // Разделение по запятой или пробелу

                        // Фильтрация по названию и жанру
                        for (Movie movie : movieList) {
                            boolean match = false;

                            // Проверяем, есть ли все жанры в жанре фильма
                            for (String genre : genres) {
                                if (movie.getGenre().toLowerCase().contains(genre)) {
                                    match = true;
                                } else {
                                    match = false;
                                    break; // Если хотя бы один жанр не совпадает, то не показываем фильм
                                }
                            }

                            if (match) {
                                filtered.add(movie);
                            }
                        }
                    }

                    FilterResults results = new FilterResults();
                    results.values = filtered;
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    filteredMovieList.clear();  // Очищаем список
                    if (results.values != null) {
                        filteredMovieList.addAll((List<Movie>) results.values);  // Добавляем отфильтрованные элементы
                    }
                    notifyDataSetChanged();  // Обновляем отображение
                }
            };
        }

        public static class MovieViewHolder extends RecyclerView.ViewHolder {
            public TextView titleTextView, genreTextView, yearTextView, ratingTextView, descriptionTextView;
            public ImageView imageView;

            public MovieViewHolder(@NonNull View itemView) {
                super(itemView);
                titleTextView = itemView.findViewById(R.id.titleTextView);
                genreTextView = itemView.findViewById(R.id.genreTextView);
                yearTextView = itemView.findViewById(R.id.yearTextView);
                ratingTextView = itemView.findViewById(R.id.ratingTextView);
                descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
                imageView = itemView.findViewById(R.id.imageView);
            }
        }
}