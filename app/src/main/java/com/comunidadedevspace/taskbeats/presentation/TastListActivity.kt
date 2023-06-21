package com.comunidadedevspace.taskbeats.presentation

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.comunidadedevspace.taskbeats.R
import com.comunidadedevspace.taskbeats.data.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private lateinit var ctnContent: LinearLayout

    //Adapter
    private val adapter: TaskListAdapter by lazy {
        TaskListAdapter(::OnListItemClicked)
    }
    private val viewModel: TaskListViewModel by lazy {
        TaskListViewModel.create(application)
    }

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->

        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val taskAction = data?.getSerializableExtra(TASK_ACTION_RESULT) as TaskAction

            viewModel.execute(taskAction)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
        setSupportActionBar(findViewById(R.id.toolbar))
    /*    val dataBase = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "taskbeats-database"
        ).build()

        val dao = dataBase.taskDao()

        CoroutineScope(IO).launch {
            val myDataBaseList: List<Task> = dao.getAll()
            adapter.submitList(myDataBaseList)
        }*/   //comentei essa parte, pq ao rodar, deu erro, mas olhando o codigo do Roque, essa parte nao existe, mas vou deixar aqui pra ver

        ctnContent = findViewById(R.id.ctn_content)

        //RecyclerView
        val rvTasks: RecyclerView = findViewById(R.id.rv_task_list)
        rvTasks.adapter = adapter

        val fab = findViewById<FloatingActionButton>(R.id.fab_add)
        fab.setOnClickListener {
            openTaskListDetail(null)
        }

    }

    override fun onStart() {
        super.onStart()
        listFromDataBase()
    }

    private fun deleteAll(){
        val taskAction = TaskAction(null, ActionType.DELETE_ALL.name)
        viewModel.execute(taskAction)
    }

    private fun listFromDataBase(){
                    //Observer
            val listObserver = Observer<List<Task>> { listTasks ->
                if (listTasks.isEmpty()){
                    ctnContent.visibility = View.VISIBLE
                }else{
                    ctnContent.visibility = View.GONE
                }
                adapter.submitList(listTasks)
            }

            //LiveData
            // Na aula, o @ dele é o "TaskListActivity", igual o da linha abaixo que foi apagada na aula.
            viewModel.taskListLiveData.observe(this@MainActivity, listObserver)
            //Na aula, quando ele insere o "this" o @ dele é a "TaskListActivity", mas no meu, é o "MainActivity" , bora ver se vai dar erro.
            //dao.getAll().observe(this@MainActivity, listObserver) - Na aula, ele apaga essa linha, mas vou deixar
        //Erro permancece, qualquer coisa, voltar na parte 10:17
    }

    private fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show()
    }

    private fun OnListItemClicked(task: Task) {
        openTaskListDetail(task)
    }

    private fun openTaskListDetail(task: Task?) {
        val intent = TaskDetailActivity.start(this, task)
        startForResult.launch(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_task_list, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all_task -> {
                deleteAll()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

}

enum class ActionType {
    DELETE,
    DELETE_ALL,
    UPDATE,
    CREATE
}

data class TaskAction(
    val task: Task?,
    val actionType: String
) : Serializable

const val TASK_ACTION_RESULT = "TASK_ACTION_RESULT"


