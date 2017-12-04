package tp;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class Cliente{

    public static  HashMap<String, Cliente> lista_cliente = new HashMap<>();   

    
    private String Nome;
    private String Telefone;
    private String Endereço;
    private String CPF;
    private LinkedList<ReservaViagem> rv = new LinkedList<>();


    
    Cliente (String nome, String tel, String end, String cpf, boolean d) throws IOException{
        this.Nome = nome;
        this.Telefone = tel;
        this.Endereço = end;
        this.CPF = cpf;            
        
        if (d == true)
            CriaArquivo.print("cliente", this.getInfo());

    }    
    Cliente (String nome, String tel, String end, String r_viagem, String cpf) throws IOException{
        this.Nome = nome;
        this.Telefone = tel;
        this.Endereço = end;
        this.CPF = cpf;        
        if(!"[]".equals(r_viagem)){
            Gson gs = new Gson();
            String[] ids_viagem = gs.fromJson(r_viagem, String[].class);//id da reserva de viagem 
            ReservaViagem[] aux = new ReservaViagem[ids_viagem.length];

            for(int i = 0; i < ids_viagem.length; i++){
                aux[i] = ReservaViagem.lista_reservaViagem.get(Integer.parseInt(ids_viagem[i]));
                if (aux[i] != null)
                     this.rv.add(aux[i]);
            }
        }
    }           
    
    public static HashMap<String, Cliente> getLista(LinkedList<String> s) throws IOException{ //Colocaremos informação do arquivo em listas
        HashMap<String, Cliente> cliente = new HashMap<>();
        while(!s.isEmpty()){
            Cliente aux = new Cliente(s.pollFirst(),s.pollFirst(),s.pollFirst(),s.pollFirst(),s.pollFirst());
            cliente.put(aux.getCPF(), aux);
        }
        return cliente;
    }
    public LinkedList<String> getInfo(){
        LinkedList<String> lista = new LinkedList<>();
        
        lista.add(this.Nome);
        lista.add(this.Telefone);
        lista.add(this.Endereço);
        if(!lista.isEmpty()){
            String voo = "[";
            if(this.getRv()!= null){
                if(this.getRv() != null){
                    for(int i = 0; i < this.getRv().size(); i++){
                        if(i != 0)
                            voo+= ',';
                        voo += this.getRv().get(i).getId();

                    }
                }     
            }
            voo = voo + "]";
            lista.add(voo);        

        }else{
            lista.add("--");
        }
        lista.add(this.CPF);        
        return lista;
        
        
    }    

    
//------------------------------- Cria Edita e Remove -----------------------------    

    public static void criaCliente(String nome, String tel, String endereco, String cpf) throws IOException{
        //EU SEI, TA FEIO
        // CE Q VAI CONCERTAR NA INTERFACE GRAFICA MENINO MARCIEL        
        Cliente da = new Cliente(nome, tel, endereco, cpf, true);
        Cliente.lista_cliente.put(da.getCPF(), da);
    }
    public static void editaCliente(String cpf, String nome, String telefone, String endereco, String novoCpf) throws IOException, tp.FaltaObjetosException{

        if(!Cliente.lista_cliente.containsKey(cpf))
            throw new FaltaObjetosException("O id não existe");
        
        Cliente.lista_cliente.remove(cpf);
        
        Cliente C = new Cliente(nome, telefone, endereco, novoCpf, false);   

        Cliente.lista_cliente.put(C.getCPF(), C);
        CriaArquivo.deleteOne("cliente", cpf, 5, 4);
        CriaArquivo.print("cliente", C.getInfo());
        
    }
    public static void removeCliente(String cpf) throws IOException, tp.FaltaObjetosException{
        
        if(!Cliente.lista_cliente.containsKey(cpf))
            throw new FaltaObjetosException("Não existe objeto");
        
        Cliente.lista_cliente.remove(cpf);        
        
        try {
            CriaArquivo.deleteOne("cliente", cpf, 5, 4); 
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } 

    }



    public void AdicionaReserva() throws IOException{
        ReservaViagem reservaViagem = new ReservaViagem("[]");
        ReservaViagem.lista_reservaViagem.put(reservaViagem.getId(), reservaViagem);
        this.getRv().add(reservaViagem);
        CriaArquivo.deleteOne("cliente", this.getCPF(), 5, 4);                
        CriaArquivo.print("cliente", this.getInfo());
        
    }
    
    public void RemoveReserva(int id) throws IOException, tp.FaltaObjetosException{
        if (this.getRv().isEmpty())
            throw new FaltaObjetosException("Não existem reservas de viagem cadastradas para esse cliente");
       
               
        int i;
        boolean controle = false;
        for(i = 0; i < this.rv.size(); i++){
            if(this.rv.get(i).getId() == id){
                controle = true;
                break;
            }
        }
       
        if(controle == false)
            throw new FaltaObjetosException("Nao existe"); //pop up maroto
 
 
        for (int j = 0;j <this.rv.get(i).getRv().size(); j++){
            //this.rv.get(i).getRv().get(j).getV().setVagas(this.rv.get(i).getRv().get(j).getV().getVagas() + 1);
            Voo.lista_voo.get( this.rv.get(id).getRv().get(j).getV().getId()).setVagas( this.rv.get(i).getRv().get(j).getV().getVagas() + 1 );
            
            CriaArquivo.deleteOne("voo", Integer.toString(this.rv.get(i).getRv().get(j).getV().getId()), 4, 3);
            CriaArquivo.print("voo",this.rv.get(i).getRv().get(j).getV().getInfo());
            CriaArquivo.deleteOne("reserva_voo", Integer.toString(this.rv.get(i).getRv().get(j).getId()), 2, 1);
            this.rv.get(i).getRv().remove(j);
        }
 
        CriaArquivo.deleteOne("reserva_viagem", Integer.toString(id), 2, 1);
        ReservaViagem.lista_reservaViagem.remove(id);        
       
        this.rv.remove(i);        
        CriaArquivo.deleteOne("cliente", this.getCPF(), 5, 4);
        CriaArquivo.print("cliente", this.getInfo());
        
    }
    
    /*
    public void RemoveReserva(int id) throws IOException, tp.FaltaObjetosException{
        int i;
        if (this.getRv().isEmpty())
            throw new FaltaObjetosException("Não existem reservas de viagem cadastradas para esse cliente");
        
        boolean controle = false;
        for(i = 0; i < this.rv.size(); i++){
            if(this.rv.get(i).getId() == id){
                controle = true;
                break;
            }
        }
        
        if(controle == false)
            throw new FaltaObjetosException("Nao existe"); //pop up maroto
        
        
        this.getRv().remove(i);
        
        ReservaViagem.lista_reservaViagem.get( id ).getRv().forEach((resVoo) -> {
        
            ReservaVoo.lista_reservaVoo.get( resVoo.getV().getId() )
            
        });
        
        for(int j = 0; j < ReservaViagem.lista_reservaViagem.get(id).getRv().size(); j++){
            CriaArquivo.deleteOne("reserva_voo", Integer.toString(ReservaViagem.lista_reservaViagem.get(id).getRv().get(j).getId()), 2, 1);
            ReservaVoo.lista_reservaVoo.remove(ReservaViagem.lista_reservaViagem.get(id).getRv().get(j).getId());
            //ReservaViagem.lista_reservaViagem.get(id).getRv().get(j).getV().setVagas(ReservaViagem.lista_reservaViagem.get(id).getRv().get(j).getV().getVagas() + 1);
            CriaArquivo.deleteOne("voo", Integer.toString(ReservaViagem.lista_reservaViagem.get(id).getRv().get(j).getId()) , 4, 3);
            CriaArquivo.print("voo", (ReservaViagem.lista_reservaViagem.get(id).getRv().get(j).getInfo()));
        }
        
        
        try {
            CriaArquivo.deleteOne("reserva_viagem", Integer.toString(id), 2, 1);
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch(IOException e){
            System.out.println(e.getMessage());
        }

        CriaArquivo.deleteOne("cliente", this.getCPF(), 5, 4);
        CriaArquivo.print("cliente", this.getInfo());

    }
 */

//-------------------------------Get e Set --------------------------------------    
    public String getNome() {
        return Nome;
    }
    public void setNome(String Nome) {
        this.Nome = Nome;
    }
    public String getTelefone() {
        return Telefone;
    }
    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }
    public String getEndereço() {
        return Endereço;
    }
    public void setEndereço(String Endereço) {
        this.Endereço = Endereço;
    }
    public String getCPF() {
        return CPF;
    }
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    public LinkedList<ReservaViagem> getRv() {
        return rv;
    }

  
}

