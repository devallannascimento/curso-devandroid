package com.allannascimento.aulathreadscoroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.allannascimento.aulathreadscoroutines.api.EnderecoAPI
import com.allannascimento.aulathreadscoroutines.api.PostagemAPI
import com.allannascimento.aulathreadscoroutines.api.RetrofitHelper
import com.allannascimento.aulathreadscoroutines.databinding.ActivityMainBinding
import com.allannascimento.aulathreadscoroutines.model.Comentario
import com.allannascimento.aulathreadscoroutines.model.Endereco
import com.allannascimento.aulathreadscoroutines.model.Filme
import com.allannascimento.aulathreadscoroutines.model.FilmeDetalhes
import com.allannascimento.aulathreadscoroutines.model.FilmeResposta
import com.allannascimento.aulathreadscoroutines.model.Foto
import com.allannascimento.aulathreadscoroutines.model.Postagem
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val retrofit by lazy {
        RetrofitHelper.retrofit
    }

    private val filmeAPI by lazy {
        RetrofitHelper.filmeAPI
    }

    private val apiViaCEP by lazy {
        RetrofitHelper.apiViaCEP
    }

    private var pararThread = false
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnAbrir.setOnClickListener {
            startActivity(
                Intent(this, SegundaActivity::class.java)
            )
        }

        binding.btnParar.setOnClickListener {
            //pararThread = true
            job?.cancel()
            binding.btnIniciar.text = "Reiniciar execução"
            binding.btnIniciar.isEnabled = true
        }

        binding.btnIniciar.setOnClickListener {
            /*            //CoroutineScope(Dispatchers.Main).launch {
            //MainScope().launch {
            //CoroutineScope(Dispatchers.IO).launch {
            //GlobalScope.launch {
            //lifecycleScope.launch {
            runBlocking {
                binding.btnIniciar.text = "Executando"
                repeat(30){ indice ->
                    //binding.btnIniciar.text = "Executando $indice"
                    Log.i("info_coroutine", "Executando: $indice T: ${Thread.currentThread().name}")
                    delay(1000L)
                }
            }

            repeat(15){ indice ->
                Log.i("info_thread", "Executando: $indice T: ${Thread.currentThread().name}")
                Thread.sleep(1000)//ms 1000 -> 1s
            }
            //MinhaThread().start()
            //Thread( MinhaRunnable() ).start()
            Thread {
                repeat(30){ indice ->
                    Log.i("info_thread", "MinhaThread: $indice T: ${Thread.currentThread().name}")
                    runOnUiThread {
                        binding.btnIniciar.text = "Executando: $indice T: ${Thread.currentThread().name}"
                        binding.btnIniciar.isEnabled = false
                        if( indice == 29 ){
                            binding.btnIniciar.text = "Reiniciar execução"
                            binding.btnIniciar.isEnabled = true
                        }
                    }
                    Thread.sleep(1000)//ms 1000 -> 1s
                }
            }.start()

            job = CoroutineScope( Dispatchers.IO ).launch {
                repeat(15){ indice ->
                    Log.i("info_coroutine", "Executando: $indice T: ${Thread.currentThread().name}")

                    withContext( Dispatchers.Main ){
                        binding.btnIniciar.text = "Executando: $indice T: ${Thread.currentThread().name}"
                    }

                    delay(1000)//ms 1000 -> 1s
                }
                withTimeout(7000L){
                    executar()
                }

                val tempo = measureTimeMillis {

                    val resultado1 = async {tarefa1()}//pedro
                    val resultado2 = async {tarefa2()}//maria

                    withContext( Dispatchers.Main ){
                        binding.btnIniciar.text = "${resultado1.await()}"
                        binding.btnParar.text = "${resultado2.await()}"
                    }

                    Log.i("info_coroutine", "resultado1: ${resultado1.await()}")
                    Log.i("info_coroutine", "resultado2: ${resultado2.await()}")

                }
                Log.i("info_coroutine", "Tempo: $tempo")
            }*/

            CoroutineScope(Dispatchers.IO).launch {

                Log.i("info_tmdb", "começa aqui")

                recuperarFilmesPesquisa()

            }

        }

    }

    private suspend fun recuperarFilmesPesquisa() {

        /*
        * 502356 - The Super Mario Bros. Movie
        * 872585 - Oppenheimer
        * 575264 - Mission: Impossible - Dead Reckoning Part One
        * */

        var retorno: Response<FilmeResposta>? = null

        try {
            val pesquisa = binding.editId.text.toString()
            retorno = filmeAPI.recuperarFilmesPesquisa(pesquisa)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_tmdb", "erro ao recuperar filmes pesquisa")
        }

        if (retorno != null) {

            if (retorno.isSuccessful) {

                val filmeResposta = retorno.body()
                val listaFilmes = filmeResposta?.results

                Log.i("info_tmdb", "CÓDIGO DE RETORNO [${retorno.code()}]")

                listaFilmes?.forEach { filme ->
                    val id = filme.id
                    val title = filme.title
                    Log.i("info_tmdb", "$id - $title")
                }
            } else {
                Log.i("info_tmdb", "CÓDIGO DE ERRO: ${retorno.code()}")
            }
        }

    }

    private suspend fun recuperarFilmeDetalhes() {

        /*
        * 502356 - The Super Mario Bros. Movie
        * 872585 - Oppenheimer
        * 575264 - Mission: Impossible - Dead Reckoning Part One
        * */

        var retorno: Response<FilmeDetalhes>? = null

        try {
            retorno = filmeAPI.recuperarFilmeDetalhes(872585)
            Log.i("info_tmdb", "chegou aqui")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_tmdb", "erro ao recuperar os detalhes do filme")
        }

        if (retorno != null) {

            Log.i("info_tmdb", "chegou aqui")

            if (retorno.isSuccessful) {

                val filmeDetalhes = retorno.body()

                val id = filmeDetalhes?.id
                val titulo = filmeDetalhes?.title
                val genero = filmeDetalhes?.genres?.get(0)
                val popularidade = filmeDetalhes?.popularity
                val lancamento = filmeDetalhes?.release_date

                val nomeImagem = filmeDetalhes?.backdrop_path

                val url = RetrofitHelper.BASE_URL_IMAGE + "w1280" + nomeImagem

                withContext(Dispatchers.Main){
                    Picasso.get()
                        .load(url)
                        .into(binding.imageFoto)
                }

                Log.i("info_tmdb", "CÓDIGO DE RETORNO: [${retorno.code()}] - ID: $id")
                Log.i("info_tmdb", "TÍTULO: $titulo")
                Log.i("info_tmdb", "GÊNERO: ${genero?.name}")
                Log.i("info_tmdb", "POPULARIDADE: $popularidade")
                Log.i("info_tmdb", "LANÇAMENTO: $lancamento")

            } else {
                Log.i("info_tmdb", "CÓDIGO DE ERRO: ${retorno.code()}")
            }
        }
    }

    private suspend fun recuperarDetalhesFilme() {

        /*
        * 502356 - The Super Mario Bros. Movie
        * 872585 - Oppenheimer
        * 575264 - Mission: Impossible - Dead Reckoning Part One
        * */

        var retorno: Response<Filme>? = null

        try {
            retorno = filmeAPI.recuperarDetalhesFilme(872585)
            Log.i("info_tmdb", "chegou aqui")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar os detalhes do filme")
        }

        if (retorno != null) {


            if (retorno.isSuccessful) {



                val filmeDetalhes = retorno.body()

                val id = filmeDetalhes?.id
                val titulo = filmeDetalhes?.title
                val popularidade = filmeDetalhes?.popularity
                val lancamento = filmeDetalhes?.release_date

                val idade = filmeDetalhes?.adult
                val adulto = if (idade != false) {
                    "Sim"
                } else {
                    "Não"
                }

                Log.i("info_tmdb", "CÓDIGO DE RETORNO: [${retorno.code()}] - ID: $id")
                Log.i("info_tmdb", "TÍTULO: $titulo")
                Log.i("info_tmdb", "POPULARIDADE: $popularidade")
                Log.i("info_tmdb", "LANÇAMENTO: $lancamento")
                Log.i("info_tmdb", "FILME +18: $adulto")

            } else {
                Log.i("info_tmdb", "CÓDIGO DE ERRO: ${retorno.code()}")
            }
        }
    }

    private suspend fun recuperarFilmesPopulares() {

        /*
        * 502356 - The Super Mario Bros. Movie
        * 872585 - Oppenheimer
        * 575264 - Mission: Impossible - Dead Reckoning Part One
        * */

        var retorno: Response<FilmeResposta>? = null

        try {
            retorno = filmeAPI.recuperarFilmesPopulares()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar filmes populares")
        }

        if (retorno != null) {

            if (retorno.isSuccessful) {

                val filmeResposta = retorno.body()
                val listaFilmes = filmeResposta?.results

                val pagina = filmeResposta?.page
                val totalPaginas = filmeResposta?.total_pages
                val totalDeFilmes = filmeResposta?.total_results

                Log.i(
                    "info_tmdb", "CÓDIGO DE RETORNO [${retorno.code()}] - PÁGINA: $pagina " +
                            "- TOTAL DE PÁGINAS: $totalPaginas - TOTAL DE FILMES: $totalDeFilmes"
                )

                listaFilmes?.forEach { filme ->
                    val id = filme.id
                    val title = filme.title
                    Log.i("info_tmdb", "$id - $title")
                }
            } else {
                Log.i("info_tmdb", "CÓDIGO DE ERRO: ${retorno.code()}")
            }
        }

    }

    private suspend fun recuperarFotoUnica() {

        var retorno: Response<Foto>? = null

        try {
            val postagemAPI = retrofit.create(PostagemAPI::class.java)
            val editId = binding.editId.text.toString()
            val id = editId.toInt()
            retorno = postagemAPI.recuperarFoto(id)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("3", "erro ao recuperar")
        }

        if (retorno != null) {

            if (retorno.isSuccessful) {
                val foto = retorno.body()
                val resultado = "[${retorno.code()}] - ${foto?.id} - ${foto?.url}"

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = resultado
                    Picasso.get()
                        .load(foto?.url)
                        .placeholder(R.drawable.carregando)
                        .error(R.drawable.carregando)
                        .into(binding.imageFoto)
                }

                Log.i("info_jsonplace", "recuperarFotoUnica: $resultado")

            } else {
                withContext(Dispatchers.Main) {
                    binding.textResultado.text = "ERROR! CODE [${retorno.code()}]"
                }

                Log.i("info_jsonplace", "recuperarFotoUnica: ERROR! CODE [${retorno.code()}]")

            }
        }
    }

    private suspend fun removerPostagem() {

        var retorno: Response<Unit>? = null

        val postagem = Postagem(
            "Corpo da postagem",
            -1,
            "Titulo Postagem",
            1090
        )

        try {
            val postagemAPI = retrofit.create(PostagemAPI::class.java)
            retorno = postagemAPI.removerPostagem(1)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao atualizar")
        }

        if (retorno != null) {

            if (retorno.isSuccessful) {

                var resultado = " CODE [${retorno.code()}] - Sucess"

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = resultado
                }

            } else {
                withContext(Dispatchers.Main) {
                    binding.textResultado.text = "ERROR! CODE [${retorno.code()}]"
                }
            }
        }

    }

    private suspend fun atualizarPostagem() {

        var retorno: Response<Postagem>? = null

        val postagem = Postagem(
            "Corpo da postagem",
            -1,
            "Titulo Postagem",
            1090
        )

        try {
            val postagemAPI = retrofit.create(PostagemAPI::class.java)
            /*retorno = postagemAPI.atualizarPostagemPut(
                1,
                Postagem("Corpo postagem",-1,null,3050)
            )*/
            retorno = postagemAPI.atualizarPostagemPatch(
                1,
                Postagem(
                    "Corpo postagem",
                    -1,
                    null,
                    4060
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao atualizar")
        }

        if (retorno != null) {

            if (retorno.isSuccessful) {
                val postagem = retorno.body()

                val id = postagem?.id
                val titulo = postagem?.title
                val corpo = postagem?.body
                val idUsuario = postagem?.userId
                var resultado =
                    "[${retorno.code()}] - ID: $id - T: $titulo - B: $corpo - U: $idUsuario"

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = resultado
                }

            } else {
                withContext(Dispatchers.Main) {
                    binding.textResultado.text = "ERRO CODE: ${retorno.code()}"
                }
            }
        }

    }

    private suspend fun salvarPostagem() {

        var retorno: Response<Postagem>? = null

        val postagem = Postagem(
            "Corpo da postagem",
            -1,
            "Titulo Postagem",
            1090
        )

        try {
            val postagemAPI = retrofit.create(PostagemAPI::class.java)
            // retorno = postagemAPI.salvarPostagem(postagem)
            retorno = postagemAPI.salvarPostagemFormulario(
                1090,
                -1,
                "Titulo postagem formulário",
                "Corpo postagem formulário"
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar")
        }

        if (retorno != null) {

            if (retorno.isSuccessful) {
                val postagem = retorno.body()

                val id = postagem?.id
                val titulo = postagem?.title
                val idUsuario = postagem?.userId
                var resultado = "CODE: [${retorno.code()}]\nID: $id\nT: $titulo\nU: $idUsuario"

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = resultado
                }

            } else {
                withContext(Dispatchers.Main) {
                    binding.textResultado.text = "ERRO CODE: ${retorno.code()}"
                }
            }
        }

    }

    private suspend fun recuperarComentarioPostagemQuery() {

        var retorno: Response<List<Comentario>>? = null

        try {
            val postagemAPI = retrofit.create(PostagemAPI::class.java)
            val editId = binding.editId.text.toString()
            val id = editId.toInt()
            retorno = postagemAPI.recuperarComentarioPostagemQuery(id)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar")
        }

        if (retorno != null) {

            if (retorno.isSuccessful) {
                val listaComentarios = retorno.body()

                var resultado = ""
                listaComentarios?.forEach { comentario ->
                    val idComentario = comentario.id
                    val email = comentario.email
                    val comentarioResultado = "$idComentario - $email \n"
                    resultado += comentarioResultado
                }

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = resultado
                }

            }
        }

    }

    private suspend fun recuperarComentarioPostagem() {

        var retorno: Response<List<Comentario>>? = null

        try {
            val postagemAPI = retrofit.create(PostagemAPI::class.java)
            val editId = binding.editId.text.toString()
            val id = editId.toInt()
            retorno = postagemAPI.recuperarComentarioPostagem(id)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar")
        }

        if (retorno != null) {

            if (retorno.isSuccessful) {
                val listaComentarios = retorno.body()

                var resultado = ""
                listaComentarios?.forEach { comentario ->
                    val idComentario = comentario.id
                    val email = comentario.email
                    val comentarioResultado = "$idComentario - $email \n"
                    resultado += comentarioResultado
                }

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = resultado
                }

            }
        }

    }

    private suspend fun recuperarPostagens() {

        var retorno: Response<List<Postagem>>? = null

        try {
            val postagemAPI = retrofit.create(PostagemAPI::class.java)
            retorno = postagemAPI.recuperarPostagens()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar")
        }

        if (retorno != null) {

            if (retorno.isSuccessful) {
                val listaPostagens = retorno.body()
                listaPostagens?.forEach { postagem ->
                    val id = postagem.id
                    val title = postagem.title
                    Log.i("info_jsonplace", "$id - $title")
                }
            }
        }

    }

    private suspend fun recuperarPostagemUnica() {

        var retorno: Response<Postagem>? = null

        try {
            val postagemAPI = retrofit.create(PostagemAPI::class.java)
            val editId = binding.editId.text.toString()
            val id = editId.toInt()
            retorno = postagemAPI.recuperarPostagemUnica(id)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("3", "erro ao recuperar")
        }

        if (retorno != null) {

            if (retorno.isSuccessful) {
                val postagem = retorno.body()
                val resultado = "${postagem?.id} - ${postagem?.title}"

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = resultado
                }

            }
        }
    }

    private suspend fun recuperarEndereco() {

        var retorno: Response<Endereco>? = null

        try {
            val enderecoAPI = apiViaCEP.create(EnderecoAPI::class.java)
            retorno = enderecoAPI.recuperarEndereco()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_endereco", "erro ao recuperar")
        }

        if (retorno != null) {

            if (retorno.isSuccessful) {
                val endereco = retorno.body()
                val rua = endereco?.logradouro
                val cidade = endereco?.localidade
                Log.i("info_endereco", "endereco: $rua, $cidade")
            }
        }
    }

}