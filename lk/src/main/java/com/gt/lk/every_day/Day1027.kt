package com.gt.lk.every_day

/**
 * time 2020/10/27 0027
 * author GT
 */
object Day1027 {
    @JvmStatic
    fun main(args: Array<String>) {
        val result = myPow(2.1,3)
        println("result:$result")
    }

    /**
     * 计算x的n次方
     */
    private fun myPow(x:Double,n:Int):Double{
        if(x == 0.toDouble()) return 0.0
        if(n == 0) return 1.0
        var result = 1.0
        if(n > 0){
            for(i in 1..n){
                result *= x
            }
        }
        if(n < 0){
            for(i in 1..-n){
                result *= x
            }
            result = 1/result
        }
        return result
    }

    /**
     * 使用二进制来计算
     * x的n次方= x的1次方 * x的2次方 * x的4次方..
     * 1对应n的二进制最低位 2对应n二进制的倒数第二位...
     * Xn = X1 * X2 * X4 * X8 + ...
     */
    private fun myPow2(x:Double, n:Int):Double {
        if(x == 0.toDouble() ) return 0.0
        if(n == 1) return 1.0
        var b = n
        var base = x
        var res = 1.0
        if(b < 0) {
            base = 1 / base
            b = -b
        }
        while(b > 0) {
            if((b and  1) == 1) res *= base
            base *= base;
            b = b.shr(1)
        }
        return res
    }


    /**
     * 判断字符串是否表示数值（包括整数和小数）。
     * 例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，
     * 但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是
     *
     * 状态的转移 判断当前状态的下一个状态 直到找到状态不正确的存在 或者找到最后 都是正确的状态
     */
    private fun isNumber(s:String):Boolean {
        //存储状态的map
        val transfer = HashMap<State, Map<CharType, State>>()
        //存储初始状态的map
        val initialMap = HashMap<CharType, State>().apply {
            put(CharType.CHAR_SPACE, State.STATE_INITIAL)
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER)
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT)
            put(CharType.CHAR_SIGN, State.STATE_INT_SIGN)
        }
        transfer[State.STATE_INITIAL] = initialMap
        //存储int数值的map
        val intSignMap = HashMap<CharType, State>().apply {
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER)
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT)
        }
        transfer[State.STATE_INT_SIGN] = intSignMap
        //存储数值 小数点 空格的集合
        val integerMap = HashMap<CharType, State>().apply {
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER)
            put(CharType.CHAR_EXP, State.STATE_EXP)
            put(CharType.CHAR_POINT, State.STATE_POINT)
            put(CharType.CHAR_SPACE, State.STATE_END)
        }
        transfer[State.STATE_INTEGER] = integerMap
        //存储小数点相关的集合
        val pointMap = HashMap<CharType, State>().apply {
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION)
            put(CharType.CHAR_EXP, State.STATE_EXP)
            put(CharType.CHAR_SPACE, State.STATE_END)
        }
        transfer[State.STATE_POINT] = pointMap
        //存储小数点 没有数值的集合
        val pointWithoutIntMap = HashMap<CharType, State>().apply {
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION)
        }
        transfer[State.STATE_POINT_WITHOUT_INT] = pointWithoutIntMap
        //分数的集合
        val fractionMap = HashMap<CharType, State>().apply {
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION)
            put(CharType.CHAR_EXP, State.STATE_EXP)
            put(CharType.CHAR_SPACE, State.STATE_END)
        }
        transfer[State.STATE_FRACTION] = fractionMap
        //科学计数法的数值
        val expMap = HashMap<CharType, State>().apply {
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER)
            put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN)
        }
        transfer[State.STATE_EXP] = expMap

        val expSignMap = HashMap<CharType, State>().apply {
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER)
        }
        transfer[State.STATE_EXP_SIGN] = expSignMap

        val expNumberMap = HashMap<CharType, State>().apply {
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER)
            put(CharType.CHAR_SPACE, State.STATE_END)
        }
        transfer[State.STATE_EXP_NUMBER] = expNumberMap

        val endMap = HashMap<CharType, State>().apply {
            put(CharType.CHAR_SPACE, State.STATE_END)
        }
        transfer[State.STATE_END] = endMap

        val length = s.length
        var state = State.STATE_INITIAL

        for (i in 0 until length) {
            val type = toCharType(s.toCharArray()[i])
            if (!transfer[state]!!.containsKey(type)) {
                return false
            } else {
                state = transfer[state]!![type] ?: error("")
            }
        }
        return state == State.STATE_INTEGER || state == State.STATE_POINT
                || state == State.STATE_FRACTION || state == State.STATE_EXP_NUMBER
                || state == State.STATE_END
    }

    private fun toCharType(ch:Char):CharType {
        return if (ch in '0'..'9') {
            CharType.CHAR_NUMBER
        } else if (ch == 'e' || ch == 'E') {
            CharType.CHAR_EXP
        } else if (ch == '.') {
            CharType.CHAR_POINT
        } else if (ch == '+' || ch == '-') {
            CharType.CHAR_SIGN
        } else if (ch == ' ') {
            CharType.CHAR_SPACE
        } else {
            CharType.CHAR_ILLEGAL
        }
    }

    enum class State {
        STATE_INITIAL,
        STATE_INT_SIGN,
        STATE_INTEGER,
        STATE_POINT,
        STATE_POINT_WITHOUT_INT,
        STATE_FRACTION,
        STATE_EXP,
        STATE_EXP_SIGN,
        STATE_EXP_NUMBER,
        STATE_END,
    }

    enum class CharType {
        CHAR_NUMBER,
        CHAR_EXP,
        CHAR_POINT,
        CHAR_SIGN,
        CHAR_SPACE,
        CHAR_ILLEGAL,
    }
}