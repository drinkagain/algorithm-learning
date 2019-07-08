package com.jiuxian;

import java.util.Arrays;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-07-08 21:54:34
 * *
 * @comment: 二叉堆
 * 最大堆：任何一个父节点的值，都大于或等于它左右孩子的值
 * 最小堆：任何一个父节点的值，都小于或等于它左右孩子的值
 * <p>
 * 二叉堆虽然是一个完全二叉树，但它的存储方式并不是链式存储，而是顺序存储。换句话说，
 * 二叉堆的所有节点都存储在数组中。
 *
 *         1
 *        / \
 *       3   2
 *      /\  /\
 *     6 5 7 8
 *    /\
 *   9 10
 *   [1,3,2,6,5,7,8,9,10]
 *  假设父节点的下标是parent,那么它的左孩子的下标是2*parent+1；右孩子的下标是2*parent+2.
 */
public class BinaryHeap {

    /**
     * 上浮
     * @param array
     */
    private static void upAdjust(int[] array) {
        int childIndex = array.length - 1;
        int parentIndex = (childIndex - 1) / 2;

        int temp = array[childIndex];
        while (childIndex > 0 && temp < array[parentIndex]) {
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        array[childIndex] = temp;
    }


    /**
     * 下沉
     * @param array
     * @param parentIndex
     * @param length
     */
    private static void downAdjust(int[] array, int parentIndex, int length) {
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex + 1;

        while (childIndex < length) {
            int rightChildIndex = childIndex + 1;
            if (rightChildIndex < length && array[rightChildIndex] < array[childIndex]) {
                childIndex++;
            }
            if (temp <= array[childIndex]) {
                break;
            }
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * parentIndex + 1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 构建堆
     * @param array 待调整的堆
     */
    public static void buildHeap(int[] array){
        for (int i = (array.length-2)/2;i >=0;i --){
            downAdjust(array,i,array.length);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,3,2,6,5,7,8,9,10,0};
        upAdjust(array);
        System.out.println(Arrays.toString(array));

        buildHeap(array);
        System.out.println(Arrays.toString(array));
    }

}
