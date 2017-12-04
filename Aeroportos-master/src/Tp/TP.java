/*package tp;

import View.TelaLogin;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import com.google.gson.Gson;
import java.util.LinkedList;


public class TP {
        
    // Iniciaremos todas as listas do programa lendo os dados dos arquivos    
    static void getListas() throws IOException{
        
        try{
            Aeroporto.lista_aeroporto = Aeroporto.getLista(CriaArquivo.read("aeroporto"));
            DescricaoAviao.lista_descricaoAviao = DescricaoAviao.getLista(CriaArquivo.read("aviao"));
            DescricaoVoo.lista_descricaoVoo = DescricaoVoo.getLista(CriaArquivo.read("descricao_voo"));
            Voo.lista_voo = Voo.getLista(CriaArquivo.read("voo"));
            ReservaVoo.lista_reservaVoo = ReservaVoo.getLista(CriaArquivo.read("reserva_voo"));
            ReservaViagem.lista_reservaViagem = ReservaViagem.getLista(CriaArquivo.read("reserva_viagem"));
            Cliente.lista_cliente = Cliente.getLista(CriaArquivo.read("cliente"));
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }    
    }
    
      
   
    public static void Aeroporto() throws IOException, FaltaObjetosException{
        System.out.println("Aeroporto: ");
        System.out.println("1 - Cria \n2 - Edita \n3 - Remove");
        int i  = new Scanner(System.in).nextInt();
        switch (i) {
            case 1:
                Aeroporto.criaAeroporto();
                break;
            case 2:
                try{
                    Aeroporto.editaAeroporto();
                }catch (FaltaObjetosException e) {
                    System.out.println(e.getMessage()); 
                }
                break;
            case 3:
                try{
                    Aeroporto.removeAeroporto();
                }catch (FaltaObjetosException e) {
                    System.out.println(e.getMessage()); 
                }
                
                break;
            default:
                break;
        }
    }
    
    public static void DescricaoVoo() throws IOException{
        System.out.println("Descricação Voo: ");
        System.out.println("1 - Cria \n2 - Edita \n3 - Remove");
        int i  = new Scanner(System.in).nextInt();
        switch (i) {
            case 1:
                try{
                    DescricaoVoo.criaDescricaoVoo();
                }catch (FaltaObjetosException e) {
                    System.out.println(e.getMessage()); 
                }
                
                break;
            case 2:
                try{
                    DescricaoVoo.editaDescricaoVoo();
                }catch (FaltaObjetosException e) {
                    System.out.println(e.getMessage()); 
                }
                
                break;
            case 3:
                try{
                    DescricaoVoo.removeDescricaoVoo();
                }catch (FaltaObjetosException e) {
                    System.out.println(e.getMessage()); 
                }
                
                break;
            default:
                break;
        }
    }
    public static void Voo() throws IOException{
        System.out.println("Voo: ");
        System.out.println("1 - Cria \n2 - Edita \n3 - Remove");
        int i  = new Scanner(System.in).nextInt();
        switch (i) {
            case 1:
                try{
                    Voo.criaVoo();
                }catch (FaltaObjetosException | IllegalArgumentException e) {
                    System.out.println(e.getMessage()); 
                }
                
                break;
            case 2:
                try{
                    Voo.editaVoo();
                }catch (FaltaObjetosException e) {
                    System.out.println(e.getMessage()); 
                }
                
                break;
            case 3:
                try{
                    Voo.removeVoo();
                }catch (FaltaObjetosException e) {
                    System.out.println(e.getMessage()); 
                }
                break;
            default:
                break;
        }
    }
    public static void Cliente() throws IOException, FaltaObjetosException{
        System.out.println("Cliente: ");
        System.out.println("1 - Cria \n2 - Edita \n3 - Remove");
        int i  = new Scanner(System.in).nextInt();
        switch (i) {
            case 1:
                Cliente.criaCliente();
                break;
            case 2:
                try{
                    Cliente.editaCliente();
                }catch (FaltaObjetosException e) {
                    System.out.println(e.getMessage()); 
                }
                
                break;
            case 3:
                try{
                    Cliente.removeCliente();
                }catch (FileNotFoundException e) {
                    System.out.println(e.getMessage()); 
                }
            default:
                break;
        }
    }
            
    
    public static void main(String[] args) throws FileNotFoundException, IOException, FaltaObjetosException {
        
       TelaLogin.getListas(); 
       

        //TP.Cliente();
       //Cliente c = Cliente.lista_cliente.get("12121212");
       //c.AdicionaReserva();
       
       //TP.Aeroporto();
       //TP.Aeroporto();       
       //TP.DescricaoAviao();
       //TP.DescricaoVoo();
       //TP.Voo();
       //TP.Cliente();
       //LinkedList<Voo> voo = Voo.obterVoo("belo horizonte", "londres", "11/11/11");
        //System.out.println(voo.size());
        //cria independente  -->
       

       //DescricaoVoo.criaDescricaoVoo(); //cria dependente  -->
       //Voo.criaVoo(); 
       //ReservaVoo.criaReservaVoo();
       //ReservaViagem.criaReservaViagem(); //add
       //Cliente.criaCliente(); //cria independete add dps 

       
    }

    
    
}
*/