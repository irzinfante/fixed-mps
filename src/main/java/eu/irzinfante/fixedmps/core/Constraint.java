package eu.irzinfante.fixedmps.core;

/**
 * @author      irzinfante contacto@irzinfante.eu
 * @version     1.0
 * @since       1.0
 */
public class Constraint {
	
	private double coeffs[];
	private char type;
	private double free;
	
	/**
	 * @author      irzinfante contacto@irzinfante.eu
	 * @version     1.0
	 * @since       1.0
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
    	 * @since	1.0
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
    	 * @since	1.0
    	 */
		public Constraint lessThan(double free) {
			
			Constraint constraint = new Constraint();
			
			constraint.setCoeffs(this.coeffs);
			constraint.setType('L');
			constraint.setFree(free);
			
			return constraint;
		}
		
		/**
    	 * Creates an inequation constraint (≥ k)
    	 *
    	 * @param	free The free term of the inequation, k
    	 * @return	a greater-than type constraint
    	 * 
    	 * @since	1.0
    	 */
		public Constraint greaterThan(double free) {
			
			Constraint constraint = new Constraint();
			
			constraint.setCoeffs(this.coeffs);
			constraint.setType('G');
			constraint.setFree(free);
			
			return constraint;
		}
		
		/**
    	 * Creates a equation constraint
    	 *
    	 * @param	free The free term of the equation
    	 * @return	a equality type constraint
    	 * 
    	 * @since	1.0
    	 */
		public Constraint equalTo(double free) {
			
			Constraint constraint = new Constraint();
			
			constraint.setCoeffs(this.coeffs);
			constraint.setType('E');
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

	public char getType() {
		return type;
	}

	private void setType(char type) {
		this.type = type;
	}

	public double getFree() {
		return free;
	}

	private void setFree(double free) {
		this.free = free;
	}

}
