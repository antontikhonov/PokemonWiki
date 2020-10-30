package com.tikhonov.android.pokemonwiki

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RandomActivity : AppCompatActivity() {
    lateinit var mWeightTextView: TextView
    lateinit var mHeightTextView: TextView
    lateinit var mAbilitiesTextView: TextView
    lateinit var mLinearLayout: LinearLayout
    lateinit var pokemonModel: PokemonModel
    lateinit var mHeaderTextView: TextView
    lateinit var mImageView: ImageView
    lateinit var randomName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)
        setTitle(R.string.app_random)

        mHeightTextView = findViewById(R.id.height_textview)
        mWeightTextView = findViewById(R.id.weight_textview)
        mAbilitiesTextView = findViewById(R.id.abilites_textview)
        mLinearLayout = findViewById(R.id.pokemon_card)
        mHeaderTextView = findViewById(R.id.header_pokemon_search)
        mImageView = findViewById(R.id.pokemon_image)
    }

    fun toRandom(view: View) {
        mLinearLayout.visibility = View.INVISIBLE
        mImageView.setImageResource(R.drawable.loading)
        val random_number = (Math.random()*1050).toInt()
        val app = App()

        app.api?.getPokemonList(1050, 0)?.enqueue(object : Callback<PokemonList?> {
            override fun onFailure(call: Call<PokemonList?>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<PokemonList?>, response: Response<PokemonList?>) {
                randomName = response.body()!!.results[random_number].name
                app.api?.getPokemon(randomName)?.enqueue(object : Callback<PokemonModel?> {
                    override fun onFailure(call: Call<PokemonModel?>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                    @SuppressLint("SetTextI18n")
                    override fun onResponse(call: Call<PokemonModel?>, response: Response<PokemonModel?>) {
                        if (response.isSuccessful) {
                            pokemonModel = response.body()!!
                            if(pokemonModel.sprites.frontDefault != null) {
                                Picasso.with(this@RandomActivity)
                                    .load(pokemonModel.sprites.frontDefault)
                                    .placeholder(R.drawable.loading)
                                    .error(R.drawable.loading)
                                    .into(mImageView)
                            } else {
                                Picasso.with(this@RandomActivity)
                                    .load(pokemonModel.sprites.frontShiny)
                                    .placeholder(R.drawable.nophoto)
                                    .error(R.drawable.nophoto)
                                    .into(mImageView)
                            }
                            mHeaderTextView.text = pokemonModel.name.capitalize(Locale.ROOT)
                            mWeightTextView.text = "Its weight is ${pokemonModel.weight / 10.0} kg"
                            mHeightTextView.text = "Its height is ${pokemonModel.height / 10.0} m"
                            var abilitiesString = "Abilities: "
                            pokemonModel.abilities.forEach {
                                abilitiesString += "\n-${it.ability.name}"
                            }
                            mAbilitiesTextView.text = abilitiesString
                            mLinearLayout.visibility = View.VISIBLE
                        }
                    }
                })
            }
        })
    }
}