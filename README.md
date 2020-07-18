# Usage of the library
Let's put it simple. Having the following linear programming (LP) problem,

<img src="https://latex.codecogs.com/gif.latex?\newline%20min%20\;%20Z%20=%20-4X_1%20+%202X_2%20-%207X_3%20+%20X_4%20\newline%20\;%20X_1%20+%205X_3%20+%207X_4%20\le%2010%20\newline%20X_1%20+%20X_2%20-%20X_3%20\le%201%20\newline%20X_1%20+%202X_2%20+%202X_4%20\le%2010%20\newline%20X_1,%20X_2,%20X_3,%20X_4%20\in%20\{0,%201\}">

our goal is to generate, in the most simple and intuitive way, a fixed-format MPS file representing the problem.

Using the ``fixed-mps`` library the instruction for building the LP problem is the following:

```java
import eu.irzinfante.fixedmps.core.Problem;
import eu.irzinfante.fixedmps.core.Problem.ProblemBuilder;
import eu.irzinfante.fixedmps.core.Variable.VarBuilder;
import eu.irzinfante.fixedmps.core.Constraint.ConstraintBuilder;

Problem lp = new ProblemBuilder(

	new VarBuilder(-4).binary(),
	new VarBuilder(2).binary(),
	new VarBuilder(-7).binary(),
	new VarBuilder(1).binary()
	
)
.addConstraint(new ConstraintBuilder( 1, 0, 5, 7).lessThan(10))
.addConstraint(new ConstraintBuilder( 1, 1,-1, 0).lessThan(1))
.addConstraint(new ConstraintBuilder( 1, 2, 0, 2).lessThan(3))
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
 L  C0000001
 L  C0000002
 L  C0000003
COLUMNS       
    INT1      'MARKER'                 'INTORG'
    X0000001  C0000002  +1.00000e+00   C0000001  +1.00000e+00
    X0000001  OBJ       -4.00000e+00   C0000003  +1.00000e+00
    X0000002  C0000002  +1.00000e+00   OBJ       +2.00000e+00
    X0000002  C0000003  +2.00000e+00
    X0000003  C0000002  -1.00000e+00   C0000001  +5.00000e+00
    X0000003  OBJ       -7.00000e+00
    X0000004  C0000001  +7.00000e+00   OBJ       +1.00000e+00
    X0000004  C0000003  +2.00000e+00
    INT1END   'MARKER'                 'INTEND'
RHS           
    RHS1      C0000001  +1.00000e+01
    RHS1      C0000002  +1.00000e+00
    RHS1      C0000003  +3.00000e+00
BOUNDS        
 UP BND1      X0000001  +1.00000e+00
 UP BND1      X0000002  +1.00000e+00
 UP BND1      X0000003  +1.00000e+00
 UP BND1      X0000004  +1.00000e+00
ENDATA
```
