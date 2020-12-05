import java.io.Console;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class Funciones extends Thread {

    //  Variables a utilizar
    public Hashtable<String, String> functions_dict;    //  Guardamos funciones, Ej: f(x) -> 2*x + 1
    public String function_to_evaluate;    //  Funcion a evaluar
    public Double number_to_evaluate;   //  Numero a evaluar
    public Double result;   //  Guarda resultado

    //  Constructor
    public Funciones(String newfunc,Double num, Hashtable<String, String> table) {
        this.number_to_evaluate = num;
        this.functions_dict = table;
        this.function_to_evaluate = newfunc;
    }

    //  Obtenemos resultado thread
    public double getResult() {
        return this.result;
    }

    //  Funcion que corre en thread
    public void run() {
        
        String expression = this.functions_dict.get(this.function_to_evaluate);

        //  Buscamos todas las funciones, 
        //      Ej: function_to_evaluate(x)*g(x)/2 tiene dos match, function_to_evaluate(x) y g(x)
        Pattern pattern = Pattern.compile("([a-z][(]x[)])", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);

        while(matcher.find()) {

            String match = matcher.group().replace(" ", "");  //  Obtenemos funcion que hizo match con la expresion regular

            try {

                String funct_to_evaluate = match.substring(0,1) + "(x)"; // Funcion a evaluar. Ej: "f"+"(x)"

                Funciones thread = new Funciones(funct_to_evaluate, this.number_to_evaluate, this.functions_dict);    //  Inicializamos thread

                thread.start(); //  Iniciamos thread
                thread.join();  //  Esperamos termino de thread

                this.result = thread.getResult();   //  Obtenemos resultado hilo

                expression = expression.replace(match, String.valueOf(this.result));   //  Remplazamos funcion con expression obtenido

            } catch (InterruptedException ie) {
                ie.printStackTrace();

            }
        }

        try {
   
            //  Iniciamos para utilizar
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript"); 

            String ret = expression.replace("x", String.valueOf(this.number_to_evaluate));    //  Remplazamos X por el expression correspondiente
            Object res = engine.eval(ret);  //  Evaluamos expresion matematica en forma de string

            this.result = Double.parseDouble(String.valueOf(res));  //  Transformamos expression obtenido de string a double

        } catch (ScriptException e) {
            System.err.println(e.getMessage());

        }

    }

}