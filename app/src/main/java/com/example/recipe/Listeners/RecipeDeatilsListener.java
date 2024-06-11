package com.example.recipe.Listeners;

import com.example.recipe.models.RecipeDetailsResponse;

public interface RecipeDeatilsListener {
    void didFetch(RecipeDetailsResponse response, String message);
    void didError(String  message);

}
