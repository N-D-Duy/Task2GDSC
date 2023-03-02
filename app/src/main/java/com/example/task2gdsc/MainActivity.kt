package com.example.task2gdsc

import com.example.task2gdsc.R
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import com.example.task2gdsc.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false
    private lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, Menu1Fragment()).commit()



    }

    override fun onResume() {
        val color = getRandomColor()
        binding.tvMain.setTextColor(color)
        Log.e("current color", color.toString())
        Log.e("life cycle", "onResume")
        super.onResume()
    }

    private fun getRandomColor():Int{
        val random = Random
        val red = random.nextInt(255-0+1)+0
        val blue = random.nextInt(255-0+1)+0
        val green = random.nextInt(255-0+1)+0

        return Color.argb(255, red, green, blue)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putInt("color", binding.tvMain.currentTextColor)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        val color = savedInstanceState.getInt("color")
        binding.tvMain.setTextColor(color)
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Nhấn 1 lần nữa để thoát", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.menu1 -> {
                fragment = Menu1Fragment()
            }
            R.id.menu2 -> {
                fragment = Menu2Fragment()
            }
        }
        if(fragment != null){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, fragment).commit()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}