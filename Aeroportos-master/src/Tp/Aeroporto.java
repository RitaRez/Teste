package tp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;


public class Aeroporto {

    public static HashMap<String, Aeroporto> lista_aeroporto = new HashMap<>();    

    private String nome;
    private String cidade;
    private String id;
    

    public Aeroporto(String nome, String cidade, String id, boolean d) throws IOException {
        this.nome = nome;
        this.cidade = cidade;
        this.id = id;  
        
        if(d == true)
            CriaArquivo.print("aeroporto", this.getInfo());                

    }    

    Aeroporto(String nome, String cidade, String id){
        this.nome = nome;
        this.cidade = cidade;
        this.id = id;
    }
    
    //Método que recebe uma lista de strings retirarada do arquivo de Aeroporto e coloca em objetos Aeroporto
    public static HashMap<String, Aeroporto> getLista(LinkedList<String> s) throws IOException{ //Colocaremos informação do arquivo em listas
        HashMap<String, Aeroporto> aeroporto = new HashMap<>();
        while(!s.isEmpty()){
            Aeroporto aux = new Aeroporto(s.pollFirst(), s.pollFirst(), s.pollFirst());
            aeroporto.put(aux.getId(), aux);
        }
        return aeroporto;

    } 
    
    //Método que recebe os atributos de um objeto Aeroporto e o transforma em uma lista de strings para ser armazenadanos arquivos
    public LinkedList<String> getInfo(){
        LinkedList<String> lista = new LinkedList<>();
        lista.add(this.nome);
        lista.add(this.cidade); 
        lista.add(this.id); 
        return lista;
    }       

    
    
//-----------------------------Cria Edita e Remove ----------------------------    
    public static void criaAeroporto() throws IOException{
          
        System.out.println("Digite o nome do novo aeroporto: ");
        String nome = new Scanner(System.in).nextLine();
        System.out.println("Digite a cidade do novo aeroporto: ");
        String cidade = new Scanner(System.in).nextLine();
        String identificador = "";
        

        boolean b = false;
        while (!b){
            System.out.println("Digite o identificador do novo aeroporto: ");
            identificador = new Scanner(System.in).nextLine();
            if(Aeroporto.lista_aeroporto.isEmpty())
                b = true;
            if(!Aeroporto.lista_aeroporto.containsKey(identificador)){
                b = true;
            }
        }
        
        Aeroporto novo_aeroporto = new Aeroporto(nome, cidade, identificador, true);
        Aeroporto.lista_aeroporto.put(novo_aeroporto.getId(),novo_aeroporto);

    }    
    public static void editaAeroporto() throws IOException, FaltaObjetosException{
        String nome, cidade;
        
        System.out.println("Digite o identificador do Aeroporto que você irá editar: ");
        String id = new Scanner(System.in).nextLine();

        
        if(!Aeroporto.lista_aeroporto.containsKey(id))
            throw new FaltaObjetosException("O id não existe");
        

        nome = Aeroporto.lista_aeroporto.get(id).getNome();
        cidade = Aeroporto.lista_aeroporto.get(id).getCidade();           
        Aeroporto.lista_aeroporto.remove(id);        
        int botao = 1;
        switch(botao){
            case 1:
                System.out.println("Digite o nome do novo aeroporto: "); 
                nome = new Scanner(System.in).nextLine();  
                break;
            case 2:
               System.out.println("Digite a cidade do novo aeroporto: ");
                cidade = new Scanner(System.in).nextLine(); 
                break;
        }
        Aeroporto a = new Aeroporto(nome, cidade, id, false);
        Aeroporto.lista_aeroporto.put(a.getId(), a);
        CriaArquivo.deleteOne("aeroporto", id, 3, 2);  
        CriaArquivo.print("aeroporto", a.getInfo());


        
    }
    public static void removeAeroporto() throws IOException, FaltaObjetosException{
        System.out.println("Digite o id do aeroporto que será removido: ");        
        String id = new Scanner(System.in).nextLine();

        
        if(!Aeroporto.lista_aeroporto.containsKey(id))
            throw new FaltaObjetosException("O id não existe");
        
        else{
            try {
                CriaArquivo.deleteOne("aeroporto", id, 3, 2); 
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } 
            
            
            DescricaoVoo.lista_descricaoVoo.entrySet().forEach((entry) -> {
            
                if(entry.getValue().getOrigem() != null){
                    if(entry.getValue().getOrigem().getId().equals(id)){

                        entry.getValue().setOrigem(null);
                        try {
                            CriaArquivo.update("descricao_voo", id, 1);
                        } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }



                    }   
                }    

            });
            
        
            
            DescricaoVoo.lista_descricaoVoo.entrySet().forEach((entry) -> {
                
                if(entry.getValue().getDestino() != null){
                    if(entry.getValue().getDestino().getId().equals(id)){
                        entry.getValue().setDestino(null);
                        try {
                            CriaArquivo.update("descricao_voo", id, 0);
                        } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }

                    }
                }

            });
            
            Aeroporto.lista_aeroporto.remove(id);                
        
            
        }

        
        
    }

//------------------------------- Get e Set -----------------------------------    

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

}
