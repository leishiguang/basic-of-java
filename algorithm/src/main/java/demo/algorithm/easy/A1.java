package demo.algorithm.easy;

/**
 * 在一个有序但元素可能重复的数组中，输入一个目标值，返回这个目标值的左边界和右边界。[1,1,2,2,2,2,2,4,5]，得到2的左右边界
 *
 * @author leishiguang
 * @since v1.0
 */
public class A1 {

    /**
     * 算法执行
     *
     * @param arrays 要查找的数组
     * @param target 要查找的目标值
     * @return 第一个值是左边界，第二个值是右边界
     */
    public static Integer[] go(Integer[] arrays, Integer target) {
        Integer first = null;
        Integer second = null;
        boolean isFirstAlready = false;
        for(int i = 0;i < arrays.length; i++){
            if(isFirstAlready){
               if(arrays[i] > target){
                   second = i - 1;
                   break;
               }
            }else{
                if(arrays[i].equals(target)){
                    first = i;
                    isFirstAlready = true;
                }
            }
        }
        return new Integer[]{first,second};
    }
}
