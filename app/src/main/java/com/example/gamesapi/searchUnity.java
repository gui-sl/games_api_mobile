package com.example.gamesapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class searchUnity extends AppCompatActivity {

    ActionBar actionBar;
    EditText nomeBuscado;
    TextView nomeGame, anoGame, avaliacaoGame, generoGame;
    ImageView imagemGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_unity);
        associaJavaXml();
        Retrofit retrofitInstance = RetrofitApi.getInstance();
        GameAPIService service = retrofitInstance.create(GameAPIService.class);
        nomeBuscado.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Call<Results> call = service.getGameByName(editable.toString());
                call.enqueue(new Callback<Results>() {
                    @Override
                    public void onResponse(Call<Results> call, Response<Results> response) {
                        Results results = response.body();
                        Results.Result firstResult = results.results.get(0);
                        Log.i("impossivel",firstResult.name);
                        loadActivityFilds(firstResult);
                    }

                    @Override
                    public void onFailure(Call<Results> call, Throwable t) {
                        Log.e("Falhou",t.toString());
                    }
                });
            }
        });

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#111111")));
    }

    private void loadActivityFilds(Results.Result firstResult) {
        Picasso.get().load(firstResult.background_image).into(imagemGame);
        nomeGame.setText(firstResult.name);
        anoGame.setText(firstResult.released);
        avaliacaoGame.setText(firstResult.rating);
    }

    private void associaJavaXml(){
        nomeBuscado = findViewById(R.id.nomeBuscado);
        imagemGame = findViewById(R.id.imagemGame);
        nomeGame = findViewById(R.id.nomeGame);
        anoGame = findViewById(R.id.anoGame);
        avaliacaoGame = findViewById(R.id.avaliacaoGame);
        generoGame = findViewById(R.id.generoGame);
    }
}