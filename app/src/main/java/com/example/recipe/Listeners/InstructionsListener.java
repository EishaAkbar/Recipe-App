package com.example.recipe.Listeners;

import com.example.recipe.models.InstructionResponse;

import java.util.List;

public interface InstructionsListener {
    void didFetch(List<InstructionResponse> response, String message);
    void didError(String message);
}
