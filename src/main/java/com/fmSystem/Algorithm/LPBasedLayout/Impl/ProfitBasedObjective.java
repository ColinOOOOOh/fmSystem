package com.fmSystem.Algorithm.LPBasedLayout.Impl;

import Jama.Matrix;
import com.fmSystem.Algorithm.LPBasedLayout.ILinearObjective;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by 74551 on 2017/5/2.
 */
public class ProfitBasedObjective implements ILinearObjective{

    private List<Item> itemList = new ArrayList<Item>();
    private Matrix coefficient;

    public ProfitBasedObjective(List<Item> itemList, Matrix coefficient){
        this.itemList = itemList;
        this.coefficient = coefficient;
    }

    public Matrix getCoefficients() {
        return this.coefficient;
    }

}
