package com.example.mcabr.detectsensors

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : Activity() {

    private lateinit var listViewSensor: ListView
    private val sensorList = mutableListOf<Sensor>()
    private var aaSensor: SensorAdapter? = null

    private var sensorManager: SensorManager? = null

    private var myListSensorClickListener: AdapterView.OnItemClickListener = AdapterView.OnItemClickListener { _, _, index, _ ->
        val mySensor = aaSensor!!.getItem(index)

        val dialogBuilder = AlertDialog.Builder(this)
        val dialogView = View.inflate(this, R.layout.sensor_layout,null)
        dialogBuilder.setView(dialogView)

        val name = dialogView.findViewById<TextView>(R.id.name)
        val vendor = dialogView.findViewById<TextView>(R.id.vendor)
        val version = dialogView.findViewById<TextView>(R.id.version)
        val type = dialogView.findViewById<TextView>(R.id.type)
        val maxRange = dialogView.findViewById<TextView>(R.id.maxRange)
        val resolution = dialogView.findViewById<TextView>(R.id.resolution)
        val power = dialogView.findViewById<TextView>(R.id.power)
        val minDelay = dialogView.findViewById<TextView>(R.id.minDelay)

        name.text = mySensor.name
        vendor.text = mySensor.vendor
        version.text = mySensor.version.toString()
        type.text = mySensor.stringType
        maxRange.text = mySensor.maximumRange.toString()
        resolution.text = mySensor.resolution.toString()
        power.text = mySensor.power.toString()
        minDelay.text = mySensor.minDelay.toString()

        dialogBuilder.create().show()
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        getSensorManager()
        getSensorList()
    }

    private fun initUI() {
        listViewSensor = findViewById<View>(R.id.listViewSensor) as ListView

        aaSensor = SensorAdapter(this,
                 sensorList)

        listViewSensor.adapter = aaSensor
        listViewSensor.onItemClickListener = myListSensorClickListener
    }

    private fun getSensorManager() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    private fun getSensorList() {

        val sensors = sensorManager!!.getSensorList(Sensor.TYPE_ALL)

        for (sensor in sensors) {
            if (!sensorList.contains(sensor))
                sensorList.add(sensor)
        }
        aaSensor!!.notifyDataSetChanged()
    }

}

