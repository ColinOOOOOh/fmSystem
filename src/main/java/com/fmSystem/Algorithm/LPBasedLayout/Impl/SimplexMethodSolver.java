package com.fmSystem.Algorithm.LPBasedLayout.Impl;

import Jama.Matrix;
import com.fmSystem.Algorithm.LPBasedLayout.*;

import java.util.ArrayList;


/**
 * Created by 74551 on 2017/5/14.
 */
public class SimplexMethodSolver implements IOptimizationProblemSolver {
    public ProfitBasedResult solve(ILinearObjective o, IConstraint c){

        int m = size(c.getB(),1);
        int n = size(o.getCoefficients(),1);

        Matrix T = constructT(o, c);
        ArrayList u = get_unbounded_variable(c.getCoefficient(), o.getCoefficients());
        if (u.size() > 0){
            ProfitBasedResult s = new ProfitBasedResult(Solvable.NO, Double.POSITIVE_INFINITY, null);
            return s;
        }
        Matrix x = zeros(n+m+1,1);
        Matrix row2basic = zeros(1+m,1).transpose();
        for (int i = 1; i <= 2+m-1-1; i++){
            row2basic.set(0,i,1+n+i);
        }
        Matrix cols = candidate_pivot_columns(T, n);
        while (size(cols,2) > 0){
            int j = (int)cols.get(0,0);
            int i = candidate_pivot_row(T, m, j);
            row2basic.set(0, i, j+1);
            T = gauss_jordan(T, m, i+1, j+1);
            cols = candidate_pivot_columns(T, n);
        }

        int[] basic_set = new int[row2basic.getColumnDimension()-1];
        for (int i = 0; i < row2basic.getColumnDimension()-1; i++){
            basic_set[i] = (int)row2basic.get(0,i+1) - 1 ;
        }
        int [] all = new int[n+m];
        for (int i = 0; i < 2+n+m-2; i++){
            all[i] = i + 2;
        }
        int[] nonbasic_set = setdiff(all ,basic_set);
        for (int i = 0; i < nonbasic_set.length; i++){
            x.set(nonbasic_set[i]-1, 0, 0);
        }

        Matrix b = T.getMatrix(1, T.getRowDimension()-1, T.getColumnDimension()-1, T.getColumnDimension()-1);
        Matrix D = T.getMatrix(1, T.getRowDimension()-1, basic_set);
        Matrix tmp = D.inverse().times(b);
        for (int i = 0; i < basic_set.length; i++){
            x.set(basic_set[i]-1, 0, tmp.get(i, 0));
        }
        Matrix nx = new Matrix(n,1);
        for (int i = 0; i < n; i++){
            nx.set(i,0,x.get(i,0));
        }

        ProfitBasedResult s = new ProfitBasedResult(Solvable.YES, T.get(0, T.getColumnDimension()-1), nx);
        return s;
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

    public Matrix size(Matrix matrix){
        Matrix m = new Matrix(1,2);
        m.set(0,0,matrix.getRowDimension());
        m.set(0,1,matrix.getColumnDimension());
        return m;
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
        return T;
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

    public Matrix candidate_pivot_columns(Matrix T, int n){
        Matrix min = min(T.getMatrix(0,0,1,2+n-2 ));
        if (min.get(0,0) >= 0){
            return new Matrix(0,0);

        }else {
            ArrayList<Integer> findList = find(T.getMatrix(0,0,1,2+n-2 ), "=", min.get(0,0));
            Matrix findListMatrix = new Matrix(1, findList.size());
            for (int i = 0; i < findList.size(); i++){
                findListMatrix.set(0, i, findList.get(i)+1);

            }
            findListMatrix = sort(findListMatrix);
            return  findListMatrix;
        }

    }

    public ArrayList<Integer> find(Matrix m, String symbol, double value){
        ArrayList list = new ArrayList();
        double e = 1e-5;
        if (symbol.equals("<")){
            for (int i = 0; i < m.getColumnDimension(); i++){
                for (int j = 0; j <m.getRowDimension(); j++){
                    if (m.get(j, i) < value){
                        list.add(j + i * m.getRowDimension()) ;
                    }
                }
            }
        }else if (symbol.equals("=")){
            for (int i = 0; i < m.getColumnDimension(); i++){
                for (int j = 0; j <m.getRowDimension(); j++){

                    if (Math.abs(m.get(j, i) - value) < e){

                        list.add(j + i * m.getRowDimension()) ;
                    }
                }
            }
        }else if (symbol.equals(">")){
            for (int i = 0; i < m.getColumnDimension(); i++){
                for (int j = 0; j <m.getRowDimension(); j++){
                    if (m.get(j, i) >value){
                        list.add(j + i * m.getRowDimension()) ;
                    }
                }
            }
        }else if (symbol == null){
            for (int i = 0; i < m.getColumnDimension(); i++){
                for (int j = 0; j <m.getRowDimension(); j++){
                    if (m.get(j, i) != 0){
                        list.add(j + i * m.getRowDimension()) ;
                    }
                }
            }
        }

        return list;
    }

    public int candidate_pivot_row(Matrix T, int m, int j){
        int i = 0;
        double min = Double.POSITIVE_INFINITY;

        for (int r = 1; r < 1 + m; r++){
            double a_rj = T.get(r, j);
            double b_r = T.get(r, T.getColumnDimension() - 1);
            double mrt = b_r / a_rj;

            if (a_rj > 0 && mrt < min){
                min = mrt;
                i = r;
            }
        }
        return i;
    }

    public ArrayList get_unbounded_variable(Matrix A, Matrix c){
        ArrayList<Integer> i = find(c, ">", 0);
        int[] is = new int[i.size()];
        for (int k = 0; k < i.size(); k++){
            is[k] = i.get(k);
        }
        ArrayList<Integer> j = new ArrayList<Integer>();
        for (int n = 0; n < is.length; n++){
            for (int m = 0; m < A.getRowDimension(); m++){
                if (A.get(m, n) >= 0 && j.size() < n + 1){
                    j.add(0);
                }
            }
            if (j.size() < n + 1){
                j.add(1);
            }
        }
        ArrayList x = new ArrayList();
        for (int k = 0; k < j.size(); k++){
            if (j.get(k) != 0){
                x.add(k+1);
            }
        }

        return x;
    }

    public Matrix gauss_jordan(Matrix T, int m, int i, int j){
        double Tij = T.get(i-1, j-1);
        for (int k = 0; k < T.getColumnDimension(); k++ ){

            T.set(i-1, k, T.get(i-1, k) / Tij);
        }

        for (int r = 1; r < m+1; r++ ){
            if (r != i){
                double a = -T.get(r-1, j-1);
                for (int k = 0; k < T.getColumnDimension(); k++){
                    T.set(r-1, k,a * T.get(i-1, k) + T.get(r-1, k));
                }

            }
        }
        return T;
    }

    public int[] setdiff(int[] arr1, int[] arr2){
        int count = 0;
        for (int j = 0; j < arr2.length; j++){
            for (int i = 0; i < arr1.length; i++){
                if (arr1[i] == arr2[j]){
                    if (i < arr1.length - count ){
                        if (count == 0){
                            for (int k = i; k < arr1.length - count - 1; k++){
                                arr1[k] = arr1[k+1];
                            }
                        }else {
                            for (int k = i; k < arr1.length - count; k++){
                                arr1[k] = arr1[k+1];
                            }
                        }

                        count++;
                    }

                }
            }
        }
        int[] diff = new int[arr1.length-count];
        for (int i = 0; i < diff.length; i++){
            diff[i] = arr1[i];
        }
        //bubble sort
        for (int i = 0; i < diff.length - 1; i++){
            for (int j = 0; j < diff.length - 1; j++){
                if (diff[j] > diff[j+1]){
                    int tmp;
                    tmp = diff[j];
                    diff[j] = diff[j+1];
                    diff[j+1] = tmp;
                }
            }
        }

        count = 0;
        for (int i = 0; i < diff.length - 1; i++){
            if (diff[i] == diff[i+1]){
                if (i < diff.length - count ){
                    if (count == 0){
                        for (int j = i + 1; j < diff.length - count - 1; j++){
                            diff[j] = diff[j+1];
                        }
                    }else {
                        for (int j = i + 1; j < diff.length - count; j++){
                            diff[j] = diff[j+1];
                        }
                    }
                    count++;
                }



            }
        }
        int[] res = new int[diff.length-count];
        for (int i = 0; i < res.length; i++){
            res[i] = diff[i];
        }
        return res;
    }


}
