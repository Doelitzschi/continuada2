package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val BaseUrl:String = "https://5f97658e42706e001695709f.mockapi.io/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun cadastrarMarmita(componente:View){
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val requests = retrofit.create(MarmitaRequest::class.java)

        val novaMarmita = Marmita(
            null,
            et_nome_carboidrato.text.toString(),
            et_nome_proteina.text.toString(),
            et_nome_Legume.text.toString(),
            et_nome_grao.text.toString()
        )

        val callCriarMarmita =requests.postMarmita(novaMarmita)

            callCriarMarmita.enqueue(object :Callback<Void>{
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(baseContext, "Erro: $t", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(baseContext,
                        getString(R.string.txt_cadastrado),
                        Toast.LENGTH_SHORT).show()
                }

            })
    }

    fun trocarTela(view: View){
        val intent = Intent(this, Tela2::class.java)
        startActivity(intent)
    }
}