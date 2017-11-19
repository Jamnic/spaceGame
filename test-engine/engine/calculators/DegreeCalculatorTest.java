package engine.calculators;

import model.Coords;

import static org.junit.Assert.*;
import org.junit.Test;

public class DegreeCalculatorTest {

    private static final double DELTA = 0.00001;

    @Test
    public void testCalculateHeadingAngle() {
        assertEquals(0, DegreeCalculator.calculateHeadingAngle(new Coords(0, 0, 0), new Coords(0, 0, 10)), DELTA);
        assertEquals(45, DegreeCalculator.calculateHeadingAngle(new Coords(0, 0, 0), new Coords(10, 0, 10)), DELTA);
        assertEquals(315, DegreeCalculator.calculateHeadingAngle(new Coords(0, 0, 0), new Coords(-10, 0, 10)), DELTA);
        assertEquals(180, DegreeCalculator.calculateHeadingAngle(new Coords(0, 0, 0), new Coords(0, 0, -10)), DELTA);
        assertEquals(0, DegreeCalculator.calculateHeadingAngle(new Coords(10, 0, 0), new Coords(10, 0, 10)), DELTA);
        assertEquals(0, DegreeCalculator.calculateHeadingAngle(new Coords(10, 0, 0), new Coords(20, 0, 0)), DELTA);
    }
    
    @Test
    public void test() {
        assertEquals(0, DegreeCalculator.rotationXChange(90, 90, 2), DELTA);
        assertEquals(2, DegreeCalculator.rotationXChange(180, 90, 2), DELTA);
        assertEquals(-2, DegreeCalculator.rotationXChange(0, 90, 2), DELTA);
        assertEquals(2, DegreeCalculator.rotationXChange(0, 270, 2), DELTA);
    }
    
}
