package org.arr.software.androidcamp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import org.arr.software.androidcamp.R
import org.arr.software.androidcamp.databinding.TodoItemDetailFragmentBinding
import org.arr.software.androidcamp.model.TodoItem
import org.arr.software.androidcamp.utils.toString
import org.arr.software.androidcamp.viewmodel.TodoItemDetailFragmentViewModel
import org.arr.software.androidcamp.viewmodel.TodoViewModel

private const val TAG = "TodoItemDetailFragment"

class TodoItemDetailFragment : Fragment() {
    private lateinit var binding: TodoItemDetailFragmentBinding
    private val todoViewModel: TodoViewModel by activityViewModels()
    private val todoItemDetailFragmentViewModel: TodoItemDetailFragmentViewModel by viewModels()

    companion object {
        fun newInstance() = TodoItemDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "called onCreateView")
        val view = inflater.inflate(R.layout.todo_item_detail_fragment, container, false)
        binding = TodoItemDetailFragmentBinding.bind(view).apply {
            viewmodel = todoItemDetailFragmentViewModel
        }
        binding.lifecycleOwner = this.viewLifecycleOwner
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "called onActivityCreated")
        binding.buttonRight.setOnClickListener {
            closeFragment()
        }
        val selectedItem = todoViewModel.selectedItem
        if (selectedItem == null) {
            showNewTask()
        } else {
            showTask(selectedItem)
        }
    }

    private fun closeFragment() {
        val mainActivity = activity
        if (mainActivity != null) {
            mainActivity.supportFragmentManager.popBackStack()
        } else {
            todoViewModel.createTask()
        }
    }

    private fun showNewTask() {
        binding.buttonLeft.setOnClickListener {
            todoItemDetailFragmentViewModel.createNewTask(
                binding.editTitle.text.toString(),
                binding.editDetail.text.toString()
            )
            closeFragment()
        }
    }

    private fun showTask(todoItem: TodoItem) {
        binding.editTitle.setText(todoItem.title, TextView.BufferType.EDITABLE)
        binding.editDetail.setText(todoItem.detail, TextView.BufferType.EDITABLE)
        binding.editCreate.text = todoItem.createDate.toString("yyyy/MM/dd")
        binding.createDate.isVisible = true
        binding.editCreate.isVisible = true
        if (todoItem.createDate != todoItem.updateDate) {
            binding.editUpdate.text = todoItem.updateDate.toString("yyyy/MM/dd")
            binding.editUpdate.isVisible = true
            binding.update.isVisible = true
        }
        binding.buttonLeft.text = "수정"
        binding.buttonLeft.setOnClickListener {
            todoItemDetailFragmentViewModel.updateTask(
                todoItem.id,
                binding.editTitle.text.toString(),
                binding.editDetail.text.toString()
            )
            closeFragment()
        }

    }
}