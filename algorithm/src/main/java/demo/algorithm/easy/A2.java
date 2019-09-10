package demo.algorithm.easy;
import java.util.List;

/**
 * 返回数组的峰值
 * 峰值：比前后元素都大，数组可能存在多个峰值，返回任何一个就可以...
 *
 * @author leishiguang
 * @since v1.0
 */
public class A2 {

    /**
     * 算法执行
     * @param cases 要查找的数组
     * @return 返回该数组的某一个峰值
     */
    public static Integer go(List<Integer> cases){
        boolean pre = false;
        Integer result = null;
        int s = 0;
        int m = 2;
        int length = cases.size();
        //先判断首尾
        if (cases.get(0) > cases.get(1)) {
            result = cases.get(0);
        } else if (cases.get(length - 1) > cases.get(length - m)) {
            result = cases.get(length - 1);
        } else {
            //继续判断其它的
            for (int i = 1; i < length - 1; i++) {
                if (!pre) {
                    if (cases.get(s) < cases.get(i)) {
                        pre = true;
                    }
                } else {
                    if (cases.get(i) > cases.get(m)) {
                        result = cases.get(i);
                        break;
                    } else if (cases.get(i).equals(cases.get(m))) {
                        pre = false;
                    }
                }
                m++;
                s++;
            }
        }
        return result;
    }

}
