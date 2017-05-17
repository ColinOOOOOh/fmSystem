package com.fmSystem.algorithmTest;


import Jama.Matrix;
import com.fmSystem.Algorithm.LP.Impl.ProfitBasedConstriant;
import com.fmSystem.Algorithm.LP.Impl.ProfitBasedObjective;
import com.fmSystem.Algorithm.LP.Impl.SimplexMethodSolver;
import org.junit.Test;

import java.lang.reflect.Array;
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
}
