package com.tikhonov.android.pokemonwiki

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("/api/v2/pokemon/{name}")
    fun getPokemon(@Path("name") name: String?): Call<PokemonModel?>

    @GET("/api/v2/pokemon/")
    fun getPokemonList(@Query("limit") limit: Int?, @Query("offset") offset: Int?): Call<PokemonList?>
}