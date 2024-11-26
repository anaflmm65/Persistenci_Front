// Imports ajustados para as atividades
package com.up.ddm
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var pontosDisponiveis = 27
    private var forca = 8
    private var destreza = 8
    private var constituicao = 8
    private var inteligencia = 8
    private var sabedoria = 8
    private var carisma = 8
    private var classe = "Mago"
    private var raca = "Anão"
    private var pontosdeVida = 10
    private var dadoDeVida = ""
    private lateinit var racaEscolhida: Raca
    private lateinit var personagemDao: PersonagemDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        personagemDao = PersonagemDB.getInstance(applicationContext).personagemDao()

        val spinnerRaca: Spinner = findViewById(R.id.spinner_raca)
        val racas = arrayOf("Anão", "Gnomo", "Humano", "Elfo")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, racas)
        spinnerRaca.adapter = adapter

        val racaInfoTextView: TextView = findViewById(R.id.raça_info)
        spinnerRaca.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                racaEscolhida = Raca.criarPorEscolha((position + 1).toString())
                racaInfoTextView.text = formatarInformacoesRaca(racaEscolhida)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        configurarSeekBar(findViewById(R.id.seekbar_forca), "Força")
        configurarSeekBar(findViewById(R.id.seekbar_destreza), "Destreza")
        configurarSeekBar(findViewById(R.id.seekbar_constituicao), "Constituição")
        configurarSeekBar(findViewById(R.id.seekbar_inteligencia), "Inteligência")
        configurarSeekBar(findViewById(R.id.seekbar_sabedoria), "Sabedoria")
        configurarSeekBar(findViewById(R.id.seekbar_carisma), "Carisma")

        findViewById<Button>(R.id.button_confirmar).setOnClickListener { confirmarPersonagem() }
        findViewById<Button>(R.id.button_salvar).setOnClickListener { salvarPersonagem() }
        findViewById<Button>(R.id.button_atualizar).setOnClickListener { atualizarPersonagem() }
        findViewById<Button>(R.id.button_deletar).setOnClickListener { deletarPersonagem() }

        // Define dadoDeVida com base na classe selecionada
        dadoDeVida = when (classe) {
            "Guerreiro" -> "d10"
            "Mago" -> "d6"
            "Ladino" -> "d8"
            else -> "d8"
        }
    }

    private fun confirmarPersonagem() {
        aplicarBonusRacial()
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra("raca", racaEscolhida.nome)
            putExtra("forca", forca)
            putExtra("destreza", destreza)
            putExtra("constituicao", constituicao)
            putExtra("inteligencia", inteligencia)
            putExtra("sabedoria", sabedoria)
            putExtra("carisma", carisma)
        }
        startActivity(intent)
    }

    private fun salvarPersonagem() {
        val personagem = PersonagemEntity(
            raca = racaEscolhida.nome,
            classe = classe,
            dadoDeVida = dadoDeVida,
            pontosDeVida = pontosdeVida,
            forca = forca,
            destreza = destreza,
            constituicao = constituicao,
            inteligencia = inteligencia,
            sabedoria = sabedoria,
            carisma = carisma
        )
        lifecycleScope.launch {
            personagemDao.insert(personagem)
            withContext(Dispatchers.Main) {
                Toast.makeText(this@MainActivity, "Personagem salvo com sucesso!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun atualizarPersonagem() {
        lifecycleScope.launch {
            val personagem = personagemDao.buscarPorId(1) // Exemplo: buscar pelo ID 1
            if (personagem != null) {
                val personagemAtualizado = personagem.copy(
                    forca = forca,
                    destreza = destreza,
                    constituicao = constituicao,
                    inteligencia = inteligencia,
                    sabedoria = sabedoria,
                    carisma = carisma
                )
                personagemDao.update(personagemAtualizado)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Personagem atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun deletarPersonagem() {
        lifecycleScope.launch {
            val personagem = personagemDao.buscarPorId(1) // Exemplo: deletar pelo ID 1
            if (personagem != null) {
                personagemDao.delete(personagem)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Personagem deletado com sucesso!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun formatarInformacoesRaca(raca: Raca): String {
        val bonus = raca.bonus.entries.joinToString("\n") { (atributo, valor) ->
            if (atributo == "Todos") {
                "Todos os atributos: +$valor"
            } else {
                "$atributo: +$valor"
            }
        }
        return "Raça: ${raca.nome}\nBônus:\n$bonus"
    }

    private fun aplicarBonusRacial() {
        racaEscolhida.bonus.forEach { (atributo, valor) ->
            when (atributo) {
                "Força" -> forca += valor
                "Destreza" -> destreza += valor
                "Constituição" -> constituicao += valor
                "Inteligência" -> inteligencia += valor
                "Sabedoria" -> sabedoria += valor
                "Carisma" -> carisma += valor
                "Todos" -> {
                    forca += valor
                    destreza += valor
                    constituicao += valor
                    inteligencia += valor
                    sabedoria += valor
                    carisma += valor
                }
            }
        }
        atualizarPontosDisponiveis()
    }

    private fun configurarSeekBar(seekBar: SeekBar, habilidade: String) {
        seekBar.max = 15 - 8
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val valorAtual = progress + 8
                when (habilidade) {
                    "Força" -> forca = valorAtual
                    "Destreza" -> destreza = valorAtual
                    "Constituição" -> constituicao = valorAtual
                    "Inteligência" -> inteligencia = valorAtual
                    "Sabedoria" -> sabedoria = valorAtual
                    "Carisma" -> carisma = valorAtual
                }
                atualizarPontosDisponiveis()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun atualizarPontosDisponiveis() {
        val totalGasto = (forca - 8) + (destreza - 8) + (constituicao - 8) + (inteligencia - 8) + (sabedoria - 8) + (carisma - 8)
        pontosDisponiveis = 27 - totalGasto
        findViewById<TextView>(R.id.pontos_disponiveis).text = "Pontos disponíveis: $pontosDisponiveis"
    }
}