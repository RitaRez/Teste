package tp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class DescricaoVoo {

    public static HashMap<Integer, DescricaoVoo> lista_descricaoVoo = new HashMap<>();    

    private String partida; //horario
    private String chegada; //horario 
    private Aeroporto origem;
    private Aeroporto destino;
    private DescricaoAviao dv;
    private int id;
    private static int count = 0;

    
    
    DescricaoVoo (String p, String c, String ids_origem, String ids_destino, int ids_descricao, int id, boolean d) throws IOException{
        this.partida = p;
        this.chegada = c;

        //Recuperar as informações de aeroporto de origem a partir dos arquivos
        Aeroporto aux = Aeroporto.lista_aeroporto.get(ids_origem);
        if (aux != null){
            this.origem = aux;
        }
    
        //Recuperar as informações de aeroporto de destino a partir dos arquivos
        aux = Aeroporto.lista_aeroporto.get(ids_destino);
        if (aux != null){
            this.destino = aux;
        }
        
        //Recuperar as informações descricao do aviao a partir dos arquivos
        DescricaoAviao auxiliar = DescricaoAviao.lista_descricaoAviao.get(ids_descricao);
        if (auxiliar != null){
            this.dv = auxiliar;
        }
        
        if (d == true){

            for(int i = 0; true; i++){
                if(!DescricaoVoo.lista_descricaoVoo.containsKey(i)){
                    this.id = i;      
                    break;
                }
            }

            CriaArquivo.print("descricao_voo", this.getInfo());     
        }else {
            this.id = id;
        }
        


    }    
    DescricaoVoo (String p, String c, String ids_origem, String ids_destino, String ids_descricao, int id){
        this.partida = p;
        this.chegada = c;

        //Recuperar as informações de aeroporto de origem a partir dos arquivos
        Aeroporto aux = Aeroporto.lista_aeroporto.get(ids_origem);
        if (aux != null){
            this.origem = aux;
        }
    
        //Recuperar as informações de aeroporto de destino a partir dos arquivos
        aux = Aeroporto.lista_aeroporto.get(ids_destino);
        if (aux != null){
            this.destino = aux;
        }
        
        //Recuperar as informações descricao do aviao a partir dos arquivos
        if(ids_descricao.equals("-")){
            this.dv = null;
        }else {        
            DescricaoAviao auxiliar = DescricaoAviao.lista_descricaoAviao.get(Integer.parseInt(ids_descricao));
            if (auxiliar != null){
                this.dv = auxiliar;
            }
        }
        this.id = id;
    
    }
    
    //Método que recebe uma lista de strings retirarada do arquivo de descricao_voo e coloca em objetos DescricaoVoo
    public static HashMap<Integer, DescricaoVoo> getLista(LinkedList<String> s) throws IOException{ //Colocaremos informação do arquivo em listas
        HashMap<Integer, DescricaoVoo> descricao_voo = new HashMap<>();
        while(!s.isEmpty()){
                                             //    partida,       chegada,      Aero origem    Aero destino   DescricaoAviao             id      
            DescricaoVoo aux = new DescricaoVoo(s.pollFirst(), s.pollFirst() , s.pollFirst(), s.pollFirst(), s.pollFirst(), Integer.parseInt(s.pollFirst()));
            descricao_voo.put(aux.getId(), aux);
        }
        return descricao_voo;

    } 
    
    //Método que recebe os atributos de um objeto Aeroporto e o transforma em uma lista de strings para ser armazenadanos arquivos
    public LinkedList<String> getInfo(){
        LinkedList<String> lista = new LinkedList<>();
        lista.add(this.partida);
        lista.add(this.chegada); 
        
        if(this.origem == null)
            lista.add("-");
        else 
            lista.add(this.origem.getId());
        if(this.destino == null)
            lista.add("-");
        else
            lista.add(this.destino.getId());
        if(this.dv != null)
            lista.add(Integer.toString(this.dv.getId()));
        else 
            lista.add("-");
        
        lista.add(Integer.toString(this.getId()));

        
        return lista;
    }  
    
    // ---------------------------  Cria Edita Remove --------------------------

    public static int criaDescricaoVoo(String partida, String chegada, String a_origem, String a_destino, int d_aviao) throws IOException, FaltaObjetosException{
    //        
        if(Aeroporto.lista_aeroporto.size() < 2){
            throw new FaltaObjetosException("Não existe Aeroportos para serem colocados, crie um primeiro"); //try catch ficara na main
        }
        
        if(DescricaoAviao.lista_descricaoAviao.isEmpty()){
            throw new FaltaObjetosException("Não existe descrições de avião para serem colocadas, crie uma primeiro"); //try catch ficara na main
        }
        
        if(!Aeroporto.lista_aeroporto.containsKey(a_origem))
            throw new FaltaObjetosException("O avião não existe");
            
        if(!Aeroporto.lista_aeroporto.containsKey(a_destino))
            throw new FaltaObjetosException("O avião não existe");
        
        if(!DescricaoAviao.lista_descricaoAviao.containsKey(d_aviao))
            throw new FaltaObjetosException("A descriçaõ não existe");
       
        
        DescricaoVoo da = new DescricaoVoo(partida, chegada, a_origem, a_destino, d_aviao, 0, true);
        DescricaoVoo.lista_descricaoVoo.put(da.getId(), da);
        return da.getId();
    }    
    public static void editaDescricaoVoo(int id, String partida, String chegada, String origem, String destino, int d_aviao) throws IOException, FaltaObjetosException{
 
        if(!DescricaoVoo.lista_descricaoVoo.containsKey(id))
            throw new FaltaObjetosException("Essa Descricao não existe");
       //dps e so usar o CriaArquivo.update c("descrica_voo", Integer.toString(this.getId()),this.getInfo() ,qtd_att)

        DescricaoVoo aux = DescricaoVoo.lista_descricaoVoo.get(id);
        
        DescricaoVoo d_voo = new DescricaoVoo(partida, chegada, origem, destino, d_aviao, id, false);
        DescricaoVoo.lista_descricaoVoo.put(d_voo.getId(), d_voo);
        CriaArquivo.deleteOne("descricao_voo", Integer.toString(id), 6, 5);  
        CriaArquivo.print("descricao_voo", d_voo.getInfo());
    }
    public static void removeDescricaoVoo(int id) throws IOException, FaltaObjetosException{
        
        if(!DescricaoVoo.lista_descricaoVoo.containsKey(id))
            throw new FaltaObjetosException("Essa Descricao não existe");
        
   
        try {
            CriaArquivo.deleteOne("descricao_voo", Integer.toString(id), 6, 5); 
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        Voo.lista_voo.entrySet().forEach((entry) -> {
            if(entry.getValue().getDv() != null){
                if(entry.getValue().getDv().getId() == id){
                    entry.getValue().setDv(null);
                    try {
                        
                        for(int i = 0; i < entry.getValue().getInfo().size(); i++){
                            System.out.println(entry.getValue().getInfo().get(i));
                        }
                        CriaArquivo.update("voo", Integer.toString(id), 1);
                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });    
        
        DescricaoVoo.lista_descricaoVoo.remove(id);    
        
    }



//------------------------------- Get e Set -------------------------------------
    
    public String getPartida() {
        return partida;
    }
    public void setPartida(String partida) {
        this.partida = partida;
    }
    public String getChegada() {
        return chegada;
    }
    public void setChegada(String chegada) {
        this.chegada = chegada;
    }
    public Aeroporto getOrigem() {
        return origem;
    }
    public void setOrigem(Aeroporto origem) {
        this.origem = origem;
    }
    public Aeroporto getDestino() {
        return destino;
    }
    public void setDestino(Aeroporto destino) {
        this.destino = destino;
    }
    public DescricaoAviao getDv() {
        return dv;
    }
    public void setDv(DescricaoAviao dv) {
        this.dv = dv;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
