package recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mymyyy.recipe_android.R
import kotlinx.android.synthetic.main.recyclerview_recipe.view.*

class RecipeAdapter(private val recipeList: List<String>) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private lateinit var listener: OnItemClickListener

    class RecipeViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = layoutInflater.inflate(R.layout.recyclerview_recipe, parent, false)
        return RecipeViewHolder(item)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.view.recipeName.text = recipeList[position]
        holder.view.setOnClickListener {
            listener.onItemClickListener(it, position, recipeList[position])
        }
    }

    interface OnItemClickListener {
        fun onItemClickListener(view: View, position: Int, clickedText: String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}