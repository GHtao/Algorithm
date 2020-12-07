package com.gt.lk.every_day

import java.util.*

/**
 * time 2020/12/3 0003
 * author GT
 */
object Day1203 {
    @JvmStatic
    fun main(args: Array<String>) {
        val result = respace(arrayOf("looked","just","like","her","brother"),
            "jesslookedjustliketimherbrother")
        println("result:$result")
    }

    /**
     * 假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数
     * dictionary = ["looked","just","like","her","brother"]
     * sentence = "jesslookedjustliketimherbrother"
     * 断句后为"jess looked just like tim her brother"，共7个未识别字符
     * 输出： 7
     */
    private fun respace(dictionary:Array<String>, sentence:String ):Int {
        val n = sentence.length

        val root = Trie()
        for (word in dictionary) {
            root.insert(word)
        }

        val dp = IntArray(n + 1)
        Arrays.fill(dp, Integer.MAX_VALUE)
        dp[0] = 0
        for (i in 1 .. n) {
            dp[i] = dp[i - 1] + 1

            var curPos = root
            for (j in i downTo 1 ) {
                val t = sentence.toCharArray()[j - 1] - 'a'
                if (curPos.next[t] == null) {
                    break
                } else if (curPos.next[t]!!.isEnd) {
                    dp[i] = Math.min(dp[i], dp[j - 1])
                }
                if (dp[i] == 0) {
                    break
                }
                curPos = curPos.next[t]!!
            }
        }
        return dp[n]
    }
}

class Trie {
    var next:Array<Trie?> = Array(26){null}
    var isEnd = false

    fun insert(s:String) {
        var curPos = this

        for (i in  s.length - 1 downTo 0) {
            val t = s.toCharArray()[i] - 'a'
            if (curPos.next[t] == null) {
                curPos.next[t] = Trie()
            }
            curPos = curPos.next[t]!!
        }
        curPos.isEnd = true
    }
}