package com.example.proto2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class sensorDataActivity : AppCompatActivity()
{
    var mMoisEditText: TextView?=null
    var mpHEditText: TextView?=null
    var mYieldEditText: TextView?=null
    var mCropEditText: TextView?= null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor_data)

        mMoisEditText = findViewById(R.id.iMoisEditText)
        mpHEditText = findViewById(R.id.ipHEditText)
        mYieldEditText = findViewById(R.id.iYieldTextView)
        mCropEditText = findViewById(R.id.iCropTextView)
        val moisDB = FirebaseDatabase.getInstance().reference.child("Data")
        moisDB.addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(dataSnapshot: DataSnapshot)
            {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                for(DataSnapshot ds : dataSnapshot.getChildren())
//                val children = dataSnapshot.children
//                children.forEach{
//                val value = it.child("MoistureValue").getValue(String::class.java)
//                val value = it.child("MoistureValue").getValue().toString()}
                val moisValue = dataSnapshot.child("MoistureValue").value.toString()
                mMoisEditText?.text = moisValue
                Log.d("Moisture sensor", "Value is: $moisValue")

                val pHValue = dataSnapshot.child("pHValue").value.toString()
                mpHEditText?.text = pHValue
                Log.d("pH sensor", "Value is: $pHValue")

                val yieldVal = dataSnapshot.child("Yield").value.toString()
                mYieldEditText?.text = yieldVal+"kg/ha"
                Log.d("Final yield ", "Value is: $yieldVal kg/ha")

                val cropName = dataSnapshot.child("Crop").value.toString()
                mCropEditText?.text = cropName
                Log.d("Final yield ", "Value is: $cropName")
//                if(dataSnapshot.exists())
//                {
//                    for(h in dataSnapshot.children)
//                    {
//                        val value = h.child("MoistureValue").getValue().toString()
//                        mMoisEditText?.setText(value)
//                        Log.d("Sensor Value", "Value is: $value")
//                    }
//                }
            }

            override fun onCancelled(error: DatabaseError)
            {
                // Failed to read value
                Log.w("Sensor Value", "Failed to read value.", error.toException())
            }
        })
    }
    fun onBackClicked(view: View)
    {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
    fun onEditClicked(view: View)
    {

    }
}
