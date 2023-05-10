package com.example.gamesapi;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class searchList extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText editTextGame;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
        GameApiServiceList gameApiService = RetrofitApiList.getInstance().create(GameApiServiceList.class);
        editTextGame = findViewById(R.id.editTextGame);
        recyclerView = findViewById(R.id.recyclerViewFilmes);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#111111")));

        final int NCOL= 2;
        recyclerView.setLayoutManager(new GridLayoutManager(searchList.this, NCOL ));

        editTextGame.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Call<GameResponseList> call = gameApiService.getGameByName(editable.toString());
                call.enqueue(new Callback<GameResponseList>() {
                    @Override
                    public void onResponse(Call<GameResponseList> call, Response<GameResponseList> response) {
                        if (response.isSuccessful()) {
                            ArrayList<GameModelList> gameModelArrayLisT = response.body().results;
                            RecyclerView.Adapter adapter = new GameAdapter(gameModelArrayLisT);
                            Log.i("impossivel", String.valueOf(adapter.getItemCount()));
                            recyclerView.setAdapter(adapter);
                        } else {
                            // Handle error
                            Toast.makeText(searchList.this, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GameResponseList> call, Throwable t) {
                        // Handle failure
                        Log.e("Falha",t.toString());
                    }
                });
            }
        });
    }
}