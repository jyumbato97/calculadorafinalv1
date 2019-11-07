
package ulima.notacion;

import ulima.pila.Pila;
import javax.swing.JOptionPane;


public class Notacion {
    
    String cadena;
    Pila infija;
    Pila posfija;
    Pila operador;
    Pila operar;
    
    
    public Notacion(String cadena){
        this.cadena=cadena;
        infija=new Pila();
        posfija=new Pila();
        operador=new Pila();
        operar=new Pila();
    }
    
    public Pila notacionInfija(){
        String str="";
        JOptionPane.showMessageDialog(null, this.cadena.length());
        for (int i = 0; i < this.cadena.length(); i++) {
            if (isOperador(String.valueOf(this.cadena.charAt(i)))) {
                if (!String.valueOf(this.cadena.charAt(i)).equals("(") || !String.valueOf(this.cadena.charAt(i)).equals(")")) {
                	JOptionPane.showMessageDialog(null, str);
                		infija.apilar(str);
				}
                infija.apilar(String.valueOf(this.cadena.charAt(i)));
                str="";
            }else{
                str=str+String.valueOf(this.cadena.charAt(i));
                if (i==(this.cadena.length()-1)) {
                    infija.apilar(str);
                    str="";
                }
            }
        }
        return infija;
    }
    
    public Pila notacionInfija2(){
    	String num="";
    	for (int i = 0; i < this.cadena.length(); i++) {
    		if (isOperador(String.valueOf(this.cadena.charAt(i)))) {
    			if (!num.equals("")) {
					infija.apilar(num);
					num="";
				}
    			infija.apilar(cadena.charAt(i));
			}else{
				num=num+String.valueOf(this.cadena.charAt(i));
			}
		}
        return infija;
    }
    
    
    
    
    public Pila notacionPosfija(Pila infija){
       Pila infijaMod=invertirElementos(infija);
        while (!infijaMod.estaVacia()) {            
            String str = String.valueOf(infijaMod.verCima());
            System.out.println(str);
            operador.mostrar();
            posfija.mostrar();
            System.out.println("----------");
            if (isOperador(str)) {
                if (operador.estaVacia()) {
                    operador.apilar(infijaMod.desapilar());
                } else {
                    switch (str){
                        case "+":
                        	String ope=String.valueOf(operador.verCima());
                        	
                            if (ope.equals("-")) {
                                posfija.apilar(operador.desapilar());
                                operador.apilar(str);
                            } else if((ope.equals("*")) || (ope.equals("/")) || (ope.equals("^"))){
                            	while(!ope.equals("(")){
                    				posfija.apilar(operador.desapilar());
                    				
                    				ope=String.valueOf(operador.verCima());
                            	}
                            	operador.apilar(infijaMod.desapilar());
                            }else{
                                operador.apilar(infijaMod.desapilar());
                            }
                            break;
                        case "-":
                        	String ope6=String.valueOf(operador.verCima());
                            if (ope6.equals("+")) {
                                posfija.apilar(operador.desapilar());
                                operador.apilar(str);
                            } else if((ope6.equals("*")) || (ope6.equals("/")) || (ope6.equals("^"))){
                            	while(!ope6.equals("(")){
                    				posfija.apilar(operador.desapilar());
                    				
                    				ope6=String.valueOf(operador.verCima());
                            	}
                            	operador.apilar(infijaMod.desapilar());
                            }else{
                                operador.apilar(infijaMod.desapilar());
                            }
                            break;
                        case "*":
                        	String ope3=String.valueOf(operador.verCima());
                            if (ope3.equals("/")) {
                                posfija.apilar(operador.desapilar());
                                operador.apilar(str);
                            } else if((ope3.equals("^"))){
                            		while(!ope3.equals("(")){
                            				posfija.apilar(operador.desapilar());
                            				ope3=String.valueOf(operador.verCima());
                                    }
                                operador.apilar(infijaMod.desapilar());
                            }else{
                                operador.apilar(infijaMod.desapilar());
                            }
                            break;
                        case "/":
                            if (operador.verCima().equals("*")) {
                                posfija.apilar(operador.desapilar());
                                operador.apilar(str);
                            } else if((operador.verCima().equals("^")) || (operador.verCima().equals("("))){
                                operador.apilar(str);
                            }else{
                                while(!operador.estaVacia()){
                                    posfija.apilar(operador.desapilar());
                                }
                            }
                            break;
                        case "^":
                        	String ope2=String.valueOf(operador.verCima());
                            if((ope2.equals("("))){
                                operador.apilar(infijaMod.desapilar());
                            }else{
                                while(!operador.estaVacia()){
                                    posfija.apilar(operador.desapilar());
                                }
                            }
                            break;
                        case "(":
                            operador.apilar(infijaMod.desapilar());
                            break;
                        case ")":
                        	String ope4=String.valueOf(operador.verCima());
                        	while(!ope4.equals("(")){
                        	//	System.out.println(ope4);
                                posfija.apilar(operador.desapilar());
                                ope4=String.valueOf(operador.verCima());
                            }
                        	operador.desapilar();
                        	infijaMod.desapilar();
                            break;
                    }
                }
            } else {
                posfija.apilar(infijaMod.desapilar());
            }
        }
        while(!operador.estaVacia()){
        	
            posfija.apilar(operador.desapilar());
        }

        posfija=invertirElementos(posfija);

        return posfija;
    }
    
    public Object operarExprecion(){
    	while(!posfija.estaVacia()){
    		String str=String.valueOf(posfija.verCima());
    		if (isOperador(str)) {
    			String x1=String.valueOf(operar.desapilar());
    			String x2=String.valueOf(operar.desapilar());
    			double x=Double.parseDouble(x1);
    			double y=Double.parseDouble(x2);
    			double res=operar(x, y, str);
    			System.out.println(res);
    			operar.apilar(res);
    			posfija.desapilar();
			}else{
				operar.apilar(posfija.desapilar());
			}
    	}
    	operar.mostrar();
        return operar.verCima();
    }
    
    public double operar(double x1,double x2,String operador){
    	double respuesta=0.0;
    	switch(operador){
    	case "+":
    		respuesta= x2+x1;
    		break;
    	case "-":
    		respuesta= x2-x1;
    		break;
    	case "*":
    		respuesta= x2*x1;
    		break;
    	case "/":
    		respuesta= x2/x1;
    		break;
    	case "^":
    		respuesta= Math.pow(x2, x1);
    		break;
    	}
    	return respuesta;
    }
    
    public static Pila invertirElementos(Pila p){
        Pila respuesta = new Pila();
        while(p.estaVacia() == false){
            respuesta.apilar(p.desapilar());
        }
        return respuesta;
    }
    
    public boolean isOperador(String c){
        boolean resp=false;
        if ((c.equals("+")) || (c.equals("-")) || (c.equals("*")) || (c.equals("/")) || (c.equals("^")) || (c.equals("(")) || (c.equals(")"))){
            resp=true;
        }
        return resp;
    }


    
    
    
}
