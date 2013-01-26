package test.labs;

import org.junit.Test;

import java.io.FileWriter;
import java.io.Writer;

public class EncryptionLab2_HammingCode {
    @Test
    public void generateMatrix() throws Exception {
        // TODO: build matrix for (64, 57) Haffman Code

        Writer output = new FileWriter("./data/encryption/haffmanMatrices.txt");
        output.close();
    }
}
