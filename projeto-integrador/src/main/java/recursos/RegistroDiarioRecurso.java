package recursos;

import java.util.List;

import entidades.RegistroDiario;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

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


@PUT
@Transactional
@Path("{codigo}")
public Response editar(@PathParam("codigo") Integer codigo, RegistroDiario registro) {
    RegistroDiario registroExistente = RegistroDiario.findById(codigo);

    if (registroExistente != null) {
        registroExistente.data = registro.data;
        registroExistente.humor = registro.humor;
        registroExistente.emocoes = registro.emocoes;
        registroExistente.sintomas = registro.sintomas;
        registroExistente.doencas = registro.doencas;
            
        return Response.noContent().build(); 
    } else {
        throw new NotFoundException("Registro Diário com código " + codigo + " não encontrado para edição.");
     }
    }


@DELETE
@Path("{codigo}")
@Transactional
public Response excluir(@PathParam("codigo") Integer codigo) {
    boolean deletado = RegistroDiario.deleteById(codigo);
        
    if (deletado) {
        return Response.noContent().build(); 
    } else {
        throw new NotFoundException("Registro Diário com código " + codigo + " não encontrado para exclusão.");
     }
    }
}
