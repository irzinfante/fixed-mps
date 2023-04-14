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

import java.util.ArrayList;
import java.util.List;
import eu.irzinfante.fixedmps.core.Constraint.ConstraintBuilder;

/**
 * @author      irzinfante iker@irzinfante.eu
 * @since       1.0.0
 */
public class Problem {
	
	private Variable columns[];
	private Constraint rows[];
	
	/**
	 * @author      irzinfante iker@irzinfante.eu
	 * @since       1.0.0
	 */
	public static class ProblemBuilder {
		
		private Variable columns[];
		private List<Constraint> rows;
		private int numVar;
		
		/**
		 * Initializes a Problem object builder
		 *
		 * @param	variables Variables of the problem
		 * 
		 * @since	1.0.0
		 */
		public ProblemBuilder(Variable ... variables) {
			
			this.columns = variables;
			this.rows = new ArrayList<>();
			this.numVar = variables.length;
		}
		
		/**
		 * Adds a constraint to the problem
		 *
		 * @param	constraint	Constraint to add to the problem
		 * @return	ProblemBuilder with the added constraint
		 * 
		 * @since	1.0.0
		 */
		public ProblemBuilder addConstraint(Constraint constraint) {
			
			double coeffs[] = new double[numVar];
			for(int i = 0; i < Math.min(numVar, constraint.getCoeffs().length); i++) {
				coeffs[i] = constraint.getCoeffs()[i];
			}
			
			switch (constraint.getType()) {
				case E:
					rows.add(new ConstraintBuilder(coeffs).equalTo(constraint.getFree()));
					break;
				case L:
					rows.add(new ConstraintBuilder(coeffs).lessThan(constraint.getFree()));
					break;
				case G:
					rows.add(new ConstraintBuilder(coeffs).greaterThan(constraint.getFree()));
					break;
			}
			
			return this;
		}
		
		/**
		 * Returns the created Problem object
		 * 
		 * @return	LP problem
		 * 
		 * @since	1.0.0
		 */
		public Problem build() {
			
			Problem lp = new Problem();
			
			Constraint rows[] = new Constraint[this.rows.size()];
			for(int i = 0; i < this.rows.size(); i++) {
				rows[i] = this.rows.get(i);
			} 
			
			lp.setColumns(this.columns);
			lp.setRows(rows);
			
			return lp;
		}
		
	}
	
	private Problem() {
	}
	
	public Variable[] getColumns() {
		return columns;
	}
	
	private void setColumns(Variable[] columns) {
		this.columns = columns;
	}
	
	public Constraint[] getRows() {
		return rows;
	}
	
	private void setRows(Constraint rows[]) {
		this.rows = rows;
	}
	
}