package eu.irzinfante.fixedmps;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.irzinfante.fixedmps.core.Problem;
import eu.irzinfante.fixedmps.core.Problem.ProblemBuilder;
import eu.irzinfante.fixedmps.core.Variable.VarBuilder;
import eu.irzinfante.fixedmps.util.MPSUtil;
import eu.irzinfante.fixedmps.core.Constraint.ConstraintBuilder;

public class MPSTest {
	
	@Test
	public void defaultTest() {
		Problem lp = new ProblemBuilder(
			
		    new VarBuilder(-1).lowerBound(0).upperBound(Double.MAX_VALUE).integer().build(),
		    new VarBuilder(-1).lowerBound(0).upperBound(Double.MAX_VALUE).integer().build()
		    
		)
		.addConstraint(new ConstraintBuilder(-2,  2).greaterThan(1))
		.addConstraint(new ConstraintBuilder(-8, 10).lessThan(13))
		.build();
		
		String resultingMPS = MPSUtil.obtainMPSfile(lp);
		String expectedMPS = String.join("\n",
			"NAME          FIXEDMPS",
			"ROWS          ",
			" N  OBJ     ",
			" G  C0000001",
			" L  C0000002",
			"COLUMNS       ",
			"    INT1      'MARKER'                 'INTORG'",
			"    X0000001  C0000002  -8.00000e+00   C0000001  -2.00000e+00",
			"    X0000001  OBJ       -1.00000e+00",
			"    X0000002  C0000002  +1.00000e+01   C0000001  +2.00000e+00",
			"    X0000002  OBJ       -1.00000e+00",
			"    INT1END   'MARKER'                 'INTEND'",
			"RHS           ",
			"    RHS1      C0000001  +1.00000e+00",
			"    RHS1      C0000002  +1.30000e+01",
			"BOUNDS        ",
			" UP BND1      X0000001  +1.00000e+10",
			" UP BND1      X0000002  +1.00000e+10",
			"ENDATA"
		);
		
		assertEquals(expectedMPS, resultingMPS);
	}
	
}
