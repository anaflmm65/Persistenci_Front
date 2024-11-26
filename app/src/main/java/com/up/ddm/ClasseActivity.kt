package com.up.ddm

class ClasseActivity {
    fun iniciar() {
        println("Escolha uma classe:")
        println("1: Guerreiro")
        println("2: Mago")
        println("3: Ladino")

        val escolha = readLine()
        val classeEscolhida = Classes.criarPorEscolha(escolha ?: "1")

        println("\nVocê escolheu a classe:")
        classeEscolhida.exibirInformacoes()
        println("Dado de Vida: ${classeEscolhida.dadoDeVida}")
    }
}

fun main() {
    val activity = ClasseActivity()
    activity.iniciar()
}
