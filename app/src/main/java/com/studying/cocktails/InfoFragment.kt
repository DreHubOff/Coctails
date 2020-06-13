package com.studying.cocktails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.studying.cocktails.network.model.CocktailLarge
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    fun update(cocktail: CocktailLarge) {
        Picasso.get().load(cocktail.imageUrl).into(image)
        val recipe = StringBuilder()
        recipe.apply {
            append("Name: ${cocktail.name}\n\n")
            append("Category: ${cocktail.category}\n")
            if (cocktail.ingredient1 != "null")
                append("Ingredient1: ${cocktail.ingredient1}\n")
            if (cocktail.ingredient2 != "null")
                append("Ingredient2: ${cocktail.ingredient2}\n")
            if (cocktail.ingredient3 != "null")
                append("Ingredient3: ${cocktail.ingredient3}\n")
            if (cocktail.ingredient4 != "null")
                append("Ingredient4: ${cocktail.ingredient4}\n")
            if (cocktail.instructions != "null")
                append("Instruction: ${cocktail.instructions}\n")
        }
        info_text.text = recipe
    }
}
