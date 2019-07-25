package com.zhoujiulong.screenmatch.screenmatch

import java.io.Serializable

/**
 * @author zhoujiulong
 * @createtime 2019/7/24 15:12
 *
 * dimens文件中的dimen数据项
 * 如：<dimen name="sip0">0dp</dimen>
 */
data class DimenItem(
    //生成的资源文件名称，如：sip0
    var name: String,
    //生产的资源文件名称对应的数值，如：0dp
    var value: String
) : Serializable












