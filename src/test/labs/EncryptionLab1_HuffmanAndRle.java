package test.labs;

import com.harrycodeman.compression.CompressInputImpl;
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
// COMPRESSION
        CompressInputImpl toCompress = new CompressInputImpl(new FileInputStream("./data/toCompress.txt"));
// ----- RLE
        BytesRleCompressor rleCompressor = new BytesRleCompressor(toCompress);
        List<BytesBlock> compressedByRle = rleCompressor.compress();
        // Log
        String rleLog = BytesBlocksToHexStringConverter.convert(compressedByRle);
        Writer rleLogWriter = new FileWriter("./data/encryption/rle.log");
        rleLogWriter.write(rleLog);
        rleLogWriter.close();
// ----- Huffman
        BytesBlockCompressInput toCompressByHuffman = new BytesBlockCompressInput(compressedByRle);
        CompressPrefixCodesTable prefixCodesTable = CompressPrefixCodesTable
                .buildPrefixTableForUncompressedStream(toCompressByHuffman);
        // Log tree
        String treeLog = prefixCodesTable.getRoot().toString();
        Writer treeLogWriter = new FileWriter("./data/encryption/huffman.tree.log");
        treeLogWriter.write(treeLog);
        treeLogWriter.close();
        // Output
        StreamHuffmanCompressOutput output = new StreamHuffmanCompressOutput(
                new FileOutputStream("./data/encryption/compressed"), prefixCodesTable
        );
        output.output();

// DECOMPRESSION
        HuffmanDecompressInput huffmanDecompressor = new HuffmanDecompressInput(
                new FileInputStream("./data/encryption/compressed"));
        huffmanDecompressor.decompress();
        CompressInputImpl toRleDecompression = new CompressInputImpl(huffmanDecompressor.decompressedBytes);
        BytesRleDecompressor rleDecompressor = new BytesRleDecompressor(toRleDecompression);
        List<Integer> decompressed = rleDecompressor.decompress();
        // Output
        OutputStream decompressedStream = new FileOutputStream("./data/encryption/decompressed.txt");
        for (int b : decompressed) {
            decompressedStream.write(b);
        }
    }
}
