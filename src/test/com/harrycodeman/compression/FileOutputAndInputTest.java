package test.com.harrycodeman.compression;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class FileOutputAndInputTest {
    private final String TEMP_FILE_NAME = "temp";

    @Test
    public void testFileOutputAndInput() throws Exception {
        try {
            int[] bytes = new int[] { 97, 98, 99, 100 };
            // Write to file
            OutputStream output = new FileOutputStream(TEMP_FILE_NAME);
            for (int b : bytes) {
                output.write(b);
            }
            output.close();
            // Read from file
            InputStream input = new FileInputStream(TEMP_FILE_NAME);
            List<Integer> result = new ArrayList<Integer>();
            int b = input.read();
            while (b != -1) {
                result.add(b);
                b = input.read();
            }
            input.close();

            assertArrayEquals(new Object[] { 97, 98, 99, 100 }, result.toArray());
        }
        finally {
            File f = new File(TEMP_FILE_NAME);
            f.delete();
        }
    }
}
