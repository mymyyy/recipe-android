import com.mymyyy.recipe_android.RecipeEntity
import com.mymyyy.recipe_android.RecipeService
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ItemRepository {

    private var recipeService: RecipeService

    init {
        val okHttpClient = OkHttpClient.Builder().build()
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.mymyyy.com/recipe/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()

        recipeService = retrofit.create(RecipeService::class.java)
    }

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