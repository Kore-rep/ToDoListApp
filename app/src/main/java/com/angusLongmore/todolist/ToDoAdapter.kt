package com.angusLongmore.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*


// Create an adapter class with a private list of toDo Items
class ToDoAdapter (
        private val todos: MutableList<ToDo> //Is a parameter

//Its required to inherit from the component's adapter system which uses a generic of type viewholder
// We create our own subclass viewholder ToDoViewHolder
    ) : RecyclerView.Adapter<ToDoAdapter.TodoViewHolder> ()  {

    // ctrl + I brings up a list of functions that need to be defined for the RV adapter
    // There are 3 of them in this case "onCreateViewHolder", "getItemCount" and "onBindViewHolder"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        // This function returns a ViewHolder of the type that we passed to the generic value
        // So we must parse the .xml file of the item we want (Item_todo)
        // This is done using a LayoutInflater and referencing a file in the res>layout folder
        return TodoViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_todo,
                        parent,
                        false
                )
        )
    }

    // Adds a new Todo Item to the list and notifies the view that an item was adde
    fun addTodo(todo: ToDo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deteleDoneTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        // Simply returns number of items in the current todo list
        return todos.size
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        // Gets called when a new list item becomes visible
        // This function binds the data from the todo list to the views
        val curTodo = todos[position] // obtains the current todo item
        holder.itemView.apply {
            tvToDoTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(tvToDoTitle, curTodo.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvToDoTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}