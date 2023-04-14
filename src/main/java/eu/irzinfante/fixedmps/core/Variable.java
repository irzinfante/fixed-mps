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

/**
 * @author      irzinfante iker@irzinfante.eu
 * @since       1.0.0
 */
public class Variable {
	
	private double objCoeff;
	private boolean integer;
	private double upperBound;
	private double lowerBound;
	
	/**
	 * @author      irzinfante iker@irzinfante.eu
	 * @since       1.0.0
	 */
	public static class VarBuilder {
		
		private double objCoeff;
		private boolean integer;
		private double upperBound;
		private double lowerBound;
		
		/**
		 * Initializes a Variable object builder
		 * giving zero value to its coefficient
		 * in the objective function
		 *
		 * @since	1.0.0
		 */
		public VarBuilder() {
			this(0);
		}
		
		/**
		 * Initializes a Variable object builder
		 *
		 * @param	objCoeff Coefficient of the variable in
		 * the objective function
		 * 
		 * @since	1.0.0
		 */
		public VarBuilder(double objCoeff) {
			
			this.objCoeff = objCoeff;
			this.integer = false;
			this.upperBound = Double.MAX_VALUE;
			this.lowerBound = 0;
		}
		
		/**
		 * Used to indicate that an integer variable is
		 * being created
		 * 
		 * @return VarBuilder for an integer variable
		 * 
		 * @since	1.0.0
		 */
		public VarBuilder integer() {
			
			this.integer = true;
			
			return this;
		}
		
		/**
		 * Used to indicate that a binary variable is
		 * being created
		 * 
		 * @return binary variable
		 * 
		 * @since	1.0.0
		 */
		public Variable binary() {
			
			this.integer = true;
			this.upperBound = 1;
			
			return this.build();
		}
		
		/**
		 * Sets the upper bound of the variable
		 * 
		 * @param	upper	Value of the upper bound
		 * @return VarBuilder for an upper-bounded variable
		 * 
		 * @since	1.0.0
		 */
		public VarBuilder upperBound(double upper) {
			
			this.upperBound = upper;
			
			return this;
		}
		
		
		/**
		 * Sets the lower bound of the variable
		 * 
		 * @param	lower	Value of the lower bound
		 * @return	VarBuilder for a lower-bounded variable
		 * 
		 * @since	1.0.0
		 */
		public VarBuilder lowerBound(double lower) {
			
			this.lowerBound = lower;
			
			return this;
		}
		
		
		/**
		 * Returns the Variable object created
		 * 
		 * @return	variable of a LP problem
		 * 
		 * @since	1.0.0
		 */
		public Variable build() {
			
			Variable var = new Variable();
			
			var.setObjCoeff(this.objCoeff);
			var.setInteger(this.integer);
			var.setUpperBound(this.upperBound);
			var.setLowerBound(this.lowerBound);
			
			return var;
		}
		
	}
	
	private Variable() {
	}
	
	public double getObjCoeff() {
		return objCoeff;
	}
	
	private void setObjCoeff(double objCoeff) {
		this.objCoeff = objCoeff;
	}
	
	public boolean isInteger() {
		return integer;
	}
	
	private void setInteger(boolean integer) {
		this.integer = integer;
	}
	
	public double getUpperBound() {
		return upperBound;
	}
	
	private void setUpperBound(double upperBound) {
		this.upperBound = upperBound;
	}
	
	public double getLowerBound() {
		return lowerBound;
	}
	
	private void setLowerBound(double lowerBound) {
		this.lowerBound = lowerBound;
	}
	
}