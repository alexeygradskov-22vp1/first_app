package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.compose.material3.contentColorFor
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : ComponentActivity() {
    private lateinit var binding: R
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val items1 = resources.getStringArray(R.array.items1)
        val items2 = resources.getStringArray(R.array.items2)
        val array: Array<Array<String>> = arrayOf(items1, items2)
        recyclerView.adapter = CustomRecyclerAdapter(array)
    }

}

class CustomRecyclerAdapter(private val names: Array<Array<String>>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {
    lateinit var _context: Context;

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemText: TextView = itemView.findViewById(R.id.item_text)
        val description: TextView = itemView.findViewById(R.id.item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        _context = recyclerView.context
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemText.text = names[0][position]
        holder.description.text = names[1][position]
        holder?.itemView?.setOnClickListener {
            val intent = Intent(_context, AboutActivity::class.java)
            intent
                .putExtra("position", position)
            _context.startActivity(intent)
        }
    }

    override fun getItemCount() = names[0].size

}

