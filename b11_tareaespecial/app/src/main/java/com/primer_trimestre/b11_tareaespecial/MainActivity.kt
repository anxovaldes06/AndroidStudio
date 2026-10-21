package com.primer_trimestre.b11_tareaespecial

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById<View?>(R.id.main),
            OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                val systemBars = insets!!.getInsets(WindowInsetsCompat.Type.systemBars())
                v!!.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            })
        val et1 = findViewById<EditText>(R.id.editTextNumber)
        val et2 = findViewById<EditText>(R.id.editTextNumber2)

        findViewById<View?>(R.id.button).setOnClickListener(View.OnClickListener { v: View? ->
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.putExtra("num1", et1.getText().toString())
            intent.putExtra("num2", et2.getText().toString())
            startActivity(intent)
        })

        val resultado = findViewById<TextView>(R.id.resultado)

        val intent = getIntent()

        if (intent.hasExtra("resultado")) {
            resultado.setText(intent.getStringExtra("resultado"))
        }
    }
}