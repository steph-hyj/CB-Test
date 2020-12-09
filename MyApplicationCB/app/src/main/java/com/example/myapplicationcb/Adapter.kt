package com.example.myapplicationcb

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationcb.model.Reqres
import kotlinx.android.synthetic.main.item_view.view.*

class Adapter(private val datalist: MutableList<Reqres>): RecyclerView.Adapter<Holder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        return Holder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false))
    }

    override fun getItemCount(): Int = datalist.size

        override fun onBindViewHolder(holder: Holder, position: Int) {
            val data = datalist[position]

            val barcodeTextView = holder.itemView.barcode
            val editDate = holder.itemView.newDate

            val title = "${data.title}"
            val barcode = "${data.barcode}"

            barcodeTextView.text = barcode

            holder.itemView.setOnClickListener{
                Toast.makeText(context, title, Toast.LENGTH_SHORT).show()
            }

    }
}

