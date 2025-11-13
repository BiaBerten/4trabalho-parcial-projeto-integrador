package recursos;

import java.util.List;

import entidades.RegistroDiario;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("registrosdiarios")
public class RegistroDiarioRecurso {
    @GET
    public List<RegistroDiario> listar(){
        return RegistroDiario.listAll();
    }


@POST
@Transactional
public void salvar(RegistroDiario registrodiario){
    registrodiario.persist();
}
}
