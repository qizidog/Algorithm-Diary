package TreeTheme;

/**
 * @author : qizidog
 * @date : 2021-04-06 17:11
 * @description :
 * 208. 实现 Trie (前缀树)
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
 * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
 * 请你实现 Trie 类：
 *
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix，返回 true ；否则，返回 false 。
 *
 *
 * 示例：
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 *
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 *
 *
 * 提示：
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
 *
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 **/

public class Trie {
    private int pass = 0;
    private int end = 0;
    private char val;
    private Trie[] next;
    /** Initialize your data structure here. */
    public Trie() {
        next = new Trie[26];
    }

    public Trie(char val) {
        this();
        this.val = val;
        this.pass = 1;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie t = this;
        for(int i=0; i<word.length(); i++){
            Trie temp = t.next[word.charAt(i)-'a'];
            if(temp != null){
                temp.pass++;
                if(i==word.length()-1){
                    temp.end++;
                }
                t = temp;
            } else{
                temp = new Trie(word.charAt(i));
                if(i==word.length()-1){
                    temp.end++;
                }
                t.next[word.charAt(i)-'a'] = temp;
                t = temp;
            }
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie t = this;
        int i = 0;
        while(i<word.length()){
            Trie temp = t.next[word.charAt(i)-'a'];
            if(temp==null){
                return false;
            }
            t = temp;
            i++;
        }
        return t.end>0;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie t = this;
        int i = 0;
        while(i<prefix.length()){
            Trie temp = t.next[prefix.charAt(i)-'a'];
            if(temp==null){
                return false;
            }
            t = temp;
            i++;
        }
        return true;
    }

}
