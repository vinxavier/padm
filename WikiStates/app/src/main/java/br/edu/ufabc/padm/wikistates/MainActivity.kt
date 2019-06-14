package br.edu.ufabc.padm.wikistates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.net.Uri


class MainActivity : AppCompatActivity() {

    var adapter: ImageAdapter? = null
    var estadoList = ArrayList<Estado>()
    var url:String="https://pt.wikipedia.org/wiki/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //carregar estados

        estadoList.add(Estado("AM",R.drawable.am))
        estadoList.add(Estado("BA",R.drawable.ba))
        estadoList.add(Estado("CE",R.drawable.ce))
        estadoList.add(Estado("DF",R.drawable.df))
        estadoList.add(Estado("ES",R.drawable.es))
        estadoList.add(Estado("MA",R.drawable.ma))
        estadoList.add(Estado("MG",R.drawable.mg))
        estadoList.add(Estado("MS",R.drawable.ms))
        estadoList.add(Estado("PA",R.drawable.pa))
        estadoList.add(Estado("PE",R.drawable.pe))
        estadoList.add(Estado("RJ",R.drawable.rj))
        estadoList.add(Estado("SC",R.drawable.sc))
        estadoList.add(Estado("SE",R.drawable.se))
        estadoList.add(Estado("SP",R.drawable.sp))
        adapter = ImageAdapter(this, estadoList)

        base_grid.adapter = adapter
        base_grid.onItemClickListener = AdapterView.OnItemClickListener{_, _, i, _ ->
            var estado = ""
            when(i){
                0-> estado = "Amazonas"
                1-> estado = "Bahia"
                2-> estado = "Ceará"
                3-> estado = "Distrito_federal(Brasil)"
                4-> estado = "Espírito_santo"
                5-> estado = "Maranhão"
                6-> estado = "Minas_Gerais"
                7-> estado = "Mato_Grosso_do_Sul"
                8-> estado = "Pará"
                9-> estado = "Pernambuco"
                10-> estado = "Rio_de_Janeiro(Estado)"
                11-> estado = "Sergipe"
                12-> estado = "São_Paulo(Estado)"

                else->{
                    estado = "Ceará"
                }
            }

            val temp_intent = Intent(Intent.ACTION_VIEW)
            temp_intent.setData(Uri.parse(url+estado))
            startActivity(temp_intent)
        }

    }

}
