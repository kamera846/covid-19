package com.example.covid19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Glide.with(this@MainActivity)
            .load("https://i.pinimg.com/564x/08/78/53/0878535c72c7b2f6a7cc9b139cd673dd.jpg")
            .placeholder(R.color.colorPrimaryDark)
            .into(img_view)
    }
}
