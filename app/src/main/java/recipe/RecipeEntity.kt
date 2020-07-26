package recipe

data class RecipeEntity(
        val id: String,
        val name: String,
        val tag: String,
        val serve: String,
        val ingredients: String,
        val instructions: String
)