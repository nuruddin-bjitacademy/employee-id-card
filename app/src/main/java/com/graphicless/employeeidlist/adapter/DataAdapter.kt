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
import com.graphicless.employeeidlist.databinding.SingleRowBinding
import com.graphicless.employeeidlist.model.DataModel

private const val TAG = "DataAdapter"
class DataAdapter(private val context: Context, private var dataset:MutableList<DataModel>):
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {


    class DataViewHolder(private var viewBinding: SingleRowBinding): RecyclerView.ViewHolder(viewBinding.root){

        fun bind(employee:DataModel, context: Context){

            val id:String = "ID No: " + employee.id
            val bloodGroup: String = "BLOOD GROUP: " + employee.blood_group
            viewBinding.employeeName.text = employee.name
            viewBinding.employeeId.text = id
            viewBinding.employeeBloodGroup.text = bloodGroup
            Glide.with(context).load(employee.imageUrl).fitCenter().into(viewBinding.employeeImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val adapterLayout =SingleRowBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return DataViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val employee = dataset[position]
        Log.d(TAG, "onBindViewHolder: ${employee.name}")

        holder.bind(employee, context)

    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: called")
        return dataset.size
    }
}