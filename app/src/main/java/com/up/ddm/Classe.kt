package com.up.ddm

open class Classes(
    val nome: String,
    val proficiencias: List<String>,
    val dadoDeVida: String
) {
    fun exibirInformacoes() {
        println("Classe: $nome")
        println("Proficiências: $proficiencias")
    }

    companion object {
        fun criarPorEscolha(escolha: String): Classes {
            return when (escolha) {
                "1" -> Guerreiro()
                "2" -> Mago()
                "3" -> Ladino()
                else -> Guerreiro()
            }
        }
    }
}

class Guerreiro : Classes(
    nome = "Guerreiro",
    proficiencias = listOf("Armaduras: Leves, Médias, Pesadas", "Armas: Simples, Marciais"),
    dadoDeVida = "d10"
)

class Mago : Classes(
    nome = "Mago",
    proficiencias = listOf("Armaduras: Nenhuma", "Armas: Adagas, Dardos, Bordões, Bestas"),
    dadoDeVida = "d6"
)

class Ladino : Classes(
    nome = "Ladino",
    proficiencias = listOf("Armaduras: Leves", "Armas: Simples, Bestas, Espadas Curtas"),
    dadoDeVida = "d8"
)
