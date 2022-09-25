package eu.irzinfante.fixedmps;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.irzinfante.fixedmps.core.Constraint;
import eu.irzinfante.fixedmps.core.Constraint.ConstraintBuilder;
import eu.irzinfante.fixedmps.core.Variable;
import eu.irzinfante.fixedmps.core.Variable.VarBuilder;

public class UnitTests {

	@Test
	public void variableTest() {
		Variable variable1 = new VarBuilder().build();
		assertEquals(0, variable1.getObjCoeff(), 0);
		assertEquals(0, variable1.getLowerBound(), 0);
		assertEquals(Double.MAX_VALUE, variable1.getUpperBound(), 0);
		assertFalse(variable1.isInteger());
		
		Variable variable2 = new VarBuilder(1).lowerBound(-10).upperBound(5).integer().build();
		assertEquals(1, variable2.getObjCoeff(), 0);
		assertEquals(-10, variable2.getLowerBound(), 0);
		assertEquals(5, variable2.getUpperBound(), 0);
		assertTrue(variable2.isInteger());
		
		Variable variable3 = new VarBuilder(-5).binary();
		assertEquals(-5, variable3.getObjCoeff(), 0);
		assertEquals(0, variable3.getLowerBound(), 0);
		assertEquals(1, variable3.getUpperBound(), 0);
		assertTrue(variable3.isInteger());
	}
	
	@Test
	public void constraintTest() {
		Constraint constraint1 = new ConstraintBuilder(0, 1, 2).greaterThan(1);
		assertArrayEquals(new double[] {0, 1, 2}, constraint1.getCoeffs(), 0);
		assertEquals(1, constraint1.getFree(), 0);
		assertEquals('G', constraint1.getType());

		Constraint constraint2 = new ConstraintBuilder(-2, 2).lessThan(13);
		assertArrayEquals(new double[] {-2, 2}, constraint2.getCoeffs(), 0);
		assertEquals(13, constraint2.getFree(), 0);
		assertEquals('L', constraint2.getType());

		Constraint constraint3 = new ConstraintBuilder(3.14).equalTo(0);
		assertArrayEquals(new double[] {3.14}, constraint3.getCoeffs(), 0);
		assertEquals(0, constraint3.getFree(), 0);
		assertEquals('E', constraint3.getType());
	}
	
}
