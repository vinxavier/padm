package br.edu.ufabc.padm.agendacontatos.model

/**
 * Here we implement two DAO Singleton strategies (via object and via class)
 */


import java.util.ArrayList

/**
 * An alias that selects which strategy to employ application-wise
 */

typealias ContatoDAO = ContatoDAOObject

/**
 * Singleton pattern implemented with a Kotlin object declaration (https://bit.ly/31J1Ev2)
 */
object ContatoDAOObject {
    private val contatos: MutableList<Contato> = ArrayList()
    val instance = this

    init {
        // TODO: remove when "add" operation is implemented
        loadTestData()
    }

    private fun loadTestData() {
        var c: Contato

        c = Contato()

        c.nome = "Joao"
        c.email = "joao@email.com"
        c.endereco = "Rua Apolinario"
        c.telefoneComercial = "123445"
        c.telefoneResidencial = "123458"

        contatos.add(c)

        c = Contato()
        c.nome = "Maria"
        c.email = "maria@email.com"
        c.endereco = "Rua Joana"
        c.telefoneComercial = "123445"
        c.telefoneResidencial = "123458"

        contatos.add(c)
    }

    fun add(contato: Contato) {
        contatos.add(contato)
    }

    fun size(): Int {
        return contatos.size
    }

    fun getItemAt(pos: Int): Contato {
        return contatos[pos]
    }
}

/**
 * Singleton pattern implemented with a class
 */
class ContatoDAOClass private constructor() {
    private var contatos: MutableList<Contato> = ArrayList()

    init {
        // TODO: remove when "add" operation is implemented
        loadTestData()
    }

    companion object {
        val instance = ContatoDAOClass()
    }

    private fun loadTestData() {
        var c: Contato

        c = Contato()

        c.nome = "Joao"
        c.email = "joao@email.com"
        c.endereco = "Rua Apolinario"
        c.telefoneComercial = "123445"
        c.telefoneResidencial = "123458"

        contatos.add(c)

        c = Contato()
        c.nome = "Maria"
        c.email = "maria@email.com"
        c.endereco = "Rua Joana"
        c.telefoneComercial = "123445"
        c.telefoneResidencial = "123458"

        contatos.add(c)
    }

    fun add(contato: Contato) {
        contatos.add(contato)
    }

    fun size(): Int {
        return contatos.size
    }

    fun getItemAt(pos: Int): Contato {
        return contatos[pos]
    }

}
