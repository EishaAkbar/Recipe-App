package com.example.recipe.Listeners;

import com.example.recipe.models.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener  {
    void didFetch (RandomRecipeApiResponse response, String message);
    void didError (String message);
}
