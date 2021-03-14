package exeriments.anything;

class LintProblem3 implements LintProblem {
    int i;

    public LintProblem3(int i) {
        this.i = i;
    }

    @Override
    public int getPosition() {
        return (i + 1);
    }

    @Override
    public String getMessage() {
        return "Bracket sequence should start with left, not right bracket.";
    }
}
