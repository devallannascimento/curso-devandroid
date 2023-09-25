package com.allannascimento.aulathreadscoroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.allannascimento.aulathreadscoroutines.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var pararThread = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnIniciar.setOnClickListener {
            //MinhaThread().start()
            /*repeat(30) { indice ->
                Log.i("info_thread", "Executando: $indice T: ${Thread.currentThread().name}")
                Thread.sleep(1000)
            }*/

            Thread(MinhaRunnable()).start()

        }

        binding.btnParar.setOnClickListener {
            pararThread = true
            binding.btnIniciar.text = "REINICIAR EXECUÇÃO"
            binding.btnIniciar.isEnabled = true
        }

        binding.btnAbrirTela.setOnClickListener {
            startActivity(
                Intent(this, SegundaActivity::class.java)
            )
        }
    }

    inner class MinhaRunnable: Runnable{
        override fun run() {

            repeat(30) { indice ->

                if (pararThread){
                    pararThread = false
                    return
                }

                Log.i("info_thread", "MinhaThread: $indice T: ${Thread.currentThread().name}")
                runOnUiThread {
                    binding.btnIniciar.text = "EXECUTANDO: $indice T: ${Thread.currentThread().name}"
                    binding.btnIniciar.isEnabled = false
                    if (indice == 4){
                        binding.btnIniciar.text = "REINICIAR EXECUÇÃO"
                        binding.btnIniciar.isEnabled = true
                    }
                }
                Thread.sleep(1000)
            }
        }
    }

    inner class MinhaThread : Thread() {

        override fun run() {
            super.run()
            repeat(5) { indice ->
                Log.i("info_thread", "MinhaThread: $indice T: ${currentThread().name}")
                //binding.btnIniciar.text = "Executando: $indice T: ${currentThread().name}"
                runOnUiThread {
                    binding.btnIniciar.text = "EXECUTANDO: $indice T: ${currentThread().name}"
                    binding.btnIniciar.isEnabled = false
                    if (indice == 4){
                        binding.btnIniciar.text = "REINICIAR EXECUÇÃO"
                        binding.btnIniciar.isEnabled = true
                    }
                }
                sleep(1000)
            }
            /*runOnUiThread {
                binding.btnIniciar.text = "INICIAR"
                binding.btnIniciar.isEnabled = true
            }*/
        }
    }

}