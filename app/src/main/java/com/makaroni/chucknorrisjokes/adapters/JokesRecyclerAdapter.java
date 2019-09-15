package com.makaroni.chucknorrisjokes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makaroni.chucknorrisjokes.R;
import com.makaroni.chucknorrisjokes.data.Joke;

import java.util.ArrayList;
import java.util.List;

public class JokesRecyclerAdapter extends RecyclerView.Adapter<JokesRecyclerAdapter.JokesViewHolder> {
    private ArrayList<Joke> jokesList = new ArrayList<>();

    public ArrayList<Joke> getList() {
        return jokesList;
    }

    public void setList(List<Joke> list) {
        jokesList.clear();
        jokesList.addAll(list);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public JokesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.joke_item, parent, false);
        return new JokesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JokesViewHolder holder, int position) {
        holder.bind(jokesList.get(position));
    }

    @Override
    public int getItemCount() {
        return jokesList.size();
    }

    public class JokesViewHolder extends RecyclerView.ViewHolder {
        private TextView jokeText;

        public JokesViewHolder(@NonNull View itemView) {
            super(itemView);
            jokeText = itemView.findViewById(R.id.joke_text);
        }

        public void bind(Joke joke) {
            jokeText.setText(joke.getBody());
        }
    }
}
