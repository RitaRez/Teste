package tp;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class ReservaVoo {

    public static  HashMap<Integer,ReservaVoo> lista_reservaVoo = new HashMap<>();

    private Voo v;
    private final int id;                           
    private static int count = 0;                   

    
    ReservaVoo(int voo) throws IOException{
        Voo aux = Voo.lista_voo.get(voo);
        if (aux != null)
            this.v = aux;         
        
            
        CriaArquivo.print("reserva_voo", this.getInfo()); 
        this.id = count++;         
        
        
    }
    ReservaVoo(int voo, int id){
        //Recuperar as informações de voo a partir dos arquivos
        Voo aux = Voo.lista_voo.get(voo);
        if(aux != null)
            this.v = aux;
        this.id = id;                       
    }
    
    //Método que recebe uma lista de strings retirarada do arquivo de Aeroporto e coloca em objetos AeroAeroportoAeroportoAeroportoporto
    public static HashMap<Integer, ReservaVoo> getLista(LinkedList<String> s) throws IOException{ //Colocaremos informação do arquivo em listas
        HashMap<Integer, ReservaVoo> reserva_voo = new HashMap<>();
        while(!s.isEmpty()){
            ReservaVoo aux = new ReservaVoo(Integer.parseInt(s.pollFirst()), Integer.parseInt(s.pollFirst()));
            reserva_voo.put(aux.getId(), aux);
        }
        return reserva_voo;

    } 
    
    //Método que recebe os atributos de um objeto Aeroporto e o transforma em uma lista de strings para ser armazenadanos arquivos
    public LinkedList<String> getInfo(){
        LinkedList<String> lista = new LinkedList<>();
        if(v != null)
            lista.add(Integer.toString(this.getV().getId()));
        else 
            lista.add("-");
        lista.add(Integer.toString(this.getId()));
        return lista;
    }



//------------------------------------Get e Set -----------------------------------    
    public int getId() {
        return this.id;
    }
    public Voo getV() {
        return this.v;
    }
    public void setV(Voo v) {
        this.v = v;
    }
}
