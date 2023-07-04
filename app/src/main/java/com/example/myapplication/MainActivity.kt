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

    private fun getList(): MutableList<Item> {
        val items: MutableList<Item> = mutableListOf()
        val links: MutableList<String> =
            mutableListOf(
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
        for (i in 0..100) {
            items.add(
                Item(
                    getString(R.string.titleText)+i,
                    getString(R.string.descriptionText) + i,
                    links[(0 until links.size).random()]
                )
            )
        }
        return items
    }

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

}

