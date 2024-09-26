package com.example.constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())
        val rvTodoItems : RecyclerView = findViewById(R.id.rvTodoItems)
        val btnAddTodo : Button = findViewById(R.id.btnAddTodo)
        val btnRemoveTodo : Button = findViewById(R.id.btnRemoveTodo)
        val etTodoTitle : EditText = findViewById(R.id.etTodoTitle)

        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)
        btnAddTodo.setOnClickListener{
            val todoTitle = etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }
        btnRemoveTodo.setOnClickListener{
            todoAdapter.deleteDoneTodos()
        }




    }


}