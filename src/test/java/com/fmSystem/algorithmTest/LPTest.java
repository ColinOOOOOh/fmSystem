package com.fmSystem.algorithmTest;


import Jama.Matrix;
import com.fmSystem.Algorithm.LPBasedLayout.Impl.ProfitBasedConstriant;
import com.fmSystem.Algorithm.LPBasedLayout.Impl.ProfitBasedObjective;
import com.fmSystem.Algorithm.LPBasedLayout.Impl.ProfitBasedResult;
import com.fmSystem.Algorithm.LPBasedLayout.Impl.SimplexMethodSolver;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by 74551 on 2017/5/15.
 */
public class LPTest {
    @Test
    public void zerosTest(){
        SimplexMethodSolver simplexMethodSolver = new SimplexMethodSolver();
        double doubles1[][] = {{1,2,3,1},{1,4,5,6,}};
        double doubles2[][] = {{10,20,30},{40,50,60}};
        Matrix m1 = new Matrix(doubles1);
        Matrix m2 = new Matrix(doubles2);
        Matrix m3 = simplexMethodSolver.append(m1, m2, "Down");
        m3.print(5, 1);
    }
    @Test
    public void newMatrixTest(){
        Matrix t1 = new Matrix(1,1,1);
        t1.print(5,1);
    }

    @Test
    public void solveTest(){
        SimplexMethodSolver simplexMethodSolver = new SimplexMethodSolver();
        ProfitBasedObjective profitBasedObjective = new ProfitBasedObjective(null, new Matrix(3,1,1));
        ProfitBasedConstriant profitBasedConstriant = new ProfitBasedConstriant(new Matrix(2,3,2), new Matrix(2, 1 , 3));
        simplexMethodSolver.solve(profitBasedObjective, profitBasedConstriant);
    }

    @Test
    public void minTest(){
        SimplexMethodSolver simplexMethodSolver = new SimplexMethodSolver();
        double doubles1[][] = {{1,-2,3,0},{1,4,5,6},{-1,8,8,-6}};
        double doubles2[][] = {{1,-2,3,0}};
        double doubles3[][] = {{1},{-2},{3},{0}};
        Matrix m1 = new Matrix(doubles1);
        simplexMethodSolver.min(m1).print(5,0);
        System.out.println();
        Matrix m2 = new Matrix(doubles2);
        simplexMethodSolver.min(m2).print(5,0);
        System.out.println();
        Matrix m3 = new Matrix(doubles3);
        simplexMethodSolver.min(m3).print(5,0);
    }

    @Test
    public void findTest(){
        SimplexMethodSolver simplexMethodSolver = new SimplexMethodSolver();
        double doubles1[][] = {{1,-2,3,0},{1,4,5,6},{-1,8,8,-6}};
        Matrix m1 = new Matrix(doubles1);
        ArrayList<Integer>  inds = simplexMethodSolver.find(m1,"=", 8);
        System.out.println(inds.toString());
    }
    @Test
    public void candidate_pivot_columns_Test(){
        SimplexMethodSolver simplexMethodSolver = new SimplexMethodSolver();
        double doubles1[][] = {{-1,-2,-3,0},{-1,-4,-5,6},{-1,-8,-8,-6},{-1,-2,-3,0},{-1,-4,-5,6},{-1,-8,-8,-6}};
        Matrix m1 = new Matrix(doubles1);
        Matrix res = simplexMethodSolver.candidate_pivot_columns(m1,3);
        res.print(5,0);
    }

    @Test
    public void sortTest(){
        SimplexMethodSolver simplexMethodSolver = new SimplexMethodSolver();
        double doubles1[][] = {{1,-2,3,0},{1,4,5,6},{-1,8,8,-6}};
        Matrix m1 = new Matrix(doubles1);
        m1 = simplexMethodSolver.sort(m1);
        m1.print(5,0);
    }

    @Test
    public void setdiffTest(){
        SimplexMethodSolver simplexMethodSolver = new SimplexMethodSolver();
        int[] arr1 = {9,1,2,5,9,3,0,9,3,4,5,5,9};
        int[] arr2 = {1,4,9};
        int[] res = simplexMethodSolver.setdiff(arr1,arr2);
        for (int i = 0; i < res.length; i++){
            System.out.print(res[i]);
        }

    }

    @Test
    public void candidate_pivot_columns_test(){
        SimplexMethodSolver simplexMethodSolver = new SimplexMethodSolver();
        double double1[][] = {{1,-2,-3,-4,0,0,0},{0,3,2,1,1,0,10},{0,2,5,3,0,1,15}};
        Matrix m1 = new Matrix(double1);
        Matrix res = simplexMethodSolver.candidate_pivot_columns(m1, 3);
        res.print(5,0);

    }


    @Test
    public void candidate_pivot_row_test(){
        SimplexMethodSolver simplexMethodSolver = new SimplexMethodSolver();
        double double1[][] = {{1,-2,-3,-4,0,0,0},{0,3,2,1,1,0,10},{0,2,5,3,0,1,15}};
        Matrix m1 = new Matrix(double1);
        int v = simplexMethodSolver.candidate_pivot_row(m1, 2,3);
        System.out.println(v);
    }

    @Test
    public void guass_jordan_test(){
        SimplexMethodSolver simplexMethodSolver = new SimplexMethodSolver();
        double double1[][] = {{1,-2,-3,-4,0,0,0},{0,3,2,1,1,0,10},{0,2,5,3,0,1,15}};
        Matrix m1 = new Matrix(double1);
        Matrix e = simplexMethodSolver.gauss_jordan(m1,2,3,4);
        e.print(5,4);
    }

    @Test
    public void get_unbounded_variable_test(){
        SimplexMethodSolver simplexMethodSolver = new SimplexMethodSolver();
        double doubles1[][] = {{3,-2,1},{2,-5,3}};
        double doubles2[][] = {{10},{15}};
        double doubles3[][] = {{2},{3},{4}};
        Matrix m1 = new Matrix(doubles1);
        Matrix m2 = new Matrix(doubles2);
        Matrix m3 = new Matrix(doubles3);
        ArrayList arrayList = simplexMethodSolver.get_unbounded_variable(m1,m3);
        System.out.println(arrayList.toString());
    }

    @Test
    public void solve(){
        SimplexMethodSolver simplexMethodSolver = new SimplexMethodSolver();

        double doubles1[][] = {{3,2,1},{2,5,3}};
        double doubles2[][] = {{10},{15}};
        double doubles3[][] = {{2},{3},{4}};
        Matrix m1 = new Matrix(doubles1);
        Matrix m2 = new Matrix(doubles2);
        Matrix m3 = new Matrix(doubles3);
        ProfitBasedConstriant profitBasedConstriant = new ProfitBasedConstriant(m1, m2);
        ProfitBasedObjective profitBasedObjective = new ProfitBasedObjective(null,m3 );
        ProfitBasedResult profitBasedResult = simplexMethodSolver.solve(profitBasedObjective, profitBasedConstriant);
        profitBasedResult.print();

    }

    @Test
    public void random_matrix_solve(){
        SimplexMethodSolver simplexMethodSolver = new SimplexMethodSolver();


        double doubles1[][] = {{4.0594, 5.3618, 9.8268, 7.0323, 6.5655, 7.3761, 5.3155, 6.1631, 7.3432, 2.9918, 1.3353, 1.1259, 3.6905, 4.4732, 2.0515, 6.2490, 2.6650},
                       {5.4916, 8.1309, 5.7332, 3.6384 ,1.3903, 6.6754, 3.7705,  5.8742,  3.3240,  4.1136,  9.9779,  2.0951,  1.7923,  8.1402,  5.7509, 8.4789, 7.3324},
                {4.0739,  5.0267,  5.5900,  9.1984, 4.9351,  1.0456, 4.6254,  5.0147,  7.2546,  7.8527,  7.6242, 6.6277,  3.7786,  3.2787,  2.7495, 9.2343,  3.6794},
                { 5.3855,  4.8317, 8.1866,  4.6987, 1.2594, 2.0163,  3.4993, 2.3510, 6.9759,  5.7529,  1.1510,  2.6837, 6.2995,  2.9528, 1.4821,6.1963,  8.0748},
                {1.8197,  8.8383,   5.7181 ,  1.7273 ,  3.5385   ,5.5605 ,  8.1650,   7.0697,   9.3883 ,  5.4364 ,  8.1977 ,  2.7324 ,  2.6035  , 9.2874 ,  1.0837,8.7834 , 8.8682},
                { 9.1153  , 5.2530  , 3.9830  , 5.3351 ,  1.6564,   3.0639 ,  2.2735 ,  9.6787  , 9.9406  , 4.9323  , 6.8329   ,2.3198  , 8.9668  , 5.7247 , 9.6253,4.9637 , 6.7013},
                { 9.7389,   7.2043  , 9.9425   ,3.6196 ,  5.0937,   6.6359 ,  9.8869  , 5.8299  , 2.2522  , 6.3000  , 8.2414  , 4.4610  , 8.5193 ,  4.1152,   6.0621,8.9800  , 7.3653},
                {2.2945 ,  8.5261,   8.2087,   4.5292 ,  4.5472 ,  9.9214  , 8.2763 ,  5.7800 ,  1.9912 ,  1.8849 ,  2.6437,   6.5320 ,  1.3178  , 6.4634  , 9.1469, 2.6440 ,  6.0255},
                {4.7301 ,  1.6901 ,  8.0876 ,  5.7615  , 9.4298 ,  9.2839 ,  7.3813 ,  9.2582 ,  2.8907  , 6.7707 ,  7.6262 ,  3.1163  , 2.3342  , 5.9706 ,  7.6715,8.7762  , 4.6080},
                {5.3243   ,3.3056 ,  6.7082 ,  1.0249  , 4.1863 ,  4.7210 ,  7.4668,   1.2978 ,  9.9145 ,  2.9085 ,  2.9051,   4.2729   ,7.3998  , 4.7561 ,  9.7787,8.3750  , 5.1457},
                {1.2019   ,9.6704 ,  4.8976 ,  7.5745  , 7.1809   ,5.9007  , 2.3756 ,  2.1517 ,  4.4179 ,  3.4284  , 2.2078  , 9.5637  , 6.5854  , 3.8983  , 3.9219,6.5835 ,  3.4847},
                {2.2122,   9.7264  , 2.0383 ,  1.7639,   6.9343,   3.7600,   4.9770,   8.1295 ,  7.4606 ,  4.8129 ,  8.4717 ,  9.9785 ,  1.6985 ,  2.6739,   7.0849,3.9611,   2.4442},
                {9.3066 ,  6.1999  , 6.4433,   8.0107   ,3.3314  , 7.1373 ,  3.2917  , 6.4195,   5.5733   ,3.7854,   7.7747  , 7.8645 ,  7.0968 ,  2.2919 ,  5.1554, 3.8289,   2.7440},
                {9.0009  , 8.8313 ,  5.1937 ,  6.0274 ,  4.9016 ,  7.5882  , 7.5990 ,  4.1369 ,  8.4765 , 8.3595  , 9.2609   ,2.3469 ,  4.2913  , 1.8273  , 2.0199,6.8004 ,  3.7904},
                {6.3534   ,5.5867,   3.3222  , 8.8685,   6.7550,   4.3156   ,7.9308,   1.3591  , 8.9350,   1.3450  , 9.2883 ,  1.0313 ,  6.1294 ,  9.8555  , 2.8920,4.6963 ,  4.1005}};
        double doubles2[][] = {{5.8985},{4.6258},{2.1558},{4.8863},{3.4680},{5.1109},{3.5760},{11.2288},{4.3443},{9.4340},{9.6732},{9.8909},{17.7349},{7.5504},{17.7383}};
        double doubles3[][] = {{-14.39027},{-10.59237},{3.24900},{-6.12600},{-2.70417},{7.12269},{-13.01069},{-17.25839},{ 15.68495},{-18.27871},{0.39365},{ 17.18180},{ -4.33544},{-1.03313},{-15.24810},{-5.76841},{-18.94440}};
        Matrix m1 = new Matrix(doubles1);
        Matrix m2 = new Matrix(doubles2);
        Matrix m3 = new Matrix(doubles3);
        ProfitBasedConstriant profitBasedConstriant = new ProfitBasedConstriant(m1, m2);
        ProfitBasedObjective profitBasedObjective = new ProfitBasedObjective(null,m3 );
        ProfitBasedResult profitBasedResult = simplexMethodSolver.solve(profitBasedObjective, profitBasedConstriant);
        profitBasedResult.print();
    }
}
