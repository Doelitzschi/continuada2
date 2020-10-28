package com.example.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_tela_desenho.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TelaDesenho : AppCompatActivity() {

    val BaseUrl:String = "https://5f97658e42706e001695709f.mockapi.io/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_desenho)
        consumirApi()
    }


    fun consumirApi(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val requests = retrofit.create(DesenhosRequest::class.java)
        val callDesenho= requests.getDesenhos(Integer(1))

        callDesenho.enqueue(object: Callback<Desenhos> {
            override fun onFailure(call: Call<Desenhos>, t: Throwable) {
                Toast.makeText(baseContext, "Erro: $t", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Desenhos>, response: Response<Desenhos>) {
                val novoTv = TextView(baseContext)

                val desenho = response.body()
                novoTv.text = getString(R.string.txt_desenho, desenho?.autor, desenho?.data,
                    desenho?.estilo, desenho?.nome)

                novoTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                novoTv.setTextColor(Color.parseColor("#000000"))

                ll_conteudo.addView(novoTv)
            }
        })
    }
}