

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import javax.management.RuntimeErrorException;
import java.util.Scanner;


public class EvalVisitor extends BCBaseVisitor<Double> {
    private HashMap<String, BCParser.MethodDefContext> functions = new HashMap<String, BCParser.MethodDefContext>();
    
    private HashMap<String, Double> globalVars = new HashMap<String, Double>();
    private Stack<HashMap<String, Double>> stack  = new Stack<HashMap<String, Double>>();


    @Override //prints expressions that are standalone
    public Double visitExprPrint(BCParser.ExprPrintContext ctx) {
        Double val = this.visit(ctx.expr());
        System.out.println(val);
        return val;
    }

    // @Override 
    // public Double visitShorthandPrint(BCParser.ShorthandPrintContext ctx) {
    //     Double val = this.visit(ctx.shorthand());
        
    //     System.out.println(val);
    //     return val;
    // }

    @Override
    public Double visitPrint(BCParser.PrintContext ctx) {
        if(ctx.ID() == null) {
            System.out.println(this.visit(ctx.expr()));
        } else {
            System.out.println(ctx.ID().getText());
        }
        return Double.NaN;
    }

    /**** ATOM EXPRESSIONS ****/
    
    @Override
    public Double visitDouble(BCParser.DoubleContext ctx) {
        //yields double value when it is encountered in parse tree
        return Double.parseDouble(ctx.DOUBLE().getText());
    }

    @Override
    public Double visitIdExpr(BCParser.IdExprContext ctx) {
        String id = ctx.ID().getText();
        HashMap<String, Double> memory = (stack.isEmpty()) ? globalVars : stack.peek();
        Double val = memory.put(id, memory.getOrDefault(id, 0.0));
        if(val == null ) { return 0.0; } else return val;
      
    }

    /**** EQUATION STATEMENTS ****/

    @Override
    public Double visitEquation(BCParser.EquationContext ctx) {
        String id = ctx.ID().getText();

        Double value = this.visit(ctx.expr());
    

        //if stack is empty we are in global scope, so will add to global hashmap
        //performs shallow copy changing class variables
        HashMap<String, Double> memory = (stack.isEmpty()) ? globalVars : stack.peek();
        
        switch (ctx.op.getType()) {
            case BCParser.ASSIGN:
                return memory.put(id, value);
            case BCParser.PLUSEQ: 
                return memory.put(id, memory.getOrDefault(id,0.0) + value);
            case BCParser.MINUSEQ: 
                return memory.put(id, memory.getOrDefault(id,0.0) - value);
            case BCParser.MULTEQ: 
                return memory.put(id, memory.getOrDefault(id,0.0) * value);
            case BCParser.DIVEQ: 
                return memory.put(id, memory.getOrDefault(id,0.0) / value);        
            case BCParser.POWEQ: 
                return memory.put(id, Math.pow(memory.getOrDefault(id,0.0), value));
            default: throw new RuntimeException("Bad Assignment"); 
        }
    }

    @Override
    public Double visitShorthand(BCParser.ShorthandContext ctx) {
        String id = ctx.ID().getText();
       
        HashMap<String, Double> memory;

        //if stack is empty we are in global scope, so will add to global hashmap
        //performs shallow copy changing class variables
        memory = (stack.isEmpty()) ? globalVars : stack.peek();
        
        Double val;
        switch (ctx.op.getType()) {
            case BCParser.PP:
                val = memory.put(id, memory.getOrDefault(id ,0.0) + 1);
                if(val == null) { val = 0.0; }

                //pre increment check
                if(!id.equals(ctx.getChild(0).getText())){
                    val++;}
                return val;
            case BCParser.MM: 
                val = memory.put(id, memory.getOrDefault(id ,0.0) - 1);
                if(val == null) { val = 0.0; }

                //pre decrement check
                if(!id.equals(ctx.getChild(0).getText())){val--;}
                return val;
            default: throw new RuntimeException("Bad Assignment"); 
        }
    }

    /**** PEMDAS EXPRESSIONS ****/

    @Override
    public Double visitParens(BCParser.ParensContext ctx) {
        return this.visit(ctx.expr());
    }

    @Override
    public Double visitPowExpr(BCParser.PowExprContext ctx) {
        Double val = Math.pow(this.visit(ctx.expr(0)), this.visit(ctx.expr(1)));
        return val;
    }

    @Override 
    public Double visitMultExpr(BCParser.MultExprContext ctx) { 
        Double left = this.visit(ctx.expr(0));
        Double right = this.visit(ctx.expr(1));

        Double val;
        switch (ctx.op.getType()) {
            case BCParser.MULT:
                val = left * right;
                return val;
            case BCParser.DIV:
                if(right == 0.0){ throw new RuntimeException("Divide by zero error!"); }
                val = left / right;
                return val;
            default:
                throw new RuntimeException("unknown operator");
        
        }
    } 

    @Override 
    public Double visitAddExpr(BCParser.AddExprContext ctx) { 
        Double left = this.visit(ctx.expr(0));
        Double right = this.visit(ctx.expr(1));

        Double val;
        switch (ctx.op.getType()) {
            case BCParser.PLUS:
                val = left + right;
                return val;
            case BCParser.MINUS:
                val = left - right;
                return val;
            default:
                throw new RuntimeException("unknown operator");
        }
    }


    /**** BOOLEAN AND RELATIONAL EXPRESSIONS ****/

    @Override
    public Double visitNotExpr(BCParser.NotExprContext ctx) {
        Double toNegate = this.visit(ctx.expr());
        Double val;

        if(toNegate == 0.0){
            val = 1.0;
        }
        else{
            val = 0.0;
        }

        return val;
    }

    @Override
    public Double visitAndExpr(BCParser.AndExprContext ctx) {
        Double left = this.visit(ctx.expr(0));
        Double right = this.visit(ctx.expr(1));
        Double val;

        if(left != 0.0 && right != 0.0){
            val = 1.0;
        }
        else{
            val = 0.0;
        }
        return val;
    }

    @Override
    public Double visitOrExpr(BCParser.OrExprContext ctx) {
        Double left = this.visit(ctx.expr(0));
        Double right = this.visit(ctx.expr(1));
        Double val;

        if(left != 0.0 || right != 0.0){
            val = 1.0;
        }
        else{
            val = 0.0;
        }
        return val;
    }

    @Override
    public Double visitLessExpr(BCParser.LessExprContext ctx) {
        Double left = this.visit(ctx.expr(0));
        Double right = this.visit(ctx.expr(1));
        Double val;

        if(left < right){
            val = 1.0;
        }
        else{
            val = 0.0;
        }

        return val;
    }

    @Override
    public Double visitGreaterExpr(BCParser.GreaterExprContext ctx) {
        Double left = this.visit(ctx.expr(0));
        Double right = this.visit(ctx.expr(1));
        Double val;

        if(left > right){
            val = 1.0;
        }
        else{
            val = 0.0;
        }
        return val;
    }

    @Override
    public Double visitEqualExpr(BCParser.EqualExprContext ctx) {
        Double left = this.visit(ctx.expr(0));
        Double right = this.visit(ctx.expr(1));
        Double val;

        if(left.equals(right)){
            val = 1.0;
        }
        else{
            val = 0.0;
        }
        return val;
    }

    @Override
    public Double visitLessEqExpr(BCParser.LessEqExprContext ctx) {
        Double left = this.visit(ctx.expr(0));
        Double right = this.visit(ctx.expr(1));
        Double val;

        if(left <= right){
            val = 1.0;
        }
        else{
            val = 0.0;
        }
        return val;
    }

    @Override
    public Double visitGreaterEqExpr(BCParser.GreaterEqExprContext ctx) {
        Double left = this.visit(ctx.expr(0));
        Double right = this.visit(ctx.expr(1));
        Double val;

        if(left >= right){
            val = 1.0;
        }
        else{
            val = 0.0;
        }
        return val;
    }

    @Override
    public Double visitNotequalExpr(BCParser.NotequalExprContext ctx) {
        Double left = this.visit(ctx.expr(0));
        Double right = this.visit(ctx.expr(1));
        Double val;

        if(!left.equals(right)){
            val = 1.0;
        }
        else{
            val = 0.0;
        }
        return val;
    }


    /**** MATH EXPRESSIONS ****/

    @Override
    public Double visitSqrtExpr(BCParser.SqrtExprContext ctx) {
        Double expr = this.visit(ctx.expr());
        Double val;
        
        if(expr < 0){ throw new RuntimeException("Square root of negative number error!");}
        else{
            val = Math.sqrt(expr);
        }
        return val;
    }

    @Override
    public Double visitSinExpr(BCParser.SinExprContext ctx) {
        Double val = Math.sin(this.visit(ctx.expr()));
        return val;
    }

    @Override
    public Double visitCosExpr(BCParser.CosExprContext ctx) {
        Double val = Math.cos(this.visit(ctx.expr()));
        return val;
    }

    @Override
    public Double visitLogExpr(BCParser.LogExprContext ctx) {
        Double val = Math.log(this.visit(ctx.expr()));
        return val;
    }

    @Override
    public Double visitExpExpr(BCParser.ExpExprContext ctx) {
        Double val = Math.exp(this.visit(ctx.expr()));
        return val;
    }

    @Override
    public Double visitReadExpr(BCParser.ReadExprContext ctx) {
        Scanner scnr = new Scanner(System.in);

        Double val = scnr.nextDouble();
        return val;
    }

    @Override
    public Double visitNegateExpr(BCParser.NegateExprContext ctx) {
        Double val = -1 * this.visit(ctx.expr());
        return val;
    }

    /**** IF, WHILTE, FOR STATEMENTS ****/

    @Override
    public Double visitIfstate(BCParser.IfstateContext ctx) {

        List<BCParser.ExprContext> conditions =  ctx.expr();
 
        int i=0; 
        Double returnVal = 0.0;
        boolean visited = false;
        //for if and each else if branch
        for(i=0; i< conditions.size(); i++){
            
            Double val = this.visit(conditions.get(i));

            //condition evaluated to true, perform statements within
            if(val > 0){
                returnVal = this.visit(ctx.actions(i));
                visited = true;
                break;
            }   
        }
        
        //last action for else statement (doesn't have a condition)
        if(ctx.actions(i) != null && !visited){
            returnVal = this.visit(ctx.actions(i));
        }
        return returnVal;
    }

    @Override
    public Double visitWhilestate(BCParser.WhilestateContext ctx) {
        Double conditionVal = this.visit(ctx.expr());
        Double check = 0.0;
    
        while(conditionVal > 0){
            check = this.visit(ctx.actions());
            conditionVal = this.visit(ctx.expr());

            if(check.equals(Double.NaN)) { return 0.0; } 

        }
        return check;
    }

    @Override
    public Double visitForstate(BCParser.ForstateContext ctx) {
        
        this.visit(ctx.equation());
        Double conditionVal = this.visit(ctx.expr());
        Double breakCheck = 0.0;

        
        while(conditionVal > 0){
            breakCheck = this.visit(ctx.actions());
            this.visit(ctx.shorthand());
            conditionVal = this.visit(ctx.expr());

            if(breakCheck.equals(Double.NaN)) { return 0.0; }
        }

        return breakCheck;
    }

    @Override
    public Double visitActions(BCParser.ActionsContext ctx) {
        List<BCParser.StatementContext> statements = (ctx.statement() == null) ? ctx.block().statement() : Arrays.asList(ctx.statement());
        
        Double val = 0.0;
        for(BCParser.StatementContext statement : statements) { 
            val = this.visit(statement); 
            if(statement.getClass().equals(BCParser.ReturnCheckContext.class) || statement.getClass().equals(BCParser.ContinueCheckContext.class))  
            { return val; }
            
            if(statement.getClass().equals(BCParser.BreakCheckContext.class)) { return Double.NaN; }

        }
        return val;
    }

    /**** FUNCTION STATEMENTS AND EXPRESSIONS ****/

    @Override
    public Double visitMethodDef(BCParser.MethodDefContext ctx) {
        String functionName = ctx.ID().getText();
        if(functions.containsKey(functionName)) { throw new RuntimeException("Function re-definition error"); }

        functions.put(functionName, ctx);
        return Double.NaN;
    }

    @Override
    public Double visitMethodCall(BCParser.MethodCallContext ctx) {
    
        if(functions.containsKey(ctx.ID().getText())){

            BCParser.MethodDefContext methodDef =  functions.get(ctx.ID().getText());
            BCParser.MethodDefArgsContext definedArgs = methodDef.methodDefArgs();
            List<BCParser.ExprContext> calledArgs = ctx.methodCallArgs().expr();

            //wrong param number
            if(definedArgs.ID().size() != calledArgs.size()){throw new RuntimeException("Incorrect number of parameters"); }
            
            HashMap<String, Double> Scope = new HashMap<String, Double>();
            int numArguments = definedArgs.ID().size();

            //puts arguments on to new scope
            for(int i=0; i<numArguments;i++){ 
                Scope.put(definedArgs.ID(i).getText(), this.visit(calledArgs.get(i)));
            }
            //add scope for function
            stack.push(Scope);

            int stackSize = stack.size();

            //list of statements for function to run
            List<BCParser.StatementContext> functionStatements = methodDef.functionblock().block().statement();
        
            for(BCParser.StatementContext functionState : functionStatements) {
                
                //visit each statement
                Double val = this.visit(functionState);
                
                //return statement was called -> popping the stack
                if(stackSize != stack.size()) { 
                    return val; }
            }

            //if no return statement, escape scope and return 0
            stack.pop();
            return 0.0;
        } else {
            throw new RuntimeException("Function definition not found");
        }
    }
    
    @Override
    public Double visitReturnCheck(BCParser.ReturnCheckContext ctx) {
        if(stack.isEmpty()) { throw new RuntimeException("return outside function");}

        //preserve value to be returned
        Double val = this.visit(ctx.expr());

        //leave scope
        stack.pop();
        return val;
    }
}