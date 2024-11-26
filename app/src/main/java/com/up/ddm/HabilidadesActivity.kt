package com.up.ddm

class HabilidadesActivity {
    fun iniciar() {
        println("Bem-vindo ao sistema de alocação de habilidades!")

        // Cria uma nova instância de Habilidades
        val habilidades = Habilidades()

        // Exibe as habilidades após a alocação e aplicação de bônus raciais
        habilidades.exibirHabilidades()

        println("Obrigado por usar o sistema de alocação de habilidades!")
    }
}

fun main() {
    val activity = HabilidadesActivity()
    activity.iniciar()
}
