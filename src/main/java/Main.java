import asl.input.ASLConsumer;
import asl.input.ASLExecutor;
import asl.input.ApplicationOptions;
import asl.input.ConsumerBuilder;
import asl.input.REPLApplication;
import asl.input.ResettableExecutor;
import asl.input.SourceBasedApplication;
import asl.input.cla.CommandLineUtils;
import asl.model.system.Context;
import org.apache.commons.cli.ParseException;

public class Main {
    public static void main(String[] argv) throws ParseException {
        ApplicationOptions options = CommandLineUtils.getOptions(argv);

        Context initialContext = new Context(null);

        ASLConsumer aslConsumer =
                new ConsumerBuilder(options)
                        .setContext(initialContext)
                        .build();

        ASLExecutor aslExecutor = new ResettableExecutor(aslConsumer);

        // REPL mode
        if (options.sourcePath == null) {
            new REPLApplication(initialContext, options, aslExecutor).run();
        } else {
            new SourceBasedApplication(initialContext, options, aslExecutor).run();
        }
    }
}
