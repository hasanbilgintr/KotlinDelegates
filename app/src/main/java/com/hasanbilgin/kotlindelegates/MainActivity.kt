package com.hasanbilgin.kotlindelegates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class MainActivity : AppCompatActivity(), LifecycleLogger by LifecyclerLoggerImplementation() {

    //property delegates
    private val myVariable by lazy {
        println("hello this is a lazy implementation")
        //demişgekene 10 ataması yapar
        10
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //yaşam döngüleri yazmadan tüm yaşam döngü ifadelerini bu şekilde getirtebiliriz
        registerLifecyclerOwner(this)

        //bu myVariable değişkeni kullanıldığında tanımlama ve atamasını yapar demek kullanılmadığında tanımlama ve ataması yapmadığı için ramde yer kaplamaz
        println(myVariable)
    }
}

interface LifecycleLogger {
    fun registerLifecyclerOwner(owner: LifecycleOwner)
}

//interface uygulayanın bu class olduğunu belirtmek için by yazıyoruz buna delegasyon diyoruz
//bir nevi bu classta interfacein implemente ettği için by ile ifade edilen terde implementasyona gerek yok kalmıyo
//başka adı uygulayıcı sunıfta denebilir
class LifecyclerLoggerImplementation : LifecycleLogger, LifecycleEventObserver {
    override fun registerLifecyclerOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_RESUME -> println("on resume executed")
            Lifecycle.Event.ON_PAUSE -> println("on pause executed")
            else -> Unit
        }
    }

}