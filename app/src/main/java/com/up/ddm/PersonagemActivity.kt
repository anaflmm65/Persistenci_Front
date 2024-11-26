package com.up.ddm


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class PersonagemActivity : AppCompatActivity() {

    private lateinit var personagemDao: PersonagemDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa o personagemDao com a instância do banco de dados
        personagemDao = PersonagemDB.getInstance(applicationContext).personagemDao()

        iniciar()
    }

    private fun iniciar() {
        val raca = Raca.criarPorEscolha("1")
        val classe = Classes.criarPorEscolha("1")
        val habilidades = Habilidades()

        // Define o dadoDeVida com base na classe escolhida
        val dadoDeVida = when (classe.nome) {
            "Guerreiro" -> "d10"
            "Mago" -> "d6"
            "Ladino" -> "d8"
            else -> "d8" // Valor padrão caso a classe não corresponda a nenhuma das opções acima
        }

        // Criação de personagem com ID fixo para testes
        val personagem = PersonagemEntity(
            id = 1, // Defina um ID ou deixe 0 se o banco de dados gerenciar o ID automaticamente
            raca = raca.nome,
            classe = classe.nome,
            dadoDeVida = dadoDeVida,
            pontosDeVida = 10,
            forca = habilidades.forca,
            destreza = habilidades.destreza,
            constituicao = habilidades.constituicao,
            inteligencia = habilidades.inteligencia,
            sabedoria = habilidades.sabedoria,
            carisma = habilidades.carisma
        )

        // Salva o personagem no banco de dados
        salvarPersonagem(personagem)

        // Busca o personagem por ID para exibir informações
        buscarPersonagemPorId(1) // Exemplo de busca pelo ID 1
    }

    private fun exibirInformacoes(personagem: PersonagemEntity) {
        Toast.makeText(this, """
            Raça: ${personagem.raca}
            Classe: ${personagem.classe}
            PV: ${personagem.pontosDeVida}
            Dado de Vida: ${personagem.dadoDeVida}
            Força: ${personagem.forca}
            Destreza: ${personagem.destreza}
            Constituição: ${personagem.constituicao}
            Inteligência: ${personagem.inteligencia}
            Sabedoria: ${personagem.sabedoria}
            Carisma: ${personagem.carisma}
        """.trimIndent(), Toast.LENGTH_LONG).show()
    }

    private fun salvarPersonagem(personagem: PersonagemEntity) {
        lifecycleScope.launch {
            personagemDao.insert(personagem)
            Toast.makeText(this@PersonagemActivity, "Personagem salvo no banco de dados.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun buscarPersonagemPorId(id: Int) {
        lifecycleScope.launch {
            val personagem = personagemDao.buscarPorId(id)
            if (personagem != null) {
                exibirInformacoes(personagem)
            } else {
                Toast.makeText(this@PersonagemActivity, "Personagem não encontrado.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}