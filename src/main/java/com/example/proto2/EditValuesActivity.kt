package com.example.proto2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class EditValuesActivity : AppCompatActivity()
{
    var mMoisEditText: EditText?= null
    var mpHEditText: EditText?= null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_values)
        mMoisEditText = findViewById(R.id.iMoisEditText)
        mpHEditText = findViewById(R.id.ipHEditText)
    }
    fun onBackClicked(view: View)
    {
        val intent = Intent(this,sensorDataActivity::class.java)
        startActivity(intent)
    }
    fun onNewReadings(view: View)
    {
        var moisVal = mMoisEditText?.text.toString()
        var pHVal = mpHEditText?.text.toString()
        val moisRef = FirebaseDatabase.getInstance().getReference("Data/MoistureValue")
//        val moisRef = FirebaseDatabase.getInstance().reference.child("Data/MoistureValue")
        moisRef.setValue(moisVal)
        val pHRef = FirebaseDatabase.getInstance().getReference("Data/pHValue")
        pHRef.setValue(pHVal)
        Toast.makeText(this,"Values updated",Toast.LENGTH_SHORT).show()
        val intent = Intent(this,sensorDataActivity::class.java)
        startActivity(intent)
    }
}
