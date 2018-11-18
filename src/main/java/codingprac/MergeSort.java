package codingprac;

public class MergeSort {

    static int[] buff;

    /**
     * <pre>
     *     A = [1, 3, 9], B = [2, 9, 4]
     *     Solution is [1, 2, 3, 4, 9, 9]
     * </pre>
     *
     * @param a マージソート対象配列
     * @param n　配列のサイズ
     */
    static  void mergeSort(int[] a, int n){
        buff = new int[n];

        __mergeSort(a, 0, n - 1);//配列全体をマージソート

        buff = null;
    }

    static void __mergeSort(int[] a, int left, int right) {
        if(left < right) {
            int i;
            int center = (left + right) / 2;
            int p = 0;
            int j = 0;
            int k = left;

            __mergeSort(a, left, center);
            __mergeSort(a, center + 1, right);

            for(i = left; i <= center; i++){
                buff[p++] = a[i];
            }
            while (i <= right && j < p){
                a[k++] = (buff[j] <= a[i]) ? buff[j++] : a[i++];
            }

            while(j < p){
                a[k++] = buff[j++];
            }
        }
    }

    /**
     * 順序関係なく二つの配列をmergeする。
     * @param a merge対象１
     * @param na　merge対象１のLength
     * @param b　merge対象２
     * @param nb　merge対象２のLength
     * @return　mergeされた配列
     */
    static int[] merge(int[] a, int na, int[] b, int nb){

        int[] result = new int[na + nb];

        int pa = 0;
        int pb = 0;
        int rc = 0;

        while (pa < na)
            result[rc++] = a[pa++];
        while (pb < nb)
            result[rc++] = b[pb++];
        return result;
    }

    public static void main(String[] args){

        int[] a = {1, 3, 9};
        int[] b = {2, 9, 4};
        int[] mergedArray = merge(a, a.length, b, b.length);

        mergeSort(mergedArray, mergedArray.length);

        for(int item: mergedArray)
        System.out.print(item);
    }
}
