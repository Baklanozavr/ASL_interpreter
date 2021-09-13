package asl.model.system;

public class BranchPoint {
    // id точки ветвления
    public final int id;

    // родительская точка ветвления
    public final BranchPoint parent;

    // количество вариантов
    public final int varsNumber;

    // номер текущего варианта внутри ветви
    public int varIndex = 0;


    public BranchPoint(int id, BranchPoint parent, int varsNumber) {
        this.id = id;
        this.parent = parent;
        this.varsNumber = varsNumber;
    }

    // возвращает false, если варианты кончились
    public boolean shiftNext() {
        ++varIndex;
        if (varIndex < varsNumber) return true;
        if (parent == null) return false;
        varIndex = 0;
        return parent.shiftNext();
    }
}
