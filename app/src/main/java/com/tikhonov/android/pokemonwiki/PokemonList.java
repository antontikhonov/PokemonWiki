package com.tikhonov.android.pokemonwiki;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonList {
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("results")
    @Expose
    private List<PokemonSingle> results = null;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<PokemonSingle> getResults() {
        return results;
    }

    public void setResults(List<PokemonSingle> results) {
        this.results = results;
    }
}
