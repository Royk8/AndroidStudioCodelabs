package com.roy.colormyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun makeColored(view: View, box_three_text: View, box_four_text: View, box_five_text: View) {
        when (view.id) {

            // Boxes using Color class colors for the background
            R.id.box_one_text -> view.setBackgroundColor(Color.DKGRAY)
            R.id.box_two_text -> view.setBackgroundColor(Color.GRAY)
            R.id.box_three_text -> view.setBackgroundColor(Color.BLUE)
            R.id.box_four_text -> view.setBackgroundColor(Color.MAGENTA)
            R.id.box_five_text -> view.setBackgroundColor(Color.BLUE)

            R.id.yellow_button -> box_three_text.setBackgroundResource(R.color.my_yellow)
            R.id.green_button -> box_four_text.setBackgroundResource(R.color.my_green)
            R.id.red_button -> box_five_text.setBackgroundResource(R.color.my_red)

            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }

    private fun makeImaged(view: View){
        when(view.id){
            R.id.box_one_text -> view.setBackgroundResource(R.drawable.bulbasaur)
            R.id.box_two_text -> view.setBackgroundResource(R.drawable.charmander)
            R.id.box_three_text -> view.setBackgroundResource(R.drawable.pikachu)
            R.id.box_four_text -> view.setBackgroundResource(R.drawable.squitle)
            R.id.box_five_text -> view.setBackgroundResource(R.drawable.eevee)
            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }

    private fun setListeners() {

        val boxOneText = findViewById<TextView>(R.id.box_one_text)
        val boxTwoText = findViewById<TextView>(R.id.box_two_text)
        val boxThreeText = findViewById<TextView>(R.id.box_three_text)
        val boxFourText = findViewById<TextView>(R.id.box_four_text)
        val boxFiveText = findViewById<TextView>(R.id.box_five_text)

        val redButton = findViewById<Button>(R.id.red_button)
        val greenButton = findViewById<Button>(R.id.green_button)
        val yellowButton = findViewById<Button>(R.id.yellow_button)

        val rootConstraintLayout = findViewById<View>(R.id.constraint_layout)

        val clickableViews: List<View> = listOf(boxOneText, boxTwoText, boxThreeText, boxFourText,
            boxFiveText, rootConstraintLayout, redButton, greenButton, yellowButton)

        for (item in clickableViews){
            item.setOnClickListener{makeColored(it,  boxThreeText, boxFourText, boxFiveText)}
        }
    }


}