package recursos;

import java.util.List;

import entidades.InformacaoSaude;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("informacoessaude")
public class InformacaoSaudeRecurso {
    @GET
    public List<InformacaoSaude> listar(){
        return InformacaoSaude.listAll();
    }


@POST
@Transactional
public void salvar(InformacaoSaude informacaosaude){
    informacaosaude.persist();
}


@PUT
@Transactional
@Path("{codigo}")
public Response editar(@PathParam("codigo") Integer codigo, InformacaoSaude info) {
    InformacaoSaude infoExistente = InformacaoSaude.findById(codigo);

    if (infoExistente != null) {
        infoExistente.titulo = info.titulo;
        infoExistente.conteudo = info.conteudo;
        infoExistente.categoria = info.categoria;
        infoExistente.tags = info.tags; 
        
        return Response.noContent().build(); 
    } else {
        throw new NotFoundException("Informação de Saúde com código " + codigo + " não encontrada.");
     }
    }


@DELETE
@Path("{codigo}")
@Transactional
public Response excluir(@PathParam("codigo") Integer codigo) {
    boolean deletado = InformacaoSaude.deleteById(codigo);
        
    if (deletado) {
        return Response.noContent().build(); 
     } else {
        throw new NotFoundException("Informação de Saúde com código " + codigo + " não encontrada para exclusão.");
     }
    }
}
