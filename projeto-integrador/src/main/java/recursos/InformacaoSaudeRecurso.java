package recursos;

import java.util.List;

import entidades.InformacaoSaude;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

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
}
