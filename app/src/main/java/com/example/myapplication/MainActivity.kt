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
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

    fun getList(): ArrayList<Item> {
        val items: ArrayList<Item> = ArrayList()
        val links: ArrayList<String> =
            ArrayList(
                listOf(
                    "https://sun9-39.userapi.com/s/v1/ig2/VoIuXZFyFLsVO3C4-bFPfXymVYqO" +
                            "_i_rTuQbNQyscf45BRJpqggy2-4nwFpUt40FgSBRzpbcmK-as1Ifbd2NA4lX.j" +
                            "pg?size=600x376&quality=96&type=album",
                    "https://yt3.googleusercontent.com/"
                            + "ytc/AL5GRJWz1KzN6a2IZaCcdGpO3um5qdGI4R3W1ILIzSBl1w=s900-c-k-"
                            + "c0x00ffffff-no-rj",
                    "https://i.ytimg.com/vi/Ae6aHDMtzBs/maxresdefault.jpg?" +
                            "sqp=-oaymwEmCIAKENAF8quKqQMa8AEB-AH-CYAC0AWKAgwIABABGGUgVChEMA8=&" +
                            "rs=AOn4CLAJmwLwIgDPnhD5SpbwuLa_Rl6GSQ"
                )
            )
        for (i in 0..100) {
            items.add(
                Item(
                    "Элемент" + i,
                    "Описание элемента" + i,
                    links[(0..links.size-1).random()]
                )
            )
        }
        return items
    }

}

class CustomRecyclerAdapter(private val names: ArrayList<Item>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemText: TextView = itemView.findViewById(R.id.item_text)
        val description: TextView = itemView.findViewById(R.id.item_description)
        val itemIMG: ImageView = itemView.findViewById(R.id.itemIMG)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemText.text = names[position].title
        val _context: Context = holder.itemView.context
        holder.description.text = names[position].description
        Glide.with(_context).load(names[position].imgLink).into(holder.itemIMG)
        holder.itemView.setOnClickListener {
            val intent = Intent(_context, AboutActivity::class.java)
            intent
                .putExtra("name", names[position].title)
            intent.putExtra("descript", names[position].description)
            intent.putExtra("linkIMG", names[position].imgLink)
            _context.startActivity(intent)
        }
    }

    override fun getItemCount() = names.size

}

