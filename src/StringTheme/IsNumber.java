package StringTheme;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : qizidog
 * @date : 2020-09-02 23:27
 * @description :
 * 剑指 Offer 20. 表示数值的字符串
 **/

/*
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，
 * 但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
 */
class IsNumber {
    public boolean isNumber(String s) {
        HashMap<State, HashMap<CharType, State>> transfer = new HashMap<>();

        // 初始状态及其对应的转移函数
        HashMap<CharType, State> initMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_INIT);
            put(CharType.CHAR_DOT, State.STATE_DOT_WITHOUT_INT);
            put(CharType.CHAR_SIGN, State.STATE_SIGN);
            put(CharType.CHAR_NUM, State.STATE_INT);  // 官方漏掉了这一条
        }};
        transfer.put(State.STATE_INIT, initMap);

        // 符号位状态及其对应的转移函数
        HashMap<CharType, State> signMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_DOT, State.STATE_DOT_WITHOUT_INT);
            put(CharType.CHAR_NUM, State.STATE_INT);
        }};
        transfer.put(State.STATE_SIGN, signMap);

        // 整数部分状态及其对应的转移函数
        HashMap<CharType, State> intMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_END);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_DOT, State.STATE_DOT);
            put(CharType.CHAR_NUM, State.STATE_INT);
        }};
        transfer.put(State.STATE_INT, intMap);

        // 有整数的小数点及其对应的转移函数
        HashMap<CharType, State> dotMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_END);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_NUM, State.STATE_FRACTION);
        }};
        transfer.put(State.STATE_DOT, dotMap);

        // 无整数的小数点及其对应的转移函数
        HashMap<CharType, State> dotWithoutIntMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUM, State.STATE_FRACTION);
        }};
        transfer.put(State.STATE_DOT_WITHOUT_INT, dotWithoutIntMap);

        // 小数部分及其对应的转移函数
        HashMap<CharType, State> fractionMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_END);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_NUM, State.STATE_FRACTION);
        }};
        transfer.put(State.STATE_FRACTION, fractionMap);

        // exp部分及其对应的转移函数
        HashMap<CharType, State> expMap = new HashMap<CharType, State>(){{
           put(CharType.CHAR_NUM, State.STATE_EXP_NUM);
           put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
        }};
        transfer.put(State.STATE_EXP, expMap);

        // 指数符号部分及其对应的转移函数
        HashMap<CharType, State> expSignMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUM, State.STATE_EXP_NUM);
        }};
        transfer.put(State.STATE_EXP_SIGN, expSignMap);

        // 指数数字部分及其对应的转移函数
        HashMap<CharType, State> expNumMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUM, State.STATE_EXP_NUM);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_EXP_NUM, expNumMap);

        // 结尾部分及其对应的转移函数
        HashMap<CharType, State> endMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_END, endMap);

        State state = State.STATE_INIT;
        for (char c : s.toCharArray()) {
            CharType charType = toCharType(c);
            if (charType == CharType.CHAR_ILLEGAL){
                return false;
            }
            state = transfer.get(state).get(charType);
            if (state==null){
                return false;
            }
        }

        // 如果返回值是以下5种状态则合法
        return state==State.STATE_INT || state==State.STATE_DOT
                || state==State.STATE_FRACTION || state==State.STATE_EXP_NUM
                || state==State.STATE_END;
    }

    /*
        将字符映射为CharType类型
     */
    CharType toCharType(char c){
        if (c>='0' && c<='9'){
            return CharType.CHAR_NUM;
        }else if (c=='.'){
            return CharType.CHAR_DOT;
        }else if (c==' '){
            return CharType.CHAR_SPACE;
        }else if (c=='e' || c=='E'){
            return CharType.CHAR_EXP;
        }else if (c=='+' || c=='-'){
            return CharType.CHAR_SIGN;
        }else {
            return CharType.CHAR_ILLEGAL;
        }
    }

    /*
        5种合法的字符类型和1种非法的字符类型
     */
    enum CharType{
        CHAR_NUM,
        CHAR_DOT,
        CHAR_SPACE,
        CHAR_EXP,
        CHAR_SIGN,
        CHAR_ILLEGAL
    }

    /*
        定义10种状态，其中5种是数字的合法结束状态
     */
    enum State{
        STATE_INIT,
        STATE_SIGN,
        STATE_INT,  // 合法
        STATE_DOT,  // 合法
        STATE_DOT_WITHOUT_INT,
        STATE_FRACTION,  // 合法
        STATE_EXP,
        STATE_EXP_SIGN,
        STATE_EXP_NUM,  // 合法
        STATE_END  // 合法
    }

    public static void main(String[] args) {
        IsNumber isNumber = new IsNumber();
        System.out.println(isNumber.isNumber(" .123 "));
        System.out.println(isNumber.isNumber(" -.1"));
        System.out.println(isNumber.isNumber("+12.e-3  "));
        System.out.println(isNumber.isNumber("-1E-16  "));
        System.out.println(isNumber.isNumber("0123 "));
        System.out.println("-----");
        System.out.println(isNumber.isNumber("-2.123-"));
        System.out.println(isNumber.isNumber("12e"));
        System.out.println(isNumber.isNumber("1a3.14"));
        System.out.println(isNumber.isNumber("1.2.3"));
        System.out.println(isNumber.isNumber("+-5"));
        System.out.println(isNumber.isNumber("12e+5.4"));
    }
}

