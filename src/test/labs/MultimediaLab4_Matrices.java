package test.labs;

import com.harrycodeman.compression.colorspaces.SquareMatrix;
import org.junit.Test;

import java.io.*;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sqrt;

public class MultimediaLab4_Matrices {
    @Test
    public void generateMatrices() throws IOException {
        SquareMatrix result = new SquareMatrix(8);

        for (int q = 0; q < 8; q++) {
            result.set(0, q, 1 / sqrt(8.0));
        }
        for (int p = 1; p < 8; p++) {
            for (int q = 0; q < 8; q++) {
                result.set(p, q, sqrt(2.0 / 8.0) * cos((PI * (2.0 * q + 1.0) * p) / 16.0));
            }
        }

        Writer writer = new FileWriter("./data/multimedia/dct.txt");
        for (int p = 0; p < 8; p++) {
            for (int q = 0; q < 8; q++) {
                writer.write("" + result.get(p, q) + ", ");
            }
            writer.write("\n");
        }
        writer.close();

        writer = new FileWriter("./data/multimedia/t-dct.txt");
        for (int q = 0; q < 8; q++) {
            for (int p = 0; p < 8; p++) {
                writer.write("" + result.get(p, q) + ", ");
            }
            writer.write("\n");
        }
        writer.close();
    }
}
