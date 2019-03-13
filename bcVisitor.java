// Generated from bc.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link bcParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface bcVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link bcParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(bcParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link bcParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(bcParser.LineContext ctx);
	/**
	 * Visit a parse tree produced by {@link bcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(bcParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link bcParser#shorthand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShorthand(bcParser.ShorthandContext ctx);
	/**
	 * Visit a parse tree produced by {@link bcParser#equation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquation(bcParser.EquationContext ctx);
	/**
	 * Visit a parse tree produced by {@link bcParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(bcParser.PrintContext ctx);
}