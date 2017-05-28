package com.fmSystem.Algorithm.LPBasedLayout.Impl;

import Jama.Matrix;
import com.fmSystem.Algorithm.LPBasedLayout.IOptimizationResult;
import com.fmSystem.Algorithm.LPBasedLayout.Solvable;

/**
 * Created by 74551 on 2017/5/18.
 */
public class ProfitBasedResult implements IOptimizationResult{
    private Solvable solvable;
    private double maxima;
    private Matrix x;

    public ProfitBasedResult(Solvable solvable, double maxima, Matrix x) {
        this.solvable = solvable;
        this.maxima = maxima;
        this.x = x;
    }


    public Solvable isSolvable(){
        return null;
    }
    public double getMaxima(){
        return 0.1;
    }
    public Matrix getX(){
        return null;
    }

    public void print(){
        System.out.println("isSolvable = " + solvable + " getMaxima = " + maxima + " \nx = ");
        x.print(5,3);

    }

}
