package com.harrycodeman.huffman;

import com.harrycodeman.ICompressByteStream;

import java.util.HashMap;
import java.util.Map;

public class PrefixCodesTableForCompress {
    protected static PrefixCodesTableForCompress getPrefixTableForMap(Map<Integer, HuffmanedByte> mapOfBytes) {
        PrefixCodesTableForCompress table = new PrefixCodesTableForCompress();
        table.mapOfBytes = mapOfBytes;
        table.buildHuffmanTree();
        return table;
    }

    public static PrefixCodesTableForCompress buildPrefixTable(ICompressByteStream stream) {
        PrefixCodesTableForCompress table = new PrefixCodesTableForCompress();
        table.countBytes(stream);
        table.buildHuffmanTree();
        return table;
    }

    private void countBytes(ICompressByteStream stream) {
        for (int b : stream) {
            getHuffmanedByteFor(b).incrementReps();
        }
    }

    private HuffmanedByte getHuffmanedByteFor(int b) {
        if (mapOfBytes.containsKey(b)) {
            return mapOfBytes.get(b);
        }
        else {
            HuffmanedByte h = new HuffmanedByte(b);
            mapOfBytes.put(b, h);
            return h;
        }
    }

    private void buildHuffmanTree() {
        // TODO: implement huffman tree build algorithm
    }

    private Map<Integer, HuffmanedByte> mapOfBytes = new HashMap<Integer, HuffmanedByte>();
    private HuffmanTreeNode root;

    protected PrefixCodesTableForCompress() {
    }

    public int size() {
        return mapOfBytes.size();
    }

    public int getRepsForByte(int b) {
        return mapOfBytes.get(b).getReps();
    }

    public HuffmanTreeNode getRoot() {
        return root;
    }

//    public BitSet compressByte(int b) {
//        // TODO: implement and use!!!
//        return null;
//    }
}
