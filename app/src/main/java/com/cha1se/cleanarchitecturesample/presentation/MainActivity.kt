package com.cha1se.cleanarchitecturesample.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cha1se.cleanacrhitecturesample.data.repository.UserRepositoryImpl
import com.cha1se.cleanacrhitecturesample.data.storage.sharedprefs.SharedPrefUserStorage
import com.cha1se.cleanacrhitecturesample.domain.models.SaveUserNameParam
import com.cha1se.cleanacrhitecturesample.domain.models.UserName
import com.cha1se.cleanacrhitecturesample.domain.usecase.GetUserNameUseCase
import com.cha1se.cleanacrhitecturesample.domain.usecase.SaveUserNameUseCase
import com.cha1se.cleanarchitecturesample.R


class MainActivity : AppCompatActivity() {

    private val userRepository by lazy(LazyThreadSafetyMode.NONE) {
        UserRepositoryImpl(
            userStorage = SharedPrefUserStorage(context = applicationContext)
        )
    }
    private val getUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetUserNameUseCase(
            userRespository = userRepository
        )
    }
    private val saveUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SaveUserNameUseCase(
            userRepository = userRepository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataTextView = findViewById<TextView>(R.id.dataTextView)
        val dataEditText = findViewById<EditText>(R.id.dataEditTextt)
        val sendButton = findViewById<Button>(R.id.sendButton)
        val receiveButton = findViewById<Button>(R.id.receiveButton)

        sendButton.setOnClickListener {

            val text = dataEditText.text.toString()
            val params = SaveUserNameParam(name = text)
            val result: Boolean = saveUserNameUseCase.execute(param = params)
            dataTextView.text = "Save res = $result"

        }

        receiveButton.setOnClickListener {

            val userName: UserName = getUserNameUseCase.execute()
            dataTextView.text = "${userName.firstName}  ${userName.lastName}"

        }

    }
}