package br.edu.ufabc.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar


class TaskInsert : AppCompatActivity(){
    private lateinit var taskForm: TaskForm

    private inner class TaskForm {
        private val title: EditText = findViewById(R.id.title)

        fun isValid(): Boolean {
            if (getTitle().isEmpty()) {
                title.error = getString(R.string.required_field_error)
                return false
            }
            return true
        }

        fun getTitle(): String {
            return title.text.toString().trim()
        }

        fun toTask(): Task {
            val task = Task()

            task.title = getTitle()
            task.isDone = false

            return task
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_insert)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_action_close)

        taskForm = TaskForm()
    }

    private fun handleInsert() {
        if (taskForm.isValid()) {
            val position = TaskDao.add(taskForm.toTask())
            val resultIntent = Intent()

            resultIntent.putExtra(App.TASK_POSITION_KEY, position)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        } else
            Snackbar.make(
                    findViewById(R.id.layout_task_insert),
                    getString(R.string.task_add_failure),
                    Snackbar.LENGTH_LONG).show()
    }

    fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_task_insert, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_save) {
            handleInsert()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
