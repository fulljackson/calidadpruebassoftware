package com.proyecto.capruso.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.proyecto.capruso.Entity.Salida;

@Repository
public interface SalidaRepository extends JpaRepository<Salida, String> {

    @Query(value = "SELECT * FROM Salidas WHERE codigo LIKE %?1% OR dniusuario LIKE %?1% OR codigoproducto LIKE %?1% OR cantidad LIKE %?1%", nativeQuery = true)
    List<Salida> buscartodo(String dato);

    @Query(value = "SELECT * FROM Salidas ORDER BY codigo ASC", nativeQuery = true)
    List<Salida> OrdenarAscendente();

    @Query(value = "SELECT * FROM Salidas ORDER BY codigo DESC", nativeQuery = true)
    List<Salida> OrdenarDescendente();
}
