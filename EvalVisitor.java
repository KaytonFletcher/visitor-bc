

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Scanner;


public class EvalVisitor extends BCBaseVisitor<Double> {
    
    private HashMap<String, Double> globalVars = new HashMap<String, Double>();
    private Stack<HashMap<String, Double>> stack  = new Stack<HashMap<String, Double>>();

    //yields double value when it is encountered in parse tree
    @Override
    public Double visitDouble(BCParser.DoubleContext ctx) {
        return Double.parseDouble(ctx.DOUBLE().getText());
    }

    //prints expressions that are standalone
    @Override
    public Double visitExprPrint(BCParser.ExprPrintContext ctx) {
        Double val = this.visit(ctx.expr());
        System.out.println(val);
        return val;
    }

    @Override
    public Double visitIdExpr(BCParser.IdExprContext ctx) {
        String id = ctx.ID().getText();
        HashMap<String, Double> memory = (stack.isEmpty()) ? globalVars : stack.peek();
        return memory.getOrDefault(id, 0.0);
    }

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
                //post increment check
                if(id.equals(ctx.getChild(0))){val--;}
                System.out.println(val);
                return val;
            case BCParser.MM: 
                val = memory.put(id, memory.getOrDefault(id ,0.0) - 1);
                //post decrement check
                if(id.equals(ctx.getChild(0))){val++;}
                System.out.println(val);
                return val;
            default: throw new RuntimeException("Bad Assignment"); 
        }
    }



    @Override
    public Double visitParens(BCParser.ParensContext ctx) {
        return this.visit(ctx.expr());
    }

    @Override
    public Double visitPowExpr(BCParser.PowExprContext ctx) {
        Double val = Math.pow(this.visit(ctx.expr(0)), this.visit(ctx.expr(1)));
        System.out.println(val);
        return val;
    }

    @Override 
    public Double visitAddExpr(BCParser.AddExprContext ctx) { 
        Double left = this.visit(ctx.expr(0));
        Double right = this.visit(ctx.expr(1));

        Double val;
        switch (ctx.op.getType()) {
            case BCParser.PLUS:
                val = left + right;
                System.out.println(val);
                return val;
            case BCParser.MINUS:
                val = left - right;
                System.out.println(val);
                return val;
            default:
                throw new RuntimeException("unknown operator");
        }
    }

    @Override 
    public Double visitMultExpr(BCParser.MultExprContext ctx) { 
        Double left = this.visit(ctx.expr(0));
        Double right = this.visit(ctx.expr(1));

        Double val;
        switch (ctx.op.getType()) {
            case BCParser.MULT:
                val = left * right;
                System.out.println(val);
                return val;
            case BCParser.DIV:
                if(right == 0.0){ throw new RuntimeException("Divide by zero error!"); }
                val = left / right;
                System.out.println(val);
                return val;
            default:
                throw new RuntimeException("unknown operator");
        
        }
    } 

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

    @Override
    public Double visitIfstate(BCParser.IfstateContext ctx) {

        List<BCParser.ExprContext> conditions =  ctx.expr();
        
        boolean visited = false;
        int i=0; 
        for(i=0; i< conditions.size(); i++){
            
            Double val = this.visit(conditions.get(i));

            if(val > 0){
                Double yeet = this.visit(ctx.actions(i));
                visited = true;
                break;
            }   
        }

        //last action for else statement (doesn't have a condition)
        if(ctx.actions(++i) != null && !visited){
            this.visit(ctx.actions(i));
        }
        return Double.NaN;
    }
    



}