package com.example.proto2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity()
{
    var mEmailEditText: EditText?=null
    var mPassEditText: EditText?=null
    val mAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        android:background="#aa74a68a"
        mEmailEditText = findViewById(R.id.iEmailEditText)
        mPassEditText = findViewById(R.id.iPasswordEditText)
    }
    fun login()
    {
        val intent = Intent(this,sensorDataActivity::class.java)
        startActivity(intent)
    }
    fun onSignInClicked(view: View)
    {
        mAuth.signInWithEmailAndPassword(mEmailEditText?.text.toString(),mPassEditText?.text.toString()).addOnCompleteListener (this)
        {task ->
            if(task.isSuccessful)
            {
                login()
            }
            else
            {
                Toast.makeText(this,"Login failed. Please try again",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun onSignUpClicked(view: View)
    {
        mAuth.createUserWithEmailAndPassword(mEmailEditText?.text.toString(),mPassEditText?.text.toString()).addOnCompleteListener(this)
        {task ->
            if(task.isSuccessful)
            {
                Toast.makeText(this,"Signing up success. Hope you have a pleasant experience :)",Toast.LENGTH_SHORT).show()
                FirebaseDatabase.getInstance().getReference().child("Users").child(task.result!!.user!!.uid).child("email").setValue(mEmailEditText?.text.toString())
                FirebaseDatabase.getInstance().getReference().child("Users").child(task.result!!.user!!.uid).child("password").setValue(mPassEditText?.text.toString())
                login()
            }
            else
            {
                Toast.makeText(this,"Signing up failed :( Please try again",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
