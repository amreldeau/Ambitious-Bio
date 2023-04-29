package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Create an instance of Data class and set the name
    private val myName: MyName = MyName("Yeldos Amire")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the app to always be displayed in daylight mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Instruction to create the binding object with all the magic that connects the layout with the activity (the line below replaces setContentView method)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Set the value of myName variable that is declared and used in a layout file
        binding.myName = myName

        // Access the done button through to binding object
        binding.doneButton.setOnClickListener {
            addNickname(it)
        }
    }

    private fun addNickname(view: View) {

        binding.apply {
            myName?.nickname = nicknameEdit.text.toString()

            // In order to refresh the UI with the new data, we need to invalidate all binding expressions so that they get recreated with the correct data
            invalidateAll()

            //Make EditText and Button disappear, TextView appear
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

        //Hide the keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)
    }
}