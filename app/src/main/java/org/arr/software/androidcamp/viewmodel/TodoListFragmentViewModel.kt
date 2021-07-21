package org.arr.software.androidcamp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.realm.OrderedRealmCollection
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where
import org.arr.software.androidcamp.model.TodoItem

class TodoListFragmentViewModel : ViewModel() {
    private lateinit var realm: Realm
    private lateinit var todoItems: MutableList<TodoItem>
    lateinit var title: MutableLiveData<String>
    lateinit var data: OrderedRealmCollection<TodoItem>

    init {
        updateUI()
    }

    fun updateUI() {
        realm = Realm.getDefaultInstance()
        todoItems = realm.where<TodoItem>().findAll()
        data = (todoItems as RealmResults<TodoItem>?)!!
    }

    fun isDoneStateChange(id: Long) {
        realm.executeTransaction { db: Realm ->
            val todoItem = db.where<TodoItem>()
                .equalTo("id", id).findFirst()
            todoItem?.isDone = !todoItem?.isDone!!
        }
    }

    fun deleteTask(todoItem: TodoItem) {
        realm.executeTransaction { db: Realm ->
            val todoItem = db.where<TodoItem>()
                .equalTo("id", todoItem.id)
                .findFirst()
                ?.deleteFromRealm()
        }
    }

    override fun onCleared() {
        super.onCleared()
        realm.close()
    }
}