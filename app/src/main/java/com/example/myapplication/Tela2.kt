package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Tela2 : AppCompatActivity() {

    val BaseUrl:String = "https://5f97658e42706e001695709f.mockapi.io/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela2)

        consumirApi()
    }

    fun consumirApi(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val requests = retrofit.create(MarmitaRequest::class.java)

        val callMarmitas = requests.getMarmitas()

        callMarmitas.enqueue(object :Callback<List<Marmita>>{
            override fun onFailure(call: Call<List<Marmita>>, t: Throwable) {
                Toast.makeText(applicationContext, "Deu ruim $t", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Marmita>>, response: Response<List<Marmita>>) {
                response.body()?.forEach {
                    val novoTv = TextView(baseContext)

                    novoTv.text =
                        "Marmita de ${it.proteina} com ${it.carboidrato} e ${it.grao}, e ${it.legume}"
                    novoTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                    novoTv.setTextColor(Color.parseColor("#000000"))

                    ll_conteudo.addView(novoTv)
                }
            }

        })
    }

    fun voltar(view: View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}