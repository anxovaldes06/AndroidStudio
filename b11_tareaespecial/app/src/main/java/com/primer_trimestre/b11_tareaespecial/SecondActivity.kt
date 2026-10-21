package com.primer_trimestre.b11_tareaespecial

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById<View?>(R.id.main),
            OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                val systemBars = insets!!.getInsets(WindowInsetsCompat.Type.systemBars())
                v!!.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            })
        val tv1 = findViewById<TextView>(R.id.textView3)
        val tv2 = findViewById<TextView>(R.id.textView4)

        val num1 = getIntent().getStringExtra("num1")
        val num2 = getIntent().getStringExtra("num2")

        tv1.setText(num1)
        tv2.setText(num2)

        val n1 = num1!!.toDouble() * 4.55
        val n2 = num2!!.toDouble() * 0.59

        findViewById<View?>(R.id.button2).setOnClickListener(View.OnClickListener { v: View? ->
            tv1.setText(n1.toString())
            tv2.setText(n2.toString())
        })
        findViewById<View?>(R.id.button).setOnClickListener(View.OnClickListener { v: View? ->
            val suma = n1 + n2
            val intent = Intent(this@SecondActivity, MainActivity::class.java)

            intent.putExtra("resultado", suma.toString())
            startActivity(intent)
        })
    }
}