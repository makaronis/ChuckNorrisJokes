package com.makaroni.chucknorrisjokes.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.makaroni.chucknorrisjokes.MainActivity;
import com.makaroni.chucknorrisjokes.R;
import com.makaroni.chucknorrisjokes.adapters.JokesRecyclerAdapter;
import com.makaroni.chucknorrisjokes.data.Joke;
import com.makaroni.chucknorrisjokes.data.JokesArray;
import com.makaroni.chucknorrisjokes.data.NorrisApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JokesFragment extends Fragment {
    private RecyclerView recyclerView;
    private JokesRecyclerAdapter adapter;
    private Button button;
    private TextInputEditText countInput;
    private Retrofit retrofit;
    private Bundle mBundleRecyclerViewState;
    private NorrisApi api;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.jokes_fragment,container,false);
        countInput = rootView.findViewById(R.id.count_input);
        countInput.clearFocus();

        button = rootView.findViewById(R.id.reload_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadJokes();

            }
        });

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new JokesRecyclerAdapter();
        recyclerView.setAdapter(adapter);

        initRetrofit();

        return rootView;
    }
    private void updateRecyclerData(List<Joke> newJokes){
        adapter.setList(newJokes);

    }
    private void initRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.icndb.com/jokes/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        api = retrofit.create(NorrisApi.class);
    }
    private void loadJokes(){
        int count;
        try {
            count = Integer.valueOf(countInput.getText().toString());
        }catch (NumberFormatException e){
            makeToast("Wrong number of jokes");
            countInput.getText().clear();
            return;
        }
        if (count<=0){
            makeToast("Can't show 0 jokes.");
            return;
        }
        if (count>300){
            makeToast("Too much jokes,sorry.");
            return;
        }
        Call<JokesArray> call = api.getJokes(count);
        call.enqueue(new Callback<JokesArray>() {
            @Override
            public void onResponse(Call<JokesArray> call, Response<JokesArray> response) {
                updateRecyclerData(response.body().getList());
                countInput.clearFocus();
                hideKeyboard();
            }

            @Override
            public void onFailure(Call<JokesArray> call, Throwable t) {
                makeToast("An error occurred, maybe you don't have internet connection.");
                hideKeyboard();
            }
        });
    }
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(countInput.getWindowToken(), 0);
    }
    public void makeToast(String toast){
        Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("1",adapter.getList());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState!=null) {
            List<Joke> jokeList = savedInstanceState.getParcelableArrayList("1");
            adapter.setList(jokeList);
        }
    }

}
