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
	 * Visit a parse tree produced by the {@code parens}
	 * labeled alternative in {@link bcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(bcParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code read}
	 * labeled alternative in {@link bcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRead(bcParser.ReadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code or}
	 * labeled alternative in {@link bcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(bcParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code log}
	 * labeled alternative in {@link bcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLog(bcParser.LogContext ctx);
	/**
	 * Visit a parse tree produced by the {@code double}
	 * labeled alternative in {@link bcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble(bcParser.DoubleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cos}
	 * labeled alternative in {@link bcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCos(bcParser.CosContext ctx);
	/**
	 * Visit a parse tree produced by the {@code not}
	 * labeled alternative in {@link bcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(bcParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plusmin}
	 * labeled alternative in {@link bcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusmin(bcParser.PlusminContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sqrt}
	 * labeled alternative in {@link bcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqrt(bcParser.SqrtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negate}
	 * labeled alternative in {@link bcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegate(bcParser.NegateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code and}
	 * labeled alternative in {@link bcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(bcParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multdiv}
	 * labeled alternative in {@link bcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultdiv(bcParser.MultdivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sin}
	 * labeled alternative in {@link bcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSin(bcParser.SinContext ctx);
	/**
	 * Visit a parse tree produced by the {@code id}
	 * labeled alternative in {@link bcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(bcParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code power}
	 * labeled alternative in {@link bcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPower(bcParser.PowerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exp}
	 * labeled alternative in {@link bcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(bcParser.ExpContext ctx);
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