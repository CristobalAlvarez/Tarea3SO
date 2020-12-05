import java.util.Arrays;

// Implementacion tipica BubbleSort de forma recursiva.
public class BubbleSort {

    //  Obtener resultado hilo anterior
    public void getResult(int arr[], int n ) {
        
        if( n > 50 ){
            System.out.println("getResult: Arreglo demasiado largo para printear.");

        }else{
            System.out.print("Recursividad: [");

            for (int i = 0; i < n - 1; i++) {
                System.out.print(String.valueOf(arr[i]) + "-");
            }

            System.out.println( String.valueOf(arr[n - 1]) + "]" );

        }
        

    }
    
    //  Funcion para checkear algoritmo de ordenamiento
    public void checkOrden(int arr[], int n){

        System.out.print("CheckOrden: ");

        for(int i=0;i<n;i++){

            if(i != 0 && arr[i] < arr[i - 1]){
                System.out.println("Fallo al ordenar.");
                break;
            }
            
        }

        System.out.println("Correctamente ordenado");

    }

    //  Algoritmo tipico de BubbleSort recursivo
    public void run(int arr[], int n) {
        if (n == 1)
            return;

        for (int i = 0; i < n - 1; i++)
            if (arr[i] > arr[i + 1]) {

                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }

        run(arr, n - 1);
    }

}