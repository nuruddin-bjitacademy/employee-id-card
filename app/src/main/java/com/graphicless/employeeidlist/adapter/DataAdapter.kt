package com.graphicless.employeeidlist.adapter
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.graphicless.employeeidlist.R
import com.graphicless.employeeidlist.model.DataModel

private const val TAG = "DataAdapter"
class DataAdapter(private val context: Context, private var dataset:MutableList<DataModel>):
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {


    class DataViewHolder(private val view: View): RecyclerView.ViewHolder(view){
//        val employeeNameTV: TextView = view.findViewById(R.id.employee_name)
//        val employeeIdTV: TextView = view.findViewById(R.id.employee_id)
//        val employeeBloodGroupTV: TextView = view.findViewById(R.id.employee_bloodGroup)
//        val employeeImageIV: ImageView = view.findViewById(R.id.employee_image)

        fun bind(employee:DataModel, context: Context){
            val employeeNameTV: TextView = view.findViewById(R.id.employee_name)
            val employeeIdTV: TextView = view.findViewById(R.id.employee_id)
            val employeeBloodGroupTV: TextView = view.findViewById(R.id.employee_bloodGroup)
            val employeeImageIV: ImageView = view.findViewById(R.id.employee_image)

            val id:String = "ID No: " + employee.id
            val bloodGroup: String = "BLOOD GROUP: " + employee.blood_group
            employeeNameTV.text = employee.name
            employeeIdTV.text = id
            employeeBloodGroupTV.text = bloodGroup
            Glide.with(context).load(employee.imageUrl).fitCenter().into(employeeImageIV)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.single_row, parent, false)
        return DataViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val employee = dataset[position]
        Log.d(TAG, "onBindViewHolder: ${employee.name}")

        holder.bind(employee, context)
//        holder.employeeNameTV.text = employee.name
//        holder.employeeIdTV.text = employee.id
//        holder.employeeBloodGroupTV.text = employee.blood_group
//        Glide.with(context).load(employee.imageUrl).fitCenter().into(holder.employeeImageIV)
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: called")
        return dataset.size
    }
}