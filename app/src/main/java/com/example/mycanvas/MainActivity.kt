package com.example.mycanvas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    /**
     *  1. 공이 좌우로 튕기게
     *  2. 공이 사방으로 튕기게
     *  3. 버튼을 클릭할 때마다 사방으로 튕기는 공이 추가되도록
     *
     */
    lateinit var btn :Button
    lateinit var rBtn :Button
    lateinit var uBtn :Button
    lateinit var dBtn :Button
    lateinit var lBtn :Button
    lateinit var myCanvas : MyCanvas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(MyCanvas(this))
        setContentView(R.layout.activity_main)
        btn = findViewById(R.id.btn)
        rBtn = findViewById(R.id.rBtn)
        uBtn = findViewById(R.id.uBtn)
        dBtn = findViewById(R.id.dBtn)
        lBtn = findViewById(R.id.lBtn)
        myCanvas = findViewById(R.id.myCanvas)

        btn.setOnClickListener {
//            myCanvas.addBall()

            Log.d("aabb","zzzzzz ")
            var arr  = ArrayList<Int>()
            arr.add(10)
            arr.add(10)
            arr.add(10)
            arr.add(10)
            arr.add(10)

            var idx = 0
            while (true){
                if(idx < arr.size) {
                    Log.d("aabb", "size1: " + arr.size + " , i: " + idx)
                }else{
                    break
                }
                arr.clear()
                idx++
            }

        }

        rBtn.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                Log.d("aabb", "ran: "+ Random.nextInt(10))
                if(event!!.action == MotionEvent.ACTION_DOWN){
                    myCanvas.myBall.isRight = true
                    Log.d("aabb","chk: "+Random.nextInt(11))
                }else if (event!!.action == MotionEvent.ACTION_CANCEL){
                    myCanvas.myBall.isRight = false
                }else if (event!!.action == MotionEvent.ACTION_UP){
                    myCanvas.myBall.isRight = false
                }
                return false
            }
        })

        lBtn.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if(event!!.action == MotionEvent.ACTION_DOWN){
                    myCanvas.myBall.isLeft = true
                }else if (event!!.action == MotionEvent.ACTION_CANCEL){
                    myCanvas.myBall.isLeft = false
                }else if (event!!.action == MotionEvent.ACTION_UP){
                    myCanvas.myBall.isLeft = false
                }
                return false
            }
        })
        uBtn.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if(event!!.action == MotionEvent.ACTION_DOWN){
                    myCanvas.myBall.isUp = true
                }else if (event!!.action == MotionEvent.ACTION_CANCEL){
                    myCanvas.myBall.isUp = false
                }else if (event!!.action == MotionEvent.ACTION_UP){
                    myCanvas.myBall.isUp = false
                }
                return false
            }
        })
        dBtn.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if(event!!.action == MotionEvent.ACTION_DOWN){
                    myCanvas.myBall.isDown = true
                }else if (event!!.action == MotionEvent.ACTION_CANCEL){
                    myCanvas.myBall.isDown = false
                }else if (event!!.action == MotionEvent.ACTION_UP){
                    myCanvas.myBall.isDown = false
                }
                return false
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        myCanvas.isRunning = false
    }
}