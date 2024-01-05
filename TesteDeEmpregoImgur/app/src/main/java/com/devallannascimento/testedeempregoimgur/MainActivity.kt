package com.devallannascimento.testedeempregoimgur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devallannascimento.testedeempregoimgur.adapter.GaleriaAdapter
import com.devallannascimento.testedeempregoimgur.api.ImgurAPI
import com.devallannascimento.testedeempregoimgur.api.RetrofitServices
import com.devallannascimento.testedeempregoimgur.api.model.Resultado
import com.devallannascimento.testedeempregoimgur.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var jobPesquisarImagens: Job? = null

    var gridLayoutManager: GridLayoutManager? = null
    private lateinit var galeriaAdapter: GaleriaAdapter
    private val imgurAPI by lazy { RetrofitServices.imgurAPI }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        galeriaAdapter = GaleriaAdapter()
        binding.rvGaleria.adapter = galeriaAdapter
        binding.rvGaleria.layoutManager = GridLayoutManager(
            this,
            3,
            RecyclerView.VERTICAL,
            false
        )

    }

    override fun onStart() {
        super.onStart()

        recuperarImagensAPI()

    }

    override fun onDestroy() {
        super.onDestroy()

        jobPesquisarImagens?.cancel()

    }

    private fun recuperarImagensAPI() {

        jobPesquisarImagens = CoroutineScope(Dispatchers.IO).launch {

            var resposta: Response<Resultado>? = null

            try {
                resposta = imgurAPI.pesquisarImagensGaleria("cats")
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (resposta != null && resposta.isSuccessful) {

                val resultado = resposta.body()
                if (resultado != null) {

                    val lista = resultado.data

                    val listaImagens = mutableListOf<String>()
                    lista.forEach { dados ->
                        val image = dados.images[0]
                        val type = image.type

                        if (type == "image/jpeg") {
                            listaImagens.add(image.link)
                        }
                    }

                    withContext(Dispatchers.Main) {
                        galeriaAdapter.adicionarLista(listaImagens)
                    }

                } else {
                    Log.i("info_api", "resultado: Vazio")
                }

            } else {
                Log.i("info_api", "Erro ao fazer a recuperar: ${resposta?.code()}")
            }

        }

    }

}