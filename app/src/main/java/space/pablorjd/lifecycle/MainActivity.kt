package space.pablorjd.lifecycle

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import space.pablorjd.lifecycle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private var position:Int = 0

    private lateinit var binding:ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener {
           startActivity(Intent(this, DialogActivity::class.java))
        }

        Log.i("LifeCycle", "OnCreatedPablo")
    }

    override fun onStart() {
        super.onStart()
        mediaPlayer = MediaPlayer.create(this, R.raw.ai_2)

        Log.i("LifeCycle", "onStartPablo")
    }

    override fun onResume() {
        super.onResume()

        // acá de hacen las consultas a la base de datos
        mediaPlayer?.seekTo(position)
        mediaPlayer?.start()
        Log.i("LifeCycle", "onResumePablo")
    }

    override fun onPause() {
        // aca se paran las consultas de base de datos
        super.onPause()
        mediaPlayer?.pause()
        if ( mediaPlayer != null)
        position = mediaPlayer!!.currentPosition
        Log.i("LifeCycle", "onPausePablo")
    }

    override fun onStop() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        super.onStop()
        Log.i("LifeCycle", "onStopPablo")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("LifeCycle", "onRestartPablo")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LifeCycle", "onDestroyPablo")
    }
}