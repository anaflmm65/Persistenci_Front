package com.up.ddm

class RacaActivity {
    fun iniciar() {
        println("Bem-vindo ao sistema de seleção de raças!")

        // Solicitar a escolha da raça
        println("Escolha uma raça: ")
        println("1 - Anão (+2 Constituição)")
        println("2 - Gnomo (+2 Inteligência)")
        println("3 - Humano (+1 em todas as habilidades)")
        println("4 - Elfo (+2 Destreza)")

        val escolhaRaca = readLine() ?: "1"
        val raca = Raca.criarPorEscolha(escolhaRaca)

        // Exibe as informações da raça escolhida
        println("\nInformações da Raça:")
        raca.exibirInformacoes()

        println("Obrigado por usar o sistema de seleção de raças!")
    }
}

// Classe Raca com bônus raciais
class Raca(val nome: String, val bonus: Map<String, Int>) {

    fun exibirInformacoes() {
        println("Raça: $nome")
        println("Bônus: $bonus")
    }

    companion object {
        fun criarPorEscolha(escolha: String): Raca {
            return when (escolha) {
                "1" -> Raca("Anão", mapOf("Constituição" to 2))
                "2" -> Raca("Gnomo", mapOf("Inteligência" to 2))
                "3" -> Raca("Humano", mapOf("Força" to 1, "Destreza" to 1, "Constituição" to 1, "Inteligência" to 1, "Sabedoria" to 1, "Carisma" to 1))
                "4" -> Raca("Elfo", mapOf("Destreza" to 2))
                else -> Raca("Anão", mapOf("Constituição" to 2))
            }
        }
    }
}
