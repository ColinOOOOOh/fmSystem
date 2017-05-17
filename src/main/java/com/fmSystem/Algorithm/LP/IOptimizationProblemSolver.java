package com.fmSystem.Algorithm.LP;

/**
 * Created by 74551 on 2017/5/2.
 */
public interface IOptimizationProblemSolver {
    abstract IOptimizationResult solve(ILinearObjective o, IConstraint c);
}
