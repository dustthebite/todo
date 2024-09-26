package com.example.constraintlayout

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Find the views from item_todo.xml
        val tvTodoTitle: TextView = itemView.findViewById(R.id.tvTodoTitle)
        val cbDone: CheckBox = itemView.findViewById(R.id.cbDone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size -1)
    }

    fun deleteDoneTodos()
    {
        todos.removeAll{todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }


    private fun toggleStrikeThrough(tvTodoTittle: TextView, isChecked: Boolean)
    {
        if(isChecked){
            tvTodoTittle.paintFlags = tvTodoTittle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else {
            tvTodoTittle.paintFlags = tvTodoTittle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.tvTodoTitle.text = curTodo.title
        holder.cbDone.isChecked = curTodo.isChecked
        toggleStrikeThrough(holder.tvTodoTitle, curTodo.isChecked)
        holder.cbDone.setOnCheckedChangeListener{_, isChecked ->
            toggleStrikeThrough(holder.tvTodoTitle, curTodo.isChecked)
            curTodo.isChecked = !curTodo.isChecked
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}
