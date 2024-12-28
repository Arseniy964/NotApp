package com.arseniy.hw1m4.ui.fragments.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arseniy.hw1m4.data.model.NoteModel
import com.arseniy.hw1m4.databinding.ItemNoteBinding
import com.arseniy.hw1m4.utils.OnClickItem

class NoteAdapter (
    private val onLongClick: OnClickItem,
    private val onClick : OnClickItem
) : ListAdapter<NoteModel, NoteAdapter.ViewHolder>(DiffCallBack()) {
    class ViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel) {
            binding.txtTitleNote.text = item.title
            binding.txtTextNote.text = item.description
            binding.txtDate.text = item.date
            binding.txtTime.text = item.time
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

        holder.itemView.setOnLongClickListener {
            onLongClick.onLongClick(getItem(position))
            true
        }
        holder.itemView.setOnClickListener {
            onClick.onClick(getItem(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    class DiffCallBack : DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }

    }
}