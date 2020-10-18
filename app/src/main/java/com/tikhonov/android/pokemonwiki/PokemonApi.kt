package com.tikhonov.android.pokemonwiki

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    @GET("/api/v2/pokemon/{name}")
    fun getPokemon(@Path("name") name: String?): Call<PokemonModel?>
}