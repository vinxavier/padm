package br.edu.ufabc.todolist


/**
 * Task POJO
 */
data class Task(
        var title: String = "",
        var isDone: Boolean = false)

/**
 * Singleon Task DAO implemented as an object expression
 */
object TaskDao {
    private val tasks: MutableList<Task> = ArrayList()

    fun size(): Int {
        return tasks.size
    }

    fun add(task: Task): Int {
        tasks.add(task)

        return tasks.size - 1
    }

    fun checkTaskAt(position: Int) {
        tasks[position].isDone = true
    }

    fun uncheckTaskAt(position: Int) {
        tasks[position].isDone = false
    }

    fun getTaskAt(position: Int): Task {
        return tasks[position]
    }
}