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
import java.util.TreeSet;

/**
 * Add-only set.
 * @author davis
 */
public class Pattern extends TreeSet {
        
        private int     m_hash = 0;
        
        public Pattern()
        {
        }
        
        public Pattern(Pattern p)
        {
                super(p);
        }
        
        public Pattern(Collection c)
        {
                super(c);
        }
        
        @Override
        public boolean add(Object o)
        {
                m_hash = 31*m_hash + o.hashCode();
                return super.add(o);
        }
        
        @Override
        public int hashCode()
        {
                return m_hash;
        }
}
