package com.cha1se.cleanarchitecturesample.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cha1se.cleanacrhitecturesample.data.repository.UserRepositoryImpl
import com.cha1se.cleanacrhitecturesample.data.storage.sharedprefs.SharedPrefUserStorage
import com.cha1se.cleanacrhitecturesample.domain.models.SaveUserNameParam
import com.cha1se.cleanacrhitecturesample.domain.models.UserName
import com.cha1se.cleanacrhitecturesample.domain.usecase.GetUserNameUseCase
import com.cha1se.cleanacrhitecturesample.domain.usecase.SaveUserNameUseCase
import com.cha1se.cleanarchitecturesample.R


class MainActivity : AppCompatActivity() {

    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("TAG", "Activity created")

        vm = ViewModelProvider(this, MainViewModelFactory(this)).get(MainViewModel::class.java)

        val dataTextView = findViewById<TextView>(R.id.dataTextView)
        val dataEditText = findViewById<EditText>(R.id.dataEditTextt)
        val sendButton = findViewById<Button>(R.id.sendButton)
        val receiveButton = findViewById<Button>(R.id.receiveButton)

        vm.resultLive.observe(this, Observer { text ->
            dataTextView.text = text
        })

        sendButton.setOnClickListener {
            val text = dataEditText.text.toString()
            vm.save(text = text)
        }

        receiveButton.setOnClickListener {
            vm.load()
        }

    }
}