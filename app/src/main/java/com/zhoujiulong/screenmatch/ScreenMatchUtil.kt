package com.zhoujiulong.screenmatch

import com.zhoujiulong.screenmatch.screenmatch.DimenItem
import com.zhoujiulong.screenmatch.screenmatch.XmlUtil
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat

/**
 * @author zhoujiulong
 * @createtime 2019/7/24 15:42
 *
 * 创建屏幕适配资源文件类
 */
object ScreenMatchUtil {

    //xml 资源文件名称
    private val mFileName: String = "dimen.xml"
    //生成的文件存放的目录，需要空文件夹或者不存在的文件夹
    private val mDirPath: String = "F:/res"
    //设计稿件中的基准屏幕宽度，如UI设计中常以 750px 进行设计
    private val mUIScreenPx = BigDecimal(750)
    //生成的资源最大值，如：设计稿件中控件可能的最大宽度为 1200px
    private val mMaxValue = 1200
    //生成的资源文件中dimen的名称开头如：dip1
    private val mDimenName = "dip"
    //基准dp，比如：360dp，这个值生成的xml文件会放到value下当作默认值
    private val mDefaultDp = BigDecimal(360)
    //默认支持的dp值，会根据默认值进行换算，如需添加其它的dip值可添加进来
    //如果有手机显示效果和UI给的偏差有点大，可以运行这个 app 在界面中找到 widthDP 值添加到该集合
    private val mDefaultDPArr by lazy {
        arrayOf(240, 300, 320, 340, 360, 380, 400, 440, 480, 520, 560, 600, 720)
    }


    public @JvmStatic
    fun main(args: Array<String>) {

        val dir = File(mDirPath)
        if (dir.exists()) {
            if (dir.isDirectory && dir.listFiles().isNotEmpty()) {
                System.out.println("${mDirPath}文件夹不为空")
                return
            }
        } else {
            dir.mkdir()
        }

        //生成基准文件
        System.out.println(
            if (XmlUtil.createDimensXmlFile(getDimenItemsByTargetDp(mDefaultDp), "$mDirPath/values/$mFileName"))
                "生成基准资源文件成功"
            else "生成基准资源文件失败"
        )

        //生成支持的资源文件
        mDefaultDPArr.forEach {
            System.out.println(
                if (XmlUtil.createDimensXmlFile(
                        getDimenItemsByTargetDp(BigDecimal(it)), "$mDirPath/values-sw${it}dp/$mFileName"
                    )
                ) "生成values-sw${it}dp资源文件成功"
                else "生成values-sw${it}dp资源文件失败"
            )
        }

        System.out.println("完成")
    }

    /**
     * @param targetDp 需要生成的资源文件对应的屏幕 dp
     */
    private fun getDimenItemsByTargetDp(targetDp: BigDecimal): List<DimenItem> {
        var value: BigDecimal
        val dimenItemList = mutableListOf<DimenItem>()
        for (i in 0..mMaxValue) {
            value = targetDp.multiply(BigDecimal(i)).divide(mUIScreenPx, 2, BigDecimal.ROUND_HALF_UP)
            dimenItemList.add(DimenItem("$mDimenName$i", "${format(value)}dp"))
        }
        return dimenItemList
    }

    /**
     * 使用NumberFormat,保留小数点后两位
     */
    private fun format(value: BigDecimal): String {
        val nf = NumberFormat.getNumberInstance()
        nf.maximumFractionDigits = 2
        nf.roundingMode = RoundingMode.HALF_UP
        nf.isGroupingUsed = false
        return nf.format(value)
    }

}




























