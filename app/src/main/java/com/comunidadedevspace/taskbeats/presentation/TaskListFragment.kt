package com.comunidadedevspace.taskbeats.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.comunidadedevspace.taskbeats.R
import com.comunidadedevspace.taskbeats.data.Task


/**
 * A simple [Fragment] subclass.
 * Use the [TaskListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskListFragment : Fragment() {

    private lateinit var ctnContent: LinearLayout

    //Adapter
    private val adapter: TaskListAdapter by lazy {
        TaskListAdapter(::openTaskListDetail)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }



    private fun openTaskListDetail(task: Task?) {
        val intent = TaskDetailActivity.start(requireContext(), task)
        requireActivity().startActivity(intent)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @return A new instance of fragment TaskListFragment.
         */
        @JvmStatic
        fun newInstance() =
            TaskListFragment()
    }
}