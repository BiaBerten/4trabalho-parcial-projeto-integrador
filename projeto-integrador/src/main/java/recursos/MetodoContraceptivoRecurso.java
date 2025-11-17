package recursos;

import java.util.List;

import entidades.MetodoContraceptivo;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("metodoscontraceptivos")
public class MetodoContraceptivoRecurso {
    @GET
    public List<MetodoContraceptivo> listar(){
        return MetodoContraceptivo.listAll();
    }


@POST
@Transactional
public void salvar(MetodoContraceptivo metodocontraceptivo){
    metodocontraceptivo.persist();
}


@PUT
@Transactional
@Path("{codigo}")
public Response editar(@PathParam("codigo") Integer codigo, MetodoContraceptivo metodo) {
    MetodoContraceptivo metodoExistente = MetodoContraceptivo.findById(codigo);

    if (metodoExistente != null) {
        metodoExistente.nome = metodo.nome;
        metodoExistente.tipo = metodo.tipo;
        metodoExistente.eficacia = metodo.eficacia;
        metodoExistente.descricao = metodo.descricao;

        return Response.noContent().build(); 
    } else {
        throw new NotFoundException("Método Contraceptivo com código " + codigo + " não encontrado para edição.");
     }
    }


@DELETE
@Path("{codigo}")
@Transactional
public Response excluir(@PathParam("codigo") Integer codigo) {
    boolean deletado = MetodoContraceptivo.deleteById(codigo);
        
    if (deletado) {
        return Response.noContent().build(); 
    } else {
        throw new NotFoundException("Método Contraceptivo com código " + codigo + " não encontrado para exclusão.");
      }
    }
}
