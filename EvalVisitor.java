

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class EvalVisitor extends BCBaseVisitor<Double> {
    
    private Map<String, Object> globalVars = new HashMap<String, Object>();
    private Stack<HashMap<String, Object>> stack  = new Stack<HashMap<String, Object>>();


}