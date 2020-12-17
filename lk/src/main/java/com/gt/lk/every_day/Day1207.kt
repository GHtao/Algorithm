package com.gt.lk.every_day

import com.gt.lk.bean.ListNode
import com.gt.lk.utils.ListNodeUtil
import java.security.interfaces.RSAKey
import java.util.*
import kotlin.collections.HashSet

/**
 * time 2020/12/7 0007
 * author GT
 */
object Day1207 {
    @JvmStatic
    fun main(args: Array<String>) {
//        val result = smallestK(intArrayOf(1,3,5,7,2,4,6,8),8)
//        println("result:${result.contentToString()}")
        val root = ListNode().apply {
            value = 3
            next = ListNode().apply {
                value = 5
                next = ListNode().apply {
                    value = 8
                    next = ListNode().apply {
                        value = 5
                        next = ListNode().apply {
                            value = 10
                            next = ListNode().apply {
                                value = 2
                                next = ListNode().apply {
                                    value = 1
                                }
                            }
                        }
                    }
                }
            }
        }
//        val result = partition(root,5)
//        ListNodeUtil.printLinkNode(result!!)
        val result = pondSizes(arrayOf(intArrayOf(0,2,1,0),
            intArrayOf(0,1,0,1),
            intArrayOf(1,1,0,1),
            intArrayOf(0,1,0,1)))
        println("result:${result.toIntArray().contentToString()}")
    }

    /**
     * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
     * 先快速排序 然后再返回前k个数
     * arr = [1,3,5,7,2,4,6,8], k = 4
     */
    private fun smallestK(array:IntArray,k:Int):IntArray{
        val result = IntArray(k)
        quick(array,0,array.size-1)
        for(i in 0 until k){
            result[i] = array[i]
        }
        return result
    }

    private fun quick(array:IntArray,low:Int,high:Int){
        var l = low
        var h = high
        if(l >= h) return
        val base = array[low]
        while(l<h){
            while(l<h && array[h] >= base) h--
            while(l<h && array[l] <= base) l++
            if(l<h){
                val temp = array[l]
                array[l] = array[h]
                array[h] = temp
            }
        }
        array[low] = array[l]
        array[l] = base
        quick(array,low,l)
        quick(array,l+1,high)
    }

    /**
     * 使得所有小于 x 的节点排在大于或等于 x 的节点之前。
     * 如果链表中包含 x，x 只需出现在小于 x 的元素之后(如下所示)。
     * 分割元素 x 只需处于“右半部分”即可，其不需要被置于左右两部分之间
     * 输入: head = 3->5->8->5->10->2->1, x = 5
     * 输出: 3->1->2->10->5->5->8
     */
    private fun partition(head:ListNode?,x:Int):ListNode?{
        var lx = ListNode()
        var rx = ListNode()
        val lTemp = lx
        val rTemp = rx
        var root = head
        while (root != null){
            val temp = root.next
            if(root.value < x){
                lx.next = root
                lx = lx.next!!
            }else{
                rx.next = root
                rx = rx.next!!
            }
            root.next = null
            root = temp
        }
        lx.next = rTemp.next
        return lTemp.next
    }

    /**
     * 你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。
     * 若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。
     * 池塘的大小是指相连接的水域的个数。
     * 编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序
     *
     * 输入：
     * [
     * [0,2,1,0],
     * [0,1,0,1],
     * [1,1,0,1],
     * [0,1,0,1]
     * ]
     * 输出： [1,2,4]
     */
    private fun pondSizes(land:Array<IntArray>):List<Int>{
        val hashSet = HashSet<Int>()
        for(i in land.indices){
            for(j in land[i].indices){
                if(land[i][j] == 0){
                    hashSet.add(dfs(land,i,j))
                }
            }
        }
        return hashSet.sorted()
    }
    private fun dfs(land: Array<IntArray>, i:Int, j:Int):Int{
        if(i<0 || j<0 || i>=land.size || j>=land[i].size || land[i][j] != 0) return 0
        land[i][j] = 1
        var temp = 1
        temp += dfs(land,i-1,j-1)
        temp += dfs(land,i-1,j)
        temp += dfs(land,i-1,j+1)
        temp += dfs(land,i,j+1)
        temp += dfs(land,i+1,j+1)
        temp += dfs(land,i+1,j)
        temp += dfs(land,i+1,j-1)
        temp += dfs(land,i,j-1)
        return temp
    }
}