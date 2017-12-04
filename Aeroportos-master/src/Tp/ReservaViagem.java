package tp;

import java.io.IOException;
import java.util.LinkedList;
import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class ReservaViagem {
    
    public static  HashMap< Integer, ReservaViagem> lista_reservaViagem = new HashMap<>();

    private LinkedList<ReservaVoo> rv = new LinkedList<>();
    private final int id;
    private static int count = 0;
    
    
    
    ReservaViagem(String voo) throws IOException{
        
        this.id = ReservaViagem.count; 
        ReservaViagem.count++;
        
        Gson gs = new Gson();
        String[] ids_voo = gs.fromJson(voo, String[].class);     
        ReservaVoo[] aux = new ReservaVoo[ids_voo.length];
        
        for(int i = 0; i < ids_voo.length; i++){
            aux[i] = ReservaVoo.lista_reservaVoo.get(Integer.parseInt(ids_voo[i]));
            if (aux != null)
                 this.rv.add(aux[i]);
        }

        CriaArquivo.print("reserva_viagem", this.getInfo());      
        
    }
    ReservaViagem(String voo, int id){
        //Recuperar as informações de reserva voo a partir dos arquivos
        Gson gs = new Gson();
        String[] ids_voo = gs.fromJson(voo, String[].class);     
        this.id = id;
        ReservaViagem.count++; 
        ReservaVoo[] aux = new ReservaVoo[ids_voo.length];
        
        for(int i = 0; i < ids_voo.length; i++){
            aux[i] =  ReservaVoo.lista_reservaVoo.get(Integer.parseInt(ids_voo[i]));
            if (aux != null)
                 rv.add(aux[i]);
        }
        
                      
    }
    

    //Método que recebe uma lista de strings retirarada do arquivo de Aeroporto e coloca em objetos AeroAeroportoAeroportoAeroportoporto
    public static HashMap<Integer, ReservaViagem> getLista(LinkedList<String> s) throws IOException{ //Colocaremos informação do arquivo em listas
        HashMap<Integer, ReservaViagem> reserva_viagem = new HashMap<>();
        while(!s.isEmpty()){
            ReservaViagem aux = new ReservaViagem(s.pollFirst(), Integer.parseInt(s.pollFirst()));
            reserva_viagem.put(aux.getId(), aux);
        }
        return reserva_viagem;

    } 
    
    //Método que recebe os atributos de um objeto Aeroporto e o transforma em uma lista de strings para ser armazenadanos arquivos
    public LinkedList<String> getInfo(){
        LinkedList<String> lista = new LinkedList<>();
        String voo = "[";
        
        if(this.getRv() != null){
            for(int i = 0; i < this.getRv().size(); i++){
                if(i != 0)
                    voo+= ',';
                voo += this.getRv().get(i).getId();

            }
        }        
        voo = voo + "]";
        lista.add(voo);
        lista.add(Integer.toString(this.getId()));
        return lista;
    }
    
    
    
    public void incluiReservaVoo(int id_voo) throws IOException, FaltaObjetosException{ //vai ser chamado de alguma r_viagem
        //ve os voos disponiveis, escolhe um, passa o id TOP
        if(!Voo.lista_voo.containsKey(id_voo))
            throw new FaltaObjetosException("Esse voo não existe");
        
        ReservaVoo rvoo = new ReservaVoo(id_voo); //cria nova r_voo
        ReservaVoo.lista_reservaVoo.put(rvoo.getId(), rvoo);
        this.getRv().add(rvoo); //coloca na lista de r_viagem
        
        ReservaVoo.lista_reservaVoo.get( rvoo.getId() ).getV().setVagas(ReservaVoo.lista_reservaVoo.get( rvoo.getId() ).getV().getVagas() - 1);

        
        CriaArquivo.deleteOne("voo", String.valueOf( ReservaVoo.lista_reservaVoo.get( rvoo.getId() ).getV().getId() ) , 4, 3);
        CriaArquivo.print("voo", ReservaVoo.lista_reservaVoo.get( rvoo.getId() ).getV().getInfo());
        
        CriaArquivo.deleteOne("reserva_viagem", Integer.toString(this.getId()), 2, 1);
        CriaArquivo.print("reserva_viagem", this.getInfo());
         
    }        
    public void removeReservaVoo(int id) throws FaltaObjetosException, IOException{
        
        boolean controle = false;
        for(int i = 0; i < this.rv.size(); i++){
            if(this.rv.get(i).getId() == id){
                controle = true;
                this.rv.remove(i);
                break;
            }
        }
        
        if(controle == false)
            throw new FaltaObjetosException("Nao existe"); //pop up maroto
        
        //this.getRv().remove(id);
        
        ReservaVoo.lista_reservaVoo.get(id).getV().setVagas(ReservaVoo.lista_reservaVoo.get(id).getV().getVagas() + 1);
        //ReservaVoo.lista_reservaVoo.remove(id);
        
        try {
            CriaArquivo.deleteOne("reserva_voo", Integer.toString(id), 2, 1);
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        CriaArquivo.deleteOne("voo", String.valueOf( ReservaVoo.lista_reservaVoo.get(id).getV().getId() ) , 4, 3);
        CriaArquivo.print("voo", ReservaVoo.lista_reservaVoo.get(id).getV().getInfo());
        
        CriaArquivo.deleteOne("reserva_viagem", Integer.toString(this.getId()), 2, 1);
        CriaArquivo.print("reserva_viagem",getInfo());
        
        ReservaVoo.lista_reservaVoo.remove(id);
        
    }  
    
    
    public static LinkedList<Integer> getIdsReservaVoo(int rviagem) throws FaltaObjetosException{
        if(!ReservaViagem.lista_reservaViagem.containsKey(rviagem))
            throw new FaltaObjetosException("A reserva de viagem não existe");
        
        LinkedList<Integer> lista = new LinkedList<>();                  
        for(int i = 0; i < ReservaViagem.lista_reservaViagem.get(rviagem).getRv().size(); i++)    
           lista.add(ReservaViagem.lista_reservaViagem.get(rviagem).getRv().get(i).getId());
        return lista;
      
    }
    
    
    
// ---------------------------------Get e Set ------------------------------------    
    public int getId() {
        return id;
    }
    public LinkedList<ReservaVoo> getRv() {
        return rv;
    }
    public void setRv(LinkedList<ReservaVoo> rv) {
        this.rv = rv;
    }
   

}