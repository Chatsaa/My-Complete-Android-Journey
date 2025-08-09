package com.jumbish.hw2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class FootBallerDetailActivity : AppCompatActivity() {
    lateinit var rpdName :TextView
    lateinit var rpdAge : TextView
    lateinit var rpdDiscription : TextView
    lateinit var rpdImageVIew : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_foot_baller_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // old Way

        /*rpdName = findViewById(R.id.rpdName)
        rpdAge = findViewById(R.id.rpdAge)
        rpdDiscription = findViewById(R.id.rpdDiscription)
        rpdImageVIew = findViewById(R.id.rpdImage)

        val name = intent.getStringExtra("name")
        val age = intent.getStringExtra("age")
        val detail = intent.getStringExtra("detail")
        val image = intent.getIntExtra("player_image",0)

    rpdName.text = name
    rpdAge.text = age
    rpdDiscription.text = detail

        Glide.with(this)
            .load(image)
            .into(findViewById(R.id.rpdImage))*/


        // New way @Parcelize se
        val footBaller = intent.getParcelableExtra<FootBallers>("footballer")

        rpdName = findViewById(R.id.rpdName)
        rpdAge = findViewById(R.id.rpdAge)
        rpdDiscription = findViewById(R.id.rpdDiscription)
        rpdImageVIew = findViewById(R.id.rpdImage)
        rpdName.text = footBaller?.name
        rpdAge.text = footBaller?.age.toString()
        rpdDiscription.text = footBaller?.achievement

        Glide.with(this)
            .load(footBaller?.playerImage)
            .into(rpdImageVIew)


    }
}