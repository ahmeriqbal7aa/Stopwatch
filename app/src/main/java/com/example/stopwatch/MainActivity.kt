package com.example.stopwatch

import android.graphics.Color
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.os.SystemClock
import android.text.Html
import android.view.View
import android.widget.Chronometer.OnChronometerTickListener
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    // we take "long" because of milliseconds
    var pauseAt: Long = 0
    var running: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ===== Remove Status Bar
        // requestWindowFeature(Window.FEATURE_NO_TITLE)
        // ===== Write 2 times for width and height
        // window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        // window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)



        // ======================================================== //
        // TODO Set ActionBarTitle to Center
      //  supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        //supportActionBar?.setCustomView(R.layout.activity_main)
        // TODO Override ActionBarTitle
//        supportActionBar?.title = Html.fromHtml("<font color='#000099'>Hello World</font>");
        // ======================================================== //

        // TODO Format
        // Below two lines mean base set to zero and apply on format
        stopwatch.format = "Timer: %s"
        stopwatch.base = SystemClock.elapsedRealtime()
        // TODO Toaster
        stopwatch.onChronometerTickListener = OnChronometerTickListener {
            // If passed time > 10sec Then toast will display
            if (SystemClock.elapsedRealtime() - stopwatch.base >= 10000) {
                stopwatch.base = SystemClock.elapsedRealtime()
                Toast.makeText(this@MainActivity, "10s Finish!", Toast.LENGTH_SHORT).show()
            }
        }

        // TODO Transition
        val transition: TransitionDrawable = mainContainer.background as TransitionDrawable

        // TODO Start Listener
        start.setOnClickListener {
            if(!running){
                transition.startTransition(500)
                stopwatch.setTextColor(Color.WHITE)
                // Method of  SystemClock class, that counts the time in milliseconds
                // from when your device was booted lastly
                stopwatch.base = SystemClock.elapsedRealtime()-pauseAt
                stopwatch.start()
                start.visibility = View.INVISIBLE
                start.isEnabled = false
                pause.visibility = View.VISIBLE
                pause.isEnabled = true
                reset.visibility = View.VISIBLE
                reset.isEnabled = true
//                resume.visibility = View.INVISIBLE
//                resume.isEnabled = false
                running = true

            }
       }
        // TODO Pause & Resume Listener
        pause.setOnClickListener {
            if(running)
            {
                pauseAt = SystemClock.elapsedRealtime()-stopwatch.base
                stopwatch.stop()
                pause.text = "Resume"
                running = false
            }
            else if(!running)
            {
                stopwatch.base = SystemClock.elapsedRealtime()-pauseAt
                stopwatch.start()
                pause.text = "Pause"
                running = true
            }
//            pauseAt = SystemClock.elapsedRealtime() - stopwatch.base
//            stopwatch.stop()
//            pause.visibility = View.INVISIBLE
//            pause.isEnabled = false
//            resume.visibility = View.VISIBLE
//            resume.isEnabled = true
        }
        // TODO Reset Listener
        reset.setOnClickListener {
            stopwatch.base = SystemClock.elapsedRealtime()
            stopwatch.stop()
            pauseAt = 0
            transition.reverseTransition(500)
            stopwatch.setTextColor(Color.BLACK)
            start.visibility = View.VISIBLE
            start.isEnabled = true
            pause.visibility = View.INVISIBLE
            pause.isEnabled = false
            reset.visibility = View.INVISIBLE
            reset.isEnabled = false
//            resume.visibility = View.INVISIBLE
//            resume.isEnabled = false
            running = false
        }
    }
}



// Resume Listener
//resume.setOnClickListener {
//    stopwatch.base = SystemClock.elapsedRealtime()-pauseAt
//    stopwatch.start()
//    pause.visibility = View.VISIBLE
//    pause.isEnabled = true
//    resume.visibility = View.INVISIBLE
//    resume.isEnabled = false
//}