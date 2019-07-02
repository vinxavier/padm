package br.edu.ufabc.padm.ebooklibrary.model


import java.util.ArrayList

/**
 * An alias that selects which strategy to employ application-wise
 */

typealias EbookDAO = EbookDAOObject

/**
 * Singleton pattern implemented with a Kotlin object declaration (https://bit.ly/31J1Ev2)
 */
object EbookDAOObject {
    private val ebooks: MutableList<Ebook> = ArrayList()
    val instance = this

    init {
        // TODO: remove when "add" operation is implemented
        loadTestData()
    }

    private fun loadTestData() {
        var ebook: Ebook

        ebook = Ebook()
        ebook.titulo = "Os contos perdidos"
        ebook.autor = "Arthur de Lima"
        ebook.ISBN = "???"
        ebook.ano = "1988"
        ebook.editora = "Editora Santos Drummond"
        ebook.avalicao = 3

        ebooks.add(ebook)

        ebook = Ebook()
        ebook.titulo = "Vidas de um programador frustrado"
        ebook.autor = "Todos Estudantes"
        ebook.ISBN = "???"
        ebook.ano = "2006"
        ebook.editora = "Editora Verde"
        ebook.avalicao = 1

        ebooks.add(ebook)

        ebook = Ebook()
        ebook.titulo = "Volta ao mundo em 80 dias"
        ebook.autor = "Julio Verne"
        ebook.ISBN = "???"
        ebook.ano = "1978"
        ebook.editora = "Europe Publisher"
        ebook.avalicao = 5

        ebooks.add(ebook)

    }

    fun add(ebook: Ebook) {
        ebooks.add(ebook)
    }

    fun size(): Int {
        return ebooks.size
    }

    fun getItemAt(pos: Int): Ebook {
        return ebooks[pos]
    }
}