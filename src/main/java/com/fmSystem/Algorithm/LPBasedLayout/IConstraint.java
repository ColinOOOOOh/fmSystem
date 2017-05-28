package com.fmSystem.Algorithm.LPBasedLayout;

import Jama.Matrix;

/**
 * Created by 74551 on 2017/5/2.
 */
public interface IConstraint {
    public Matrix getCoefficient();
    public Matrix getB();

}
