public class Calculadora {
    boolean num1H;
    String num1;
    String num2;
    Double resultat;
    String operador;
    
    
    public Calculadora(){
        this.num1 = "";
        this.num2 = "";
        this.resultat = null;
        this.operador = "";
        this.num1H = false;
    }
    
    public void ponerNum(String num){
        if(num1H == false){
            this.num1 += num;
        }else{
            this.num2 += num;
        }
    }
}