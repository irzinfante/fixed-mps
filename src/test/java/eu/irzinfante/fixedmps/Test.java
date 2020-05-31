package eu.irzinfante.fixedmps;

import eu.irzinfante.fixedmps.core.Constrain.ConstrainBuilder;
import eu.irzinfante.fixedmps.core.Problem;
import eu.irzinfante.fixedmps.core.Problem.ProblemBuilder;
import eu.irzinfante.fixedmps.core.Variable.VarBuilder;
import eu.irzinfante.fixedmps.util.MPSUtil;

class Test {

	@org.junit.jupiter.api.Test
	void test() {
		
		Problem lp = new ProblemBuilder(
				
			new VarBuilder(-4).binary(),
			new VarBuilder(2).binary(),
			new VarBuilder(-7).binary(),
			new VarBuilder(1).binary()
			
		)
		.addConstrain(new ConstrainBuilder( 1, 0, 5, 7).lessThan(10))
		.addConstrain(new ConstrainBuilder( 1, 1,-1, 0).lessThan(1))
		.addConstrain(new ConstrainBuilder( 6, 5, 2, 0).lessThan(7))
		.addConstrain(new ConstrainBuilder( 1, 0, 2, 2).lessThan(3))
		.build();
		
		System.out.println(MPSUtil.obtainMPSfile(lp));
	}
}
