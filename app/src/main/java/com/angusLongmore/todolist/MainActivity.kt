package com.angusLongmore.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: ToDoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = ToDoAdapter(mutableListOf())

        rvToDoItems.adapter = todoAdapter
        rvToDoItems.layoutManager = LinearLayoutManager(this)



        btnAddToDo.setOnClickListener {
            // Defines actions to be taken when Clicking the "Add ToDo" button
            val todoTitle = etToDoTitle.text.toString()
            if (todoTitle.isNotEmpty()) { // If the text in the box isnt empty
                // Create a new ToDo item and add it to the list.
                val todo = ToDo(todoTitle)
                todoAdapter.addTodo(todo)
                etToDoTitle.text.clear() // Then clea the text box
            }
        }

        btnDeleteDone.setOnClickListener {
            todoAdapter.deteleDoneTodos()
        }
    }
}