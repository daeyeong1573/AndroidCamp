package org.arr.software.androidcamp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.arr.software.androidcamp.NoteRecyclerAdapter
import org.arr.software.androidcamp.R
import org.arr.software.androidcamp.model.Blog
import org.arr.software.androidcamp.viewmodel.MainViewModel
import org.arr.software.androidcamp.viewmodel.factory.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: MainViewModel
    private lateinit var mainrecycler: RecyclerView
    private lateinit var but: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainrecycler = findViewById(R.id.recycler)
        val application = requireNotNull(this).application
        val factory = MainViewModelFactory()
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        but = findViewById(R.id.button)
        but.setOnClickListener {
            addData()
        }
        initialiseAdapter()
    }

    private fun initialiseAdapter() {
        mainrecycler.layoutManager = viewManager
        observeData()
    }

    fun observeData() {
        viewModel.lst.observe(this, Observer {
            Log.i("data", it.toString())
            mainrecycler.adapter = NoteRecyclerAdapter(viewModel, it, this)
        })
    }


    fun addData() {
        var txtplce = findViewById<EditText>(R.id.titletxt)
        var title = txtplce.text.toString()
        if (title.isNullOrBlank()) {
            Toast.makeText(this, "Enter value!", Toast.LENGTH_LONG).show()
        } else {
            var blog = Blog(title)
            viewModel.add(blog)
            txtplce.text.clear()
            mainrecycler.adapter?.notifyDataSetChanged()
        }

    }
}
