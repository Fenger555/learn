package leetcode;

import java.util.*;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Fenger
 * @date 2021-01-18 16:17
 */
public class Lc020_Stack {

    private final static Map<Character, Character> SYMBOLS_MAP = new HashMap<Character, Character>(){
        {
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }
    };


    public boolean isValid(String s) {

        if (s == null || s.length() == 0) {
            return true;
        }

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (SYMBOLS_MAP.containsKey(c)) {
                stack.push(c);
            } else if (SYMBOLS_MAP.containsValue(c)) {
                if (stack.empty() || SYMBOLS_MAP.get(stack.pop()) != c) {
                    return false;
                }
            }
        }

        return stack.empty();
    }

    public static void main(String[] args) {
        String s = "([)]";

        Lc020_Stack lc020_stack = new Lc020_Stack();
        boolean valid = lc020_stack.isValid(s);
        System.out.println(valid);
    }

}
