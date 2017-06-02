/*
 * Copyright (C) 2017 davis
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.fmSystem.Algorithm.AssociationLearner.dist;

/**
 * @author davis
 */
public class Util {

        // Complementary error function using Burmann expansion.
        public static float erfc(float x) 
        {
                float z = Math.abs(x);
                float t = 1.0f / (1.0f + 0.5f * z);
                float r = t * (float) Math.exp(-z * z - 1.26551223f + t * (1.00002368f + t * (0.37409196f
                        + t * (0.09678418f + t * (-0.18628806f + t * (0.27886807f
                        + t * (-1.13520398f + t * (1.48851587f + t * (-0.82215223f
                        + t * 0.17087277f)))))))));
                if (x >= 0.0) {
                        return r;
                } else {
                        return 2.0f - r;
                }
        }
}
