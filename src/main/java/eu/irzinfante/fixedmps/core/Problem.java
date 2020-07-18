package eu.irzinfante.fixedmps.core;

import java.util.ArrayList;
import java.util.List;
import eu.irzinfante.fixedmps.core.Constrain.ConstrainBuilder;

/**
 * @author      irzinfante contacto@irzinfante.eu
 * @version     1.0
 * @since       1.0
 */
public class Problem {

	private Variable columns[];
	private Constrain rows[];
	
	/**
	 * @author      irzinfante contacto@irzinfante.eu
	 * @version     1.0
	 * @since       1.0
	 */
	public static class ProblemBuilder {
		
		private Variable columns[];
		private List<Constrain> rows;
		private int numVar;
		
		/**
    	 * Initializes a Problem object builder
    	 *
    	 * @param	variables Variables of the problem
    	 * 
    	 * @since	1.0
    	 */
		public ProblemBuilder(Variable ... variables) {
			
			this.columns = variables;
			this.rows = new ArrayList<>();
			this.numVar = variables.length;
		}
		
		/**
    	 * Adds a constrain to the problem
    	 * 
    	 * @since	1.0
    	 */
		public ProblemBuilder addConstrain(Constrain constrain) {
			
			double coeffs[] = new double[numVar];
			for(int i = 0; i < Math.min(numVar, constrain.getCoeffs().length); i++) {
				coeffs[i] = constrain.getCoeffs()[i];
			}
			
			switch (constrain.getType()) {
			case 'E':
				rows.add(new ConstrainBuilder(coeffs).equalTo(constrain.getFree()));
				break;
			case 'L':
				rows.add(new ConstrainBuilder(coeffs).lessThan(constrain.getFree()));
				break;
			case 'G':
				rows.add(new ConstrainBuilder(coeffs).greaterThan(constrain.getFree()));
				break;
			}
			
			return this;
		}
		
		/**
    	 * Returns the Problem object created
    	 * 
    	 * @since	1.0
    	 */
		public Problem build() {

            Problem lp = new Problem();
            
            Constrain rows[] = new Constrain[this.rows.size()];
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

	public Constrain[] getRows() {
		return rows;
	}

	private void setRows(Constrain rows[]) {
		this.rows = rows;
	}
}
