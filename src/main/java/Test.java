
/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/6/16 16:31
 */
public class Test {
    /**
     * 1，2，3，4，5，6
     * 4，5，6，1，2，3
     */
    public int solution(int[] param) {
        if (param == null || param.length < 1) {
            return -1;
        }
        int left = 0;
        int right = param.length - 1;
        int maxNumIdx = -1;
        int maxVal = Integer.MIN_VALUE;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (param[left] <= param[mid]) {
                left = mid + 1;
                maxVal = Math.max(maxVal, param[mid]);
                if (maxVal == param[mid]) {
                    maxNumIdx = mid;
                }
            } else {
                right = mid - 1;
            }
        }
        return maxNumIdx;
    }

    public static void main(String[] args) {
        int[] param = new int[]{4, 5, 6, 7, 1, 2, 3};
        System.out.println(new Test().solution(param));
    }

}