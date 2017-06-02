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
 * Full table scan miner.
 * @author davis
 */
public class FTSMiner implements IPatternMiner {

        @Override
        public FrequentPatterns mine(IDataAccessor accessor, float support, float confidence, FrequentPatternsFactory fact) 
        {       
                FrequentPatterns patterns = fact.__new();
                FrequentPattern pattern;
                while (null != (pattern = accessor.next_transaction())) {
                        patterns.add(pattern);
                }
                long epsilon = (long) Math.ceil(patterns.amount()*support);
                FrequentPatterns filtered = fact.__new();
                for (FrequentPattern p: patterns) {
                        if (patterns.count(p) > epsilon)
                                filtered.add(p);
                }
                return filtered;
        }
        
}
