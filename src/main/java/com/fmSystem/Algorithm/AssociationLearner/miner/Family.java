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
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Handle 2nd order set (a set of sets) operations.
 * @author davis
 * @param <T>
 */
public class Family<T extends Collection> {
        
        private final IKHash                    m_f;
        
        private final int                       K;
        
         // Sets statistics.
        private int                             m_sum_length = 0;
        private int                             m_num_elements = 0;
        private static final float              ACCEPTABLE_TYPE_I_RATE = 0.1f;
        
        // Filter size.
        private int                             m_bit_length = 2;
        
        private Node                            m_root = new Node();
        
        private class Node 
        {        
                public List<T> e;
                public Node left;
                public Node right;
        }
        
        private int mu_length()
        {
                return m_sum_length/m_num_elements;
        }
        
        private float int_pow(float a, int b)
        {
            if (b == 0)        
                    return 1;
            if (b == 1)        
                    return a;
            if ((b & 1) == 0)
                    return int_pow(a*a, b >> 1);
            else                
                    return a*int_pow(a*a, b >> 1);

        }
        
        private float prob_type_I_match(int m, int L)
        {
                float prob_any_zero = int_pow(1.0f - 1.0f/m, K*L);
                return int_pow(1 - prob_any_zero, K);
        }
        
        private void copy_over(Node target, Node source, int m)
        {
                if (source == null)
                        return;
                if (source.e != null)
                        source.e.forEach((e) -> insert(target, e, m));
                else {
                        copy_over(target, source.left, m);
                        copy_over(target, source.right, m);
                }
        }
        
        private void expand()
        {
                int e = 2*mu_length();
                int m = m_bit_length;
                while (prob_type_I_match(m, e) > ACCEPTABLE_TYPE_I_RATE)
                        m <<= 1;
                if (m > m_bit_length) {
                        // Need to resize.
                        Node new_root = new Node();
                        copy_over(new_root, m_root, m);
                        m_root = new_root;
                        m_bit_length = m;
                }
        }
        
        private boolean[] bloom(T set, int m)
        {
                boolean[] bits = new boolean [m];
                set.forEach((element) -> {
                        int h[] = m_f.f(element);
                        for (int i = 0; i < K; i ++)
                                bits[h[i] & (m - 1)] = true;
                });
                return bits;
        }
        
        private void insert(Node node, T set, int m)
        {
                boolean[] bits = bloom(set, m);
                Node curr_node = node;
                for (int i = 0; i < K; i ++) {
                        if (bits[i]) {
                                if (curr_node.right == null)
                                        curr_node.right = new Node();
                                curr_node = curr_node.right;
                        } else {
                                if (curr_node.left == null)
                                        curr_node.left = new Node();
                                curr_node = curr_node.left;
                        }
                }
                if (curr_node.e == null)
                        curr_node.e = new ArrayList() {{ add(set); }};
                else
                        curr_node.e.add(set);
        }
        
        private List<T> filter_subset_of(Node node, boolean[] bits, int depth)
        {
                if (node == null)
                        return null;
                if (depth == K)
                        return node.e;
                if (bits[depth])
                        return filter_subset_of(node.right, bits, depth + 1);
                else {
                        List<T> left = filter_subset_of(node.left, bits, depth + 1);
                        List<T> right = filter_subset_of(node.right, bits, depth + 1);
                        if (left == null)
                                return right;
                        else if (right == null)
                                return left;
                        else
                                return Stream.concat(left.stream(), right.stream()).collect(Collectors.toList());
                }
        }
        
        private List<T> filter_superset_of(Node node, boolean[] bits, int depth)
        {
                if (node == null)
                        return null;
                if (depth == K)
                        return node.e;
                if (bits[depth] == false)
                        return filter_superset_of(node.left, bits, depth + 1);
                else {
                        List<T> left = filter_superset_of(node.left, bits, depth + 1);
                        List<T> right = filter_superset_of(node.right, bits, depth + 1);
                        if (left == null)
                                return right;
                        else if (right == null)
                                return left;
                        else
                                return Stream.concat(left.stream(), right.stream()).collect(Collectors.toList());
                }
        }
        
        private List<T> superset_of(T set, int m)
        {
                List<T> result = filter_subset_of(m_root, bloom(set, m), 0);
                return result == null ? new ArrayList() : result;
        }
        
        private List<T> subset_of(T set, int m)
        {
                List<T> result = filter_superset_of(m_root, bloom(set, m), 0);
                return result == null ? new ArrayList() : result;
        }
        
        public Family(IKHash f)
        {
                m_f = f;
                K = f.k();
                
                while (m_bit_length < K)
                        m_bit_length <<= 1;
        }
        
        public T add(T set)
        {
                m_num_elements ++;
                m_sum_length += set.size();
                
                insert(m_root, set, m_bit_length);
                expand();
                return set;
        }
        
        public List<? extends T> superset_of(T set)
        {
                return superset_of(set, m_bit_length).stream()
                        .filter((candid) -> candid.containsAll(set))
                        .collect(Collectors.toList());
        }
        
        public List<? extends T> subset_of(T set)
        {
                return subset_of(set, m_bit_length).stream()
                        .filter((candid) -> set.containsAll(candid))
                        .collect(Collectors.toList());
        }
}
