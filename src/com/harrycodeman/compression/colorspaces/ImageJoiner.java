package com.harrycodeman.compression.colorspaces;

public class ImageJoiner {
//    public static final ThreeComponentPixelBlock BACK_COLOR = new ThreeComponentPixelBlock(255, 0, 0);
//
//    public Image horizontalJoinWith(Image left, Image right) {
//        Image result = new Image(left.getWidth() + right.getWidth(), Math.max(left.getHeight(), right.getHeight()));
//        joinRowsOfHigherImageWithBlankLines(left, right);
//        joinFullRows(left, right);
//        return result;
//    }
//
//    private void joinRowsOfHigherImageWithBlankLines(Image left, Image right) {
//        if (left.getHeight() < right.getHeight()) {
//            joinRowsOfOtherWithBlankLines(left, right);
//        }
//        else if (left.getHeight() > right.getHeight()) {
//            joinRowsOfThisWithBlankLines(right);
//        }
//    }
//
//    private void joinRowsOfOtherWithBlankLines(Image left, Image right) {
//        List<ThreeComponentPixelBlock> blankRow = nCopies(left.getWidth(), BACK_COLOR);
//        int heightDifference = right.getHeight() - left.getHeight();
//        for (int i = 0; i < heightDifference; i++) {
//            pixelBlocks.addAll(i*(left.getWidth() + right.getWidth()), blankRow);
//            pixelBlocks.addAll(
//                    i*(width + right.getWidth()) + width,
//                    right.pixelBlocks.subList(i*right.getWidth(), (i + 1)*right.getWidth()));
//        }
//    }
//
//    private void joinRowsOfThisWithBlankLines(Image other) {
//        List<ThreeComponentPixelBlock> blankRow = nCopies(other.getWidth(), BACK_COLOR);
//        int heightDifference = height - other.getHeight();
//        for (int i = 0; i < heightDifference; i++) {
//            pixelBlocks.addAll(i*(width + other.getWidth()) + width, blankRow);
//        }
//    }
//
//    private void joinFullRows(Image other) {
//        int heightDifference = abs(height - other.getHeight());
//        int maxHeight = max(height, other.getHeight());
//        for (int i = heightDifference; i < maxHeight; i++) {
//            if (height < other.getHeight()) {
//                pixelBlocks.addAll(
//                        i*(width + other.getWidth()) + width,
//                        other.pixelBlocks.subList(i*other.getWidth(), (i + 1)*other.getWidth())
//                );
//            }
//            else {
//                pixelBlocks.addAll(
//                        i*(width + other.getWidth()) + width,
//                        other.pixelBlocks.subList(
//                                (i - heightDifference)*other.getWidth(), (i - heightDifference + 1)*other.getWidth()
//                        )
//                );
//            }
//        }
//    }
}
