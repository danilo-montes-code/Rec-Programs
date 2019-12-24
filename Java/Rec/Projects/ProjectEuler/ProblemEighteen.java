/* By starting at the top of the triangle below and moving to adjacent numbers on the row below,
the maximum total from top to bottom is 23.

3
7 4
2 4 6
8 5 9 3

That is, 3 + 7 + 4 + 9 = 23.

Find the maximum total from top to bottom of the triangle below:

75
95 64
17 47 82
18 35 87 10
20 04 82 47 65
19 01 23 75 03 34
88 02 77 73 07 63 67
99 65 04 28 06 16 70 92
41 41 26 56 83 40 80 70 33
41 48 72 33 47 32 37 16 94 29
53 71 44 65 25 43 91 52 97 51 14
70 11 33 28 77 73 17 78 39 68 17 57
91 71 52 38 17 14 91 43 58 50 27 29 48
63 66 04 68 89 53 67 30 73 16 69 87 40 31
04 62 98 27 23 09 70 98 73 93 38 53 60 04 23

NOTE: As there are only 16384 routes, it is possible to solve this problem by trying every route.
However, Problem 67, is the same challenge with a triangle containing one-hundred rows;
it cannot be solved by brute force, and requires a clever method! ;o) */
//
public class ProblemEighteen {
    static int total = 0;
    public static void main(String[] args) {
        int[] r1 = {75};
        int[] r2 = {95, 64};
        int[] r3 = {17, 47, 82};
        int[] r4 = {18, 35, 87, 10};
        int[] r5 = {20, 4, 82, 47, 65};
        int[] r6 = {19, 1, 23, 75, 3, 34};
        int[] r7 = {88, 2, 77, 73, 7, 63, 67};
        int[] r8 = {99, 65, 4, 28, 6, 16, 70, 92};
        int[] r9 = {41, 41, 26, 56, 83, 40, 80, 70, 33};
        int[] r10 = {41, 48, 72, 33, 47, 32, 37, 16, 94, 29};
        int[] r11 = {53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14};
        int[] r12 = {70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57};
        int[] r13 = {91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48};
        int[] r14 = {63, 66, 4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31};
        int[] r15 = {4, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 4, 23};
        int[][] rows = {r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15};
        total = rows[0][0];
        int index = 0;
        for (int x = 0; x < 14; x++) {
            index = getIndex(rows[x+1], total, index);
        }
        System.out.println(total);
    }
    // Every space matches up with that same index plus one in the next row
    static int getIndex(int[] row2, long initialTotal, int initialIndex) {
        if (initialTotal + row2[initialIndex] > initialTotal + row2[initialIndex+1]) {
            total += row2[initialIndex];
            System.out.println(row2[initialIndex]);
            return initialIndex;
        } else {
            total += row2[initialIndex+1];
            System.out.println(row2[initialIndex+1]);
            return initialIndex + 1;
        }
    }
}