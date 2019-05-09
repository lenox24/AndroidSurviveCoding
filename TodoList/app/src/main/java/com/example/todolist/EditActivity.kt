package com.example.todolist

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_edit.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton
import java.util.*

class EditActivity : AppCompatActivity() {

    val realm = Realm.getDefaultInstance()
    val calendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val id = intent.getLongExtra("id", -1)
        if (id == -1L) {
            insertMode()
        } else {
            updateMode(id)
        }

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }
    }

    @SuppressLint("RestrictedApi")
    private fun insertMode() {
        fab_delete_edit.visibility = View.GONE

        fab_done_edit.setOnClickListener {
            insertTodo()
        }
    }

    private fun updateMode(id: Long) {
        val todo = realm.where<Todo>().equalTo("id", id).findFirst()!!
        edt_todo_edit.setText(todo.title)
        calendarView.date = todo.date

        fab_done_edit.setOnClickListener {
            updateTodo(id)
        }

        fab_delete_edit.setOnClickListener {
            deleteTodo(id)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    private fun insertTodo() {
        realm.beginTransaction()

        val newItem = realm.createObject<Todo>(nextId())

        newItem.title = edt_todo_edit.text.toString()
        newItem.date = calendar.timeInMillis

        realm.commitTransaction()

        alert("내용이 추가되었습니다.") {
            yesButton { finish() }
        }.show()
    }

    private fun nextId(): Int {
        val maxId = realm.where<Todo>().max("id")
        if (maxId != null) {
            return maxId.toInt() + 1
        }
        return 0
    }

    private fun updateTodo(id: Long) {
        realm.beginTransaction()

        val updateItem = realm.where<Todo>().equalTo("id", id).findFirst()!!

        updateItem.title = edt_todo_edit.text.toString()
        updateItem.date = calendar.timeInMillis

        realm.commitTransaction()

        alert("내용이 변경되었습니다.") {
            yesButton { finish() }
        }.show()
    }

    private fun deleteTodo(id: Long) {
        realm.beginTransaction()

        val deleteItem = realm.where<Todo>().equalTo("id", id).findFirst()!!

        deleteItem.deleteFromRealm()
        realm.commitTransaction()

        alert("내용이 삭제되었습니다.") {
            yesButton { finish() }
        }.show()
    }
}
