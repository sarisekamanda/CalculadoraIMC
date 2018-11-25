package com.example.sarise.calculadoraimc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var altura = 0.0
    var peso = 0
    var radio = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swGenero.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                buttonView.text = "Mulher"
            } else {
                buttonView.text = "Homem"
            }
        }

        seekIdade.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    txtSeekIdade.text = progress.toString() + " anos"
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {                }
                override fun onStopTrackingTouch(seekBar: SeekBar?) {                }
            })

        seekPeso.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    txtSeekPeso.text = progress.toString()+ "kg"
                    peso = seekBar!!.progress.toDouble().toInt()
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {               }
                override fun onStopTrackingTouch(seekBar: SeekBar?) {               }
            })

        seekAltura.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                txtSeekAltura.text = progress.toString() + "cm"
                altura = seekBar!!.progress.toDouble()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {            }
        })

        rgAtividadeFisica?.setOnCheckedChangeListener { group, checkedId -> radio

            when(checkedId){
                R.id.rbSedentario -> radio = "Exercite-se mais! #YouCanDoIt"
                R.id.rbModerado -> radio= "Já é um começo! #KeepGoing"
                R.id.rbIntenso -> radio= "woohoo! #YouRock"
            }
        }

    }

    fun calcularIMC (v: View){
        altura/=100

        val calc = peso / (altura * altura)
        textResultado.text =calc.toString()


        if (calc < 18.5){
            rbSaudavel.rating = 2F
            textResultado.text = "IMC:" + calc + " Você está abaixo do peso :(  " +  radio.toString()
        }else{
            if (calc > 18.5 && calc < 24.9){
                rbSaudavel.rating = 5F
                textResultado.text = "IMC:" + calc + " Você esta no peso ideal :D   " + radio.toString()
            }else{
                rbSaudavel.rating = 2F
                if(calc>25 && calc<29.9){
                    textResultado.text = "IMC:" + calc + " Você esta Acima do peso :(   "+ radio.toString()
                }else{
                    if(calc>30){
                        rbSaudavel.rating = 1F
                        textResultado.text = "IMC:" + calc + "Você está Obeso :(   " + radio.toString()
                    }
                }
            }
        }
    }
}
