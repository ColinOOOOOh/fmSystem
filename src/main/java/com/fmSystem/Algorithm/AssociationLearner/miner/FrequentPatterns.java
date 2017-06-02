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

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Frequent patterns container.
 * @author davis
 */
public final class FrequentPatterns implements Iterable<FrequentPattern> 
{        
        private final IKHash                            m_f;
        private final Map<Pattern, FrequentPattern>     m_patterns = new HashMap();
        private int                                     m_amount = 0;
        private Family<Pattern>                         m_filter;
        
        public FrequentPatterns(IKHash f)
        {
                m_f = f;
                m_filter = new Family(f);
        }
        
        public FrequentPatterns(IKHash f, Collection<Pattern> patterns)
        {
                this(f);
                add_all(patterns);
        }
        
        public FrequentPatterns(IKHash f, Collection<FrequentPattern> patterns, int o)
        {
                this(f);
                add_all(patterns, o);
        }
        
        /**
         * P(evidence|belief).
         * @param evidence
         * @param belief
         * @return p
         */
        public float prior_probability(Pattern evidence, Pattern belief)
        {
                FrequentPatterns beliefs = match(belief);
                long num_evidences = beliefs.count(evidence);
                return (float) num_evidences/beliefs.amount();
        }
        
        /**
         * Search for any Pattern p' where p is a subset of p.
         * @param p Pattern.
         * @return Set of pattern that satisfy the constraint.
         */
        public FrequentPatterns match(Pattern p)
        {
                return new FrequentPatterns(m_f, (List<FrequentPattern>) m_filter.superset_of(p), 0);
        }
        
        /**
         * Union of the matches given by each element in p.
         * @param p
         * @return All possible sub-patterns.
         */
        public FrequentPatterns match_subpattern_of(Pattern p)
        {
                return new FrequentPatterns(m_f, (List<FrequentPattern>) m_filter.subset_of(p), 0);
        }
        
        /**
         * Count the number of sets of which pattern p is a subset.
         * @param p
         * @return the number of sets that satisfy the constraint.
         */
        public long count(Pattern p)
        {
                return ((List<FrequentPattern>) m_filter.superset_of(p)).stream().mapToLong(f -> f.freq).sum();
        }
        
        private void add(Pattern p, long freq)
        {
                FrequentPattern fp = m_patterns.get(p);
                if (fp != null)
                        fp.freq += freq;
                else
                        m_patterns.put(p, (FrequentPattern) m_filter.add(new FrequentPattern(p, freq)));
                m_amount += freq;
        }
        
        /**
         * Add a pattern to the frequent sets.
         * @param p 
         */
        public void add(Pattern p)
        {
                add(p, 1);
        }
        
        /**
         * Add a frequent pattern to the frequent sets.
         * @param p 
         */
        public void add(FrequentPattern p)
        {
                add(p, p.freq);
        }
        
        /**
         * Add a collection of patterns to the frequent sets.
         * @param ps
         */
        public void add_all(Collection<Pattern> ps)
        {
                ps.forEach(this::add);
        }
        
        /**
         * Add a collection of frequent patterns to the frequent sets.
         * @param ps
         */
        public void add_all(Collection<FrequentPattern> ps, int o)
        {
                ps.forEach(this::add);
        }
        
        /**
         * @return The total amount of pattern occurrences.
         */
        public long amount()
        {
                return m_amount;
        }
        
        /**
         * @return Number of sets in frequent sets.
         */
        public int size()
        {
                return m_patterns.size();
        }
        
        // Iterators.
        public class FrequentSetsIterator implements Iterator<FrequentPattern> {
                
                private final Iterator<FrequentPattern> m_it;
                
                public FrequentSetsIterator(Collection<FrequentPattern> patterns)
                {
                        m_it = patterns.iterator();
                }

                @Override
                public boolean hasNext() {
                        return m_it.hasNext();
                }

                @Override
                public FrequentPattern next() {
                        return m_it.next();
                }
                
        }

        @Override
        public Iterator<FrequentPattern> iterator() {
                return new FrequentSetsIterator(m_patterns.values());
        }
}
