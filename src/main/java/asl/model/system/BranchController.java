package asl.model.system;

import java.util.HashMap;
import java.util.Map;

public class BranchController {
    // посещённые точки увеличения состояний
    private static final Map<Integer, BranchPoint> branchPoints = new HashMap<>();

    private static BranchPoint currentBranchPoint = null;

    public static void addBranchPoint(int id, int possibleVariantsNumber) {
        var newBranchPoint = new BranchPoint(id, currentBranchPoint, possibleVariantsNumber);
        branchPoints.put(id, newBranchPoint);
        currentBranchPoint = newBranchPoint;
    }

    // возвращает индекс текущего состояния или -1, если состояние не обнаружено
    public static int getVariantIndex(int id) {
        BranchPoint branchPoint = branchPoints.get(id);
        if (branchPoint == null) return -1;
        return branchPoint.varIndex;
    }

    public static boolean shiftNext() {
        if (currentBranchPoint == null) return false;
        boolean hasNext = currentBranchPoint.shiftNext();
        if (!hasNext) currentBranchPoint = null;
        return hasNext;
    }

    public static boolean isEmpty() {
        return currentBranchPoint == null;
    }
}
