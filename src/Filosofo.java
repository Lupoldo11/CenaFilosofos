public class Filosofo implements Runnable{
    //Atributos
    private int comensal;
    private Mesa mesa;

    //Constructor
    public Filosofo(int comensal, Mesa mesa){
        this.comensal=comensal;
        this.mesa=mesa;
    }

    //Metodos
    public void pensar(){}
    public void comer(){}

    @Override
    public void run(){}
}
