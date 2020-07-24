package com.mymyyy.recipe_android

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ItemRepository(retrofit: Retrofit) {

    private val recipeService: RecipeService = retrofit.create(RecipeService::class.java)

    fun getRecipeList(callback: (List<RecipeEntity>) -> Unit) {
        recipeService.getRecipe().enqueue(object : Callback<List<RecipeEntity>> {

            override fun onResponse(call: Call<List<RecipeEntity>>?, response: Response<List<RecipeEntity>>?) {
                response?.let {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback(it)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<RecipeEntity>>?, t: Throwable?) {}
        })
    }

    fun addRecipe(recipe: RecipeEntity, callback: (RecipeEntity) -> Unit) {
        recipeService.addRecipe(recipe).enqueue(object : Callback<RecipeEntity> {
            override fun onResponse(call: Call<RecipeEntity>, response: Response<RecipeEntity>) {
                response.let {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback(it)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<RecipeEntity>, t: Throwable?) {}
        })
    }

    fun getRecipeById(id: String, callback: (RecipeEntity) -> Unit) {
        recipeService.getRecipeById(id).enqueue(object : Callback<RecipeEntity> {
            override fun onResponse(call: Call<RecipeEntity>, response: Response<RecipeEntity>) {
                response.let {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback(it)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<RecipeEntity>, t: Throwable?) {}
        })
    }

    fun updateRecipeById(id: String, recipe: RecipeEntity, callback: (RecipeEntity) -> Unit) {
        recipeService.updateRecipe(id, recipe).enqueue(object : Callback<RecipeEntity> {
            override fun onResponse(call: Call<RecipeEntity>, response: Response<RecipeEntity>) {
                response.let {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback(it)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<RecipeEntity>, t: Throwable?) {}
        })
    }
}