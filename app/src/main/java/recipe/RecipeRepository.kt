package recipe

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RecipeRepository(retrofit: Retrofit) {

    private val recipeService: RecipeService = retrofit.create(RecipeService::class.java)

    private fun <T> Call<T>.enqueue(onResponse: (T) -> Unit, onFailure: ((Throwable?) -> Unit)? = null) {
        enqueue(object : Callback<T> {

            override fun onResponse(call: Call<T>?, response: Response<T>?) {
                response?.let {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onResponse(it)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                onFailure?.let { it(t) }
            }
        })
    }

    fun getRecipeList(callback: (List<RecipeEntity>) -> Unit) {
        recipeService.getRecipe().enqueue(callback)
    }

    fun addRecipe(recipe: RecipeEntity, callback: (RecipeEntity) -> Unit) {
        recipeService.addRecipe(recipe).enqueue(callback)
    }

    fun getRecipeById(id: String, callback: (RecipeEntity) -> Unit) {
        recipeService.getRecipeById(id).enqueue(callback)
    }

    fun updateRecipeById(id: String, recipe: RecipeEntity, callback: (RecipeEntity) -> Unit) {
        recipeService.updateRecipe(id, recipe).enqueue(callback)
    }
}