package com.mymyyy.recipe_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ListActivity : AppCompatActivity() {

    //　ViewBinding を使うと findViewById をなくせるよ
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        // リポジトリ取得
        val okHttpClient = OkHttpClient.Builder().build()
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.mymyyy.com/recipe/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
        val itemRepository = ItemRepository(retrofit)

        // レシピ一覧を取得してlistViewにセット
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

        // 新規登録ボタン押下時
        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val intent = Intent(this@ListActivity, AddActivity::class.java)
            startActivity(intent)
        }
    }
}
