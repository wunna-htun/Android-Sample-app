package com.example.sampleapp

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        var language :Array<String> =resources.getStringArray(R.array.Technology);

        val arrayAdapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,language)

        listView.adapter=arrayAdapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { adpaterView, view, position, id ->
            var selectedItem=adpaterView.getItemAtPosition(position) as String
            var itemPostion=adpaterView.getItemAtPosition(position)

            Toast.makeText(applicationContext,"click item $selectedItem its position $itemPostion",Toast.LENGTH_LONG).show()
        }
    }
}
