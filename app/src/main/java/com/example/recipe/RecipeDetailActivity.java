package com.example.recipe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe.Adapters.IngredientsAdapter;
import com.example.recipe.Adapters.InstructionsAdapter;
import com.example.recipe.Adapters.SimilarRecipeAdapter;
import com.example.recipe.Listeners.InstructionsListener;
import com.example.recipe.Listeners.RecipeClickListener;
import com.example.recipe.Listeners.RecipeDeatilsListener;
import com.example.recipe.Listeners.SimilarRecipesListener;
import com.example.recipe.models.InstructionResponse;
import com.example.recipe.models.RecipeDetailsResponse;
import com.example.recipe.models.SimilarRecipeResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity {
    int id;
    TextView textView_meal_name, textView_meal_source, textView_meal_summary;
    ImageView imageView_meal_image;
    RecyclerView recycler_meal_ingredients, recycler_meal_similar, recycler_meal_instructions;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;

    SimilarRecipeAdapter similarRecipeAdapter;
    InstructionsAdapter instructionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
            EdgeToEdge.enable(this);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViews();
        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);
        manager.getRecpieDetails(recipeDeatilsListener, id);
        manager.getSimilarRecipes(similarRecipesListener, id);
        manager.getInstructions(instructionsListener, id);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Details....");
        dialog.show();
    }

    private void findViews() {
        textView_meal_name = findViewById(R.id.textView_meal_name);
        textView_meal_source = findViewById(R.id.textView_meal_source);
        textView_meal_summary = findViewById(R.id.textView_meal_summary);
        imageView_meal_image = findViewById(R.id.imageView_meal_image);
        recycler_meal_ingredients = findViewById(R.id.recycler_meal_ingredients);
        recycler_meal_similar = findViewById(R.id.recycler_meal_similar);
        recycler_meal_instructions = findViewById(R.id.recycler_meal_instructions);
    }
    private final RecipeDeatilsListener recipeDeatilsListener = new RecipeDeatilsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
            textView_meal_name.setText(response.title);
            textView_meal_source.setText(response.sourceName);
            textView_meal_summary.setText(response.summary);
            Picasso.get().load(response.image).into(imageView_meal_image);

            recycler_meal_ingredients.setHasFixedSize(true);
            recycler_meal_ingredients.setLayoutManager(new LinearLayoutManager(RecipeDetailActivity.this, LinearLayoutManager.HORIZONTAL, false));
            ingredientsAdapter = new IngredientsAdapter(RecipeDetailActivity.this,response.extendedIngredients);
            recycler_meal_ingredients.setAdapter(ingredientsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailActivity.this, message, Toast.LENGTH_SHORT).show();

        }
    };

    private final SimilarRecipesListener similarRecipesListener = new SimilarRecipesListener() {
        @Override
        public void didFetch(List<SimilarRecipeResponse> response, String message) {
            recycler_meal_similar.setHasFixedSize(true);
            recycler_meal_similar.setLayoutManager(new LinearLayoutManager(RecipeDetailActivity.this, LinearLayoutManager.HORIZONTAL, false));
            similarRecipeAdapter = new SimilarRecipeAdapter(RecipeDetailActivity.this, response, recipeClickListener);
            recycler_meal_similar.setAdapter(similarRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
         startActivity(new Intent(RecipeDetailActivity.this, RecipeDetailActivity.class)
                 .putExtra("id", id));

        }
    };
    private final InstructionsListener instructionsListener = new InstructionsListener() {
        @Override
        public void didFetch(List<InstructionResponse> response, String message) {

            recycler_meal_instructions.setHasFixedSize(true);
            recycler_meal_instructions.setLayoutManager(new LinearLayoutManager(RecipeDetailActivity.this, LinearLayoutManager.VERTICAL, false));
            instructionsAdapter = new InstructionsAdapter(RecipeDetailActivity.this, response);
            recycler_meal_instructions.setAdapter(instructionsAdapter);
        }

        @Override
        public void didError(String message) {

        }
    };
}