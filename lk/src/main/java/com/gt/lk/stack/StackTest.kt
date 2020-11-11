package com.gt.lk.stack

import java.lang.RuntimeException
import java.util.*

/**
 * time 2020/6/18 0018
 * author GT
 */
object StackTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(2,1,2,4,3)
//        val temp = singleStack(array)
        val temp = circleStack(array)
//        val temp = temperDay(array)
//        println(temp.contentToString())
        val str = "1+((2+3)* 4)-5"
        val value = calculator(str)
        println(value)
    }

    /**
     * 寻找数组中下一个比自己大的值
     */
    private fun singleStack(arr:IntArray):IntArray{
        val temp = IntArray(arr.size)
        val stack = Stack<Int>()
        for(i in arr.size-1 downTo 0){
            while(!stack.isEmpty() && arr[i] >= stack.peek()){//判断栈的顶端是不是小于当前值
                stack.pop()//小于就直接丢弃
            }
            //栈为null说明后面没有比arr[i]大的数 部位空栈顶就是下一个最大的数
            temp[i] = if(stack.isEmpty()) -1 else stack.peek()
            stack.push(arr[i])
        }
        return temp
    }
    /**
     * 寻找环形数组中下一个比自己大的值
     * 将数组拼接
     * 用 i%n 来做循环 i in 0 ..2n-1
     */
    private fun circleStack(arr:IntArray):IntArray{
        val n = arr.size
        val temp = IntArray(n)
        val stack = Stack<Int>()
        for(i in 2*n-1 downTo 0){
            while(!stack.isEmpty() && arr[i%n] >= stack.peek()){//判断栈的顶端是不是小于当前值
                stack.pop()//小于就直接丢弃
            }
            //栈为null说明后面没有比arr[i]大的数 部位空栈顶就是下一个最大的数
            temp[i%n] = if(stack.isEmpty()) -1 else stack.peek()
            stack.push(arr[i%n])
        }
        return temp
    }

    /**
     * 对于每一天，你还要至少等多少天才能等到一个更暖和的气温；如果等不到那一天，填 0 。
     */
    private fun temperDay(arr:IntArray):IntArray{
        val days = IntArray(arr.size)
        val stack = Stack<Int>()
        for(i in arr.size-1 downTo 0){
            while(!stack.isEmpty() && arr[i] >= arr[stack.peek()]){
                stack.pop()
            }
            days[i] = if(stack.isEmpty()) 0 else stack.peek()-i
            //存储索引
            stack.push(i)
        }
        return days
    }

    /**
     * 输入一个表达式 计算出结果
     * 1+（（2+3）* 4）-5
     */
    private fun calculator(str:String):Int{
        val stack = Stack<String>()
        val deque = ArrayDeque<String>()

        str.map { c->
            val it = c.toString()
            when(it){
                "+", "-"->{
                    //如果是+或者-要先判断stack为空或者栈顶为（ 时才能入栈
                    if(stack.isEmpty() || stack.peek() == "("){
                        stack.push(it)
                    }else{
                        //栈顶的优先级大于等于当前操作符 需要将栈顶移除放入队列 并继续判断栈顶 然后放入
                        while(!stack.isEmpty() && stack.peek() != "("){
                            deque.add(stack.pop())
                        }
                        stack.push(it)
                    }
                }
                "*","/","x","X"->{
                    //与加减类似 */优先级高 只要栈顶不是*/就可以入栈
                    if(stack.isEmpty() || stack.peek() != it){
                        stack.push(it)
                    }else{
                        //栈顶是*/就要先出栈放入队列
                        while(!stack.isEmpty() && stack.peek() == it){
                            deque.add(stack.pop())
                        }
                        stack.push(it)
                    }
                }
                "("->{//直接入栈
                    stack.push(it)
                }
                ")"->{
                    //将从（ 到栈顶的操作符都出栈 并入到队列 并将（ 丢弃
                    while(!stack.isEmpty() && stack.peek() != "("){
                        deque.add(stack.pop())
                    }
                    if(!stack.isEmpty()) stack.pop()
                }
                in "0".."9"->{
                    deque.add(it)
                }
            }
            it
        }
        if(!stack.isEmpty()) deque.add(stack.pop())
        println(deque.toString())
        stack.clear()
        deque.map {
            when(it){
                "+"->{
                    if(stack.size < 2) throw RuntimeException("解析异常")
                    val value = Integer.parseInt(stack.pop().toString())+Integer.parseInt(stack.pop().toString())
                    stack.push(value.toString())
                }
                "-"->{
                    if(stack.size < 2) throw RuntimeException("解析异常")
                    val value1 = Integer.parseInt(stack.pop().toString())
                    val value2 = Integer.parseInt(stack.pop().toString())
                    val value = value2 - value1
                    stack.push(value.toString())
                }
                "*","x","X"->{
                    if(stack.size < 2) throw RuntimeException("解析异常")
                    val value = Integer.parseInt(stack.pop().toString()) * Integer.parseInt(stack.pop().toString())
                    stack.push(value.toString())
                }
                "/"->{
                    if(stack.size < 2) throw RuntimeException("解析异常")
                    val value1 = Integer.parseInt(stack.pop().toString())
                    val value2 = Integer.parseInt(stack.pop().toString())
                    val value = value2 / value1
                    stack.push(value.toString())
                }
                else ->{
                    stack.push(it)
                }
            }
            it
        }
        if(stack.size != 1) throw RuntimeException("解析异常")
        return stack.pop().toInt()
    }
}