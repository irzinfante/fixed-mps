package eu.irzinfante.fixedmps.core;

/**
 * @author      irzinfante contacto@irzinfante.eu
 * @version     1.0
 * @since       1.0
 */
public class Constrain {
	
	private double coeffs[];
	private char type;
	private double free;
	
	/**
	 * @author      irzinfante contacto@irzinfante.eu
	 * @version     1.0
	 * @since       1.0
	 */
	public static class ConstrainBuilder {
		
		private double coeffs[];
		
		/**
    	 * Initializes a Constrain object builder
    	 *
    	 * @param	coeffs Coefficients of the variables in the
    	 * same order they are added to the Problem object builder.
    	 * Extra coefficients are ignored and, if there are less
    	 * coefficients than variables, missing coefficients
    	 * are set to zero
    	 * 
    	 * @since	1.0
    	 */
		public ConstrainBuilder (double ... coeffs) {
			
			this.coeffs = coeffs;
		}
		
		/**
    	 * Creates an inequation constrain (≥ k)
    	 *
    	 * @param	free The free term of the inequation, k
    	 * 
    	 * @since	1.0
    	 */
		public Constrain lessThan(double free) {
			
			Constrain constrain = new Constrain();
			
			constrain.setCoeffs(this.coeffs);
			constrain.setType('L');
			constrain.setFree(free);
			
			return constrain;
		}
		
		/**
    	 * Creates an inequation constrain (≤ k)
    	 *
    	 * @param	free The free term of the inequation, k
    	 * 
    	 * @since	1.0
    	 */
		public Constrain greaterThan(double free) {
			
			Constrain constrain = new Constrain();
			
			constrain.setCoeffs(this.coeffs);
			constrain.setType('G');
			constrain.setFree(free);
			
			return constrain;
		}
		
		/**
    	 * Creates a equation constrain
    	 *
    	 * @param	free The free term of the equation
    	 * 
    	 * @since	1.0
    	 */
		public Constrain equalTo(double free) {
			
			Constrain constrain = new Constrain();
			
			constrain.setCoeffs(this.coeffs);
			constrain.setType('E');
			constrain.setFree(free);
			
			return constrain;
		}
		
	}
	
	private Constrain() {
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
