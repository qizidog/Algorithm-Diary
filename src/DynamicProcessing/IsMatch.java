package DynamicProcessing;

/**
 * @author : qizidog
 * @date : 2020-09-25 10:20
 * @description :
 **/

public class IsMatch {
    public boolean isMatch(String s, String p) {
        // 不考虑null值的三种特殊情况
        if("".equals(s) && "".equals(p)) return true;
        if("".equals(s) || "".equals(p)) return false;
        // if(s.length()<p.length()) return false;
        boolean[] result = new boolean[1];
        backtracking(s, p, result);
        return result[0];

    }

    public void backtracking(String s, String p, boolean[] result){
        // 终止条件
        if(p.length()==1){
            if("*".equals(p) || s.equals(p)){
                result[0] = true;
            }else if("?".equals(p) && s.length()==1){
                result[0] = true;
            }
            return;
        }
        if (s.length()==1){
            boolean ok = true;
            for (int j=0; j<p.length(); j++){
                if (p.charAt(j)=='*'){
                    continue;
                }else if(p.charAt(j)==s.charAt(0) || p.charAt(j)=='?'){
                    // 检查剩下的是不是都为*
                    for(int k=0; k<p.substring(j+1).length(); k++){
                        if (p.substring(j+1).charAt(k)!='*'){
                            ok = false;
                            break;
                        }
                    }
                }else{
                    ok = false;
                }
            }
            result[0] = ok;
            return;
        }

        char pattern = p.charAt(0);
        String subPattern = p.substring(1);
        if('?'==pattern || s.charAt(0)==pattern){
            // continue;
            backtracking(s.substring(1), subPattern, result);
        }else if('*'==pattern){
            for(int i=0; i<s.length(); i++){
                backtracking(s.substring(i), subPattern, result);
            }
        }else{
            result[0] = false;
        }
    }

    public static void main(String[] args) {
        IsMatch isMatch = new IsMatch();
        System.out.println(isMatch.isMatch("etcode", "*e*t?d*"));
        // System.out.println(isMatch.isMatch("f", "*fee"));
        // System.out.println("1".substring(1));
    }
}
