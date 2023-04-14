/**
 * Library to easily generate fixed MPS files for LP solver
 * Copyright (C) 2020-2023 Iker Ruiz de Infante Gonzalez <iker@irzinfante.eu>
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
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package eu.irzinfante.fixedmps.core;

import eu.irzinfante.fixedmps.constant.ConstraintType;

/**
 * @author      irzinfante iker@irzinfante.eu
 * @since       1.0.0
 */
public class Constraint {
	
	private double coeffs[];
	private ConstraintType type;
	private double free;
	
	/**
	 * @author      irzinfante iker@irzinfante.eu
	 * @since       1.0.0
	 */
	public static class ConstraintBuilder {
		
		private double coeffs[];
		
		/**
		 * Initializes a Constraint object builder
		 *
		 * @param	coeffs Coefficients of the variables in the
		 * same order they are added to the Problem object builder.
		 * Extra coefficients are ignored and, if there are less
		 * coefficients than variables, missing coefficients
		 * are set to zero
		 * 
		 * @since	1.0.0
		 */
		public ConstraintBuilder (double ... coeffs) {
			this.coeffs = coeffs;
		}
		
		/**
		 * Creates an inequation constraint (≤ k)
		 *
		 * @param	free	The free term of the inequation, k
		 * @return	a less-than type constraint
		 * 
		 * @since	1.0.0
		 */
		public Constraint lessThan(double free) {
			
			Constraint constraint = new Constraint();
			
			constraint.setCoeffs(this.coeffs);
			constraint.setType(ConstraintType.L);
			constraint.setFree(free);
			
			return constraint;
		}
		
		/**
		 * Creates an inequation constraint (≥ k)
		 *
		 * @param	free The free term of the inequation, k
		 * @return	a greater-than type constraint
		 * 
		 * @since	1.0.0
		 */
		public Constraint greaterThan(double free) {
			
			Constraint constraint = new Constraint();
			
			constraint.setCoeffs(this.coeffs);
			constraint.setType(ConstraintType.G);
			constraint.setFree(free);
			
			return constraint;
		}
		
		/**
		 * Creates a equation constraint
		 *
		 * @param	free The free term of the equation
		 * @return	a equality type constraint
		 * 
		 * @since	1.0.0
		 */
		public Constraint equalTo(double free) {
			
			Constraint constraint = new Constraint();
			
			constraint.setCoeffs(this.coeffs);
			constraint.setType(ConstraintType.E);
			constraint.setFree(free);
			
			return constraint;
		}
		
	}
	
	private Constraint() {
	}
	
	public double[] getCoeffs() {
		return coeffs;
	}
	
	private void setCoeffs(double coeffs[]) {
		this.coeffs = coeffs;
	}
	
	public ConstraintType getType() {
		return type;
	}
	
	private void setType(ConstraintType type) {
		this.type = type;
	}
	
	public double getFree() {
		return free;
	}
	
	private void setFree(double free) {
		this.free = free;
	}
	
}