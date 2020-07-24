package com.mymyyy.recipe_android

import ItemRepository
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListActivity : AppCompatActivity() {

    //　ViewBinding を使うと findViewById をなくせるよ

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        // レシピ一覧を取得してlistViewにセット
        val itemRepository = ItemRepository()
        val listView = findViewById<ListView>(R.id.recipe_List)
        itemRepository.getRecipeList { recipeList ->
            val recipeListDisplay = recipeList.map { recipe -> recipe.name }

            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recipeListDisplay)
            listView.adapter = adapter

            // 遷移
            listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->

                //val jsonData = Json.stringify(recipe)
                val intent = Intent(this@ListActivity, EditActivity::class.java)
                intent.putExtra("id", recipeList[position].id)
                startActivity(intent)
            }
        }

        // 以下を追加
        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val intent = Intent(this@ListActivity, AddActivity::class.java)
            startActivity(intent)
        }
    }
}
