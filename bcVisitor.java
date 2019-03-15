// Generated from BC.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BCParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BCVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BCParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(BCParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(BCParser.LineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parens}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(BCParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sinExpr}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSinExpr(BCParser.SinExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sqrtExpr}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqrtExpr(BCParser.SqrtExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code double}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble(BCParser.DoubleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code readExpr}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadExpr(BCParser.ReadExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(BCParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultExpr(BCParser.MultExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logExpr}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogExpr(BCParser.LogExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negateExpr}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegateExpr(BCParser.NegateExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(BCParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cosExpr}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCosExpr(BCParser.CosExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expExpr}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpExpr(BCParser.ExpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addExpr}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpr(BCParser.AddExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code powExpr}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPowExpr(BCParser.PowExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpr(BCParser.IdExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(BCParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCParser#shorthand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShorthand(BCParser.ShorthandContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCParser#equation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquation(BCParser.EquationContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(BCParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCParser#ifstate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfstate(BCParser.IfstateContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCParser#actions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActions(BCParser.ActionsContext ctx);
}