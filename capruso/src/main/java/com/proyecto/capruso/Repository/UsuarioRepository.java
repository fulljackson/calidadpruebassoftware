package com.proyecto.capruso.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.proyecto.capruso.Entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Query(value = "SELECT * FROM Usuarios WHERE dni LIKE %?1% OR nombres LIKE %?1% OR apellidos LIKE %?1% OR celular LIKE %?1% OR perfil LIKE %?1%", nativeQuery = true)
    List<Usuario> buscartodo(String dato);

    @Query(value = "SELECT * FROM Usuarios ORDER BY dni ASC", nativeQuery = true)
    List<Usuario> OrdenarAscendente();

    @Query(value = "SELECT * FROM Usuarios ORDER BY dni DESC", nativeQuery = true)
    List<Usuario> OrdenarDescendente();

    Usuario findByUsernameAndPassword(String username, String password);
}