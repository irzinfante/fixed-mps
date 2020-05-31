package eu.irzinfante.fixedmps.core;

/**
 * @author      irzinfante <contacto@irzinfante.eu>
 * @version     1.0
 * @since       1.0
 */
public class Variable {
	
	private double objCoeff;
	private boolean integer;
    private double upperBound;
    private double lowerBound;

    /**
     * @author      irzinfante <contacto@irzinfante.eu>
     * @version     1.0
     * @since       1.0
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
    	 * @since	1.0
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
    	 * @since	1.0
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
    	 * @since	1.0
    	 */
        public VarBuilder integer() {

        	this.integer = true;
            
            return this;
        }
        
        /**
    	 * Used to indicate that a binary variable is
    	 * being created
    	 * 
    	 * @since	1.0
    	 */
        public Variable binary() {

        	this.integer = true;
        	this.upperBound = 1;
            
            return this.build();
        }

        /**
    	 * Sets the upper bound of the variable
    	 * 
    	 * @param	upper Value of the upper bound
    	 * @since	1.0
    	 */
        public VarBuilder upperBound(double upper){
        	
            this.upperBound = upper;
            
            return this;
        }


        /**
    	 * Sets the lower bound of the variable
    	 * 
    	 * @param	Value of the lower bound
    	 * @since	1.0
    	 */
        public VarBuilder lowerBound(double lower){
        	
            this.lowerBound = lower;
            
            return this;
        }


        /**
    	 * Returns the Variable object created
    	 * 
    	 * @since	1.0
    	 */
        public Variable build(){

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