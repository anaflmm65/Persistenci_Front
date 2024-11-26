
class Raca(
    val nome: String,
    val idiomas: List<String>,
    val bonus: Map<String, Int>
) {
    fun exibirInformacoes() {
        println("Raça: $nome")
        println("Idiomas: $idiomas")

        println("Bônus:")
        if (bonus.isNotEmpty()) {
            bonus.forEach { (atributo, valor) ->
                if (atributo == "Todos") {
                    println("  - Todos os atributos: +$valor")
                } else {
                    println("  - $atributo: +$valor")
                }
            }
        } else {
            println("  - Sem bônus adicionais.")
        }
    }

    companion object {
        fun criarPorEscolha(escolha: String): Raca {
            return when (escolha) {
                "1" -> Raca("Anão", listOf("Comum", "Anão"), mapOf("Constituição" to 2))
                "2" -> Raca("Gnomo", listOf("Comum", "Gnômico"), mapOf("Inteligência" to 2))
                "3" -> Raca("Humano", listOf("Comum", "Humano"), mapOf("Força" to 1))
                "4" -> Raca("Elfo", listOf("Comum", "Élfico"), mapOf("Destreza" to 2))
                else -> Raca("Anão", listOf("Comum", "Anão"), mapOf("Constituição" to 2))
            }
        }
    }
}
