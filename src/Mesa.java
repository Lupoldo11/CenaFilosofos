import java.util.concurrent.Semaphore;

public class Mesa {
    //Atributos
    private static final int filosofos = 5;
    private boolean[] tenedores;
    // 0 1 2 3 4 5 filosofos
    //0 1 2 3 4 5 0 tenedores
    private Filosofo[] list_filosofos = new Filosofo[filosofos];
    private Semaphore semaforo = new Semaphore((int) filosofos/2,true);

    //Constructor
    public Mesa(){
        tenedores= new boolean[filosofos];
    }

    //Metodos
    public void cogerTenedor(int comensal){
        try {
            semaforo.acquire();
            tenedores[comensal]=true;
            if(comensal+1==filosofos){
                tenedores[0]=true;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public int tenedorDerecha(int comensal){
        int tenedor=1;
        if(comensal+1==filosofos){
            comensal=0;
        }
        if(!tenedores[comensal+1])
            tenedor=0;
        return tenedor;
    }
    public int tenederoIzquierdo(int comensal){
        int tenedor=1;
        if(!tenedores[comensal])
            tenedor=0;
        return tenedor;
    }
    public void dejarTenedores(int comensal){
        tenedores[comensal]=false;
        if(comensal+1==filosofos){
            tenedores[0]=false;
        }
        semaforo.release();
    }

    //Metodos relacionados con el Semaforo
    public int cantidadTicket(){
        return semaforo.availablePermits();
    }

    //Lanzador
    public static void main(String[] args) throws InterruptedException {
        Mesa mesa = new Mesa();
        mesa.comienzo(mesa,filosofos);
    }

    public void comienzo(Mesa mesa, int filosofos){
        for(int i =0; i<=(filosofos-1); i++){
            Filosofo filosofo= new Filosofo(i,mesa);
            list_filosofos[i] = filosofo;
            filosofo.getHilo().start();
        }
    }
}
