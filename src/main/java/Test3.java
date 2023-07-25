import java.util.Stack;

/**
 * @author renxinlei
 * @version 1.0
 * description TODO
 * create date 2023/4/3 14:40
 */
public class Test3 {

    public static void main(String[] args) {
        String param = "3[a3[abc]]";
        System.out.println(new Solution().decodeString(param));
    }

    static class Solution {

        class Node {
            public Node(String str, int start, int end) {
                this.result = str;
                this.startIdx = start;
                this.endIdx = end;
            }

            String result;
            int startIdx;
            int endIdx;
        }

        public String decodeString(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }
            char[] charArr = s.toCharArray();
            StringBuilder result = new StringBuilder();
            for (int idx = 0; idx < charArr.length; idx++) {
                if (!isSpecialChar(charArr[idx]) && !isNumChar(charArr[idx])) {
                    result.append(charArr[idx]);
                } else if (isSpecialChar(charArr[idx])) {
                    int num = calNum(charArr, idx - 1);
                    Node node = buildStr(num, idx, charArr.length - 1, charArr);
                    result.append(node.result);
                    idx = node.endIdx + 1;
                }
            }
            return result.toString();
        }


        private int calNum(char[] arr, int start) {
            int result = 0;
            int count = 0;
            for (int idx = start; idx >= 0; idx--) {
                if (isNumChar(arr[idx])) {
                    result += count == 0 ? Integer.parseInt(String.valueOf(arr[idx])) : count * 10 * Integer.parseInt(String.valueOf(arr[idx]));
                    count++;
                } else {
                    break;
                }
            }
            return result;
        }

        /**
         * 判断字符是否大括号
         *
         * @return
         */
        private boolean isSpecialChar(char param) {
            return param == '[' || param == ']';
        }

        /**
         * 判断是否数字
         *
         * @param param
         * @return
         */
        private boolean isNumChar(char param) {
            return param >= '0' && param <= '9';
        }

        private boolean isNormalString(char[] charArr, int start, int end) {
            for (int idx = start; idx <= end; idx++) {
                if (isSpecialChar(charArr[idx])) {
                    return false;
                }
            }
            return true;
        }

        /**
         * 判断当前大括号范围
         *
         * @param charArr
         * @param start
         * @param end
         * @return
         */
        public int[] calIdx(char[] charArr, int start, int end) {
            Stack<Character> stack = new Stack<>();
            int startIdx = -1;
            int endIdx = -1;
            for (int idx = start; idx <= end; idx++) {
                if (charArr[idx] == '[') {
                    if (startIdx == -1) {
                        startIdx = idx;
                    }
                    stack.push(charArr[idx]);
                }
                if (charArr[idx] == ']') {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    endIdx = idx;
                    break;
                }
            }
            return new int[]{startIdx, endIdx};
        }

        public Node buildStr(int num, int start, int end, char[] param) {
            int[] idxArr = calIdx(param, start, end);
            if (isNormalString(param, idxArr[0] + 1, idxArr[1] - 1)) {
                String str = buildDupStrByNum(num, buildStr(idxArr[0] + 1, idxArr[1] - 1, param));
                return new Node(str, start, end);
            }
            StringBuilder result = new StringBuilder();
            for (int idx = idxArr[0] + 1; idx < idxArr[1]; idx++) {
                if (!isSpecialChar(param[idx]) && !isNumChar(param[idx])) {
                    result.append(param[idx]);
                } else if (isSpecialChar(param[idx])) {
                    int subNum = calNum(param, idx - 1);
                    Node node = buildStr(subNum, idx, end, param);
                    result.append(node.result);
                    idx = node.endIdx + 1;
                }
            }
            return new Node(buildDupStrByNum(num, result.toString()), start, idxArr[1]);
        }

        public String buildStr(int start, int end, char[] param) {
            StringBuilder result = new StringBuilder();
            for (int idx = start; idx <= end; idx++) {
                result.append(param[idx]);
            }
            return result.toString();
        }

        public String buildDupStrByNum(int num, String str) {
            StringBuilder result = new StringBuilder();
            for (int idx = 1; idx <= num; idx++) {
                result.append(str);
            }
            return result.toString();
        }

    }

}
