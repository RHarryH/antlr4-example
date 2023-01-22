import com.avispa.antlr.CalcLexer;
import com.avispa.antlr.CalcListenerImpl;
import com.avispa.antlr.CalcParser;
import com.avispa.antlr.CalcPatternsParser;
import com.avispa.antlr.CalcSemanticLexer;
import com.avispa.antlr.CalcSemanticParser;
import com.avispa.antlr.CalcVisitorImpl;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Rafał Hiszpański
 */
class ANTLRExampleTest {

    @Test
    void givenSimpleInput_whenParse_thenRootContainsFullInput() {
        String input = "2+2";

        Lexer lexer = new CalcLexer(CharStreams.fromString(input));
        CalcParser parser = new CalcParser(new CommonTokenStream(lexer));

        assertEquals(input, parser.input().getText());
    }

    @Test
    void givenSimpleInput_whenParse_thenOutputCalculated() {
        String input = "2+2\n4*4";

        Lexer lexer = new CalcSemanticLexer(CharStreams.fromString(input));
        CalcSemanticParser parser = new CalcSemanticParser(new CommonTokenStream(lexer));

        List<CalcSemanticParser.LineContext> lines = parser.input().line();

        double firstResult = lines.get(0).result;
        assertEquals(4.0, firstResult);

        double secondResult = lines.get(1).result;
        assertEquals(16.0, secondResult);
    }

    @Test
    void givenSimpleInput_whenParseWithVisitor_thenOutputCalculated() {
        String input = "2+2\n4*4";

        Lexer lexer = new CalcSemanticLexer(CharStreams.fromString(input));
        CalcPatternsParser parser = new CalcPatternsParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.input();
        CalcVisitorImpl calculator = new CalcVisitorImpl();
        double lastResult = calculator.visit(tree);

        assertEquals(16.0, lastResult);
        assertEquals(List.of(4.0, 16.0), calculator.getResults());
    }

    @Test
    void givenSimpleInput_whenParseWithListener_thenOutputCalculated() {
        String input = "2+2\n4*4";

        Lexer lexer = new CalcSemanticLexer(CharStreams.fromString(input));
        CalcPatternsParser parser = new CalcPatternsParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.input();
        CalcListenerImpl calculator = new CalcListenerImpl();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(calculator, tree);

        assertEquals(List.of(4.0, 16.0), calculator.getResults());
    }
}
