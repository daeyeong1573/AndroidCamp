package org.arr.software.androidcamp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import org.arr.software.androidcamp.R
import org.arr.software.androidcamp.TodoItemAdapter
import org.arr.software.androidcamp.databinding.TodoListFragmentBinding
import org.arr.software.androidcamp.model.TodoItem
import org.arr.software.androidcamp.viewmodel.TodoListFragmentViewModel
import org.arr.software.androidcamp.viewmodel.TodoViewModel

private const val TAG = "TodoListFragment"

class TodoListFragment : Fragment() {

    private lateinit var binding: TodoListFragmentBinding
    private val mainActivityViewModel: TodoViewModel by activityViewModels()
    private val todoListFragmentViewModel: TodoListFragmentViewModel by viewModels()

    companion object {
        fun newInstance() = TodoListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "called onCreateView")
        val view = inflater.inflate(R.layout.todo_list_fragment, container, false)
        binding = TodoListFragmentBinding.bind(view).apply {
            viewmodel = todoListFragmentViewModel
        }
        binding.lifecycleOwner = this.viewLifecycleOwner
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "called onActivityCreated")

        // adapterのバインド処理
        val adapter = TodoItemAdapter(todoListFragmentViewModel)
        binding.list.adapter = adapter

        adapter.setOnItemClickListener(object : TodoItemAdapter.OnItemClickListener {
            override fun onItemClickListener(todoItem: TodoItem, position: Int) {
                Log.d(TAG, "called onItemClickListener")
                mainActivityViewModel.todoItemClicked(todoItem)
            }
        })
        adapter.setOnLongItemClickListener(object : TodoItemAdapter.OnLongItemClickListener {
            override fun onLongItemClickListener(todoItem: TodoItem, position: Int): Boolean {
                Log.d(TAG, "called onLongItemClickListener")
                todoListFragmentViewModel.deleteTask(todoItem)
                return true
            }
        })
        adapter.setOnCheckBoxClickListener(object : TodoItemAdapter.OnCheckBoxClickListener {
            override fun onCheckBoxClickListener(todoItem: TodoItem, position: Int) {
                Log.d(TAG, "called onCheckBoxClickListener")
                todoListFragmentViewModel.isDoneStateChange(todoItem.id)
                todoListFragmentViewModel.updateUI()
            }
        })
        binding.floatingActionButton.setOnClickListener {
            Log.d(TAG, "FAB listener clicked")
            mainActivityViewModel.createTask()
        }
        todoListFragmentViewModel.updateUI()
    }

}