package exeriments.anything;

class LintProblem1 implements LintProblem {
    int i;

    LintProblem1(int i) {
        this.i = i;
    }

    @Override
    public int getPosition() {
        return i + 1;
    }

    @Override
    public String getMessage() {
        return "Invalid command.";
    }
}

