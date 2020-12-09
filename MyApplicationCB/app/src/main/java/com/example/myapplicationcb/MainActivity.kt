package com.example.myapplicationcb

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.myapplicationcb.model.Reqres
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_view.*
import kotlinx.android.synthetic.main.item_view.view.*
import java.util.*
import kotlin.collections.contains as contains1

class MainActivity : AppCompatActivity() {

    private val datalist: MutableList<Reqres> = mutableListOf()
    private val barcodelist: MutableList<String> = mutableListOf()
    private val expiryDateList: MutableList<String> = mutableListOf()
    private lateinit var adapter:Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

        val search = findViewById<SearchView>(R.id.search)

        adapter = Adapter(datalist)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this,OrientationHelper.VERTICAL))
        recyclerView.adapter = adapter

        AndroidNetworking.initialize(this)

        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(code: String): Boolean {
                search.clearFocus()
                if(barcodelist.contains(code))
                {
                    adapter.notifyDataSetChanged()

                }else{
                    AndroidNetworking.get("https://api.upcdatabase.org/product/${code}?apikey=1823F8ECAA44C04351A3C0FF5D90339F")
                        .build()
                        .getAsObject(Reqres::class.java, object : ParsedRequestListener<Reqres>{
                            override fun onResponse(response: Reqres) {
                                datalist.add(response)
                                barcodelist.add(response.barcode)
                                adapter.notifyDataSetChanged()
                            }

                            override fun onError(anError: ANError?) {
                                Toast.makeText(parent,"Not Found !",Toast.LENGTH_SHORT).show()
                            }

                        })
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.notifyDataSetChanged()
                return false
            }

        })

    }

    fun getSet(view: View){
        val editDate = findViewById<EditText>(R.id.ExDate)
        val viewDate = editDate.text.toString()
        expiryDateList.add(viewDate)
        val barcodeTextView = findViewById<TextView>(R.id.barcode).apply {
            text = barcodelist[barcodelist.size -1]
        }
        val txtDate = findViewById<TextView>(R.id.newDate).apply{
            text = expiryDateList[expiryDateList.size -1]
        }
    }

    fun saveData(view: View){

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        for(i in barcodelist.indices)
        edit.apply{
            putString("STRING_BARCODE_KEY", barcodelist[i])
            putString("STRING_DATE_KEY",expiryDateList[i])
            putInt("INT_KEY",barcodelist.size)
        }.apply()

        Toast.makeText(this,"Data saved",Toast.LENGTH_SHORT).show()

    }

    private fun loadData(){
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedIntData = sharedPreferences.getInt("INT_KEY", 0)
        val savedString = sharedPreferences.getString("STRING_BARCODE_KEY", null)
        val savedDate = sharedPreferences.getString("STRING_DATE_KEY", null)
        /**
         *  barcode.text = savedString
            newDate.text = savedDate
         */

    }

}
