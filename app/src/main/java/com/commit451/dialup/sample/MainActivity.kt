package com.commit451.dialup.sample

import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView

import com.commit451.dialup.DialUp

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val root = findViewById<ViewGroup>(R.id.root)

        val textView = findViewById<TextView>(R.id.status)
        DialUp.observable(this)
                .subscribe { connected ->
                    Snackbar.make(root, "Connectivity changed to: $connected", Snackbar.LENGTH_LONG)
                            .show()
                    textView.text = "Connected: $connected"
                }
    }
}
