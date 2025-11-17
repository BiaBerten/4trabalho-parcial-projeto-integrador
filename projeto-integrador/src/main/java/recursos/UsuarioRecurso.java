package recursos;

import java.util.List;

import entidades.Usuario;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

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


@PUT
@Transactional
@Path("{codigo}")
public Response editar(@PathParam("codigo") Integer codigo, Usuario usuario) {
    Usuario usuarioExistente = Usuario.findById(codigo);

    if (usuarioExistente != null) {
        usuarioExistente.nome = usuario.nome;
        usuarioExistente.email = usuario.email;
        usuarioExistente.senha = usuario.senha; 
        usuarioExistente.dataNascimento = usuario.dataNascimento;
        usuarioExistente.genero = usuario.genero;
        usuarioExistente.preferenciaNotificacao = usuario.preferenciaNotificacao;
            
        return Response.noContent().build(); 
     } else {
        throw new NotFoundException("Usuário com código " + codigo + " não encontrado para edição.");
     }
    }


@DELETE
@Path("{codigo}")
@Transactional
public Response excluir(@PathParam("codigo") Integer codigo) {
    boolean deletado = Usuario.deleteById(codigo);
        
    if (deletado) {
        return Response.noContent().build(); 
    } else {
        throw new NotFoundException("Usuário com código " + codigo + " não encontrado para exclusão.");
     }
    }
}
