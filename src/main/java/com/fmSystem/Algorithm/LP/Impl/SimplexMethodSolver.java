package com.fmSystem.Algorithm.LP.Impl;

import Jama.Matrix;
import com.fmSystem.Algorithm.LP.IConstraint;
import com.fmSystem.Algorithm.LP.ILinearObjective;
import com.fmSystem.Algorithm.LP.IOptimizationProblemSolver;
import com.fmSystem.Algorithm.LP.IOptimizationResult;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by 74551 on 2017/5/14.
 */
public class SimplexMethodSolver implements IOptimizationProblemSolver {
    public IOptimizationResult solve(ILinearObjective o, IConstraint c){
        int m = size(c.getB(),1);
        int n = size(o.getCoefficients(),1);

        Matrix T = constructT(o, c);
        Matrix x = zeros(n+m,1);
        Matrix row2basic = zeros(1+m,1).transpose();
        for (int i = 1; i < 2+m-1-1; i++){
            row2basic.set(i,1,1+n+i);
        }
        Matrix cols = candidate_pivot_columns(T, n);


        return null;
    }

    private int size(Matrix matrix, int i){
        if(1 == i){
            return matrix.getRowDimension();
        }
        if(2 == i){
            return matrix.getColumnDimension();
        }
        return 999999;
    }

    private Matrix zeros(int x, int y){
        Matrix matrix = new Matrix(x, y);
        return matrix;
    }

    public Matrix eye(int x, int y){
        return Matrix.identity(x,y);
    }

    public Matrix append(Matrix m1, Matrix m2, String direction){

        if(direction.equals("Right")){
            if(m1.getRowDimension() != m2.getRowDimension()){
                System.out.println("m1.getRowDimension() = " + m1.getRowDimension());
                System.out.println("m2.getRowDimension() = " + m2.getRowDimension());


                return null;
            }
            Matrix newMatrix = new Matrix(m1.getRowDimension(),m1.getColumnDimension() + m2.getColumnDimension());
            for (int i = 0; i < m1.getRowDimension(); i++){
                    for (int k = 0; k < m1.getColumnDimension(); k++){
                        newMatrix.set(i, k, m1.get(i, k));
                    }
                    for (int k = 0; k < m2.getColumnDimension(); k++){
                        newMatrix.set(i, k + m1.getColumnDimension(), m2.get(i, k));
                    }
            }
            return newMatrix;
        }
        if(direction.equals("Down")){
            if(m1.getColumnDimension() != m2.getColumnDimension()){
                System.out.println("m1.getColumnDimension() = " + m1.getColumnDimension());
                System.out.println("m2.getColumnDimension() = " + m2.getColumnDimension());
                return null;
            }
            Matrix newMatrix = new Matrix(m1.getRowDimension() + m2.getRowDimension(),m1.getColumnDimension());
            for (int i = 0; i < m1.getRowDimension(); i++){
                for (int j = 0; j < m1.getColumnDimension(); j++){
                    newMatrix.set(i, j, m1.get(i, j));
                }
            }
            for (int i = 0; i < m2.getRowDimension(); i++){
                for (int j = 0; j < m2.getColumnDimension(); j++){
                    newMatrix.set(i + m1.getRowDimension(), j, m2.get(i, j));
                }
            }
            return newMatrix;
        }


        return null;
    }

    public Matrix min(Matrix m){
        if (m.getRowDimension() == 1){
            Matrix minMatrix = new Matrix(1,1);
            double tmp = m.get(0,0);
            for (int i = 0; i < m.getColumnDimension(); i++){
                if (tmp > m.get(0,i)){
                    tmp = m.get(0,i);
                }
            }
            minMatrix.set(0, 0, tmp);
            return minMatrix;
        }else if (m.getColumnDimension() == 1){
            Matrix minMatrix = new Matrix(1,1);
            double tmp = m.get(0,0);
            for (int i = 0; i < m.getRowDimension(); i++){
                if (tmp > m.get(i,0)){
                    tmp = m.get(i,0);
                }
            }
            minMatrix.set(0, 0, tmp);
            return minMatrix;
        }else {
            Matrix minMatrix = new Matrix(1,m.getColumnDimension());
            for (int i = 0; i < m.getColumnDimension(); i++){
                double tmp = m.get(0,i);
                for (int j = 0; j < m.getRowDimension(); j++){
                    if (tmp > m.get(j,i)){
                        tmp = m.get(j,i);
                    }
                }
                minMatrix.set(0, i, tmp);
            }
            return minMatrix;
        }



    }

    private Matrix constructT(ILinearObjective o, IConstraint c){
        int m = size(c.getB(),1);
        int n = size(o.getCoefficients(),1);
        Matrix T = append(new Matrix(1,1,1), o.getCoefficients().uminus().transpose(),"Right");
        T = append(T, zeros(m,1).transpose(), "Right" );
        T = append(T, new Matrix(1,1,0),"Right");
        Matrix t2 = append(zeros(m,1),c.getCoefficient(),"Right");
        t2 = append(t2, eye(m, m), "Right");
        t2 = append(t2, c.getB(), "Right");
        T = append(T, t2, "Down");
        T.print(3, 1);
        return T;
    }

    public Matrix candidate_pivot_columns(Matrix T, int n){
        Matrix min = min(T.getMatrix(0,0,1,2+n-2 ));
        if (min.get(0,0) >= 0){
            return new Matrix(0,0);

        }else {
            ArrayList<Integer> findList = find(T.getMatrix(0,0,1,2+n-2 ), min.get(0,0));
            Matrix findListMatrix = new Matrix(1, findList.size());
            for (int i = 0; i < findList.size(); i++){
                findListMatrix.set(0, i, findList.get(i)+1);

            }
            findListMatrix = sort(findListMatrix);
            return  findListMatrix;
        }

    }

    public ArrayList<Integer> find(Matrix m, double value){
        ArrayList list = new ArrayList();
        for (int i = 0; i < m.getColumnDimension(); i++){
            for (int j = 0; j <m.getRowDimension(); j++){
                if (m.get(j, i) == value){
                    list.add(j + i * m.getRowDimension()) ;
                }
            }
        }
        return list;
    }

    public Matrix sort(Matrix m){
        double[][] array = m.getArray();
        double tmp;
        for (int k = 0; k < m.getColumnDimension()*m.getRowDimension(); k++){
            for (int i = 0; i < m.getRowDimension(); i++){
                for (int j = 0; j < m.getColumnDimension(); j++){
                    if (j == m.getColumnDimension() - 1 && i == m.getRowDimension() - 1){
                        continue;
                    }
                    if (j == m.getColumnDimension() - 1){
                        if (array[i][j] > array[i+1][0]){
                            tmp = array[i][j];
                            array[i][j] = array[i+1][0];
                            array[i+1][0] = tmp;
                        }
                    }else {
                        if (array[i][j] > array[i][j+1]){
                            tmp = array[i][j];
                            array[i][j] = array[i][j+1];
                            array[i][j+1] = tmp;
                        }
                    }

                }
            }
        }
        return m;
    }

}
