package SortTheme;

import java.util.Arrays;

/**
 * @author : qizidog
 * @date : 2021-01-22 23:13
 * @description :
 * 堆排序
 * 时间复杂度O(NlogN)，空间复杂度O(1)，不稳定
 **/

public class HeapSort {

    public static void main(String[] args){
        int[] arr = new int[]{7,2,1,7,3,4,12,0,5,9,6,8,6};
        arr = sort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static int[] sort(int[] arr){
        MyHeap myHeap = new MyHeap(arr);
        return myHeap.sort();
    }

    private static class MyHeap{  // 大顶堆
        private int[] heap;
        private int size;

        public MyHeap(int[] arr) {
            size = 0;
            heap = new int[arr.length];
            for (int e : arr) {
                insert(e);
            }
        }

        // （将最后一个元素）向上调整
        private void adjustUp(){
            int curIndex = size-1;
            while (heap[curIndex]>heap[(curIndex-1)/2]){
                swap(heap, curIndex, (curIndex-1)/2);
                curIndex = (curIndex-1)/2;
            }
        }

        // （将第一个元素）向下调整
        private void adjustDown(){
            int curIndex = 0;
            int leftChildIndex = (curIndex<<1)+1;
            while (leftChildIndex<size){
                if (leftChildIndex+1<size){  // 如果有右孩子
                    int bigChildIndex = heap[leftChildIndex]>=heap[leftChildIndex+1]?leftChildIndex:leftChildIndex+1;
                    if (heap[curIndex]>=heap[bigChildIndex])
                        break;
                    swap(heap, curIndex, bigChildIndex);
                    curIndex = bigChildIndex;
                    leftChildIndex = (curIndex<<1)+1;
                }else{  // 如果没有右孩子
                    if (heap[curIndex]>=heap[leftChildIndex])
                        break;
                    swap(heap, curIndex, leftChildIndex);
                    curIndex = leftChildIndex;
                    leftChildIndex = (curIndex<<1)+1;
                }
            }
        }

        // 插入一个数
        public void insert(int e){
            if (size>=heap.length) throw new IndexOutOfBoundsException("超出堆上限");
            heap[size++] = e;
            adjustUp();
        }

        // 弹出一个数
        public int pop(){
            if (size<=0) throw new IndexOutOfBoundsException("堆元素为空，无法弹出");
            swap(heap, 0, --size);
            adjustDown();

            return heap[size];
        }

        public int[] sort(){
            int[] clone1;
            int[] clone2 = heap.clone();  // 用来保存堆的副本
            int length = size;
            for (int i=0; i<length; i++) {
                this.pop();
            }
            clone1 = heap;
            heap = clone2;
            return clone1;
        }

        private static void swap(int[] arr, int i, int j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        @Override
        public String toString() {
            return Arrays.toString(heap);
        }
    }
}
