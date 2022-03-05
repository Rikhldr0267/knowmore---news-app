package com.example.read_the_news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class myadapter(private val listen:clickhandler): RecyclerView.Adapter<viewholder>() {

    private val items:ArrayList<news> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):viewholder{ //returns viewholder

        //converting xml(named as "recyclerview_items.xml) to view and passing to view veriable as onCreateViewHolder function returns view
        // so we converted xml to view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_items,parent,false)
        val viewholders = viewholder(view)
        view.setOnClickListener{
            listen.onclicked(items[viewholders.adapterPosition] )
        }
        return viewholders

    }

    override fun onBindViewHolder(holder: viewholder , position: Int) {
        val curr = items[position] //this is current item
        holder.titleview.text = curr.title
        holder.authorview.text = curr.author
        Glide.with(holder.itemView.context).load(curr.urlToImage).into(holder.image)
    }

    override fun getItemCount(): Int {        // how many items on this list will be present
        return items.size
    }
    fun updateNews(updatedNews:ArrayList<news>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }

}


class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleview:TextView = itemView.findViewById(R.id.title)
    val image:ImageView = itemView.findViewById(R.id.image)
    val authorview:TextView = itemView.findViewById(R.id.author)

}

interface clickhandler{
    fun onclicked(item:news)
}
