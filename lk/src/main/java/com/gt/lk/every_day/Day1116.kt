package com.gt.lk.every_day

import com.gt.lk.bean.ListNode
import java.lang.StringBuilder
import java.util.*

/**
 * time 2020/11/16 0016
 * author GT
 */
object Day1116 {
    @JvmStatic
    fun main(args: Array<String>) {
//        val result = getLeastNumbers(intArrayOf(0,1,2,1),2)
//        println("result:${result.contentToString()}")
        val head = ListNode().apply {
            value = 1
            next = ListNode().apply {
                value = 2
                next = ListNode().apply {
                    value = 3
                    next = ListNode().apply {
                        value = 4
                        next = ListNode().apply {
                            value = 5
                        }
                    }
                }
            }
        }
//        val array = reversePrint(head)
//        println("result:${array.contentToString()}")

//        val result = getKthFromEnd(head,2)
//        ListNodeUtil.printLinkNode(result)

//        val result = replaceSpace("we are happy")
//        println("result:$result")

        val result = reverseLeftWords("abcdefg",2)
        println("result:$result")
    }

    /**
     * 输入整数数组 arr ，找出其中最小的 k 个数。
     * 例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     */
    private fun getLeastNumbers(array:IntArray,key:Int):IntArray{
        val temp = IntArray(key)
        fastSort(array,0,array.size-1)
        for(i in 0 until key){
            temp[i] = array[i]
        }
        return temp
    }
    private fun fastSort(array:IntArray,l:Int,h:Int){
        if(l >= h) return
        var li = l
        var hi = h
        val temp = array[li]
        while (li < hi){
            while(li < hi && array[hi] >= temp) hi--
            while(li < hi && array[li] <= temp) li++
            if(li<hi){
                val t = array[li]
                array[li] = array[hi]
                array[hi] = t
            }
        }
        array[l] = array[li]
        array[li] = temp
        fastSort(array,l,li)
        fastSort(array,li+1,h)
    }

    /**
     * 输入一个链表，输出该链表中倒数第k个节点。
     * 为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
     * 例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。
     * 这个链表的倒数第3个节点是值为4的节点
     */
    private fun getKthFromEnd(head:ListNode, k:Int):ListNode{
        //双指针
        var former = head
        var latter = head
        var i = 0
        while(former?.next != null){
            former = former.next!!
            if(i < k) i++
            if(i == k){
                latter = latter.next!!
            }
        }
        return latter
    }

    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"
     */
    private fun replaceSpace(str:String):String{
        val sb = StringBuilder()
        val charArray = str.toCharArray()
        charArray.forEach {  c ->
            if(c == ' '){
               sb.append("%20")
            }else{
                sb.append(c)
            }
        }
        return sb.toString()
    }

    /**
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     * 可以使用栈结果 先入后出
     * 如果返回的是链表头 就需要反转链表了
     */
    private fun reversePrint(head:ListNode):IntArray{
        val stack = Stack<Int>()
        var result:ListNode? = head
        var next:ListNode? = head
        var pre:ListNode? = null
        var size = 0
        //反转链表
        while(next != null){
            val temp = next.next

            result = next
            result.next = pre
            pre = result

            next = temp
            size++
        }
        val array = IntArray(size)
        size = 0
        while (result != null){
            array[size++] = result.value
            result = result.next
        }
        return array
    }

    /**
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
     * 请定义一个函数实现字符串左旋转操作的功能。
     * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"
     */
    private fun reverseLeftWords(str:String,n:Int):String{
        val result = str.substring(0,n)
        return str.substring(2,str.length)+result
    }
}