class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {

        for (int x = rec1[0]; x <= rec1[2]; x++) {

            for (int y = rec1[1]; y <= rec1[3]; y++) {

                boolean insideRec2 =
                    x > rec2[0] &&
                    x < rec2[2] &&
                    y > rec2[1] &&
                    y < rec2[3];

                if (insideRec2) {
                    return true;
                }
            }
        }

        return false;
    }
}