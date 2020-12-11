public class ThreadBubbleSort extends Thread{

    public Integer n;   //  Largo arreglo
    public int[] arr;   //  Arreglo

    //  Constructor: Seteamos arreglo y largo para ordenar
    public ThreadBubbleSort(int[] array, int num) {
        this.n = num;
        this.arr = array;
    }

    //  Obtener resultado hilo anterior
    public void getResult() {

        if( this.n > 50 ){
            System.out.println("getResult: Arreglo demasiado largo para printear.");
        }else{
            System.out.print("Recursividad: [");

            for (int i = 0; i < this.n - 1; i++) {
                System.out.print(String.valueOf(this.arr[i]) + "-");
            }

            System.out.println( String.valueOf(this.arr[this.n - 1]) + "]" );
        }

    }

    //  Checkear resultado de algoritmo
    public void checkOrden(){

        System.out.print("CheckOrden: ");

        for(int i=0;i<this.n;i++){

            if(i != 0 && arr[i] < arr[i - 1]){
                System.out.println("Fallo al ordenar.");
                break;
            }
            
        }

        System.out.println("Correctamente ordenado");

    }

    //  Implementacion bubbleSort con hilos
    public void run() {

        try{
        
            if (this.n == 1) {
                return;
            }

            for (int i = 0; i < n - 1; i++)
                if (this.arr[i] > this.arr[i + 1]){

                    int temp = this.arr[i];
                    this.arr[i] = this.arr[i + 1];
                    this.arr[i + 1] = temp;
                    
                }

                //  Creamos nuevo hilo
                ThreadBubbleSort thread = new ThreadBubbleSort(this.arr, this.n - 1);

                //  Iniciamos hilo
                thread.start();

                //  Esperamos termino de hilo
                thread.join();

        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

    }

}
