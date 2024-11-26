

import com.up.ddm.Habilidades
import com.up.ddm.PersonagemEntity
import com.up.ddm.Raca
import com.up.ddm.Classes

fun main() {
    println("Escolha uma Raça:")
    println("1. Anão")
    println("2. Gnomo")
    println("3. Humano")
    println("4. Elfo")

    val racaEscolhida = readLine() ?: "1"
    val raca = Raca.criarPorEscolha(racaEscolhida)

    println("\nEscolha uma Classe:")
    println("1. Guerreiro")
    println("2. Mago")
    println("3. Ladino")

    val classeEscolhida = readLine() ?: "1"
    val classe = Classes.criarPorEscolha(classeEscolhida)

    // Supondo que Habilidades seja uma classe que inicializa com valores padrão
    val habilidades = Habilidades()

    // Criando o personagem usando o método from()
    val personagem = PersonagemEntity(1,"anao","mago","",10,10,10,10,10,10,10)
//
    println("\nInformações do Personagem:")
    println("Raça: ${personagem.raca}")
    println("Classe: ${personagem.classe}")
    println("Dado de Vida: ${personagem.dadoDeVida}")
    println("Pontos de Vida: ${personagem.pontosDeVida}")
    println("Força: ${personagem.forca}")
    println("Destreza: ${personagem.destreza}")
    println("Constituição: ${personagem.constituicao}")
    println("Inteligência: ${personagem.inteligencia}")
    println("Sabedoria: ${personagem.sabedoria}")
    println("Carisma: ${personagem.carisma}")
}