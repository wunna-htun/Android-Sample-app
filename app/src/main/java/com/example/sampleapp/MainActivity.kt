package com.example.sampleapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_camera.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        button1.setOnClickListener(){
            var listIntent = Intent(this,ListViewActivity::class.java)
            startActivity(listIntent)
        }

        button2.setOnClickListener(){
            var camearIntent= Intent(this,CameraActivity::class.java)
            startActivity(camearIntent)
        }

        button3.setOnClickListener(){



        }

        button4.setOnClickListener(){
//            var LocationIntent=Intent(this,LocationActivity::class.java)
//            startActivity(LocationIntent)
        }

        checkNetwork()


    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu, menu)
//        return true
//    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.action_settings -> {
//                Toast.makeText(applicationContext, "click on setting", Toast.LENGTH_LONG).show()
//                true
//            }
//            R.id.action_share ->{
//                Toast.makeText(applicationContext, "click on share", Toast.LENGTH_LONG).show()
//                return true
//            }
//            R.id.action_exit ->{
//                Toast.makeText(applicationContext, "click on exit", Toast.LENGTH_LONG).show()
//                return true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    fun checkNetwork(){
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        Log.d("network ","$isConnected")
    }


}
