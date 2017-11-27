package com.example.arifinfrds.tugas5thread

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var mCounterThread: Thread? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        uiButtonPlay?.setOnClickListener(this)
        uiButtonPause?.setOnClickListener(this)

        uiTextViewNumber.setOnClickListener {
            toast("TextView Number")
        }


    }


    override fun onClick(v: View?) {
        val id = v?.id

        if (id == R.id.uiButtonPlay) {
            startCounter()
        } else if (id == R.id.uiButtonPause) {
            stopCounter()
        }
    }

    private fun startCounter() {
        toast("startCounter()")
        if (mCounterThread == null || mCounterThread?.state === Thread.State.TERMINATED) {
            val runnable = Runnable {
                try {
                    while (true) {
                        var counterNumber = (Math.random() * 10).toInt()
                        Thread.sleep(500)
                        uiTextViewNumber.post {
                            uiTextViewNumber.setText(counterNumber.toString())
                        }
                    }
                } catch (e: InterruptedException) {

                    e.printStackTrace()
                }
            }
            mCounterThread = Thread(runnable)
            mCounterThread?.start()
        }
    }

    private fun stopCounter() {
        toast("stopCounter()")
        mCounterThread?.interrupt()
    }

    fun Context.toast(message: CharSequence) =
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


