package br.edu.ufabc.padm.agendacontatos.model

data class Contato(
    var nome: String = "",
    var endereco: String = "",
    var telefoneResidencial: String = "",
    var telefoneComercial: String = "",
    var email: String = ""
)