package com.example.fuel.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fuel.R
import com.example.fuel.activities.PumpActivity
import com.example.fuel.models.pump
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class PumpAdapter(val context: Context, val pumps: ArrayList<pump>, val path: String) :
    RecyclerView.Adapter<PumpAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pump_item, parent, false)
        return BookViewHolder(view)
    }

    val userType = FirebaseAuth.getInstance().currentUser!!.email


    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        if (userType.equals("krupalmp1111@gmail.com")) {
            holder.delBtn.visibility = View.VISIBLE
        }

        holder.delBtn.setOnClickListener {
            val query = FirebaseFirestore.getInstance().collection(path).whereEqualTo("pumpname", pumps[position].pumpname).get()
            query.addOnSuccessListener {
                for (document in it) {
                    FirebaseFirestore.getInstance().collection(path).document(document.id).delete()
                }
                (context as Activity).finish()
                context.startActivity((context as Activity).intent)
            }
                .addOnFailureListener { }
        }


        if (pumps.size > 0) {
            val currentlist = pumps[position]
            holder.pumpname.text = pumps[position].pumpname
            holder.ownername.text = pumps[position].ownername
            Picasso.get().load(pumps[position].pumpimage).into(holder.pumpimage)
            holder.price.text = pumps[position].price
            holder.itemView.setOnClickListener {
                val intent = Intent(context, PumpActivity::class.java)
                intent.putExtra("pname", pumps[position].pumpname)
                intent.putExtra("oname", pumps[position].ownername)
                intent.putExtra("price", pumps[position].price)
                intent.putExtra("img", pumps[position].pumpimage)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return pumps.size
    }

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pumpname: TextView = itemView.findViewById(R.id.pumpname)
        var pumpimage: ImageView = itemView.findViewById(R.id.pumpimage)
        var ownername: TextView = itemView.findViewById(R.id.ownername)
        var price: TextView = itemView.findViewById(R.id.pricetag)
        var delBtn: Button = itemView.findViewById(R.id.delBtn)
    }

}