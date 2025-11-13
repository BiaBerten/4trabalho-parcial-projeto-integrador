package recursos;

import java.util.List;

import entidades.MetodoContraceptivo;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

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
}
