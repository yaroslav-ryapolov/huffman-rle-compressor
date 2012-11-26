package com.harrycodeman.huffman;

import com.harrycodeman.ICompressByteStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.sort;

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
        List<ICountedObject> huffmanedBytes = new ArrayList<ICountedObject>(mapOfBytes.values());
        while (huffmanedBytes.size() > 1) {
            sort(huffmanedBytes);
            huffmanedBytes.add(new HuffmanTreeNode(popFirst(huffmanedBytes), popFirst(huffmanedBytes)));
        }
        root = extractRoot(huffmanedBytes.get(0));
    }

    private static HuffmanTreeNode popFirst(List<ICountedObject> list) {
        ICountedObject result = list.get(0);
        list.remove(0);
        if (result.getClass() == HuffmanedByte.class) {
            return new HuffmanTreeNode((HuffmanedByte)result);
        }
        return (HuffmanTreeNode)result;
    }

    private static HuffmanTreeNode extractRoot(ICountedObject value) {
        if (value.getClass() == HuffmanedByte.class) {
            return new HuffmanTreeNode((HuffmanedByte)value);
        }
        return (HuffmanTreeNode)value;
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
