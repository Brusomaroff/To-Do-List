package com.example.todolistapp

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class TaskAdapter(
    context: Context,
    tasks: List<Task>,
    private val selectable: Boolean // NEW flag to control selection
) : ArrayAdapter<Task>(context, 0, tasks) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = if (selectable) {
            android.R.layout.simple_list_item_single_choice
        } else {
            android.R.layout.simple_list_item_1
        }

        val listItemView = convertView ?: LayoutInflater.from(context)
            .inflate(layout, parent, false)

        val task = getItem(position)
        val textView = listItemView!!.findViewById<TextView>(android.R.id.text1)

        if (task != null) {
            textView.text = if (task.isCompleted) {
                "${task.name} (Completed)"
            } else {
                task.name
            }

            if (task.isCompleted) {
                textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                textView.setTextColor(Color.GRAY)
            } else {
                textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                textView.setTextColor(Color.BLACK)
            }
        }

        return listItemView
    }
}
