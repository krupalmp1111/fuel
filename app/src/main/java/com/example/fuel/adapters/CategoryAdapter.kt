package com.example.fuel.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fuel.R
import com.example.fuel.activities.Pump_list
import com.example.fuel.models.pumpcategory
import com.example.fuel.utils.ColorPicker
import com.google.firebase.auth.FirebaseAuth


class CategoryAdapter(val context: Context, val Categorys: List<pumpcategory>) :
    RecyclerView.Adapter<CategoryAdapter.QuizViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false)
        return QuizViewHolder(view)
    }

    val auth = FirebaseAuth.getInstance()

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.textViewTitle.text = Categorys[position].title
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        holder.itemView.setOnClickListener {
            Toast.makeText(context, Categorys[position].title, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, Pump_list::class.java)
            intent.putExtra("cat", Categorys[position].title)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return Categorys.size
    }

    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView = itemView.findViewById(R.id.categoryTitle)
        var iconView: ImageView = itemView.findViewById(R.id.categoryIcon)
        var cardContainer: CardView = itemView.findViewById(R.id.cardContainer)

    }
}