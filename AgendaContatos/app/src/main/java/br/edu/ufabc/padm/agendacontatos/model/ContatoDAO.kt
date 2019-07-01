package br.edu.ufabc.padm.agendacontatos.model

/**
 * Here we implement two DAO Singleton strategies (via object and via class)
 */


import android.content.Context
import br.edu.ufabc.padm.agendacontatos.App
import java.io.FileWriter
import java.io.IOException
import java.util.ArrayList
import android.content.Context.MODE_PRIVATE
import java.io.File
import java.io.FileOutputStream


/**
 * An alias that selects which strategy to employ application-wise
 */

typealias ContatoDAO = ContatoDAOObject

/**
 * Singleton pattern implemented with a Kotlin object declaration (https://bit.ly/31J1Ev2)
 */
object ContatoDAOObject {
    private var contatos: MutableList<Contato> = ArrayList()
    val instance = this

    init {
        loadMockData()

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

    fun update(position: Int, contato: Contato) {
        contatos.set(position, contato)

    }

    fun removeAll(removePositions: IntArray): Boolean {
        return contatos.removeAll(
            Array(removePositions.size) {
                    i -> contatos.get(removePositions[i])
            })

    }

    private fun loadMockData() {
        contatos.addAll(
            App.context.assets.open("contato_mock_data.csv")
            .bufferedReader().readLines()
            .map {
                Array(5){ i -> it.split(",")[i]}}
            .map {
                Contato(
                    nome = it[0],
                    endereco = it[1],
                    telefoneComercial = it[2],
                    telefoneResidencial = it[3],
                    email = it[4])
            })
    }

//    private fun updateCSVContatos(){
//
//        val contatosCSV: File = File("\\","contatos.csv")
//        val fos = FileOutputStream(contatosCSV)
//
//        val contatosString: String = ""
//
//        try {
//
//            for (cc in this.contatos) {
//                contatosString.plus(cc.nome)
//                contatosString.plus(',')
//                contatosString.plus(cc.endereco)
//                contatosString.plus(',')
//                contatosString.plus(cc.telefoneComercial)
//                contatosString.plus(',')
//                contatosString.plus(cc.telefoneResidencial)
//                contatosString.plus(',')
//                contatosString.plus(cc.email)
//                contatosString.plus('\n')
//            }
//
//            fos.write(contatosString.toByteArray())
//            fos.close()
//
//
//            println("Write CSV successfully!")
//        } catch (e: Exception) {
//            println("Writing CSV error!")
//            e.printStackTrace()
//        } finally {
//            try {
//                fos!!.flush()
//                fos.close()
//            } catch (e: IOException) {
//                println("Flushing/closing error!")
//                e.printStackTrace()
//            }
//        }
//
//    }
//
//    private fun ordenarContatos(){
//        this.contatos.sortWith(compareBy({it.nome}))
//        updateCSVContatos()
//    }
}
