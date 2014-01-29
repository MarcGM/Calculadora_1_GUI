
import static java.awt.image.ImageObserver.ABORT;

public class Calculadora {
    boolean num1H;
    String num1;
    String num2;
    Double resultado;
    String operador;
    String operadorViejo;
    boolean acabaDePonerOperador;
    PantPrint pantallaPrincipal;
    String bufferPantallaCalc = "";
    
    
    public Calculadora(PantPrint pantallaPrincipal){
        this.pantallaPrincipal = pantallaPrincipal;
        this.num1 = "";
        this.num2 = "";
        this.resultado = null;
        this.operador = "";
        this.operadorViejo = "";
        this.num1H = false;
        this.acabaDePonerOperador = true;
    }
    
    public void ponerNum(String num){
        if(num1H == false){
            //Imprimir por el campo de texto (enganchado al texto anterior), la variable "num".
            bufferPantallaCalc += num;
            pantallaPrincipal.escribirPantallaCalc(bufferPantallaCalc);
            this.num1 += num;
            this.acabaDePonerOperador = false;
        }else{
            //Imprimir por el campo de texto (enganchado al texto anterior), la variable "num".
            bufferPantallaCalc += num;
            pantallaPrincipal.escribirPantallaCalc(bufferPantallaCalc);
            this.num2 += num;
            this.acabaDePonerOperador = false;
        }
    }
    public void ponerOperador(String op){
        boolean llevaIgual = false;
        
        if(this.acabaDePonerOperador == true){
            //Enviar a método donde muestre mensaje donde diga que no se pueda poner un operador aquí.
            System.out.println("¡¡¡ERROR!!!");
        }else{
            //Si entra en el "if" siguiente, significa que aún no se ha puesto el primer operador: por lo tanto, no hará ninguna operación.
            if(this.operador.equals("")){
                if(op.equals("=")){
                    System.out.println(" ¡¡¡NO SE PUEDE PONER UN IGUAL AQUÍ!!! ");
                }else{
                    //Imprimir por el campo de texto (con un espacio a la derecha y otro a la izquierda), la variable "op".
                    bufferPantallaCalc += " "+op+" ";
                    pantallaPrincipal.escribirPantallaCalc(bufferPantallaCalc);
                    
                    //System.out.print(" "+op+" ");
                    this.operador = op;
                    this.acabaDePonerOperador = true;
                    this.num1H = true;
                }
            }else{
                this.operadorViejo = this.operador;
                hacerOperacion();
                // Imprimir por el campo de texto (con un espacio a la derecha y otro a la izquierda), un símbolo de igual, seguido de la variable "this.resultado", en String.
                bufferPantallaCalc = " = "+String.valueOf(this.resultado)+"\n"+op+" ";
                pantallaPrincipal.escribirPantallaCalc(bufferPantallaCalc);
                //System.out.println(" = "+String.valueOf(this.resultado));
                //System.out.print(op+" ");
                this.operador = op;
                /*
                 * La instrucción siguiente, comprueva si "this.operador" es un igual o no.
                 * En caso afirmativo, devuelve "true". sino, devuelve "false".
                 */
                llevaIgual = this.operador.equals("=");
                accionesLlevaIgual(llevaIgual);

                this.acabaDePonerOperador = true;
            }
        }
    }
    public void hacerOperacion(){
        switch(this.operadorViejo){
            case "+":
                this.resultado = Double.parseDouble(num1) + Double.parseDouble(num2);
                break;
            case "-":
                this.resultado = Double.parseDouble(num1) - Double.parseDouble(num2);
                break;
            case "*":
                this.resultado = Double.parseDouble(num1) * Double.parseDouble(num2);
                break;
            case "/":
                this.resultado = Double.parseDouble(num1) / Double.parseDouble(num2);
                break;
            default:
                break;
        }
    }
    public void accionesLlevaIgual(boolean llevaIgual){
        this.num1 = String.valueOf(this.resultado);
        this.num2 = "";
        if(llevaIgual){
            bufferPantallaCalc = " = "+this.resultado;
            pantallaPrincipal.escribirPantallaCalc(bufferPantallaCalc);
            
            //Poner instrucciónes que bloquee todas las teclas:
            pantallaPrincipal.bloquearTodasMenosCE();
        }
    }
}