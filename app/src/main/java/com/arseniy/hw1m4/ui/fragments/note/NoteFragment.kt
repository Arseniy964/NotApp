package com.arseniy.hw1m4.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.arseniy.hw1m4.App
import com.arseniy.hw1m4.R
import com.arseniy.hw1m4.data.model.NoteModel
import com.arseniy.hw1m4.databinding.FragmentNoteBinding
import com.arseniy.hw1m4.utils.OnClickItem

class NoteFragment : Fragment(), OnClickItem {
    private lateinit var binding: FragmentNoteBinding
    private var isGridLayout = false
    private val noteAdapter = NoteAdapter(onLongClick =this@NoteFragment, onClick = this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
        getData()
    }


    private fun initialize() {
        binding.rvNote.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }

    private fun setupListener() = with(binding) {
        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.noteDetailFragment)
        }
        btnChangeIcon.setOnClickListener {
            isGridLayout = !isGridLayout
            val layoutManager = if (isGridLayout) {
                GridLayoutManager(requireContext(), 2)
            } else {
                LinearLayoutManager(requireContext())
            }
            binding.rvNote.layoutManager = layoutManager
            updateLayoutChangerIcon()
        }
    }

    private fun updateLayoutChangerIcon() {
        val iconResId = if (isGridLayout) {
            R.drawable.img_menu
        } else {
            R.drawable.img_shape
        }
        binding.btnChangeIcon.setImageResource(iconResId)
    }

    private fun getData() {
        App().getInstate()?.noteDao()?.getAll()?.observe(viewLifecycleOwner) {
            noteAdapter.submitList(it)
        }
    }

    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Удалить заметку?")
            setPositiveButton("Удалить") { dialog, _ ->
                App.appDatabase?.noteDao()?.deleteNote(noteModel)
            }
            setNegativeButton("Отмена") { dialog, _ ->
                dialog.cancel()
                show()
            }
            builder.create()
        }
    }

    override fun onClick(noteModel: NoteModel) {
        val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(noteModel.id)
        findNavController().navigate(action)
    }
}