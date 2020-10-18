package com.tikhonov.android.pokemonwiki

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SearchActivity : AppCompatActivity() {
    lateinit var mWeightTextView: TextView
    lateinit var mHeightTextView: TextView
    lateinit var mAbilitiesTextView: TextView
    lateinit var mEditText: EditText
    lateinit var mLinearLayout: LinearLayout
    lateinit var pokemonModel: PokemonModel
    lateinit var mHeaderTextView: TextView
    lateinit var mImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setTitle(R.string.app_search)

        mHeightTextView = findViewById(R.id.height_textview)
        mWeightTextView = findViewById(R.id.weight_textview)
        mAbilitiesTextView = findViewById(R.id.abilites_textview)
        mEditText = findViewById(R.id.search_edittext)
        mLinearLayout = findViewById(R.id.pokemon_card)
        mHeaderTextView = findViewById(R.id.header_pokemon_search)
        mImageView = findViewById(R.id.pokemon_image)
    }

    fun toSearch(view: View) {
        val app = App()
        app.api?.getPokemon(mEditText.text.toString())?.enqueue(object : Callback<PokemonModel?> {
            override fun onFailure(call: Call<PokemonModel?>, t: Throwable) {
                TODO("Not yet implemented")
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<PokemonModel?>, response: Response<PokemonModel?>) {
                if (response.isSuccessful) {
                    mLinearLayout.visibility = View.INVISIBLE
                    pokemonModel = response.body()!!
                    if(pokemonModel.sprites.frontDefault != null) {
                        Picasso.with(this@SearchActivity)
                            .load(pokemonModel.sprites.frontDefault)
                            .placeholder(R.drawable.loading)
                            .error(R.drawable.loading)
                            .into(mImageView)
                    } else {
                        Picasso.with(this@SearchActivity)
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
}