package com.taewoo.mysampleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdapter(val List : MutableList<Model>) : BaseAdapter()   {

    override fun getCount(): Int {
        return List.count()
    }

    override fun getItem(p0: Int): Any {
        return List[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView

        if (view == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.listview_item, parent, false)
        }

        val title = view?.findViewById<TextView>(R.id.itemTextid)
        title!!.text = List[position].title
        return view!!

    }
}