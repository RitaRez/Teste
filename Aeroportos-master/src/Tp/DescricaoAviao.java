package tp;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;


public class DescricaoAviao {

    public static HashMap<Integer,DescricaoAviao> lista_descricaoAviao = new HashMap<>();

    private String nome_f; //nome do fabricante
    private int num_m; //número do modelo
    private int num_a; // número de assentos
    private final int id;
    private static int count = 0;

    
    DescricaoAviao(String nome, int a, int m, int id, boolean d) throws FileNotFoundException, IOException{
        this.num_m = m;
        this.num_a = a;
        this.nome_f = nome;        
        
        if (d == true){
            int aux = 0;
            for(int i = 0; true; i++){
                if(!DescricaoAviao.lista_descricaoAviao.containsKey(i)){
                    this.id = i;      
                    break;
                }
            }
     
            CriaArquivo.print("aviao", this.getInfo());     
        }else {
            this.id = id;
        }
    }    
    
    DescricaoAviao(String nome, int a, int m, int id){
        this.nome_f = nome;
        this.num_m = m;
        this.num_a = a;
        this.id = id;
        DescricaoAviao.count++;
    }
    
    public DescricaoAviao() {
        id = 0;
    }

    //Método que recebe uma lista de strings retirarada do arquivo de Aeroporto e coloca em objetos Aeroporto
    public static HashMap<Integer, DescricaoAviao> getLista(LinkedList<String> s) throws IOException{ //Colocaremos informação do arquivo em listas
        HashMap<Integer,DescricaoAviao> d_aviao = new HashMap<>();
        while(!s.isEmpty()){
            DescricaoAviao aux = new DescricaoAviao(s.pollFirst(), Integer.parseInt(s.pollFirst()), Integer.parseInt(s.pollFirst()), Integer.parseInt(s.pollFirst()));
            d_aviao.put(aux.getId(), aux);
        }
        return d_aviao;

    }
    
    //Método que recebe os atributos de um objeto Aeroporto e o transforma em uma lista de strings para ser armazenadanos arquivos
    public LinkedList<String> getInfo(){
        LinkedList<String> lista = new LinkedList<>();
        lista.add(this.nome_f);
        lista.add(Integer.toString(this.num_a)); //Se for int por exemplo
        lista.add(Integer.toString(this.num_m));
        lista.add(Integer.toString(this.getId()));
        return lista;
    }
 
    
//----------------------------- Cria Edita e Remove ----------------------------
    public static void criaDescricaoAviao(String nome, int n_vagas, int modelo) throws IOException{

        DescricaoAviao novo = new DescricaoAviao(nome, n_vagas, modelo,0, true);
        DescricaoAviao.lista_descricaoAviao.put(novo.getId(), novo);

    }       
    public static void editaDescricaoAviao(int id, String nome, int vagas, int modelo) throws IOException, FaltaObjetosException{
                
        if(!DescricaoAviao.lista_descricaoAviao.containsKey(id))
            throw new FaltaObjetosException("Essa descricao nn existe"); //try catch na main
        
        DescricaoAviao e = DescricaoAviao.lista_descricaoAviao.get(id);
        
        e.setNome_f(nome);
        e.setNum_m(modelo);
        e.setNum_a(vagas);
        
        DescricaoAviao.lista_descricaoAviao.put(e.getId(), e);
        CriaArquivo.deleteOne("aviao", Integer.toString(id), 4, 3);  
        CriaArquivo.print("aviao", e.getInfo());
        
        
    }
    public static void removeDescricaoAviao(int id) throws IOException, FaltaObjetosException{
        //na interface podia aparecer em lista tds e o user so clicar nela
        
        if(!DescricaoAviao.lista_descricaoAviao.containsKey(id))
            throw new FaltaObjetosException("Essa descricao nn existe"); //try catch na main
        else{    
                     
            try {
                CriaArquivo.deleteOne("aviao", Integer.toString(id), 4, 3); 
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
            

            DescricaoVoo.lista_descricaoVoo.entrySet().forEach((entry) -> {
                if(entry.getValue().getDv() != null){
                    if(entry.getValue().getDv().getId() == id){
                        
                        entry.getValue().setDv(null);
                        try {
                            CriaArquivo.update("descricao_voo", Integer.toString(id), 1);
                        } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            });
            
            
            DescricaoAviao.lista_descricaoAviao.remove(id);            
        }
    }
        
    
//-------------------------------- Get e Set------------------------------------    
    
    public String getNome_f() {
        return nome_f;
    }
    public void setNome_f(String nome_f) {
        this.nome_f = nome_f;
    }
    public int getNum_m() {
        return num_m;
    }
    public void setNum_m(int num_m) {
        this.num_m = num_m;
    }
    public int getNum_a() {
        return num_a;
    }
    public void setNum_a(int num_a) {
        this.num_a = num_a;
    }   
    public int getId() {
        return id;
    }
    
}
