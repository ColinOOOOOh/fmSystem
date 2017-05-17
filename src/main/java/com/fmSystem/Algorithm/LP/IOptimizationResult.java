package com.fmSystem.Algorithm.LP;

import Jama.Matrix;

/**
 * Created by 74551 on 2017/5/2.
 */
public interface IOptimizationResult {
    public  Solvable isSolvable();
    public double getMaxima();
    public Matrix getX();
}
