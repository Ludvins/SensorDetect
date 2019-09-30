package com.example.mcabr.detectsensors

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

import java.util.ArrayList

class MainActivity : Activity() {

    private lateinit var listViewSensor: ListView
    private val foundSensor = ArrayList<Sensor>()
    private var aaSensor: ArrayAdapter<Sensor>? = null

    private var sensorManager: SensorManager? = null

    private var myListSensorClickListener: AdapterView.OnItemClickListener = AdapterView.OnItemClickListener { _, _, index, _ ->
        val mySensor = aaSensor!!.getItem(index)

        val infoSensor = mySensor.stringType + " - " + mySensor.name + " - " + mySensor.type

        Toast.makeText(applicationContext, infoSensor, Toast.LENGTH_LONG).show()
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

        aaSensor = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, foundSensor)

        listViewSensor.adapter = aaSensor
        listViewSensor.onItemClickListener = myListSensorClickListener
    }

    private fun getSensorManager() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    private fun getTypeSensor(type: Int): String {

        return when (type) {
            Sensor.TYPE_ACCELEROMETER -> "ACCELEROMETER"
            Sensor.TYPE_GRAVITY -> "GRAVITY"
            Sensor.TYPE_GYROSCOPE -> "GYROSCOPE"
            Sensor.TYPE_LIGHT -> "LIGHT"
            Sensor.TYPE_LINEAR_ACCELERATION -> "LINEAR_ACCELERATION"
            Sensor.TYPE_MAGNETIC_FIELD -> "MAGNETIC_FIELD"
            Sensor.TYPE_PRESSURE -> "PRESSURE"
            Sensor.TYPE_PROXIMITY -> "PROXIMITY"
            Sensor.TYPE_ROTATION_VECTOR -> "ROTATION_VECTOR"
            Sensor.TYPE_AMBIENT_TEMPERATURE -> "TEMPERATURE"
            else -> "OTHER"
        }
    }

    private fun getSensorList() {

        val sensors = sensorManager!!.getSensorList(Sensor.TYPE_ALL)

        for (sensor in sensors) {
            if (!foundSensor.contains(sensor))
                foundSensor.add(sensor)
        }
        aaSensor!!.notifyDataSetChanged()
    }

}

