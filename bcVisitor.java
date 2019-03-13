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
	 * Visit a parse tree produced by the {@code read}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRead(BCParser.ReadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code or}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(BCParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code log}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLog(BCParser.LogContext ctx);
	/**
	 * Visit a parse tree produced by the {@code double}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble(BCParser.DoubleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cos}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCos(BCParser.CosContext ctx);
	/**
	 * Visit a parse tree produced by the {@code not}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(BCParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plusmin}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusmin(BCParser.PlusminContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sqrt}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqrt(BCParser.SqrtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negate}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegate(BCParser.NegateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code and}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(BCParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multdiv}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultdiv(BCParser.MultdivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sin}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSin(BCParser.SinContext ctx);
	/**
	 * Visit a parse tree produced by the {@code id}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(BCParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code power}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPower(BCParser.PowerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exp}
	 * labeled alternative in {@link BCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(BCParser.ExpContext ctx);
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
}