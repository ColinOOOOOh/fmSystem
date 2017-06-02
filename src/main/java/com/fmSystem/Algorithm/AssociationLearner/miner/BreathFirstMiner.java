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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Breath first search miner.
 * @author davis
 */
public class BreathFirstMiner implements IPatternMiner {

        @Override
        public FrequentPatterns mine(IDataAccessor accessor, float support, float confidence, FrequentPatternsFactory fact) 
        {
                long epsilon = (long) (accessor.count(new Pattern())*support);
                
                Set<Object> items = accessor.items();
                FrequentPatterns patterns = fact.__new();
                
                // Load up the single items 
                List<FrequentPattern> candids = new ArrayList();
                items.forEach((o) -> {
                        Pattern p = new Pattern() {{ add(o); }};
                        candids.add(new FrequentPattern(p, accessor.count(p)));
                });
                
                while (!candids.isEmpty()) {
                        List<FrequentPattern> pruned = candids.stream()
                                .filter(p -> p.freq > epsilon)
                                .collect(Collectors.toList());
                        patterns.add_all(pruned, 0);
                        
                        // Generate candidates.
                        candids.clear();
                        Set<Pattern> check_repeat = new HashSet();
                        pruned.forEach((FrequentPattern p) -> {{
                                items.stream().filter(i -> !p.contains(i)).forEach((Object i) -> {{
                                        Pattern n = new Pattern(p) {{ add(i); }};
                                        if (!check_repeat.contains(n)) {
                                                candids.add(new FrequentPattern(n, accessor.count(n)));
                                                check_repeat.add(n);
                                        }
                                }});
                        }});
                }
                
                // @FIXME: normalize frequency (exclude superset frequency).
                FrequentPatterns result;
                for (FrequentPattern p: patterns) {
                        
                }
                return patterns;
        }
        
}
