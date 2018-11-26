package com.example.sarise.calculadoraimc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var altura = 0.0
    var peso = 0.0
    var radio = ""
    var alturaCalc = 0.0

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
                    txtSeekPeso.text = progress.toString()+ " kg"

                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {               }
                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    txtSeekPeso.text = seekPeso.progress.toString()+ " kg"
                    peso = seekPeso.progress.toDouble()
                }
            })

        seekAltura.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                txtSeekAltura.text = progress.toString() + " cm"

            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                txtSeekAltura.text = seekAltura.progress.toString() + " cm"
                altura = seekAltura.progress.toDouble()
            }
        })

        rgAtividadeFisica?.setOnCheckedChangeListener { group, checkedId -> radio

            when(checkedId){
                R.id.rbSedentario -> radio = "Exercite-se mais! #YouCanDoIt"
                R.id.rbModerado -> radio = "Parabéns!!! #KeepGoing"
                R.id.rbIntenso -> radio = "woohoo! #YouRock"
            }
        }

    }

    fun calcularIMC (v: View){
        //altura = (seekAltura.progress.toDouble())/100
        alturaCalc = altura/100

        val calc = peso / (alturaCalc * alturaCalc)
        textResultado.text =calc.toString()


        if (calc < 18.5){
            rbSaudavel.rating = 2F
            //textResultado.text = String.format("IMC: %.2f \nVocê está abaixo do peso :( \n%s", radio.toString(), calc.toString())
            textResultado.text = ("IMC: " + calc.toString()+ "\nVocê está abaixo do peso :( -->  " + radio.toString())
        }else{
            if (calc > 18.5 && calc < 24.9){
                rbSaudavel.rating = 5F
            //    textResultado.text = String.format("IMC: %.2f" + "\nVocê esta no peso ideal :D \n%s", radio.toString(), calc.toString())
                textResultado.text =  ("IMC: " + calc.toString() + "\nVocê esta no peso ideal :D -->  " + radio.toString())
            }else{
                rbSaudavel.rating = 2F
                if(calc>25 && calc<29.9){
                   // textResultado.text = String.format("IMC: %.2f" + "\nVocê esta Acima do peso :(  \n%s", radio.toString(), calc.toString())
                    textResultado.text = String.format("IMC: " + calc.toString() + "\nVocê esta acima do peso -->   :(  " + radio.toString())

                }else{
                    if(calc>30){
                        rbSaudavel.rating = 1F
                        //textResultado.text = String.format("IMC: %.2f" + "\nVocê está Obeso :(  \n%s", radio.toString(), calc.toString())
                        textResultado.text = String.format("IMC: " + calc.toString() + "\nVocê está Obeso :( -->  " + radio.toString())

                    }
                }
            }
        }
    }
}
