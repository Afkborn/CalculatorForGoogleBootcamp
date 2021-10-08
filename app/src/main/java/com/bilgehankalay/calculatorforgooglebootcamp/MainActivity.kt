package com.bilgehankalay.calculatorforgooglebootcamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bilgehankalay.calculatorforgooglebootcamp.databinding.ActivityMainBinding

import java.lang.ArithmeticException
import kotlin.math.sqrt


class MainActivity : AppCompatActivity() {
    private  lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.islemEditText.showSoftInputOnFocus= false

        binding.esittirButton.setOnClickListener {
            calculate()
        }
        binding.sifirButton.setOnClickListener {
            addTextToIslemEditText("0")
        }
        binding.birButton.setOnClickListener {
            addTextToIslemEditText("1")
        }
        binding.ikiButton.setOnClickListener {
            addTextToIslemEditText("2")
        }
        binding.ucButton.setOnClickListener {
            addTextToIslemEditText("3")
        }
        binding.dortButton.setOnClickListener {
            addTextToIslemEditText("4")
        }
        binding.besButton.setOnClickListener {
            addTextToIslemEditText("5")
        }
        binding.altiButton.setOnClickListener {
            addTextToIslemEditText("6")
        }
        binding.yediButton.setOnClickListener {
            addTextToIslemEditText("7")
        }
        binding.sekizButton.setOnClickListener {
            addTextToIslemEditText("8")
        }
        binding.dokuzButton.setOnClickListener {
            addTextToIslemEditText("9")
        }
        binding.artiButton.setOnClickListener {
            addTextToIslemEditText("+")
        }
        binding.eksiButton.setOnClickListener {
            addTextToIslemEditText("-")
        }
        binding.carpiButton.setOnClickListener {
            addTextToIslemEditText("*")
        }
        binding.bolmeButton.setOnClickListener {
            addTextToIslemEditText("/")
        }
        binding.noktaButton.setOnClickListener {
            addTextToIslemEditText(".")
        }
        binding.temizleButton.setOnClickListener {
            binding.islemEditText.text.clear()
        }
        binding.faktoriyelButton.setOnClickListener {
            val yazi = binding.islemEditText.text.toString()
            var sayi = ""
            yazi.forEach{
                if (it == '+' || it == '-' ||it == '*' || it == '/'){
                    binding.islemEditText.setText(R.string.sadece_sayi_hata_mesaji)
                }
                else{
                    sayi += it
                }
            }
            val intSayi = sayi.toIntOrNull()
            if (intSayi==null){
                binding.islemEditText.setText(R.string.sadece_sayi_hata_mesaji)
            }
            else{
                    var carpim = 1
                (1..intSayi).forEach {
                    carpim *= it
                }
                binding.islemEditText.setText(carpim.toString())
            }
        }
        binding.karekokButton.setOnClickListener {
            val yazi = binding.islemEditText.text.toString()
            var sayi = ""
            yazi.forEach{
                if (it == '+' || it == '-' ||it == '*' || it == '/'){
                    binding.islemEditText.setText(R.string.sadece_sayi_hata_mesaji)
                }
                else{
                    sayi += it
                }
            }
            val doubleSayi = sayi.toDoubleOrNull()
            if (doubleSayi==null){
                binding.islemEditText.setText(R.string.sadece_sayi_hata_mesaji)
            }
            else{
                binding.islemEditText.setText((sqrt(doubleSayi)).toString())
            }
        }
        binding.ussuIkiButton.setOnClickListener {
            val yazi = binding.islemEditText.text.toString()
            var sayi = ""
            yazi.forEach{
                if (it == '+' || it == '-' ||it == '*' || it == '/'){
                    binding.islemEditText.setText(R.string.sadece_sayi_hata_mesaji)
                }
                else{
                    sayi += it
                }
            }
            val doubleSayi = sayi.toDoubleOrNull()
            if (doubleSayi==null){
                binding.islemEditText.setText(R.string.sadece_sayi_hata_mesaji)
            }
            else{
                binding.islemEditText.setText((doubleSayi * doubleSayi).toString())
            }
        }
        binding.backspaceButton.setOnClickListener {
            if (binding.islemEditText.text.isNotEmpty()){
                binding.islemEditText.text.delete(binding.islemEditText.text.length-1,binding.islemEditText.text.length)
            }
        }
        binding.backspaceButton.setOnLongClickListener {
            binding.islemEditText.setText("")
            true
        }
    }
    fun calculate(){
        var toplam = 0.0
        var recyclerRowText =  binding.islemEditText.text.toString()
        val yazi = binding.islemEditText.text.toString() + "?"
        val sayilar : MutableList<Double> = mutableListOf()
        val islemler : MutableList<Char> = mutableListOf()
        var sayi = ""
        var eksiMi = false
        yazi.forEachIndexed() { index,it ->
            if (index == 0 && it == '-'){
                eksiMi = true
            }
            else{
                if (it == '+' || it == '-' ||it == '*' || it == '/' || it == '?'){
                    if (it != '?'){
                        islemler.add(it)
                    }
                    val doubleSayi = sayi.toDoubleOrNull()
                    if (doubleSayi == null){
                        binding.islemEditText.setText(R.string.hata)
                        return
                    }
                    if(eksiMi){
                        sayilar.add(doubleSayi * -1)
                        eksiMi = false
                    }
                    else{
                        sayilar.add(doubleSayi)
                    }
                    sayi = ""
                }
                else{
                    sayi += it
                }
            }
        }
        if (sayilar.size -1 != islemler.size){
            return
        }
        islemler.forEachIndexed() {index,it ->
            if (it == '+'){
                if (index == 0){
                    toplam = (sayilar[0] + sayilar[1])
                }
                else{
                    toplam += sayilar[index+1]
                }
            }
            else if (it == '-'){
                if (index == 0){
                    toplam = (sayilar[0] - sayilar[1])
                }
                else{
                    toplam -= sayilar[index+1]
                }
            }
            else if (it == '*'){
                if (index == 0){
                    toplam = (sayilar[0] * sayilar[1])
                }
                else{
                    toplam *= sayilar[index+1]
                }
            }
            else{
                if (index == 0){
                    try {
                        toplam = (sayilar[0] / sayilar[1])
                    }catch (e:ArithmeticException){
                        binding.islemEditText.setText(R.string.sonuc_tanimsiz)
                        return
                    }
                }
                else{
                    try {
                        toplam /= sayilar[index+1]
                    }catch (e:ArithmeticException){
                        binding.islemEditText.setText(R.string.sonuc_tanimsiz)
                        return
                    }
                }
            }
        }
        recyclerRowText += "=$toplam"
        println(recyclerRowText)
        binding.islemEditText.setText(toplam.toString())
    }
    fun addTextToIslemEditText(text : String){
        if (binding.islemEditText.text.isEmpty()){
            if (text != "."){
                binding.islemEditText.setText(( binding.islemEditText.text.toString() + text))
            }
        }
        else{
            if (binding.islemEditText.text[binding.islemEditText.text.length-1] == '0' && text == "0"){

            }
            else{
                binding.islemEditText.setText(( binding.islemEditText.text.toString() + text))
            }
        }

    }

}