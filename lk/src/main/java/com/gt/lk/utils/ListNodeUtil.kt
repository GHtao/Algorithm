package com.gt.lk.utils

import com.gt.lk.bean.ListNode
import java.lang.StringBuilder

/**
 * time 2020/11/16 0016
 * author GT
 */
object ListNodeUtil {
    /**
     * 打印链表
     */
    fun printLinkNode(linkNode: ListNode){
        var temp = linkNode
        val sb = StringBuilder()
        sb.append(temp.value)
        while(temp?.next != null){
            temp = temp.next!!
            sb.append("->${temp.value}")
        }
        println("list node:${sb}")
    }
}