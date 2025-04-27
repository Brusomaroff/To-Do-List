package com.example.todolistapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StatsActivity : AppCompatActivity() {

    private lateinit var totalTasksTextView: TextView
    private lateinit var completedTasksTextView: TextView
    private lateinit var pendingTasksTextView: TextView
    private lateinit var backStatsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        totalTasksTextView = findViewById(R.id.totalTasksTextView)
        completedTasksTextView = findViewById(R.id.completedTasksTextView)
        pendingTasksTextView = findViewById(R.id.pendingTasksTextView)
        backStatsButton = findViewById(R.id.backStatsButton)

        backStatsButton.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        updateStats()
    }

    private fun updateStats() {
        val total = TaskManager.taskList.size
        val completed = TaskManager.taskList.count { it.isCompleted }
        val pending = total - completed

        totalTasksTextView.text = "Total Tasks: $total"
        completedTasksTextView.text = "Completed Tasks: $completed"
        pendingTasksTextView.text = "Pending Tasks: $pending"
    }
}
