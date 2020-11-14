package mx.tecnm.tepic.ladm_u2_practica2

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

class dibujo(punteroLienzo: Lienzo) {
    var x = (0..1080).random().toFloat()
    var y = (0..1776).random().toFloat()
    var incX = 10
    var incY = 10
    var mosca = BitmapFactory.decodeResource(punteroLienzo.resources, R.drawable.mosca)

    var moscafaltante = true

    fun dibujar(c: Canvas, p: Paint){
            c.drawBitmap(mosca, x, y, p)
    }

    fun hitbox(toqueX:Float,toqueY:Float): Boolean {
        var x2 = x + mosca.width
        var y2 = y + mosca.height
        if(toqueX >= x && toqueX<= x2){
            if(toqueY >= y && toqueY <= y2){
                return true
            }
        }
        return false
    }

    fun limites(width:Int, height:Int){
        x+=incX
        y+=incY
        if(x<= -100||x>=width){
            incX*=-1
        }
        if(y<=-100||y>=height){
            incY*=-1
        }
    }

}