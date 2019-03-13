import java.lang.Exception;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {

    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            args = new String[]{"input.txt"};
        }

        System.out.println("parsing: " + args[0]);

        BCLexer lexer = new BCLexer(CharStreams.fromFileName(args[0]));
        BCParser parser = new BCParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.program();
        EvalVisitor visitor = new EvalVisitor();
        visitor.visit(tree);
    }
}