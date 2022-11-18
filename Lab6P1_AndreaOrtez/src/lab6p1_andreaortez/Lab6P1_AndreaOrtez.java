package lab6p1_andreaortez;

import java.security.SecureRandom;
import java.util.Scanner;

public class Lab6P1_AndreaOrtez {

    static Scanner leer = new Scanner (System.in);
    static SecureRandom random = new SecureRandom();
    
    public static void main(String[] args) {
        
        int opcion;
        do{
            System.out.println("-- MENU --");
            System.out.println("1-> Turing");
            System.out.println("2-> Constante de Kaprekar");
            System.out.println("3-> Salir");
            System.out.print("Ingrese una opcion: ");
            opcion = leer.nextInt();
            
            switch(opcion){
                case 1:
                    System.out.println("\n -- TURING EN PROCESO --");
                    System.out.print("Ingrese el tamaño de arreglo: ");
                    int tam = leer.nextInt();
                    
                    while (tam<0){
                        System.out.print("Ingrese el tamaño de arreglo: ");
                        tam = leer.nextInt();
                    }
                    int [] array = Lectura (tam);
                    
                    System.out.print("Arreglo generado: ");
                    Imprimir (array);
                    System.out.print("\nIngrese cadena de instrucciones: ");
                    String ins = leer.next().toUpperCase();
                    
                    System.out.println("Cadena generada: " + Turing (ins,array) + "\n");
                    break;
                case 2:
                    System.out.println("\n-- CONSTANTE DE KAPREKAR EN PROCESO --");
                    
                    int var=0, cont=0;
                    do{
                        System.out.print("Ingrese un numero de 4 cifras: ");
                        int num =leer.nextInt();
                    
                        while (num<1000 || num>9999){
                            System.out.print("Ingrese un numero de 4 cifras: ");
                            num =leer.nextInt();
                        }
                        
                        if (num>=1000 || num<=9999){
                            int cont2=0;
                            String cad= num + "";
                            for (int i=0; i<4; i++){
                                char a = cad.charAt(i);
                                for(int j=i+1; j<4; j++){
                                    char b = cad.charAt(j);
                                    if (a==b){
                                        cont2++;
                                    }
                                }
                                if (cont2==4){
                                    System.out.println("Los numeros no pueden ser iguales");
                                
                                }else{
                                    System.out.print(ConversionNum(Descendente(ConversionArray(num))));
                                    System.out.print(" - ");
                                    System.out.print(ConversionNum(Ascendente(ConversionArray(num)))); 
                                    System.out.print(" = ");
                                    System.out.println(Resta(Descendente(ConversionArray(num)),Ascendente(ConversionArray(num))));
                                    var = Resta(Descendente(ConversionArray(num)),Ascendente(ConversionArray(num)));
                                    System.out.println();
                                    cont++;
                                }    
                            }
                             
                        }
                    
                        
                    }while (var!=6174 && cont<8);
                    
            }
        }while (opcion!=3);
    }
    
    public static int [] Lectura (int tam){
        int [] temp = new int [tam];
        for (int i=0; i<tam; i++){
            temp[i]=random.nextInt(9)+0;
        }
        return temp;
    }//Fin Lectura
    
    public static void Imprimir(int [] array){
       for (int i=0; i<array.length; i++){
           System.out.print("[" + array[i] + "]");
       }
   }//Fin imprimir
    
    public static String Turing (String ins, int [] array){
        int apunt=0;
        String cad="";
        
        while (apunt<array.length){
            for(int i=0; i<ins.length(); i++){
                char a = ins.charAt(i);
                switch (a){
                    case 'R':
                        apunt++;
                        break;
                    case 'L':
                        apunt--;
                        break;
                    case 'X':
                        if (apunt>=0){
                            cad+=array[apunt];
                        }
                        break;
                    default:
                        break;
                }
            }
          apunt=array.length;
        }
        return cad;
    }//Fin Turing
    
    public static int [] ConversionArray (int num){
        String cad = num + "";
        int [] array = new int [4];
        for(int i=0; i<array.length; i++){
            char a = cad.charAt(i);
            array[i]=a-48;
        }
        return array;
    }
    
    public static String ConversionNum (int [] num){
        String numf="";
        for (int i=0; i<num.length; i++){
           numf+=num[i];
       }
        return numf;
    }
    public static int [] Ascendente (int [] array){
        int [] asc = new int [4];
        for (int i=0; i<4; i++){
            int menor=array[i];//le damos un valor a menor para compararlo
            for (int j=i+1; j<4; j++){
                int b=array[j];//le damos el siguiente valor del arreglo a b para empezar a comparar
                if (menor>=b){
                    menor=b;//Si encuentra un valor menor al original, se sustituye para el nuevo valor
                    array[j]=array[i];//se sustituye la posición de j y colocamos i para no perder el valor de i
                    array[i]=menor;//se sustituye el valor de i al valor del menor para que ya no vuelvan a leer ese valor
                }
            }
            asc[i]=menor;//asignamos el valor menor a la posicion en el arreglo
        }
        return asc;
    }
    
    public static int [] Descendente (int [] array){
        int [] asc = new int [4];
        for (int i=0; i<4; i++){
            int mayor=array[i];
            for (int j=i+1; j<4; j++){
                int b=array[j];
                if (mayor<=b){
                    mayor=b;
                    array[j]=array[i];
                    array[i]=mayor;
                }
            }
            asc[i]=mayor;
        }
        return asc;
    }
    
    public static int Resta (int [] num1, int [] num2){
        int a = Integer.parseInt(ConversionNum(num1));
        int b = Integer.parseInt(ConversionNum(num2));
        int resta;
        resta = a-b;
        return resta;
    }
}
