package tp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class Voo {

    public static  HashMap<Integer,Voo> lista_voo = new HashMap<>();

    private String data_p;
    private int vagas; //vagas livres
    private DescricaoVoo dv;
    private int id;
    static int count = 0;
    

    Voo(String dp, int vagas, int dv, int id, boolean d) throws IOException{
                
        
        
        this.vagas = vagas;
        this.data_p = dp;        
        //Recuperar as informações de aeroporto de origem a partir dos arquivos
        DescricaoVoo aux = DescricaoVoo.lista_descricaoVoo.get(dv);        
        if (aux != null){
            this.dv = aux;

        }  
                
        if (d == true){
            for(int i = 0; true; i++){
                if(!Voo.lista_voo.containsKey(i)){
                    this.id = i;      
                    break;
                }
            }

            CriaArquivo.print("voo", this.getInfo());     
        }else {
            this.id = id;
        }
    }   
    Voo(String dp, int vagas, String dv, int id){
        this.data_p = dp;
        this.vagas = vagas;
        //Recuperar as informações de descricao de voo a partir dos arquivos
        if(dv.equals("-"))
            this.dv = null;
        else {
            DescricaoVoo aux = DescricaoVoo.lista_descricaoVoo.get(Integer.parseInt(dv));
            if (aux != null){
                this.dv = aux;
            }
        }        
        this.id = id;
                        
    }

    //Método que recebe uma lista de strings retirarada do arquivo de voo e coloca em objetos Voo
    public static HashMap<Integer, Voo> getLista(LinkedList<String> s) throws IOException{ //Colocaremos informação do arquivo em listas
        HashMap<Integer, Voo> voo = new HashMap<>();
        while(!s.isEmpty()){
                           //    data_p                  vagas              id_descricao voo                id   
            Voo aux = new Voo(s.pollFirst(), Integer.parseInt(s.pollFirst()), s.pollFirst(), Integer.parseInt(s.pollFirst()));
            voo.put(aux.getId(), aux);
        }
        return voo;
    } 
    
    //Método que recebe os atributos de um objeto Aeroporto e o transforma em uma lista de strings para ser armazenadanos arquivos
    public LinkedList<String> getInfo(){
        LinkedList<String> lista = new LinkedList<>();
        lista.add(this.data_p);
        lista.add(Integer.toString(this.vagas));
        if(this.dv != null)
            lista.add(Integer.toString(this.dv.getId()));
        else
            lista.add("-");
        lista.add(Integer.toString(this.getId()));

        return lista;
    }
    
// -------------------------------------- Cria Edita e Remove ------------------------
    public static void criaVoo(String partida, int d_voo, int vagas) throws IOException, FaltaObjetosException{
    //        
        if(Aeroporto.lista_aeroporto.isEmpty()){
            throw new FaltaObjetosException("Não existe Descrições de voo para serem colocadas, crie uma primeiro"); //try catch ficara na main
        }
        
            if(!DescricaoVoo.lista_descricaoVoo.containsKey(d_voo))
                throw new FaltaObjetosException("Essa descrição não existe");
        
        if(DescricaoVoo.lista_descricaoVoo.get(d_voo).getDv() != null)
            if(vagas > DescricaoVoo.lista_descricaoVoo.get(d_voo).getDv().getNum_a())
                throw new IllegalArgumentException("A quantidade de vagas digitada é inválida, de uma quantidade menor");           

        
        try{
            Voo da = new Voo(partida, vagas, d_voo, 0, true);
            Voo.lista_voo.put(da.getId(), da);

        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }    
    public static void editaVoo(int id, String data, int vagas, int dv) throws IOException, FaltaObjetosException{
        if(!Voo.lista_voo.containsKey(id))
            throw new FaltaObjetosException("Não existe"); // aqui ce poe aql pop up top
        
        //int vagas = Voo.lista_voo.get(id).getVagas();
        //int dv = Voo.lista_voo.get(id).dv.getId();
        
        Voo v = new Voo(data, vagas, dv, id, false); 
        Voo.lista_voo.put(v.getId(), v);
        CriaArquivo.deleteOne("voo", Integer.toString(id), 4, 3);  
        CriaArquivo.print("voo", v.getInfo());


                  
    }
    public static void removeVoo(int id) throws IOException, FaltaObjetosException{
        
        if(!Voo.lista_voo.containsKey(id))
            throw new FaltaObjetosException("Não existe"); // aqui ce poe aql pop up top
                
        try {
            CriaArquivo.deleteOne("voo", Integer.toString(id), 4, 3); 
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());                           
        } catch (IOException e ){
            System.out.println(e.getMessage());
        }
        
        ReservaVoo[] rv = new ReservaVoo[ReservaVoo.lista_reservaVoo.size()];
        rv = ReservaVoo.lista_reservaVoo.values().toArray(rv);
        
        for (ReservaVoo rv1 : rv) {
            if (rv1.getV() != null) {
                if (rv1.getV().getId() == id) {
                    ReservaVoo.lista_reservaVoo.remove(rv1.getId());
                    try {
                        CriaArquivo.deleteOne("reserva_voo", Integer.toString(id), 2, 0);
                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception s){
                        System.out.println(s.getMessage());
                        
                    }
                }
            }
        }
        Voo.lista_voo.remove(id);
        
    }

    
//------------------ Apartir de blablabla obtenha blablabla -------------------    
     
    
    public static int obterVagas(int id_voo) throws FaltaObjetosException{
        if(!Voo.lista_voo.containsKey(id_voo))
            throw new FaltaObjetosException("O voo não existe");
        
        return Voo.lista_voo.get(id_voo).getVagas();
    }    
    public static LinkedList<String> obterHorarios(int id_voo) throws FaltaObjetosException{
        
        LinkedList<String> lista = new LinkedList<>();
        
        if(!Voo.lista_voo.containsKey(id_voo))
            throw new FaltaObjetosException("O voo não existe");
        
        lista.add(Voo.lista_voo.get(id_voo).getDataPartida());
        lista.add(Voo.lista_voo.get(id_voo).getDv().getChegada());
        lista.add(Voo.lista_voo.get(id_voo).getDv().getPartida());
        
        return lista;
    }
    public static DescricaoAviao obterDescricaoAviao(int id_voo) throws FaltaObjetosException{
        if(!Voo.lista_voo.containsKey(id_voo))
            throw new FaltaObjetosException("O voo não existe");
        
        return Voo.lista_voo.get(id_voo).getDv().getDv();
                
    }
    public static LinkedList<String> obterAeroportos(int id_voo)throws FaltaObjetosException{
        
        LinkedList<String> lista= new LinkedList<>();
        
        if(!Voo.lista_voo.containsKey(id_voo))
            throw new FaltaObjetosException("O voo não existe");
        
        lista.add( Voo.lista_voo.get(id_voo).getDv().getDestino().getId());
        lista.add( Voo.lista_voo.get(id_voo).getDv().getDestino().getNome());
        lista.add(Voo.lista_voo.get(id_voo).getDv().getOrigem().getId());
        lista.add( Voo.lista_voo.get(id_voo).getDv().getDestino().getNome());
        
        return lista;
    }
    public static LinkedList<Voo> obterVoo(String cidade_origem, String cidade_destino, String data) throws FaltaObjetosException{
        
        LinkedList<Voo> lista = new LinkedList<>();
        Voo[] voo = new Voo[Voo.lista_voo.size()];
        voo = Voo.lista_voo.values().toArray(voo);
        
        for (Voo voo1 : voo) {
            if(voo1.getDv().getOrigem().getCidade().equals(cidade_origem) && voo1.getDv().getDestino().getCidade().equals(cidade_destino) && voo1.getDataPartida().equals(data))
                if(voo1.getVagas() != 0) 
                    lista.add(voo1);                                
        }
        
        return lista;
                
    }

//-------------------------------- Get e Set-----------------------------------------------    
    
    public String getDataPartida() {
        return data_p;
    }
    public void setDataPartida(String dataPartida) {
        this.data_p = dataPartida;
    }
    public int getVagas() {
        return vagas;
    }
    public void setVagas(int vagas) {
        this.vagas = vagas;
    }
    public DescricaoVoo getDv() {
        return dv;
    }
    public void setDv(DescricaoVoo dv) {
        this.dv = dv;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
}
