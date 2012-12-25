package test.com.harrycodeman.compression.colorspaces;

import com.harrycodeman.compression.colorspaces.SquareMatrix;
import org.junit.Test;

import static com.harrycodeman.compression.colorspaces.DctMatrices.forwardDct;
import static com.harrycodeman.compression.colorspaces.DctMatrices.inverseDct;
import static junit.framework.Assert.assertEquals;

public class DctTest {
    @Test
    public void testForwardDct() throws Exception {
        assertEquals(
                new SquareMatrix(2,
                        5, -1,
                        -2, 0
                ),
                forwardDct(
                        new SquareMatrix(2,
                                1, 2,
                                3, 4
                        )
                )
        );
    }

    @Test
    public void testInverseDct() throws Exception {
        assertEquals(
                new SquareMatrix(2,
                        1, 2,
                        3, 4
                ),
                inverseDct(
                        new SquareMatrix(2,
                                5, -1,
                                -2, 0
                        )
                )
        );
    }

    @Test
    public void testMatrixDivideByElement() {
        assertEquals(
                new SquareMatrix(2,
                        15.875 , 0,
                        0, 0
                ),
                new SquareMatrix(2,
                        254, 0,
                        0, 0
                ).divideByElement(
                        new SquareMatrix(2,
                                16, 16,
                                16, 16
                        )
                )
        );
    }

    @Test
    public void testMatrixMultiplyByElement() {
        assertEquals(
                new SquareMatrix(2,
                        254, 0,
                        0, 0
                ),
                new SquareMatrix(2,
                        15.875 , 0,
                        0, 0
                ).multiplyByElement(
                        new SquareMatrix(2,
                                16, 16,
                                16, 16
                        )
                )
        );
    }
}
