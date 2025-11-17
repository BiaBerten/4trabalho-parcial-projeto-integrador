package recursos;

import java.util.List;

import entidades.CicloMenstrual;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("ciclosmenstruais")
public class CicloMenstrualRecurso {
    @GET
    public List<CicloMenstrual> listar(){
        return CicloMenstrual.listAll();
    }


@POST
@Transactional
public void salvar(CicloMenstrual ciclomenstrual){
    ciclomenstrual.persist();
}



@PUT
@Transactional
@Path("{codigo}")
public Response editar(@PathParam("codigo") Integer codigo, CicloMenstrual ciclo) {
    CicloMenstrual cicloExistente = CicloMenstrual.findById(codigo);

    if (cicloExistente != null) {
        cicloExistente.dataInicio = ciclo.dataInicio;
        cicloExistente.duracaoCiclo = ciclo.duracaoCiclo;
        cicloExistente.duracaoMenstruacao = ciclo.duracaoMenstruacao;

    return Response.noContent().build();
    } else {
        throw new NotFoundException("Ciclo Menstrual com código " + codigo + " não encontrado.");
        }
    }


@DELETE
@Path("{codigo}")
@Transactional
public Response excluir(@PathParam("codigo") Integer codigo) {
    boolean deletado = CicloMenstrual.deleteById(codigo);
        
    if (deletado) {
        return Response.noContent().build(); 
    } else {
        throw new NotFoundException("Ciclo Menstrual com código " + codigo + " não encontrado para exclusão.");
     }
    }
}
