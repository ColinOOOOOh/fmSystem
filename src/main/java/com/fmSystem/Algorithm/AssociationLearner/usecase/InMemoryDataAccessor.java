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
package com.fmSystem.Algorithm.AssociationLearner.usecase;

import com.fmSystem.Algorithm.AssociationLearner.dist.IDistribution1D;
import com.fmSystem.Algorithm.AssociationLearner.dist.Normal1D;
import com.fmSystem.Algorithm.AssociationLearner.dist.NormalParams;
import com.fmSystem.Algorithm.AssociationLearner.miner.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * In-memory implementation of transaction data accessor.
 * @author davis
 */
public class InMemoryDataAccessor implements IDataAccessor {
        
        private final Set<Object>                       m_items = new HashSet();
        private final FrequentPatterns m_transactions;
        private Iterator<FrequentPattern>         m_it = null;
        
        public InMemoryDataAccessor(FrequentPatternsFactory fact)
        {
                m_transactions = fact.__new();
        }
        
        @Override
        public long count(Pattern p) {
                return m_transactions.count(p);
        }

        @Override
        public Set<Object> items() {
                return m_items;
        }

        @Override
        public FrequentPattern next_transaction() {
                m_it = m_it == null ? m_transactions.iterator() : m_it;
                return m_it.hasNext() ? m_it.next() : null;
        }
        
        public void add_transaction(FrequentPattern pattern) {
                pattern.forEach((item) -> m_items.add(item));
                m_transactions.add(pattern);
        }

        @Override
        public IDistribution1D distribution()
        {
                float sum_freq = 0.0f;
                float sum_freq2 = 0.0f;
                int n = 0;
                for (FrequentPattern p: m_transactions) {
                        sum_freq += p.freq;
                        sum_freq2 += p.freq*p.freq;
                        n ++;
                }
                
                float mu = sum_freq/n;
                float sigma = (float) Math.sqrt(sum_freq2/n - mu*mu);
                NormalParams params = new NormalParams(mu, sigma);
                return new Normal1D(params);
        }
}
