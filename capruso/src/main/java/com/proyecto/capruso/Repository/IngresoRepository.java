package com.proyecto.capruso.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.proyecto.capruso.Entity.Ingreso;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, String> {

    @Query(value = "SELECT * FROM Ingresos WHERE codigo LIKE %?1% OR dniusuario LIKE %?1% OR codigoproducto LIKE %?1% OR cantidad LIKE %?1%", nativeQuery = true)
    List<Ingreso> buscartodo(String dato);

    @Query(value = "SELECT * FROM Ingresos ORDER BY codigo ASC", nativeQuery = true)
    List<Ingreso> OrdenarAscendente();

    @Query(value = "SELECT * FROM Ingresos ORDER BY codigo DESC", nativeQuery = true)
    List<Ingreso> OrdenarDescendente();
}
