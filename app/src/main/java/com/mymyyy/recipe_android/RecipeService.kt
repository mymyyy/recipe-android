package com.mymyyy.recipe_android

import retrofit2.Call
import retrofit2.http.*

interface RecipeService {

    @GET("api")
    fun getRecipe(
    ): Call<List<RecipeEntity>>

    @POST("api")
    fun addRecipe(
            @Body recipeEntity: RecipeEntity
    ): Call<RecipeEntity>

    @GET("api/{id}")
    fun getRecipeById(
            @Path("id") id: String
    ): Call<RecipeEntity>

    @PUT("api/{id}")
    fun updateRecipe(
            @Path("id") id: String,
            @Body recipeEntity: RecipeEntity
    ): Call<RecipeEntity>
}


