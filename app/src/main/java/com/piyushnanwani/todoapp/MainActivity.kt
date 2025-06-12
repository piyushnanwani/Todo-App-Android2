package com.piyushnanwani.todoapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var inputTodo: EditText
    private lateinit var addButton: Button
    private lateinit var todoListTV: TextView
    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = TaskViewModel()

        viewModel.tasks.observe(this) {
            tasks -> updateTodoList(tasks)
        }

        inputTodo = findViewById(R.id.inputTodoET)
        addButton = findViewById(R.id.addTaskBtn)
        todoListTV = findViewById(R.id.todoListTV)

        addButton.setOnClickListener {
            val task = inputTodo.text.toString().trim()
            if (task.isNotEmpty()) {
                viewModel.addTask(task)
                inputTodo.text.clear()
            }
            else
            {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show()
            }

        }
    }


    fun updateTodoList(tasks: List<Task>) {
        todoListTV.text = ""
        for (i in tasks.indices) {
            todoListTV.append("${tasks[i].text}\n")
        }

    }
}