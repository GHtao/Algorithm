package com.gt.lk.every_day

import com.gt.lk.bean.TreeNode

/**
 * time 2020/10/21 0021
 * author GT
 */
object Day1021 {

    @JvmStatic
    fun main(args:Array<String>){
        val pre = intArrayOf(1,2,4,5,3,6,7)
        val inOrder = intArrayOf(4,2,5,1,6,3,7)
        val post = intArrayOf(4,5,2,6,7,3,1)
//        val node = getTreeByPreAndIn(pre,inOrder)
//        val node = getTreeByInAndPost(inOrder,post)
        val node = getTreeByPreAndPost(pre,post)
        print(node)
    }

    /**
     * 根据先序和中序获取数结构
     */
    private fun getTreeByPreAndIn(pre:IntArray,inOrder:IntArray): TreeNode?{
        val map = HashMap<Int,Int>()
        //将中序的值和索引存起来 方便方法中根据先序的值来查找在中序中的索引
        inOrder.mapIndexed { index, i ->
            map.put(i,index)
        }
        return getTreeByPreAndIn(pre,0,pre.size-1,inOrder,0,inOrder.size-1,map)
    }

    private fun getTreeByPreAndIn(pre:IntArray,preStart:Int,preEnd:Int,
                                  inOrder:IntArray,inStart:Int,inEnd:Int,map:HashMap<Int,Int>): TreeNode?{
        if(preStart > preEnd) return null
        val node = TreeNode()
        val rootValue = pre[preStart]
        node.value = rootValue
        return if(preStart == preEnd){
            node
        }else{
            val index = map[rootValue]!!
            node.left = getTreeByPreAndIn(pre,preStart+1,preStart+index-inStart,
                inOrder,inStart,index-1,map)
            node.right = getTreeByPreAndIn(pre,preStart+index-inStart+1,preEnd,
                inOrder,index+1,inEnd,map)
            node
        }
    }

    /**
     * 根据 后序和中序获取数结构
     */
    private fun getTreeByInAndPost(inOrder: IntArray,post:IntArray): TreeNode?{
        val map = HashMap<Int,Int>()
        inOrder.mapIndexed{index,value->
            map.put(value,index)
        }
        return getTreeByInAndPost(inOrder,0,inOrder.size-1,post,0,post.size-1,map)
    }

    private fun getTreeByInAndPost(inOrder: IntArray,inStart:Int,inEnd:Int,
                                   post:IntArray,postStart:Int,postEnd:Int,
                                   map: HashMap<Int, Int>): TreeNode?{
        if(postStart > postEnd) return null
        val root = TreeNode()
        root.value = post[postEnd]
        return if(postStart == postEnd){
            root
        }else{
            val index = map[root.value]!!
            println("index:$index inStart:$inStart inEnd:$inEnd postStart:$postStart postEnd:$postEnd")
            //注意获取具体叶节点的个数
            root.left = getTreeByInAndPost(inOrder,inStart,index-1,
                                            post,postStart,postStart+(index-1-inStart),
                                            map)
            root.right = getTreeByInAndPost(inOrder,index+1,inEnd,
                                            post,postEnd-(inEnd-index),postEnd-1,
                                            map)
            root
        }
    }

    /**
     * 根据前序和后序获取数结构
     * 不是满树的时候 可能获取的结果有多种
     */
    private fun getTreeByPreAndPost(pre: IntArray,post: IntArray): TreeNode?{
        val map = HashMap<Int,Int>()
        post.mapIndexed{index,value->
            map[value] = index
        }

        return getTreeByPreAndPost(pre,0,pre.size-1,post,0,post.size-1,map)
    }
    private fun getTreeByPreAndPost(pre: IntArray,preStart:Int,preEnd:Int,
                                    post: IntArray,postStart: Int,postEnd:Int,
                                    map: HashMap<Int, Int>): TreeNode?{
        if(preStart > preEnd) return null
        val root = TreeNode()
        root.value = pre[preStart]
        return if(preStart == preEnd || preStart+1==pre.size){
            root
        }else{
            val index = map[pre[preStart+1]]!!
            println("index:$index inStart:$preStart inEnd:$preEnd postStart:$postStart postEnd:$postEnd")
            root.left = getTreeByPreAndPost(pre,preStart+1,preStart+1+index-postStart,
                                            post,postStart,index,map)
            root.right= getTreeByPreAndPost(pre,preStart+1+(postEnd-index-1),preStart+2*(postEnd-index-1),
                                            post,index+1,index+(postEnd-index-1),map)
            root
        }
    }
}