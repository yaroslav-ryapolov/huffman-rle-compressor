package com.harrycodeman.compression.colorspaces;

import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.util.Collections.nCopies;

public class ImageJoiner {
    public static final ThreeComponentPixelBlock BACK_COLOR = new ThreeComponentPixelBlock(255, 0, 0);

    private int joinedRowWidth;

    public Image horizontalJoinWithLeftImageModifying(Image left, Image right) {
        joinedRowWidth = left.getWidth() + right.getWidth();
        joinRowsOfOtherWithBlankLinesIfNeed(left, right);
        joinRowsOfThisWithBlankLinesIfNeed(left, right);
        joinFullRows(left, right);
        left.setWidthAndHeight(left.getWidth() + right.getWidth(), max(left.getHeight(), right.getHeight()));
        return left;
    }

    private void joinRowsOfOtherWithBlankLinesIfNeed(Image left, Image right) {
        List<ThreeComponentPixelBlock> blankRow = nCopies(left.getWidth(), BACK_COLOR);
        int heightDifference = right.getHeight() - left.getHeight();
        for (int i = 0; i < heightDifference; i++) {
            left.addAll(i*joinedRowWidth, blankRow);
            left.addAll(i*joinedRowWidth + left.getWidth(), right.getRow(i)
            );
        }
    }

    private void joinRowsOfThisWithBlankLinesIfNeed(Image left, Image right) {
        List<ThreeComponentPixelBlock> blankRow = nCopies(right.getWidth(), BACK_COLOR);
        int heightDifference = left.getHeight() - right.getHeight();
        for (int i = 0; i < heightDifference; i++) {
            left.addAll(i*joinedRowWidth + left.getWidth(), blankRow);
        }
    }

    private void joinFullRows(Image left, Image right) {
        int heightDifference = abs(left.getHeight() - right.getHeight());
        int rightLowerDifference = left.getHeight() > right.getHeight() ? heightDifference : 0;
        int maxHeight = max(left.getHeight(), right.getHeight());
        for (int i = heightDifference; i < maxHeight; i++) {
                left.addAll(i*joinedRowWidth + left.getWidth(), right.getRow(i - rightLowerDifference));
        }
    }
}
