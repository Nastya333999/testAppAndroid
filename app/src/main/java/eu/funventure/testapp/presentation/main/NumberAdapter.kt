package eu.funventure.testapp.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.funventure.testapp.R
import eu.funventure.testapp.domain.model.NumberInfo
import eu.funventure.testapp.presentation.main.NumberAdapter.*

class NumberAdapter : ListAdapter<NumberInfo, NumberInfoVH>(NumberInfoDiffCallBack()) {

    private var onClickListener: ((NumberInfo) -> Unit)? = null

    fun setOnItemClickListener(l: (NumberInfo) -> Unit) {
        onClickListener = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberInfoVH {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.iten_number_info, parent, false)
        return NumberInfoVH(itemView)
    }

    override fun onBindViewHolder(holder: NumberInfoVH, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NumberInfoVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val number = itemView.findViewById<TextView>(R.id.txtNumber)
        private val textDescription = itemView.findViewById<TextView>(R.id.txtDescription)
        private var data: NumberInfo? = null

        init {
            itemView.setOnClickListener {
                data?.let { onClickListener?.invoke(it) }
            }
        }

        fun bind(item: NumberInfo) {
            data = item
            number.text = item.value.toString()
            textDescription.text = item.description
        }
    }

    private class NumberInfoDiffCallBack : DiffUtil.ItemCallback<NumberInfo>() {
        override fun areItemsTheSame(oldItem: NumberInfo, newItem: NumberInfo): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: NumberInfo, newItem: NumberInfo): Boolean =
            oldItem == newItem
    }
}