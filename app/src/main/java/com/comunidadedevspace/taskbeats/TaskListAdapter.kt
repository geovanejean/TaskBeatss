package com.comunidadedevspace.taskbeats

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TaskListAdapter(
    private val openTaskDetailView:(task: Task) -> Unit
):  ListAdapter<Task, TaskListViewHolder>(TaskListAdapter) {


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_task, parent, false )
           return TaskListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        val task = getItem(position)                                        //      0       1       2
        holder.bind(task, openTaskDetailView)     //esse aqui é so lembrar do Array [title1, title2, title3]
    }

    companion object : DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
         return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
           return oldItem.title == newItem.title &&
                   oldItem.description == newItem.description
        }

    }
}
class TaskListViewHolder(
    private val view:View
) : RecyclerView.ViewHolder(view){

    val tvTaskTitle = view.findViewById<TextView>(R.id.tv_task_title)
    val tvTaskDescription  = view.findViewById<TextView>(R.id.tv_task_description)

    fun bind(
        task: Task,
        openTaskDetailView:(task: Task) -> Unit

    ) {
        tvTaskTitle.text = task.title
        tvTaskDescription.text = "${task.id}- ${task.description}"

        view.setOnClickListener{
            openTaskDetailView.invoke(task)
        }





    }

}