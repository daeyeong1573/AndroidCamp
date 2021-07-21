package org.arr.software.androidcamp.viewmodel

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.arr.software.androidcamp.model.TodoItem
import org.arr.software.androidcamp.utils.Event
import org.arr.software.androidcamp.view.fragment.TodoItemDetailFragment

private const val TAG = "TodoActivityViewModel"


class TodoViewModel : ViewModel(){
    var selectedItem: TodoItem? = null
    val navigateToFragment: LiveData<Event<FragmentNavigationRequest>> get() = _navigateToFragment
    private val _navigateToFragment = MutableLiveData<Event<FragmentNavigationRequest>>()

    fun createTask() {
        Log.d(TAG, "createTask")
        selectedItem = null
        showFragment(TodoItemDetailFragment.newInstance())
    }

    fun showFragment(fragment: Fragment, backStack: Boolean = true, tag: String? = null) {
        _navigateToFragment.value = Event(FragmentNavigationRequest(fragment, backStack, tag))
    }

    fun todoItemClicked(todoItem: TodoItem) {
        Log.d(TAG, "called todoItemClicked")
        selectedItem = todoItem
        showFragment(TodoItemDetailFragment.newInstance())
    }
}

data class FragmentNavigationRequest(
    val fragment: Fragment,
    val backStack: Boolean = false,
    val tag: String? = null
)