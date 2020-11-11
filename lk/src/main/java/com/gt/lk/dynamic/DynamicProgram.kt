package com.gt.lk.dynamic

/**
 * time 2020/6/9 0009
 * author GT
 * 动态规划问题
 * 动态规划问题的一般形式就是求最值
 * 存在「重叠子问题」
 *
 * 但凡遇到需要递归的问题，最好都画出递归树，这对你分析算法的复杂度，寻找算法低效的原因都有巨大帮助
 *
 *  1、确定 base case，这个很简单，显然目标金额 amount 为 0 时算法返回 0，因为不需要任何硬币就已经凑出目标金额了。
    2、确定「状态」，也就是原问题和子问题中会变化的变量。由于硬币数量无限，硬币的面额也是题目给定的，
        只有目标金额会不断地向 base case 靠近，所以唯一的「状态」就是目标金额 amount。
    3、确定「选择」，也就是导致「状态」产生变化的行为。目标金额为什么变化呢，因为你在选择硬币，你每选择一枚硬币，
        就相当于减少了目标金额。所以说所有硬币的面值，就是你的「选择」。
    4、明确 dp 函数/数组的定义

    计算机解决问题其实没有任何奇技淫巧，它唯一的解决办法就是穷举，穷举所有可能性。
        算法设计无非就是先思考“如何穷举”，然后再追求“如何聪明地穷举”。
    列出动态转移方程，就是在解决“如何穷举”的问题。之所以说它难，一是因为很多穷举需要递归实现，
        二是因为有的问题本身的解空间复杂，不那么容易穷举完整。
    备忘录、DP table 就是在追求“如何聪明地穷举”。用空间换时间的思路，是降低时间复杂度的不二法门，除此之外，试问，还能玩出啥花活？
 */
object DynamicProgram{
    @JvmStatic
    fun main(args: Array<String>) {
//        val value = fib2(20)
//        val map = HashMap<Int,Int>()
//        val value = coin(intArrayOf(1,3,5),11,map)
//        val array = intArrayOf(-3,4,-1,-2,-6,1,4)
//        val value = maxLengthGain(array)
//        val value = maxSubNum(array)

//        val value = packetZeroAndOne(3,4, intArrayOf(2,1,3), intArrayOf(4,2,3))
//        val value = canPattern(intArrayOf(1,2,3,6))
//        val value = coinChange(5, intArrayOf(1,2,3))
//        val value = matchStr("horse","ros",4,2)
//        val value = matchStrDp("horse","ros")
//        val value = eggDrop(6,2)
//        val value = maxCoins(intArrayOf(3,1,5,8))
        val str1 = "bxab"
        val str2 = "badce"
//        val value = lcs(str1,str2,str1.length-1,str2.length-1)
//        val value = lcs2(str1,str2)
        val value = palindrome(str1)
        println(value)
    }

    /**
     * 斐波那契数列 0 1 1 2 3 5 ...
     * 递归求解 时间复杂度 就是用子问题个数乘以解决一个子问题需要的时间
     *  二叉树的节点个数就是子问题数 二叉树是2^n指数级别
     *  这个方法存在重复计算的问题
     *  自顶向下
     */
    private fun fib(n:Int):Int{
        if(n == 1 || n == 2) return 1
        return fib(n-1) + fib(n-2)
    }

    /**
     * 自底向上
     * 其实并不需要那么长的一个 DP table 来存储所有的状态，只要想办法存储之前的两个状态就行了
     */
    private fun fib2(n:Int):Int{
        if(n == 1 || n == 2) return 1
        var pre = 1
        var curr = 1
        for (i in 3 ..n){
            val sum = curr + pre
            pre = curr
            curr = sum
        }
        return curr
    }

    /**
     * 要符合「最优子结构」，子问题间必须互相独立
     * 比如说，假设你考试，每门科目的成绩都是互相独立的。
     * 你的原问题是考出最高的总成绩，那么你的子问题就是要把语文考到最高，数学考到最高…… 为了每门课考到最高，
     * 你要把每门课相应的选择题分数拿到最高，填空题分数拿到最高…… 当然，最终就是你每门课都是满分，这就是最高的总成绩。
     * 得到了正确的结果：最高的总成绩就是总分。因为这个过程符合最优子结构，“每门科目考到最高”这些子问题是互相独立，互不干扰的。
     *
     * 但是，如果加一个条件：你的语文成绩和数学成绩会互相制约，数学分数高，语文分数就会降低，反之亦然。
     * 这样的话，显然你能考到的最高总成绩就达不到总分了，按刚才那个思路就会得到错误的结果。
     * 因为子问题并不独立，语文数学成绩无法同时最优，所以最优子结构被破坏。
     */
    private fun coin(coins:IntArray,mount:Int,map:HashMap<Int,Int>):Int{
        if(mount == 0) return 0
        if(mount<0) return -1
        //存在计算过的数据就直接返回 优化
        if(map.containsKey(mount)) return map[mount]!!
        var min = Int.MAX_VALUE
        for(i in coins){
            val num = coin(coins,mount-i,map)
            if(num == -1) continue
            min = Math.min(min,num+1)
        }
        //存储已经计算过的数据
        map[mount] = if(min == Int.MAX_VALUE) -1 else min
        return map[mount]!!
    }

    /**
     * 最长递增子序列（子串是连续的）
     * 数学归纳法
     */
    private fun maxLengthGain(array:IntArray):Int{
        val arrLen = IntArray(array.size)
        var maxLen = 1
        arrLen.fill(1)
        for(i in array.indices){
            for(j in 0 .. i){
                if(array[j] < array[i]){
                    arrLen[i] = Math.max(arrLen[i],arrLen[j]+1)
                    if(maxLen < arrLen[i]){
                        maxLen = arrLen[i]
                    }
                }
            }
        }
        return maxLen
    }

    /**
     * 最大和的连续子数组
     */
    private fun maxSubNum(array:IntArray):Int{
        var pre = array[0]
        var curr = 0
        var maxNum = Int.MIN_VALUE
        for(i in 1 until array.size){
            curr = Math.max(array[i],pre+array[i])
            pre = curr
            if(maxNum<curr){
                maxNum = curr
            }
        }
        return maxNum
    }

    /**
     * 背包装重量问题
     * num 数量
     * weight 重量
     * weightArr 要装入物品的重量数组
     * valueArr 物品的价值
     */
    private fun packetZeroAndOne(num:Int,weight:Int,weightArr:IntArray,valueArr:IntArray):Int{
        // 3 4   2 1 3  4 2 3
        val dp = Array(num+1) { Array(weight+1){0} }
        for(i in 1 .. num){
            for(j in 1 .. weight){
                if(j - weightArr[i-1]<0){
                    //重量不足 只能不装入背包 保存之前的结果
                    dp[i][j] = dp[i-1][j]
                }else{
                    dp[i][j] = Math.max(dp[i-1][j-weightArr[i-1]] + valueArr[i-1],dp[i-1][j])
                }
            }
        }
        return dp[num][weight]
    }

    /**
     * 将一个数组分成两个子集 并且和相等
     */
    private fun canPattern(array:IntArray):Boolean{
        var num = array.sum()
        if(num % 2 != 0) return false //和不能被2整除 肯定不能分开
        num /= 2 //子集的和
        val dp = Array(array.size+1){Array(num+1){false} }
        for(i in 1 .. array.size){
            for(j in 1 .. num){
                when {
                    j - array[i-1] < 0 -> {
                        //不能装入了
                        dp[i][j] = dp[i-1][j]//去之前的结果
                    }
                    j == array[i-1] -> {
                        dp[i][j] = true
                    }else -> {
                        dp[i][j] = dp[i-1][j] || dp[i-1][j-array[i-1]]
                    }
                }
            }
        }
        return dp[array.size][num]
    }

    /**
     * 硬币交换 集合中的硬币组合加起来等于coins
     * 返回一共的组合数
     */
    private fun coinChange(coins:Int,array:IntArray):Int{
        val dp = Array(array.size+1){Array(coins+1){0}}
        for(i in 0..array.size){
            dp[i][0] = 1
        }
        for(i in 1..array.size){
            for(j in 1 ..coins){
                when {
                    j - array[i-1] < 0 -> {
                        dp[i][j] = dp[i-1][j]
                    }else -> {
                        dp[i][j] = dp[i-1][j] + dp[i][j-array[i-1]]
                    }
                }
            }
        }
        return dp[array.size][coins]
    }

    /**
     * 将字符串1 变成字符串2
     * 只能有 增 删 替换三种操作
     * 需要最少的步骤
     * 双指针从后往前找
     */
    private fun matchStr(str1:String,str2:String,len1:Int,len2:Int):Int{
        if(len1 == -1) return len2 +1
        if(len2 == -1) return len1 +1
        return if(str1[len1] == str2[len2]){
            matchStr(str1,str2,len1-1,len2-1)
        }else{
            Math.min(Math.min(
                matchStr(str1,str2,len1,len2-1) +1 //增
                , matchStr(str1,str2,len1-1,len2) +1)//删
                , matchStr(str1,str2,len1-1,len2-1) +1) //替换
        }
    }

    /**
     * 两个字符串的最小匹配距离
     * dp[i][j]表示 i 和 j的最小匹配距离
     */
    private fun matchStrDp(str1:String,str2:String):Int{
        val m = str1.length
        val n = str2.length
        val dp = Array(m+1){Array(n+1){0} }
        for(i in 1 .. m) dp[i][0] = i
        for(i in 1 .. n) dp[0][i] = i
        for(i in 1 .. m){
            for(j in 1 .. n){
                if(str2[j-1] == str1[i-1]){
                    dp[i][j] = dp[i-1][j-1]
                }else{
                    dp[i][j]= Math.min(Math.min(
                        dp[i-1][j]+1,//
                        dp[i][j-1]+1),//
                        dp[i-1][j-1]+1)//替换
                }
            }
        }
        return dp[m][n]
    }

    /**
     * 高楼扔鸡蛋
     * 最坏情况下，你至少要扔几次鸡蛋
     * n 楼层 k鸡蛋
     */
    private fun eggDrop(n:Int,k:Int):Int{
        if(n == 0) return 0 //0层楼 不需要鸡蛋0
        if(k == 1) return n //1个鸡蛋 每层试一下 需要试n个楼层
        var res = Int.MAX_VALUE
        for(i in 1 ..n){
            res = Math.min(res,
                Math.max(eggDrop(i-1,k-1), eggDrop(n-i,k))+1)
        }
        return res
    }

    /**
     * 戳气球
     * [3,1,5,8]
     * 求戳破任意一个气球和相邻的乘积的最大值
     */
    private fun maxCoins(array:IntArray):Int{
        //将数组扩展头尾
        val n = array.size
        val nums = IntArray(n+2)
        nums[0] = 1
        nums[nums.size-1] = 1
        array.mapIndexed { index, i ->
            nums[index+1] = i
        }
        // base case 已经都被初始化为 0
        val dp = Array(n+2){Array(n+2){0} }
        //从下往上 从左到右遍历
        for(i in n downTo  0){
            for(j in i+1 until  n+2){
                for(k in i+1 until j){
                    dp[i][j] = Math.max(dp[i][j],
                    dp[i][k]+dp[k][j]+nums[i]*nums[k]*nums[j])
                }
            }
        }
        return dp[0][n+1]
    }

    /**
     * 寻找公共的子串最大长度
     */
    private fun lcs(str1:String,str2:String,index1:Int,index2:Int):Int{
        if(index1 == -1) return 0
        if(index2 == -1) return 0
        return if(str1[index1] == str2[index2]){
            lcs(str1,str2,index1-1,index2-1) +1
        }else{
            Math.max(
                lcs(str1,str2,index1,index2-1)
                , lcs(str1,str2,index1-1,index2)
            )
        }
    }

    /**
     * 动态规划的方法
     */
    private fun lcs2(str1:String,str2:String):Int{
        val index1 = str1.length
        val index2 = str2.length
        val dp = Array(index1+1){Array(index2+1){0} }
        for(i in 1 ..  index1){
            for(j in 1 .. index2){
                if(str1[i-1] == str2[j-1]){
                    dp[i][j] = dp[i-1][j-1]+1
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1])
                }
            }
        }
        return dp[index1][index2]
    }

    /**
     * 查找最长回文子串
     */
    private fun palindrome(str:String):Int{
        //状态
        //选择
        //状态转移
        //base case
        //dp
        val n = str.length
        val dp = Array(n){Array(n){0}}
        for(i in 0 until  n){
            dp[i][i] = 1
        }
        for(i in n-1 downTo 0){
            for(j in i+1 until  n){
                if(str[i] == str[j]){
                    dp[i][j] = dp[i+1][j-1]+2
                }else{
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1])
                }
            }
        }
        return dp[0][n-1]
    }
}