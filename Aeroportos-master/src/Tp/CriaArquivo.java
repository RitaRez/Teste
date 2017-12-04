package tp;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class CriaArquivo {

    // Método abre arquivo(variando da classe que a qual este salva os dados) e adiciona tudo das listas da classe
    public static void print(String nomeA, List<String> info) throws FileNotFoundException, IOException{
        PrintWriter writer;
        writer = new PrintWriter(new FileWriter("." + nomeA + ".txt", true));
        info.forEach((s) -> {
            //Para cada string (que vamos chamar de s) da lista "info"
            writer.append(s + "\n");        
        });
        writer.close();   
    }
    
    // Método recebe objeto específico e apaga este
    public static void deleteOne(String nomeA, String s, int qtd_att, int auxiliar) throws FileNotFoundException, IOException{
        FileReader reader;
        LinkedList<String> lista = new LinkedList<>();
        reader = new FileReader("." + nomeA + ".txt"); 
        if(reader == null)
            throw new FileNotFoundException("Não foi possível acessar o arquivo");
        String arquivo = "";
        while(reader.ready()){ //Enquanto tiver coisas pra ler
            char aux;
            aux = (char) reader.read();
            if(aux != '\n'){
                arquivo += aux;
            } else {
                lista.add(arquivo);
                
                arquivo = "";
                
            }
        }
          
        for(int i = 0; i <= lista.size() - qtd_att; i += qtd_att){
            if(lista.get(i + auxiliar).equals(s)){
                
                for(int j = 0; j < qtd_att; j++){
                    lista.remove(i);

                }

            }
        }

        
                                                  
        reader.close();                           
        CriaArquivo.deleteAll(nomeA);             
        CriaArquivo.print(nomeA, lista);
    }
    
    // Método apaga arquivo e o substitui por um em branco
    public static void deleteAll(String nomeA) throws FileNotFoundException, IOException{
        PrintWriter writer;
        writer = new PrintWriter("." + nomeA + ".txt");
        writer.close();   
    }
    
    // Método recebe objeto específico de uma classe específica e substitui este
    public static void update(String nomeA, String s, int auxiliar) throws FileNotFoundException, IOException{
        FileReader reader;
        LinkedList<String> lista = new LinkedList<>();
        reader = new FileReader("." + nomeA + ".txt"); 
        int count = 1;
        if(reader == null)
            throw new FileNotFoundException("Não foi possível acessar o arquivo");
        String arquivo = "";
        if(!reader.ready())
            throw new IOException("Não foi possivel ler o arquivo");
        while(reader.ready()){ //Enquanto tiver coisas pra ler
        
            char aux;
            aux = (char) reader.read();
            
            if(aux != '\n'){
                arquivo += aux;
            } else {
                lista.add(arquivo);
                arquivo = "";                 
            }
   
        }
        for(int i = 0; i < lista.size(); i++){
            if(i % 2 != auxiliar && lista.get(i).equals(s)){
                lista.set(i, "-");
            }
        }
        reader.close();
        CriaArquivo.deleteAll(nomeA);
        CriaArquivo.print(nomeA, lista);
    }
    
    // Método lê todos os objetos do arquivo de uma classe e os retorna em lista de string
    public static LinkedList<String> read(String nomeA) throws FileNotFoundException, IOException{
        FileReader reader;
        LinkedList<String> lista = new LinkedList<>();
        String output = "";
        reader = new FileReader("." + nomeA + ".txt");   
        if(reader == null){
            throw new FileNotFoundException("Não foi possível acessar o arquivo");
        }
        while(reader.ready()){ //Enquanto tiver coisas pra ler
            char aux;
            aux = (char) reader.read();
            if(aux != '\n'){
                output += aux;
            } else {
                if(output != "")
                    lista.add(output);
                output = "";

            }
        }
        reader.close();
        return lista;
    }
}
