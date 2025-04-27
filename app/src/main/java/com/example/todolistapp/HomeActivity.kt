package com.example.todolistapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var homeTaskListView: ListView
    private lateinit var completeHomeButton: Button
    private lateinit var deleteHomeButton: Button
    private lateinit var backHomeButton: Button
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeTaskListView = findViewById(R.id.homeTaskListView)
        completeHomeButton = findViewById(R.id.completeHomeButton)
        deleteHomeButton = findViewById(R.id.deleteHomeButton)
        backHomeButton = findViewById(R.id.backHomeButton)

        updateAdapter()

        completeHomeButton.setOnClickListener {
            val position = homeTaskListView.checkedItemPosition
            if (position != ListView.INVALID_POSITION) {
                TaskManager.taskList[position].isCompleted = true
                updateAdapter()
                homeTaskListView.clearChoices()
                Toast.makeText(this, "Task marked as completed!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please select a task to complete", Toast.LENGTH_SHORT).show()
            }
        }

        deleteHomeButton.setOnClickListener {
            val position = homeTaskListView.checkedItemPosition
            if (position != ListView.INVALID_POSITION) {
                TaskManager.taskList.removeAt(position)
                updateAdapter()
                homeTaskListView.clearChoices()
                Toast.makeText(this, "Task deleted!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please select a task to delete", Toast.LENGTH_SHORT).show()
            }
        }

        backHomeButton.setOnClickListener {
            finish()
        }
    }

    private fun updateAdapter() {
        adapter = TaskAdapter(this, TaskManager.taskList, selectable = true)
        homeTaskListView.adapter = adapter
        homeTaskListView.choiceMode = ListView.CHOICE_MODE_SINGLE
    }

    override fun onResume() {
        super.onResume()
        updateAdapter()
    }
}
