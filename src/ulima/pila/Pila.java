
package ulima.pila;


public class Pila {
    
    Object [] arr;
    static final int MAX_ELEMENT=50;
    int cima;
    
    public Pila(){
        arr=new Object[MAX_ELEMENT];
        cima=-1;
    }
    
    //apilar
    public void apilar(Object dato){
        if (estaLlena()==false) {
            cima++;
            arr[cima]=dato;
        }
    }
    //desapilar
    public Object desapilar(){
        Object respuesta=null;
        if (estaVacia()==false) {
            respuesta=arr[cima];
            arr[cima]=null;
            cima--;
        }
        return respuesta;
    }
    //esta vacia
    public boolean estaVacia(){
        boolean respuesta=false;
        if (cima==-1) {
            respuesta=true;
        }
        return respuesta;
    }
    //esta llena
    public boolean estaLlena(){
        boolean respuesta=false;
        if (cima==MAX_ELEMENT-1) {
            respuesta=true;
        }
        return respuesta;
    }
    
    //ver cima
    public Object verCima(){
        Object respuesta=null;
        if (estaVacia()==false) {
            respuesta=arr[cima];
        }
        return respuesta;
    }
    //mostrar elementos de pila
    public void mostrar(){
        System.out.print("[");
        for (int i = 0; i <= cima; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println("<-Cima]");
    }
    

    
    public void mostrar2(){
    	for (int i = 0; i <= cima; i++) {
            System.out.println(arr[i]+" ");
        }
    }
    
    
}
