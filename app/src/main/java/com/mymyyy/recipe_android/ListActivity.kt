package com.mymyyy.recipe_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_list.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ListActivity : AppCompatActivity() {

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

        // レシピ一覧を取得してrecyclerViewにセット
        itemRepository.getRecipeList { recipeList ->
            val recipeListDisplay = recipeList.map { recipe -> recipe.name }

            val recipeAdapter = RecipeAdapter(recipeListDisplay)
            val viewManager = LinearLayoutManager(this)

            recipe_recycler_view.layoutManager = viewManager
            recipe_recycler_view.adapter = recipeAdapter
            recipe_recycler_view.setHasFixedSize(true)

            recipeAdapter.setOnItemClickListener(object : RecipeAdapter.OnItemClickListener {
                override fun onItemClickListener(view: View, position: Int, clickedText: String) {
                    val intent = Intent(this@ListActivity, EditActivity::class.java)
                    intent.putExtra("id", recipeList[position].id)
                    startActivity(intent)
                }
            })
        }

        // 新規登録ボタン押下時
        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val intent = Intent(this@ListActivity, AddActivity::class.java)
            startActivity(intent)
        }
    }
}
