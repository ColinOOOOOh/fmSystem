package com.fmSystem.Algorithm.LPBasedLayout.Impl;

import Jama.Matrix;
import com.fmSystem.Algorithm.LPBasedLayout.IConstraint;

/**
 * Created by 74551 on 2017/5/16.
 */
public class ProfitBasedConstriant implements IConstraint {
    private Matrix coefficient;
    private Matrix b;

    public ProfitBasedConstriant(Matrix coefficient, Matrix b) {
        this.coefficient = coefficient;
        this.b = b;
    }

    public Matrix getCoefficient(){
        return coefficient;
    }

    public Matrix getB(){
        return b;
    }
}
