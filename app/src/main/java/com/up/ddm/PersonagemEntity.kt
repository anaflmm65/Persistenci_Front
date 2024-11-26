package com.up.ddm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personagem")
data class PersonagemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val raca: String,
    val classe: String,
    val dadoDeVida: String,
    val pontosDeVida: Int,
    val forca: Int,
    val destreza: Int,
    val constituicao: Int,
    val inteligencia: Int,
    val sabedoria: Int,
    val carisma: Int
) {
   /* companion object {
        // Cria uma instância de Personagem a partir de Raca, Classe e Habilidades.
        fun from(raca: Raca, classe: Classes, habilidades: Habilidades): Personagem {
            val pontosDeVida = calcularPontosDeVida(classe.dadoDeVida, habilidades.modificadorConstituicao)
            return Personagem(
                raca = raca.nome,
                classe = classe.nome,
                dadoDeVida = classe.dadoDeVida,
                pontosDeVida = pontosDeVida,
                forca = habilidades.forca,
                destreza = habilidades.destreza,
                constituicao = habilidades.constituicao,
                inteligencia = habilidades.inteligencia,
                sabedoria = habilidades.sabedoria,
                carisma = habilidades.carisma
            )
        }*/

        private fun calcularPontosDeVida(dadoDeVida: String, modificadorConstituicao: Int): Int {
            val valorMaximo = when (dadoDeVida) {
                "d8" -> 8
                "d6" -> 6
                "d10" -> 10
                else -> 10
            }
            return valorMaximo + modificadorConstituicao
        }

    }

