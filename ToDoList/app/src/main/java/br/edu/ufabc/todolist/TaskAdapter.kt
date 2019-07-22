package br.edu.ufabc.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val checkedTextView: CheckedTextView = v.findViewById(R.id.checked_task_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_task, parent, false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = TaskDao.getTaskAt(position)

        holder.checkedTextView.text = task.title
        holder.checkedTextView.isChecked = task.isDone
        holder.checkedTextView.setOnClickListener { v ->
            val cv = v as CheckedTextView
            if (cv.isChecked) {
                TaskDao.uncheckTaskAt(position)
                cv.isChecked = false
            } else {
                TaskDao.checkTaskAt(position)
                cv.isChecked = true
            }
        }
    }

    override fun getItemCount(): Int {
        return TaskDao.size()
    }

}
