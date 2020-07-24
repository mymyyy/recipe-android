package com.mymyyy.recipe_android

import ItemRepository
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        findViewById<TextView>(R.id.update_Button).text = getString(R.string.edit_recipe_add_button_label)

        val itemRepository = ItemRepository()

        val name = findViewById<TextView>(R.id.edit_Name)
        val tag = findViewById<TextView>(R.id.edit_Tag)
        val serve = findViewById<TextView>(R.id.edit_Serve)
        val ingredients = findViewById<TextView>(R.id.edit_Ingredients)
        val instructions = findViewById<TextView>(R.id.edit_Instructions)

        // 登録ボタン押下時

        val addButton = findViewById<Button>(R.id.update_Button)

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

                    name.text = ""
                    tag.text = ""
                    serve.text = ""
                    ingredients.text = ""
                    instructions.text = ""

                    msg = getString(R.string.add_message)
                }
            }
            Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
        }

        // 戻るボタン押下時
        val backButton = findViewById<Button>(R.id.back_Button)
        backButton.setOnClickListener {
            val intent = Intent(this@AddActivity, ListActivity::class.java)
            startActivity(intent)
        }
    }
}
