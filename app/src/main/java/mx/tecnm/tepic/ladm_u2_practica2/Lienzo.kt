package mx.tecnm.tepic.ladm_u2_practica2

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AlertDialog
import java.lang.String
import java.util.*

class Lienzo(p: MainActivity): View(p){

    var puntos = 0
    var puntost =""
    var moscaL : Array<dibujo> = Array(100, { dibujo(this) })

    var countDownTimer: CountDownTimer? = object : CountDownTimer(400000, 10) {
        override fun onTick(millisUntilFinished: Long) {
            puntost=(
                    String.format(
                        Locale.getDefault(),
                        "%d sec.",
                        millisUntilFinished / 1000L
                    )
                    )
            if(puntos==100){
                resultado("FELICIDADES APLASTASTE A TODAS!")
                this.cancel()    }
            if(millisUntilFinished/1000L==0.toLong()){
                resultado("LO SIENTO)): PARA LA PRÓXIMA :D")}
        }

        override fun onFinish() {
            this.cancel()
        }
    }.start()

    override fun onDraw(c: Canvas){
        super.onDraw(c)
        var paint = Paint()

        c.drawColor(Color.BLACK)
        paint.setColor(Color.BLACK)

        (0..99).forEach {
            moscaL[it].dibujar(c, paint)
        }

        paint.textSize = 50f
        paint.setColor(Color.WHITE)
        c.drawText("Puntos totales: " +puntos.toString(), 50f, 150f, paint)
        paint.textSize = 50f
        c.drawText("Tiempo: "+ puntost, 50f, 210f, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                var puntero = 0
                while (puntero <= 99) {
                    if (moscaL[puntero].hitbox(event.x, event.y)) {
                        if (moscaL[puntero].moscafaltante) {
                            var mancha = BitmapFactory.decodeResource(this.resources, R.drawable.mancha)
                            moscaL[puntero].mosca = mancha
                            moscaL[puntero].moscafaltante = false
                            puntos++
                        }
                    }
                    puntero++
                }
            }
        }
        invalidate()
        return true
    }
    fun movimiento(){
        (0..10).forEach {
            if(moscaL[it].moscafaltante)
                moscaL[it].limites(width, height)
        }
        invalidate()
    }

    fun resultado(m: kotlin.String){
        AlertDialog.Builder(this.context)
            .setTitle("TOTAL")
            .setMessage("APLASTASTE " + puntos + " MOSCAS :O "+m)
            .setPositiveButton("OK"){p, i ->}
            .show()
    }
}