package recursos;

import java.util.List;

import entidades.CicloMenstrual;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

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
}
