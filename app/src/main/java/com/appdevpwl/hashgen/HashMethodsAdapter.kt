package com.appdevpwl.hashgen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appdevpwl.hashgen.databinding.HashMethodItemBinding

class HashMethodsAdapter(
    private val dataList: Array<String>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<HashMethodsAdapter.ViewHolder>() {

    var checkedPosition: Int = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            HashMethodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.hastName.text = dataList[position]

        if (checkedPosition == position) {
            holder.binding.hastName.setBackgroundResource(R.drawable.round_button_clicked)
        } else {
            holder.binding.hastName.setBackgroundResource(R.drawable.round_button)
        }

        holder.binding.hastName.setOnClickListener {
            checkedPosition = position
            listener.onItemClick(position)
            notifyDataSetChanged()
        }

    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(val binding: HashMethodItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}