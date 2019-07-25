package com.zhoujiulong.screenmatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvMsg.text = getScreenParams()
    }

    fun getScreenParams(): String {
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val heightPixels = dm.heightPixels
        val widthPixels = dm.widthPixels
        val xdpi = dm.xdpi
        val ydpi = dm.ydpi
        val densityDpi = dm.densityDpi
        val density = dm.density
        val scaledDensity = dm.scaledDensity
        val heightDP = heightPixels / density
        //在 ScreenMatchUtil 中用到 默认支持的dp值
        val widthDP = widthPixels / density

        val str = StringBuilder("heightPixels: ${heightPixels}px")
        str.append("\nwidthPixels: ${widthPixels}px")
        str.append("\nxdpi: ${xdpi}dpi")
        str.append("\nydpi: ${ydpi}dpi")
        str.append("\ndensityDpi: ${densityDpi}dpi")
        str.append("\ndensity: $density")
        str.append("\nscaledDensity: $scaledDensity")
        str.append("\nheightDP: ${heightDP}dp")
        str.append("\nwidthDP: ${widthDP}dp")
        return str.toString()
    }

}















