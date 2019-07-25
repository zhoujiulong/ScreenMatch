package com.zhoujiulong.screenmatch.screenmatch

import org.xml.sax.helpers.AttributesImpl
import java.io.File
import javax.xml.transform.OutputKeys
import javax.xml.transform.sax.SAXTransformerFactory
import javax.xml.transform.stream.StreamResult

/**
 * @author zhoujiulong
 * @createtime 2019/7/24 15:15
 *
 * xml 文件操作工具类
 */

object XmlUtil {

    /**
     * 生成xml文件
     *
     * @param list        源dimens数据
     * @param outPutFilePath  目标文件输出目录
     */
    fun createDimensXmlFile(list: List<DimenItem>, outPutFilePath: String): Boolean {
        try {
            val targetFile = File(outPutFilePath)
            if (!targetFile.parentFile.exists()) targetFile.parentFile.mkdir()
            //创建SAXTransformerFactory实例
            val saxTransformerFactory = SAXTransformerFactory.newInstance() as SAXTransformerFactory
            //创建TransformerHandler实例
            val handler = saxTransformerFactory.newTransformerHandler()
            //创建Transformer实例
            val transformer = handler.transformer
            //是否自动添加额外的空白
            transformer.setOutputProperty(OutputKeys.INDENT, "yes")
            //设置字符编码
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8")
            //添加xml版本，默认也是1.0
            transformer.setOutputProperty(OutputKeys.VERSION, "1.0")
            //保存xml路径
            val result = StreamResult(targetFile)
            handler.setResult(result)
            //创建属性Attribute对象
            val attributes = AttributesImpl()
            //开始xml
            handler.startDocument()
            //换行
            handler.characters("\n".toCharArray(), 0, "\n".length)
            //写入根节点resources
            handler.startElement("", "", "resources", attributes)
            //集合大小
            val size = list.size
            for (i in 0 until size) {
                val (name, value) = list[i]
                attributes.clear()
                attributes.addAttribute("", "", "name", "", name)

                //新dimen之前，换行、缩进
                handler.characters("\n".toCharArray(), 0, "\n".length)
                handler.characters("\t".toCharArray(), 0, "\t".length)

                //开始标签对输出
                handler.startElement("", "", "dimen", attributes)
                handler.characters(value.toCharArray(), 0, value.length)
                handler.endElement("", "", "dimen")
            }
            handler.endElement("", "", "resources")
            handler.endDocument()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

}




















