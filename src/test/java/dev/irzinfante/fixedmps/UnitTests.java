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

import dev.irzinfante.fixedmps.constant.ConstraintType;
import dev.irzinfante.fixedmps.core.Constraint;
import dev.irzinfante.fixedmps.core.Variable;
import dev.irzinfante.fixedmps.core.Constraint.ConstraintBuilder;
import dev.irzinfante.fixedmps.core.Variable.VarBuilder;

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
		assertEquals(ConstraintType.G, constraint1.getType());

		Constraint constraint2 = new ConstraintBuilder(-2, 2).lessThan(13);
		assertArrayEquals(new double[] {-2, 2}, constraint2.getCoeffs(), 0);
		assertEquals(13, constraint2.getFree(), 0);
		assertEquals(ConstraintType.L, constraint2.getType());

		Constraint constraint3 = new ConstraintBuilder(3.14).equalTo(0);
		assertArrayEquals(new double[] {3.14}, constraint3.getCoeffs(), 0);
		assertEquals(0, constraint3.getFree(), 0);
		assertEquals(ConstraintType.E, constraint3.getType());
	}
	
}
