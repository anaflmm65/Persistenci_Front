package com.up.ddm

import kotlin.math.floor

class Habilidades {
    var forca: Int = 8
    var destreza: Int = 8
    var constituicao: Int = 8
    var inteligencia: Int = 8
    var sabedoria: Int = 8
    var carisma: Int = 8

    var modificadorForca: Int = 0
    var modificadorDestreza: Int = 0
    var modificadorConstituicao: Int = 0
    var modificadorInteligencia: Int = 0
    var modificadorSabedoria: Int = 0
    var modificadorCarisma: Int = 0

    init {
        alocarHabilidades()
        calcularModificadores()
    }

    fun alocarHabilidades() {
        val valores = listOf(15, 14, 13, 12, 10, 8).shuffled()
        val habilidadesMap = mutableMapOf(
            1 to "Força",
            2 to "Destreza",
            3 to "Constituição",
            4 to "Inteligência",
            5 to "Sabedoria",
            6 to "Carisma"
        )

        println("Você tem os seguintes valores disponíveis: $valores")
        println("Escolha uma habilidade para cada valor:")

        for (valor in valores) {
            var escolhaValida = false

            while (!escolhaValida) {
                println("\nEscolha uma habilidade para o valor $valor:")
                habilidadesMap.forEach { (numero, habilidade) ->
                    println("$numero. $habilidade")
                }

                val escolha = readLine()?.toIntOrNull()

                if (escolha != null && habilidadesMap.containsKey(escolha)) {
                    val habilidadeEscolhida = habilidadesMap[escolha]
                    when (habilidadeEscolhida) {
                        "Força" -> {
                            forca += valor
                            println("Você atribuiu $valor para Força. (Novo valor: $forca)")
                        }
                        "Destreza" -> {
                            destreza += valor
                            println("Você atribuiu $valor para Destreza. (Novo valor: $destreza)")
                        }
                        "Constituição" -> {
                            constituicao += valor
                            println("Você atribuiu $valor para Constituição. (Novo valor: $constituicao)")
                        }
                        "Inteligência" -> {
                            inteligencia += valor
                            println("Você atribuiu $valor para Inteligência. (Novo valor: $inteligencia)")
                        }
                        "Sabedoria" -> {
                            sabedoria += valor
                            println("Você atribuiu $valor para Sabedoria. (Novo valor: $sabedoria)")
                        }
                        "Carisma" -> {
                            carisma += valor
                            println("Você atribuiu $valor para Carisma. (Novo valor: $carisma)")
                        }
                    }
                    habilidadesMap.remove(escolha)
                    escolhaValida = true
                } else {
                    println("Escolha inválida. Tente novamente.")
                }
            }
        }

        calcularModificadores()
    }

    fun calcularModificadores() {
        modificadorForca = calcularModificador(forca)
        modificadorDestreza = calcularModificador(destreza)
        modificadorConstituicao = calcularModificador(constituicao)
        modificadorInteligencia = calcularModificador(inteligencia)
        modificadorSabedoria = calcularModificador(sabedoria)
        modificadorCarisma = calcularModificador(carisma)
    }

    private fun calcularModificador(valorHabilidade: Int): Int {
        return floor((valorHabilidade - 10) / 2.0).toInt()
    }

    fun aplicarBonusRacial(raca: Raca) {
        raca.bonus.forEach { (atributo, valor) ->
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
        calcularModificadores()
    }

    fun exibirHabilidades() {
        println("\nHabilidades do Personagem:")
        println("Força: $forca (Modificador: $modificadorForca)")
        println("Destreza: $destreza (Modificador: $modificadorDestreza)")
        println("Constituição: $constituicao (Modificador: $modificadorConstituicao)")
        println("Inteligência: $inteligencia (Modificador: $modificadorInteligencia)")
        println("Sabedoria: $sabedoria (Modificador: $modificadorSabedoria)")
        println("Carisma: $carisma (Modificador: $modificadorCarisma)")
    }
}
