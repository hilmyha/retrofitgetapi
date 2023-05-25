package com.example.retrofitgetapi.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitgetapi.API.Mahasiswa
import com.example.retrofitgetapi.R

class RvAdapter(private val context: Context, private val dataList: ArrayList<Mahasiswa>) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNim = itemView.findViewById<TextView>(R.id.nimTextView)
        val tvNama = itemView.findViewById<TextView>(R.id.namaTextView)
        val tvTelp = itemView.findViewById<TextView>(R.id.telpTextView)
        val cvMain = itemView.findViewById<CardView>(R.id.cv_main)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_layout,
            parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = dataList.size

    fun setData(data: List<Mahasiswa>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvNim.text = dataList[position].nim
        holder.tvNama.text = dataList[position].nama
        holder.tvTelp.text = dataList[position].telepon
        holder.cvMain.setOnClickListener {
            Toast.makeText(context, dataList[position].nama,
                Toast.LENGTH_SHORT).show()
        }
    }
}