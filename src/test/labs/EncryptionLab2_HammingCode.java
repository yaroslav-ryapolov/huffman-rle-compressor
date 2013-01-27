package test.labs;

import com.harrycodeman.compression.hamming.HammingCode;
import com.harrycodeman.compression.hamming.UnrecoverableErrorsException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;

import static java.lang.String.format;

public class EncryptionLab2_HammingCode {
    @Test
    public void encode() throws Exception {
        Scanner input = new Scanner(new File("./data/hamming.txt"));
        int toEncode = Integer.valueOf(input.next(), 2);
        input.close();

        int encoded = HammingCode.encode16by11(toEncode);
        int infoBits = encoded >>> 5;
        int checkBits = encoded & 31;
        Writer output = new FileWriter("./data/encryption/hamming.encoded.txt");
        output.write(
                format("%1$11s", Integer.toBinaryString(infoBits)).replace(' ', '0') + " " +
                        format("%1$5s", Integer.toBinaryString(checkBits)).replace(' ', '0') + "\n"
        );
        output.close();
    }

    @Test
    public void decode() throws Exception {
        Scanner input = new Scanner(new File("./data/encryption/hamming.encoded.txt"));
        int infoBits = Integer.valueOf(input.next(), 2) << 5;
        int checkBits = Integer.valueOf(input.next(), 2) & 31;
        input.close();

        Writer output = new FileWriter("./data/encryption/hamming.decoded.txt");
        try {
            int decoded = HammingCode.decode16by11(infoBits | checkBits);
            output.write(format("%1$11s", Integer.toBinaryString(decoded)).replace(' ', '0') + "\n");
        }
        catch (UnrecoverableErrorsException ex) {
            output.write("Две или более ошибки!");
        }
        output.close();
    }
}
