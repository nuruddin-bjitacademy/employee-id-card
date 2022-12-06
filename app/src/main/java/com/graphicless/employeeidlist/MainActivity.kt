package com.graphicless.employeeidlist

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.graphicless.employeeidlist.adapter.DataAdapter
import com.graphicless.employeeidlist.model.DataModel
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream


private const val TAG = "MainActivity"
val employees  = mutableListOf<DataModel>()
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LoadJSON()

        val dataset = employees

        val recyclerView = findViewById<RecyclerView>(R.id.rv_employee_id_card)
        recyclerView.adapter = DataAdapter(this, dataset)
        recyclerView.hasFixedSize()
    }

    fun LoadJSON(){
        Log.d(TAG, "LoadJSON: called")
        var data:String ? = null

        try{
            val inputStream: InputStream = assets.open("employee.json")
            data = inputStream.bufferedReader().use { it.readText() }


            val jsonObject:JSONObject = JSONObject(data)
            val users: JSONArray = jsonObject.getJSONArray("users")

            for (i in 0 until users.length()) {
                val user = users.getJSONObject(i)
                val employeeName = user.getString("name")
                val employeeId = user.getString("id")
                val employeeBloodGroup = user.getString("blood_group")
                val employeeImageUrl = user.getString("image")
                val employeeInfo = DataModel(employeeName, employeeId, employeeBloodGroup, employeeImageUrl)

                employees.add(employeeInfo)
            }

            Log.d(TAG, "LoadJSON: ${employees.size}")
        }
        catch (e: IOException)
        {
            Log.e(TAG, "LoadJSON: $e" )
        }
    }
}