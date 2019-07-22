package br.edu.ufabc.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var taskList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        title = getString(R.string.activity_main_title)

        taskList = findViewById(R.id.task_list)
        taskList.setHasFixedSize(true)
        taskList.layoutManager = LinearLayoutManager(this)
        taskList.addItemDecoration(DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL))
        taskList.adapter = TaskAdapter()

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            startActivityForResult(
                    Intent(this, TaskInsert::class.java),
                    App.INSERT_TASK_REQCODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == App.INSERT_TASK_REQCODE && resultCode == Activity.RESULT_OK) {
            val position: Int = data?.getIntExtra(App.TASK_POSITION_KEY, 0) ?: 0

            taskList.adapter?.notifyItemInserted(position)
            Snackbar.make(findViewById(R.id.layout_main),
                    getString(R.string.task_add_success), Snackbar.LENGTH_LONG).show()

        }
    }

}
