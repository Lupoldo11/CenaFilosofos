public class Filosofo implements Runnable{
    //Atributos
    private int comensal;
    private Mesa mesa;
    private Thread hilo;

    //Constructor
    public Filosofo(int comensal, Mesa mesa){
        this.comensal=comensal;
        this.mesa=mesa;
        hilo = new Thread(this,"Filosofo"+comensal);
    }

    //Metodos
    public void pensar(){
        System.out.println("El filosofo "+getComensal()+" está pensando...");
        try {
            hilo.sleep(random(1,5000));
        } catch (InterruptedException e) {}
    }

    public void comer(){
        System.out.println("El filosofo "+getComensal()+" está comiendo...");
        try {
            hilo.sleep(random(1,2000));
        } catch (InterruptedException e) {}
    }

    public int getComensal(){
        return comensal;
    }

    public Thread getHilo(){
        return hilo;
    }

    private int random(int limitMin, int limitMax){
        return ((int) (Math.random() * (limitMax-limitMin+1)) + limitMin);
    }

    @Override
    public void run(){
        while(true){
          switch (String.valueOf(random(0,1))){
              case "0"->{ //comer (si puede sino piensa hasta comer)
                  boolean salida = true;
                  while(salida){
                      if(mesa.cantidadTicket() > 0){
                          if(mesa.tenederoIzquierdo(comensal)==0 && mesa.tenedorDerecha(comensal)==0) {
                              mesa.cogerTenedor(comensal);
                              comer();
                              mesa.dejarTenedores(comensal);
                              System.out.println("Suelta los tenedores filosofo"+getComensal());
                              salida = false;
                          }
                      } else {
                          pensar();
                      }
                  }
              }
              case "1"->{ //pensar
                  pensar();
              }
              default ->{ //error fatal
                  System.out.println("Se le ha parado el corazon al Filosofo");
              }
          }
        }
    }
}
