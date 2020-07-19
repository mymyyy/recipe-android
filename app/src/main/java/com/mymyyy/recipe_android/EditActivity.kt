package com.mymyyy.recipe_android

import ItemRepository
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        findViewById<TextView>(R.id.update_Button).text = getString(R.string.edit_recipe_update_button_label)

        val itemRepository = ItemRepository()
        val updateId = intent.getStringExtra("id").toString()

        itemRepository.getRecipeById(updateId) { recipe ->
            val name = findViewById<TextView>(R.id.edit_Name)
            name.text = recipe.name
            val tag = findViewById<TextView>(R.id.edit_Tag)
            tag.text = recipe.tag
            val serve = findViewById<TextView>(R.id.edit_Serve)
            serve.text = recipe.serve
            val ingredients = findViewById<TextView>(R.id.edit_Ingredients)
            ingredients.text = recipe.ingredients
            val instructions = findViewById<TextView>(R.id.edit_Instructions)
            instructions.text = recipe.instructions

            // 更新ボタン押下時
            val updateButton = findViewById<Button>(R.id.update_Button)
            updateButton.setOnClickListener {
                val msg: String

                when {
                    name.text.toString() == "" -> {
                        msg = "レシピ名を入力してください"
                    }
                    tag.text.toString() == "" -> {
                        msg = "タグを入力してください"
                    }
                    serve.text.toString() == "" -> {
                        msg = "何人前のレシピか入力してください"
                    }
                    ingredients.text.toString() == "" -> {
                        msg = "材料を入力してください"
                    }
                    instructions.text.toString() == "" -> {
                        msg = "作り方を入力してください"
                    }
                    else -> {
                        val updateRecipe = RecipeEntity(
                                updateId,
                                name.text.toString(),
                                tag.text.toString(),
                                serve.text.toString(),
                                ingredients.text.toString(),
                                instructions.text.toString())


                        itemRepository.updateRecipeById(updateId, updateRecipe) {
                        }
                        msg = "レシピを更新しました"
                    }
                }
                Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
            }


            // 戻るボタン押下時
            val backButton = findViewById<Button>(R.id.back_Button)
            backButton.setOnClickListener {
                val intent = Intent(this@EditActivity, ListActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
