package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.Item
import com.example.myapplication.data.ItemRepository


class MainActivity : AppCompatActivity() {
    private lateinit var listViewModel: ItemRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tb: Toolbar = findViewById(R.id.toolbar)
        tb.setTitle(R.string.app_name)
        val recyclerView: RecyclerView = findViewById(R.id.list)
        listViewModel= ViewModelProvider(this).get(ItemRepository::class.java)
        listViewModel.list.observe(this
        ) { list -> recyclerView.adapter = CustomRecyclerAdapter(list) }
        listViewModel.fetchData()
    }



class CustomRecyclerAdapter(private val names: MutableList<Item>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemText: TextView = itemView.findViewById(R.id.item_text)
        val description: TextView = itemView.findViewById(R.id.item_description)
        val itemIMG: ImageView = itemView.findViewById(R.id.item_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemText.text = names[position].title
        val context: Context = holder.itemView.context
        holder.description.text = names[position].description
        Glide.with(context).load(names[position].imgLink).into(holder.itemIMG)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, AboutActivity::class.java).apply {
                putExtra(context.getString(R.string.titleTag), names[position].title)
                putExtra(context.getString(R.string.descriptTag), names[position].description)
                putExtra(context.getString(R.string.linkIMGTag), names[position].imgLink)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = names.size

}}

