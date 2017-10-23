package com.example.mcabr.detectsensors;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

        ListView listViewSensor;
        private ArrayList<Sensor> foundSensor = new ArrayList<Sensor>();
        private ArrayAdapter<Sensor> aaSensor;

        private SensorManager sensorManager;

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            initUI();

            getSensorManager();

            getSensorList();
        }

    public void initUI(){
        listViewSensor = (ListView) findViewById(R.id.listViewSensor);

        aaSensor = new ArrayAdapter<Sensor>(this,
                android.R.layout.simple_list_item_1, foundSensor);

        listViewSensor.setAdapter(aaSensor);
        listViewSensor.setOnItemClickListener(myListSensorClickListener);
    }

    public void getSensorManager(){
        // TODO obtener en la variable sensorManager una instancia del servicio SENSOR_SERVICE
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }

    public String getTypeSensor(int type){
        String cadType;

        // TODO tipos de sensores de entero a cadena de texto
        // EJ. Sensor.TYPE_ACCELEROMETER --> cadType = "ACCELEROMETER";

        switch (type) {
            case (Sensor.TYPE_ACCELEROMETER) :
                cadType = "ACCELEROMETER";
                break;
            case (Sensor.TYPE_GRAVITY) :
                cadType = "GRAVITY";
                break;
            case (Sensor.TYPE_GYROSCOPE) :
                cadType = "GRAVITY";
                break;
            case (Sensor.TYPE_LIGHT) :
                cadType = "LIGHT";
                break;
            case (Sensor.TYPE_LINEAR_ACCELERATION) :
                cadType = "LINEAR_ACCELERATION";
                break;
            case (Sensor.TYPE_MAGNETIC_FIELD) :
                cadType = "MAGNETIC_FIELD";
                break;
            case (Sensor.TYPE_ORIENTATION) : // Deprecated
                cadType = "ORIENTATION";
                break;
            case (Sensor.TYPE_PRESSURE) :
                cadType = "PRESSURE";
                break;
            case (Sensor.TYPE_PROXIMITY) :
                cadType = "PROXIMITY";
                break;
            case (Sensor.TYPE_ROTATION_VECTOR) :
                cadType = "ROTATION_VECTOR";
                break;
            case (Sensor.TYPE_AMBIENT_TEMPERATURE) :
                cadType = "TEMPERATURE";
                break;
            default:
                cadType = "OTHER";
                break;
        }
        return cadType;
    }

    public void getSensorList(){
        // TODO Obtener la lista de todos los sensores
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor sensor : sensors) {
            if (!foundSensor.contains(sensor))
                foundSensor.add(sensor);
        }
        aaSensor.notifyDataSetChanged();
    }

    AdapterView.OnItemClickListener myListSensorClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> arg0, View view, int index,
                                long arg3) {

            // TODO sensor que ha sido seleccionado en la lista
            Sensor mySensor = aaSensor.getItem(index);

            // TODO informacion sobre el sensor seleccionado
            // Por ejemplo, su tipo y su nombre
            // Podeis usar la funcion getTypeSensor para obtener el tipo del sensor en String
            String infoSensor = getTypeSensor(mySensor.getType()) + " - " + mySensor.getName();

            // TODO Imprimir en pantalla
            Toast.makeText(getApplicationContext(), infoSensor, Toast.LENGTH_LONG).show();
        }
    };

}

