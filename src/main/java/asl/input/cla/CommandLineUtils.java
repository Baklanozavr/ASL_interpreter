package asl.input.cla;

import asl.input.ApplicationOptions;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.nio.file.Paths;

public class CommandLineUtils {
    private static final String SOURCE_OPTION_NAME = "source";
    private static final String DEBUG_OPTION_NAME = "debug";

    public static ApplicationOptions getOptions(String[] commandArgs) throws ParseException {
        final Options options = new Options();
        options.addOption(Option.builder("s")
                .longOpt(SOURCE_OPTION_NAME)
                .hasArg()
                .desc("Read ASL-file or all ASL-files in a directory.")
                .build());
        options.addOption(Option.builder("de")
                .longOpt(DEBUG_OPTION_NAME)
                .desc("Print parsed input expressions.")
                .build());

        CommandLine commandLine = new DefaultParser().parse(options, commandArgs);

        boolean isDebug = commandLine.hasOption(DEBUG_OPTION_NAME);
        String filePathStringValue = commandLine.getOptionValue(SOURCE_OPTION_NAME);

        return ApplicationOptions.builder()
                .setDebug(isDebug)
                .setSourcePath(filePathStringValue == null ? null : Paths.get(filePathStringValue))
                .build();
    }
}
