package com.sankuai.test.algorithm;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/1/10 15:14
 *
 * 输入一个字符串，使得字符串反转，但是需要保证单词内字母顺序不变<br/>
 * eg:输入"     I    Am  A  Student!"
 * 输出："!Student  A  mA    I     "
 */
public class reversalString {
    public static void main(String[] args) {
        String s = "     I    Am  A  Student!";
        new reversalString().stringOperation(s);
    }

    public char[] reverse(int start, int end, char[] param) {
        char param1;
        while (start < end) {
            param1 = param[start];
            param[start] = param[end];
            param[end] = param1;
            start++;
            end--;
        }
        return param;
    }

    public boolean isLetter(char param) {
        if (param > 'a' && param < 'z')
            return true;
        else if (param > 'A' && param < 'Z')
            return true;
        else
            return false;
    }

    public void stringOperation(String arg) {
        char[] charArray = arg.toCharArray();
        char[] reverseString = reverse(0, charArray.length - 1, charArray);
        int start = 0, finish;
        boolean isFirstIndex = true;
        for (int i = 0; i < reverseString.length; i++) {
            if (isLetter(reverseString[i])) {
                if (isFirstIndex) {
                    isFirstIndex = false;
                    start = i;
                }
            } else {
                if (!isFirstIndex) {
                    finish = i - 1;
                    isFirstIndex = true;
                    reverse(start, finish, reverseString);
                }
            }
        }
        System.out.println(reverseString);
    }
}
