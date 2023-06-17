package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Item


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tb: Toolbar = findViewById(R.id.toolbar)
        tb.setTitle(R.string.app_name)
        val recyclerView: RecyclerView = findViewById(R.id.list)
        recyclerView.adapter = CustomRecyclerAdapter(getList())
    }

    fun getList(): ArrayList<Item>{
        val items1 = resources.getStringArray(R.array.items1)
        val items2 = resources.getStringArray(R.array.items2)
        val items: ArrayList<Item> = ArrayList()
        items1.forEachIndexed { index, item -> items.add(Item(item, items2[index])) }
        return items
    }

}

class CustomRecyclerAdapter(private val names: ArrayList<Item>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

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

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemText.text = names[position].title
        val _context :Context = holder.itemView.context
        holder.description.text = names[position].description
        holder.itemView.setOnClickListener {
            val intent = Intent(_context, AboutActivity::class.java)
            intent
                .putExtra("position", position)
            _context.startActivity(intent)
        }
    }

    override fun getItemCount() = names.size

}

