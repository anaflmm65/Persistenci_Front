package com.up.ddm

import android.content.Intent
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.up.ddm.R.*
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Obtendo os dados da Intent
        val raca = intent.getStringExtra("raca")
        val forca = intent.getIntExtra("forca", 8)
        val destreza = intent.getIntExtra("destreza", 8)
        val constituicao = intent.getIntExtra("constituicao", 8)
        val inteligencia = intent.getIntExtra("inteligencia", 8)
        val sabedoria = intent.getIntExtra("sabedoria", 8)
        val carisma = intent.getIntExtra("carisma", 8)

        // Exibindo os dados no layout
        findViewById<TextView>(R.id.textViewRaca).text = "Raça: $raca"
        findViewById<TextView>(R.id.textViewForca).text = "Força: $forca"
        findViewById<TextView>(R.id.textViewDestreza).text = "Destreza: $destreza"
        findViewById<TextView>(R.id.textViewConstituicao).text = "Constituição: $constituicao"
        findViewById<TextView>(R.id.textViewInteligencia).text = "Inteligência: $inteligencia"
        findViewById<TextView>(R.id.textViewSabedoria).text = "Sabedoria: $sabedoria"
        findViewById<TextView>(R.id.textViewCarisma).text = "Carisma: $carisma"
    }
}
