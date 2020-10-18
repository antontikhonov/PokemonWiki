package com.tikhonov.android.pokemonwiki

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App {
    private var pokemonApi: PokemonApi? = null
    private lateinit var retrofit: Retrofit
    val api: PokemonApi?
        get() {
            retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            pokemonApi = retrofit.create(PokemonApi::class.java)
            return pokemonApi
        }
}