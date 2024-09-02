/**
 * Library to easily generate fixed MPS files for LP solver
 * Copyright (C) 2020-2024 Iker Ruiz de Infante Gonzalez <iker@irzinfante.dev>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package dev.irzinfante.fixedmps;

import static org.junit.Assert.*;

import org.junit.Test;

import dev.irzinfante.fixedmps.core.Problem;
import dev.irzinfante.fixedmps.core.Constraint.ConstraintBuilder;
import dev.irzinfante.fixedmps.core.Problem.ProblemBuilder;
import dev.irzinfante.fixedmps.core.Variable.VarBuilder;
import dev.irzinfante.fixedmps.util.MPSUtil;

public class IntegrationsTests {
	
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
