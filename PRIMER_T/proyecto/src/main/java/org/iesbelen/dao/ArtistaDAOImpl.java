package org.iesbelen.dao;

import org.iesbelen.model.Artista;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArtistaDAOImpl extends AbstractDAOImpl implements ArtistaDAO {

    /**
     * Inserta en base de datos el nuevo artista, actualizando el id en el bean artista.
     */
    @Override
    public synchronized void create(Artista artista) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("INSERT INTO artista (nombre,nacionalidad,descripcion,anioInicio) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setString(idx++, artista.getNombre());
            ps.setString(idx++, artista.getNacionalidad());
            ps.setString(idx++, artista.getDescripcion());
            ps.setInt(idx, artista.getAnioInicio());

            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de artista con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                artista.setIdArtista(rsGenKeys.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    /**
     * Devuelve lista con todos los artistas.
     */
    @Override
    public List<Artista> getAll() {

        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Artista> listaArtista = new ArrayList<>();

        try {
            conn = connectDB();

            // Se utiliza un objeto Statement dado que no hay parámetros en la consulta.
            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM artista");
            while (rs.next()) {
                Artista artista = new Artista();
                int idx = 1;
                artista.setIdArtista(rs.getInt(idx++));
                artista.setNombre(rs.getString(idx++));
                artista.setNacionalidad(rs.getString(idx++));
                artista.setDescripcion(rs.getString(idx++));
                artista.setAnioInicio(rs.getInt(idx));
                listaArtista.add(artista);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listaArtista;

    }

    /**
     * Devuelve Optional de artista con el ID dado.
     */
    @Override
    public Optional<Artista> find(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT * FROM artista WHERE idArtista = ?");

            int idx =  1;
            ps.setInt(idx, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Artista artista = new Artista();
                idx = 1;
                artista.setIdArtista(rs.getInt(idx++));
                artista.setNombre(rs.getString(idx++));
                artista.setNacionalidad(rs.getString(idx++));
                artista.setDescripcion(rs.getString(idx++));
                artista.setAnioInicio(rs.getInt(idx));

                return Optional.of(artista);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return Optional.empty();

    }
    /**
     * Actualiza artista con campos del bean artista según ID del mismo.
     */
    @Override
    public void update(Artista artista) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("UPDATE artista SET nombre = ?, nacionalidad = ?, descripcion = ?, anioInicio = ? WHERE idArtista = ?");
            int idx = 1;
            ps.setString(idx++, artista.getNombre());
            ps.setString(idx++, artista.getNacionalidad());
            ps.setString(idx++, artista.getDescripcion());
            ps.setInt(idx++, artista.getAnioInicio());
            ps.setInt(idx, artista.getIdArtista());

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Update de artista con 0 registros actualizados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    /**
     * Borra artista con ID proporcionado.
     */
    @Override
    public void delete(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("DELETE FROM artista WHERE idArtista = ?");
            int idx = 1;
            ps.setInt(idx, id);

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Delete de artista con 0 registros eliminados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }


}
