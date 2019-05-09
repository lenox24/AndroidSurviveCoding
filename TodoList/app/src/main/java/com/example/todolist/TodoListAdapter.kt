package com.example.todolist

import android.view.View
import android.view.ViewGroup
import io.realm.OrderedRealmCollection
import io.realm.RealmBaseAdapter

class TodoListAdapter(realmResult: OrderedRealmCollection<Todo>) : RealmBaseAdapter<Todo>(realmResult) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}