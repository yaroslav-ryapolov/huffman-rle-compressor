package test.com.harrycodeman.compression.longarithmetic;

import com.harrycodeman.compression.longarithmetic.LongNumber;
import com.harrycodeman.compression.longarithmetic.LongNumberDivideResult;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LongArithmeticTest {
    @Test
    public void testConvert() throws Exception {
        String toConvert = "17782432165291732";
        Object[] expected = { 16561180, 544499412 };
    }

    @Test
    public void testAdd() throws Exception {
        LongNumber one = new LongNumber(835201792, 942);
        LongNumber two = new LongNumber(656920271, 165);
        LongNumber expected = new LongNumber(418380239, 1108);
        assertEquals(expected, one.add(two));
    }

    @Test
    public void testSubtract() throws Exception {
        LongNumber one = new LongNumber(835201792, 942);
        LongNumber two = new LongNumber(656920271, 165);
        LongNumber expected = new LongNumber(178281521, 777);
        assertEquals(expected, one.subtract(two));
    }

    @Test
    public void testMultiply() throws Exception {
        LongNumber one = new LongNumber(835201792, 942);
        LongNumber two = new LongNumber(656920271, 165);
        LongNumber expected = new LongNumber(544523520, 150185405, 156135);
        assertEquals(expected, one.multiply(two));
    }

    @Test
    public void testDivide() throws Exception {
        LongNumber one = new LongNumber(835201792, 942);
        LongNumber two = new LongNumber(656920271, 165);
        LongNumber quotient = new LongNumber(5);
        LongNumber rest = new LongNumber(771825909, 114);
        LongNumberDivideResult expected = new LongNumberDivideResult(quotient, rest);
        assertEquals(expected, one.divide(two));
    }

    @Test
    public void testIsGreaterThanOrEqual() throws Exception {
        LongNumber one = new LongNumber(835201792, 942);
        LongNumber two = new LongNumber(656920271, 165);
        assertTrue(one.isGreaterThanOrEqual(two));
        assertTrue(one.isGreaterThanOrEqual(one));
        assertFalse(two.isGreaterThanOrEqual(one));
    }
}
