package test.labs;

import com.harrycodeman.compression.CompressInput;
import com.harrycodeman.compression.huffman.CompressPrefixCodesTable;
import com.harrycodeman.compression.huffman.HuffmanDecompressInput;
import com.harrycodeman.compression.huffman.StreamHuffmanCompressOutput;
import com.harrycodeman.compression.rle.*;
import org.junit.Test;

import java.io.*;
import java.util.List;

public class EncryptionLab1_HuffmanAndRle {
    @Test
    public void compressAndDecompress() throws Exception {
        compressAndDecompress("./data/one.txt", "./data/encryption/one");
        compressAndDecompress("./data/two.txt", "./data/encryption/two");
    }

    public void compressAndDecompress(String filePath, String savePath) throws Exception {
// COMPRESSION
        CompressInput toCompress = new CompressInput(new FileInputStream(filePath));
// ----- RLE
        BytesRleCompressor rleCompressor = new BytesRleCompressor(toCompress);
        List<BytesBlock> compressedByRle = rleCompressor.compress();
        // Log
        String rleLog = BytesBlocksToHexStringConverter.convert(compressedByRle);
        Writer rleLogWriter = new FileWriter(savePath + ".rle.log");
        rleLogWriter.write(rleLog);
        rleLogWriter.close();
// ----- Huffman
        BytesBlockCompressInput toCompressByHuffman = new BytesBlockCompressInput(compressedByRle);
        CompressPrefixCodesTable prefixCodesTable = CompressPrefixCodesTable
                .buildPrefixTableForUncompressedStream(toCompressByHuffman);
        // Log tree
        String treeLog = prefixCodesTable.getRoot().toString();
        Writer treeLogWriter = new FileWriter(savePath + ".huffman-tree.log");
        treeLogWriter.write(treeLog);
        treeLogWriter.close();
        // Output
        StreamHuffmanCompressOutput output = new StreamHuffmanCompressOutput(
                new FileOutputStream(savePath + ".compressed"), prefixCodesTable
        );
        output.output();

// DECOMPRESSION
        HuffmanDecompressInput huffmanDecompressor = new HuffmanDecompressInput(
                new FileInputStream(savePath + ".compressed"));
        huffmanDecompressor.decompress();
        CompressInput toRleDecompression = new CompressInput(huffmanDecompressor.decompressedBytes);
        BytesRleDecompressor rleDecompressor = new BytesRleDecompressor(toRleDecompression);
        List<Integer> decompressed = rleDecompressor.decompress();
        // Output
        OutputStream decompressedStream = new FileOutputStream(savePath + ".decompressed.txt");
        for (int b : decompressed) {
            decompressedStream.write(b);
        }
    }
}
