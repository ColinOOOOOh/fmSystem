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
package com.fmSystem.Algorithm.AssociationLearner.miner;

/**
 * @author davis
 */
public class IntKHash implements IKHash {
        
        private int fnv(Integer x, int k)
        {
                int b0 = x & 0XFF;
                int b1 = (x >> 8) & 0XFF;
                int b2 = (x >> 16) & 0XFF;
                int b3 = (x >> 24) & 0XFF;
                int hash = 2038074743*k + 33;
                hash ^= b0;
                hash *= 16777619;
                hash ^= b1;
                hash *= 16777619;
                hash ^= b2;
                hash *= 16777619;
                hash ^= b3;
                hash *= 16777619;
                return hash;
        }

        @Override
        public int[] f(Object x) {
                int[] h = new int [k()];
                for (int i = 0; i < k(); i ++) {
                        h[i] = fnv((Integer) x, i);
                }
                return h;
        }

        @Override
        public int k() {
                return 3;
        }
        
}
