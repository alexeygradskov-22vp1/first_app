package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemRepository: ViewModel() {
    private val _list = MutableLiveData<MutableList<Item>>()
    val list: LiveData<MutableList<Item>> get() = _list

    fun fetchData() {
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
                    "элемент"+i,
                    "описание элемента" + i,
                    links[(0 until links.size).random()]
                )
            )
        }
        _list.value = items
    }
}
