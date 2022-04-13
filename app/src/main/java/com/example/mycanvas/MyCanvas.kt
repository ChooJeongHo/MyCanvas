package com.example.mycanvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class MyCanvas : View {
    lateinit var p: Paint
    var isRunning = true
    lateinit var myBall: Ball
    lateinit var enemy: Ball
    var ballArr: ArrayList<Ball> = ArrayList()

    constructor(context: Context?) : super(context) {
        p = Paint()
//        ballArr.add(Ball(100f,100f,5,5,50f))
        MyThread().start()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        p = Paint()
        myBall = Ball(300f, 600f, 5, 5, 50f, 50f)
        enemy = Ball(200f, 300f, 0, 0, 50f, 100f, 50)

        MyThread().start()
    }

    fun addBall() {
        ballArr.add(Ball(myBall.posX, myBall.posY, 5, 8, 10f,25f))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        p.setColor(Color.RED)

        var i =0
        while (true){
            if(i< ballArr.size){
                var ball = ballArr.get(i)

                canvas.drawRect(ball.posX, ball.posY, ball.posX + ball.bWidth, ball.posY +ball.bHeight, p)
            }else{
                break
            }
            i++
        }

        p.setColor(Color.BLUE)
        canvas.drawCircle(myBall.posX, myBall.posY, myBall.bWidth, p)

//        intersect

        if(enemy.life > 40){
            p.setColor(Color.BLUE)
        }else if(enemy.life > 30){
            p.setColor(Color.YELLOW)
        }else if(enemy.life > 20){
            p.setColor(Color.RED)
        }else if(enemy.life > 10){
            p.setColor(Color.BLACK)
        }else{
            p.setColor(Color.DKGRAY)
        }
        canvas.drawRect(enemy.posX, enemy.posY, enemy.posX + enemy.bWidth, enemy.posY + enemy.bHeight, p)
    }

    inner class MyThread : Thread() {
        override fun run() {
            super.run()



            while (width == 0) {
                Thread.sleep(500)
            }
            var temp = 0;
            while (isRunning) {
                temp++
                if(temp == 20){
                    temp = 0;
                    addBall()
                }
                //이동
                if (myBall.isRight) {
                    myBall.posX += myBall.speedX
                }
                if (myBall.isLeft) {
                    myBall.posX -= myBall.speedX
                }

                if (myBall.isUp) {
                    myBall.posY -= myBall.speedY
                }

                if (myBall.isDown) {
                    myBall.posY += myBall.speedY
                }

                /**
                 * 1. 화면 나갔을 때 총알제거 (for문 형식 2군데 바꾸기)
                 * 2. 액티비티 종료 했을 때 쓰레드가 꺼지도록
                 * **/
                var i = 0
                while(true){
                    if(i < ballArr.size){
                        var ball = ballArr.get(i)
                        ball.posY -= ball.speedY
                        if(ball.posY < -100){
                            ballArr.removeAt(i)
                            i--
                        }else{
                            var eRect: Rect = Rect(enemy.posX.toInt(), enemy.posY.toInt(), (enemy.posX + enemy.bWidth).toInt(), (enemy.posY + enemy.bHeight).toInt())
                            var fRect :Rect = Rect(ball.posX.toInt(), ball.posY.toInt(), (ball.posX + ball.bWidth).toInt(), (ball.posY + ball.bHeight).toInt())
                            if(eRect.intersect(fRect)){
                                enemy.life--
                                if(enemy.life < 1){
                                    enemy.posX = -1000f
                                }
                                ballArr.removeAt(i)
                                i--
                            }
                        }

                    }else{
                        break
                    }
                    i++
                }

                //충돌


                invalidate()
                Thread.sleep(5)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        myBall.posX = event!!.x
        myBall.posY = event!!.y

        return true
    }
}