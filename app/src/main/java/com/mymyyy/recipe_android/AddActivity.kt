package com.mymyyy.recipe_android

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mymyyy.recipe_android.databinding.ActivityEditBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import recipe.RecipeEntity
import recipe.RecipeRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // リポジトリ取得
        val okHttpClient = OkHttpClient.Builder().build()
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.mymyyy.com/recipe/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
        val itemRepository = RecipeRepository(retrofit)

        val addButton = binding.updateButton
        addButton.text = getString(R.string.edit_recipe_add_button_label)
        val name = binding.editName
        val tag = binding.editTag
        val serve = binding.editServe
        val ingredients = binding.editIngredients
        val instructions = binding.editInstructions

        // 登録ボタン押下時
        addButton.setOnClickListener {
            val msg: String

            when {
                name.text.toString() == "" -> {
                    msg = getString(R.string.validate_name_message)
                }
                tag.text.toString() == "" -> {
                    msg = getString(R.string.validate_tag_message)
                }
                serve.text.toString() == "" -> {
                    msg = getString(R.string.validate_serve_message)
                }
                ingredients.text.toString() == "" -> {
                    msg = getString(R.string.validate_ingredients_message)
                }
                instructions.text.toString() == "" -> {
                    msg = getString(R.string.validate_instructions_message)
                }
                else -> {
                    val recipe = RecipeEntity(
                            "",
                            name.text.toString(),
                            tag.text.toString(),
                            serve.text.toString(),
                            ingredients.text.toString(),
                            instructions.text.toString())

                    itemRepository.addRecipe(recipe) {
                    }

                    name.text.clear()
                    tag.text.clear()
                    serve.text.clear()
                    ingredients.text.clear()
                    instructions.text.clear()

                    msg = getString(R.string.add_message)
                }
            }
            Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
        }

        // 戻るボタン押下時
        val backButton = binding.backButton
        backButton.setOnClickListener {
            val intent = Intent(this@AddActivity, ListActivity::class.java)
            startActivity(intent)
        }
    }
}
