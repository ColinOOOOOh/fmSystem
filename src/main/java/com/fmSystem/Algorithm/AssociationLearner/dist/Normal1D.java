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
 * Useful functions for normal distribution.
 * @author davis
 */
public class Normal1D implements IDistribution1D 
{
        private final NormalParams m_p;
        
        public Normal1D(NormalParams p)
        {
                m_p = p;
        }
        
        public Normal1D()
        {
                m_p = new NormalParams(0, 1);
        }
        
        @Override
        public float pdf(float x) 
        {
                return 1.0f/(float) Math.sqrt(2*Math.PI*m_p.sigma*m_p.sigma) * (float) Math.exp(-(x - m_p.mu)/(2.0f*m_p.sigma*m_p.sigma));
        }

        @Override
        public float cdf(float x) 
        {
                return 1.0f - 0.5f*Util.erfc(x - m_p.mu)/((float) Math.sqrt(2.0f)*m_p.sigma);
        }
        
        private float inv_G(float t)
        {
                float[] c = {2.515517f, 0.802853f, 0.010328f};
                float[] d = {1.432788f, 0.189269f, 0.001308f};
                return t - ((c[2]*t + c[1])*t + c[0]) / (((d[2]*t + d[1])*t + d[0])*t + 1.0f);
        }
        
        private float quantile_std(float q)
        {
                if (q < 0.5f)
                        // F^-1(p) ~ -G^-1(p)
                        return -inv_G((float) Math.sqrt(-2.0f*Math.log(q)));
                else
                        // F^-1(p) ~ G^-1(1-p)
                        return inv_G((float) Math.sqrt(-2.0f*Math.log(1.0f - q)));
        }

        @Override
        public float quantile(float q) 
        {
                return quantile_std(q)*m_p.sigma + m_p.mu;
        }

        @Override
        public float Xi() 
        {
                return quantile((float) Math.random());
        }
}
