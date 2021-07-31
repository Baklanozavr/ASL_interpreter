package asl.input;

import java.io.PrintStream;
import java.nio.file.Path;

public class ApplicationOptions {
    private static final String DEFAULT_INPUT_PREFIX = "<<< ";
    private static final PrintStream DEFAULT_OUTPUT = System.out;

    public static Builder builder() {
        return new Builder();
    }

    public final String inputPrefix;
    public final PrintStream output;
    public final boolean isDebug;
    public final Path sourcePath;

    private ApplicationOptions(
            String inputPrefix,
            PrintStream output,
            boolean isDebug,
            Path sourcePath
    ) {
        this.inputPrefix = inputPrefix;
        this.output = output;
        this.isDebug = isDebug;
        this.sourcePath = sourcePath;
    }

    public static class Builder {
        private String inputPrefix = DEFAULT_INPUT_PREFIX;
        private PrintStream output = DEFAULT_OUTPUT;
        private boolean isDebug = false;
        private Path sourcePath = null;

        public Builder setInputPrefix(String inputPrefix) {
            this.inputPrefix = inputPrefix;
            return this;
        }

        public Builder setOutput(PrintStream output) {
            this.output = output;
            return this;
        }

        public Builder setDebug(boolean debug) {
            isDebug = debug;
            return this;
        }

        public Builder setSourcePath(Path sourcePath) {
            this.sourcePath = sourcePath;
            return this;
        }

        public ApplicationOptions build() {
            return new ApplicationOptions(
                    inputPrefix,
                    output,
                    isDebug,
                    sourcePath
            );
        }
    }
}
