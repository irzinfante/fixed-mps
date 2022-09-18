package eu.irzinfante.fixedmps.util;

import java.util.HashMap;
import java.util.Map;

import eu.irzinfante.fixedmps.core.Problem;

/**
 * @author      irzinfante contacto@irzinfante.eu
 * @version     1.0.0
 * @since       1.0.0
 */
public class MPSUtil {
	
	private static final String TITLE = "FIXEDMPS";
	private static final String COST = "OBJ";
	private static final String INT1 = "INT1";
	private static final String RHS1 = "RHS1";
	private static final String BND1 = "BND1";
	
	private static final String NAME = "NAME";
	private static final String ROWS = "ROWS";
	private static final String COLUMNS = "COLUMNS";
	private static final String RHS = "RHS";
	private static final String BOUNDS = "BOUNDS";
	private static final String ENDATA = "ENDATA";
	
	private MPSUtil() {
	}
	
	/**
	 * Creates a MPS file from a Problem
	 *
	 * @param	problem	The LP problem from which to generate the MPS file
	 * @return	MPS file in a String
	 * @since	1.0.0
	 */
	public static String obtainMPSfile(Problem problem) {
			
		String MPS = String.format("%-14.14s%-8.8s%n", NAME, TITLE);
		
		MPS += String.format("%-14.14s%n", ROWS);
		MPS += String.format(" %-2.2s %-8.8s%n", 'N', COST);
		for(int c = 0; c < problem.getRows().length; c++) {
			MPS += String.format(" %-2.2s %-8.8s%n", problem.getRows()[c].getType(), String.format("C%07d", c+1));
		}
		
		MPS += String.format("%-14.14s%n", COLUMNS);
		String integerPart = "";
		String realPart = "";
		String column;
		String var;
		Map<String, Double> map;
		for(int x = 0; x < problem.getColumns().length; x++) {
			column = "";
			var = String.format("X%07d", x+1);
			map = new HashMap<String, Double>();
			
			if(problem.getColumns()[x].getObjCoeff() != 0) {
				map.put(COST, bound(problem.getColumns()[x].getObjCoeff()));
			}
			
			for(int c = 0; c < problem.getRows().length; c++) {
				if(problem.getRows()[c].getCoeffs()[x] != 0) {
					map.put(String.format("C%07d", c+1), bound(problem.getRows()[c].getCoeffs()[x]));
				}
			}
			
			boolean odd = true;
			for(String key : map.keySet()) {
				if(odd) {
					column += String.format("    %-8.8s  %-8.8s  %+.5e", var, key, bound(map.get(key)));
				} else {
					column += String.format("   %-8.8s  %+.5e%n", key, bound(map.get(key)));
				}
				odd = !odd;
			}
			if(!odd) column += String.format("%n", 0);
			if(problem.getColumns()[x].isInteger()) integerPart += column;
			else realPart += column;
		}
		if(!integerPart.equals("")) {
			MPS += String.format("    %-8.8s  %-8.8s                 %-8.8s%n", INT1, "'MARKER'", "'INTORG'");
			MPS += integerPart;
			MPS += String.format("    %-8.8s  %-8.8s                 %-8.8s%n", INT1+"END", "'MARKER'", "'INTEND'");
		}
		MPS += realPart;
		
		MPS += String.format("%-14.14s%n", RHS);
		for(int c = 0; c < problem.getRows().length; c++) {
			MPS += String.format("    %-8.8s  %-8.8s  %+.5e%n", RHS1, String.format("C%07d", c+1), bound(problem.getRows()[c].getFree()));
		}
		
		MPS += String.format("%-14.14s%n", BOUNDS);
		for(int x = 0; x < problem.getColumns().length; x++) {
			if(problem.getColumns()[x].getLowerBound() != 0) {
				MPS += String.format(" %-2.2s %-8.8s  %-8.8s  %+.5e%n", "LO", BND1, String.format("X%07d", x+1), bound(problem.getColumns()[x].getLowerBound()));
			}
			if((problem.getColumns()[x].isInteger() && problem.getColumns()[x].getUpperBound() != 1)
				||	(!problem.getColumns()[x].isInteger() && problem.getColumns()[x].getUpperBound() < Double.MAX_VALUE)) {
				
				MPS += String.format(" %-2.2s %-8.8s  %-8.8s  %+.5e%n", "UP", BND1, String.format("X%07d", x+1), bound(problem.getColumns()[x].getUpperBound()));
			}
		}
		
		MPS += String.format("%-14.14s", ENDATA);
		
		return MPS.replaceAll(",", ".").trim();
	}
	
	static private double bound(double num) {
		if( num == 0) {
			return 0;
		} else if (num > 0) {
			return Math.max(Math.min(Math.pow(10, 10), num), Math.pow(10, -10));
		} else {
			return Math.min(Math.max(-Math.pow(10, 10), num), -Math.pow(10, -10));
		}
	}
	
}