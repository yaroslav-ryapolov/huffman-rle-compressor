package com.harrycodeman.compression.colorspaces;

import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.util.Collections.nCopies;

public class ImageJoiner {
    public static final ThreeComponentPixelBlock BACK_COLOR = new ThreeComponentPixelBlock(255, 0, 0);

    public Image horizontalJoin(Image left, Image right) {
        joinRowsOfHigherImageWithBlankLines(left, right);
        joinFullRows(left, right);
        left.setWidthAndHeight(left.getWidth() + right.getWidth(), max(left.getHeight(), right.getHeight()));
        return left;
    }

    private void joinRowsOfHigherImageWithBlankLines(Image left, Image right) {
        if (left.getHeight() < right.getHeight()) {
            joinRowsOfOtherWithBlankLines(left, right);
        }
        else if (left.getHeight() > right.getHeight()) {
            joinRowsOfThisWithBlankLines(left, right);
        }
    }

    private void joinRowsOfOtherWithBlankLines(Image left, Image right) {
        List<ThreeComponentPixelBlock> blankRow = nCopies(left.getWidth(), BACK_COLOR);
        int heightDifference = right.getHeight() - left.getHeight();
        for (int i = 0; i < heightDifference; i++) {
            left.addAll(i*(left.getWidth() + right.getWidth()), blankRow);
            left.addAll(
                    i*(left.getWidth() + right.getWidth()) + left.getWidth(),
                    right.getRow(i)
            );
        }
    }

    private void joinRowsOfThisWithBlankLines(Image left, Image right) {
        List<ThreeComponentPixelBlock> blankRow = nCopies(right.getWidth(), BACK_COLOR);
        int heightDifference = left.getHeight() - right.getHeight();
        for (int i = 0; i < heightDifference; i++) {
            left.addAll(i*(left.getWidth() + right.getWidth()) + left.getWidth(), blankRow);
        }
    }

    private void joinFullRows(Image left, Image right) {
        int heightDifference = abs(left.getHeight() - right.getHeight());
        int maxHeight = max(left.getHeight(), right.getHeight());
        for (int i = heightDifference; i < maxHeight; i++) {
            if (left.getHeight() < right.getHeight()) {
                left.addAll(
                        i*(left.getWidth() + right.getWidth()) + left.getWidth(),
                        right.getRow(i)
                );
            }
            else {
                left.addAll(
                        i * (left.getWidth() + right.getWidth()) + left.getWidth(),
                        right.getRow(i - heightDifference)
                );
            }
        }
    }
}
