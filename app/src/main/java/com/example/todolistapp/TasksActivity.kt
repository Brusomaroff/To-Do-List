package com.example.todolistapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class TasksActivity : AppCompatActivity() {

    private lateinit var taskEditText: EditText
    private lateinit var addButton: Button
    private lateinit var clearButton: Button
    private lateinit var backButton: Button
    private lateinit var taskListView: ListView
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        taskEditText = findViewById(R.id.taskEditText)
        addButton = findViewById(R.id.addButton)
        clearButton = findViewById(R.id.clearButton)
        backButton = findViewById(R.id.backButton)
        taskListView = findViewById(R.id.taskListView)

        updateAdapter()

        addButton.setOnClickListener {
            val taskName = taskEditText.text.toString()
            if (taskName.isNotEmpty()) {
                TaskManager.taskList.add(Task(taskName))
                updateAdapter()
                taskEditText.text.clear()
            } else {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show()
            }
        }

        clearButton.setOnClickListener {
            taskEditText.text.clear()
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun updateAdapter() {
        adapter = TaskAdapter(this, TaskManager.taskList, selectable = false)
        taskListView.adapter = adapter
        taskListView.choiceMode = ListView.CHOICE_MODE_NONE
    }
}
