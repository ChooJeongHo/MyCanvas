package com.example.mycanvas

class Ball {
    var posX = 0f
    var posY = 0f
    var speedX = 0
    var speedY = 0
    var bWidth = 0f
    var bHeight = 0f
    var isUp =false
    var isDown =false
    var isLeft =false
    var isRight =false
    var life = 0

    constructor(posX: Float, posY: Float, speedX: Int, speedY: Int, bWidth: Float, bHeight: Float) {
        this.posX = posX
        this.posY = posY
        this.speedX = speedX
        this.speedY = speedY
        this.bWidth = bWidth
        this.bHeight = bHeight
    }

    constructor(
        posX: Float,
        posY: Float,
        speedX: Int,
        speedY: Int,
        bWidth: Float,
        bHeight: Float,
        life: Int
    ) {
        this.posX = posX
        this.posY = posY
        this.speedX = speedX
        this.speedY = speedY
        this.bWidth = bWidth
        this.bHeight = bHeight
        this.life = life
    }


}