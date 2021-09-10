package com.example.letslearninkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerListAdapter (
    var userList : List<User>
        ) : RecyclerView.Adapter<RecyclerListAdapter.RecyclerListHolder>() {

    inner class RecyclerListHolder(itemView : View) :
            RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerListHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_list_layout, parent, false)

        return RecyclerListHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerListHolder, position: Int) {
        holder.itemView.apply {
            val tv_listName : TextView = findViewById(R.id.tv_listName)
            val tv_listPassword : TextView = findViewById(R.id.tv_listPassword)
            tv_listName.text = userList[position].username
            tv_listPassword.text = userList[position].password
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}