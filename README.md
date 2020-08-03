# Usage of the library
Let's put it simple. Having the following linear programming (LP) problem,

<img src="https://latex.codecogs.com/gif.latex?\newline%20min%20\;%20Z%20=%20-X_1%20-%20X_2\newline%20\;%20-2X_1%20+%202X_2%20\ge%201\newline%20-8X_1%20+%2010X_2\le%2013\newline%20X_1,%20X_2%20\ge%200,\;%20X_1,%20X_2%20\in%20\mathbb{Z}">

our goal is to generate, in the most simple and intuitive way, a fixed-format MPS file representing the problem.

Using the ``fixed-mps`` library the instruction for building the LP problem is the following:

```java
import eu.irzinfante.fixedmps.core.Problem;
import eu.irzinfante.fixedmps.core.Problem.ProblemBuilder;
import eu.irzinfante.fixedmps.core.Variable.VarBuilder;
import eu.irzinfante.fixedmps.core.Constraint.ConstraintBuilder;

Problem lp = new ProblemBuilder(

	new VarBuilder(-1).lowerBound(0).upperBound(Double.MAX_VALUE).integer().build(),
	new VarBuilder(-1).lowerBound(0).upperBound(Double.MAX_VALUE).integer().build()
	
)
.addConstraint(new ConstraintBuilder(-2,  2).greaterThan(1))
.addConstraint(new ConstraintBuilder(-8, 10).lessThan(13))
.build();
```

As we can see, the coefficient for each variable in the objective function is given at the time of declaring the variable. We tell also at this point if the variable is integer or binary (or we just build the variable to tell that it is a real).

Then we add the constraints for the problem row by row, giving the coeffient for each variable in order, an telling the type of the constraint (<img src="https://latex.codecogs.com/gif.latex?\le">, <img src="https://latex.codecogs.com/gif.latex?\ge"> or <img src="https://latex.codecogs.com/gif.latex?=">) and the value of the free term.

Now, to get the MPS file (as a String) from the problem we do this:
```java
import eu.irzinfante.fixedmps.util.MPSUtil;

MPSUtil.obtainMPSfile(lp)
```
The result in this case would be:
```fortran
NAME          FIXEDMPS
ROWS          
 N  OBJ     
 G  C0000001
 L  C0000002
COLUMNS       
    INT1      'MARKER'                 'INTORG'
    X0000001  C0000002  -8.00000e+00   C0000001  -2.00000e+00
    X0000001  OBJ       -1.00000e+00
    X0000002  C0000002  +1.00000e+01   C0000001  +2.00000e+00
    X0000002  OBJ       -1.00000e+00
    INT1END   'MARKER'                 'INTEND'
RHS           
    RHS1      C0000001  +1.00000e+00
    RHS1      C0000002  +1.30000e+01
BOUNDS        
 UP BND1      X0000001  +1.00000e+10
 UP BND1      X0000002  +1.00000e+10
ENDATA
```
