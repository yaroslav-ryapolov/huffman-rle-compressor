package com.harrycodeman.compression.huffman;

import com.harrycodeman.compression.ICompressInput;

import java.util.*;

public class CompressPrefixCodesTable {
    public static CompressPrefixCodesTable buildPrefixTableForUncompressedStream(ICompressInput input) {
        CompressPrefixCodesTable table = new CompressPrefixCodesTable(input);
        table.countBytes(input);
        table.buildHuffmanTree();
        return table;
    }

    protected static CompressPrefixCodesTable getPrefixTableForMap(Map<Integer, HuffmanedByte> mapOfBytes) {
        CompressPrefixCodesTable table = new CompressPrefixCodesTable();
        table.mapOfBytes = mapOfBytes;
        table.buildHuffmanTree();
        return table;
    }

    private void countBytes(ICompressInput stream) {
        for (int b : stream) {
            getHuffmanedByteFor(b).incrementReps();
            totalCountOfBytes++;
        }
        stream.reset();
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
        TreeSet<ICountedObject> huffmanedBytes = new TreeSet<ICountedObject>(mapOfBytes.values());
        while (huffmanedBytes.size() > 1) {
            huffmanedBytes.add(new HuffmanTreeNode(popFirstAsHuffmanTreeNode(huffmanedBytes),
                    popFirstAsHuffmanTreeNode(huffmanedBytes)));
        }
        root = convertToHuffmanTreeNode(huffmanedBytes.first());
    }

    private static HuffmanTreeNode popFirstAsHuffmanTreeNode(SortedSet<ICountedObject> huffmanedBytes) {
        ICountedObject result = huffmanedBytes.first();
        huffmanedBytes.remove(result);
        return convertToHuffmanTreeNode(result);
    }

    private static HuffmanTreeNode convertToHuffmanTreeNode(ICountedObject value) {
        if (value.getClass() == HuffmanedByte.class) {
            return new HuffmanTreeNode((HuffmanedByte)value);
        }
        return (HuffmanTreeNode)value;
    }

    private ICompressInput input;
    private int totalCountOfBytes;
    private Map<Integer, HuffmanedByte> mapOfBytes = new HashMap<Integer, HuffmanedByte>();
    private HuffmanTreeNode root;

    protected CompressPrefixCodesTable(ICompressInput input) {
        this.input = input;
    }

    protected CompressPrefixCodesTable() {
    }

    public int getTotalCountOfBytes() {
        return totalCountOfBytes;
    }

    public int differentBytesCount() {
        return mapOfBytes.size();
    }

    public int getRepsForByte(int b) {
        return mapOfBytes.get(b).getReps();
    }

    public HuffmanTreeNode getRoot() {
        return root;
    }

    public List<HuffmanedByte> compress() {
        List<HuffmanedByte> result = new ArrayList<HuffmanedByte>();
        for (int b : input) {
            result.add(mapOfBytes.get(b));
        }
        return result;
    }
}
