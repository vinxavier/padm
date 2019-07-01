package br.edu.ufabc.padm.agendacontatos.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Contato(
    var nome: String = "",
    var endereco: String = "",
    var telefoneResidencial: String = "",
    var telefoneComercial: String = "",
    var email: String = ""
):Parcelable