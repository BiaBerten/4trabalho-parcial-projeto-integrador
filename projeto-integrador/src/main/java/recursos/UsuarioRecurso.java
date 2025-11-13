package recursos;

import java.util.List;

import entidades.Usuario;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("usuarios")
public class UsuarioRecurso {
    @GET
    public List<Usuario> listar(){
        return Usuario.listAll();
    }


@POST
@Transactional
public void salvar(Usuario usuario){
    usuario.persist();
}

}
