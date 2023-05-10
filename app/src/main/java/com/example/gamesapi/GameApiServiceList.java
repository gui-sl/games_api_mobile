package com.example.gamesapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GameApiServiceList {
    final String Key ="games?key=eff3225a572d4b8b974c0e212560841f";

    @GET(Key)
    Call<GameResponseList> getGameByName(@Query("search")String name);

}
