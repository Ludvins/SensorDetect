package com.example.mcabr.detectsensors

import android.content.Context
import android.hardware.Sensor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


class SensorAdapter(context: Context, private val dataSource: MutableList<Sensor>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Sensor {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val rowView = inflater.inflate(R.layout.list_item_sensor, parent, false)
        val titleTextView = rowView.findViewById(R.id.type) as TextView
        val sensor = getItem(position)

        titleTextView.text = sensor.stringType.replace("_|[.]".toRegex(), " ").capitalize()

        return rowView
    }
}