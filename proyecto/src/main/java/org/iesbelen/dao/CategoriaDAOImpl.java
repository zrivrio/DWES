package org.iesbelen.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.iesbelen.model.Categoria;

public class CategoriaDAOImpl extends AbstractDAOImpl implements CategoriaDAO {

    /**
     * Inserta en base de datos el nuevo categoria, actualizando el id en el bean categoria.
     */
    @Override
    public synchronized void create(Categoria categoria) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("INSERT INTO categoria (nombre) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setString(idx++, categoria.getNombre());

            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de categoria con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                categoria.setIdCategoria(rsGenKeys.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    /**
     * Devuelve lista con todos los categorias.
     */
    @Override
    public List<Categoria> getAll() {

        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Categoria> listaCategoria = new ArrayList<>();

        try {
            conn = connectDB();

            // Se utiliza un objeto Statement dado que no hay parámetros en la consulta.
            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM categoria");
            while (rs.next()) {
                Categoria categoria = new Categoria();
                int idx = 1;
                categoria.setIdCategoria(rs.getInt(idx++));
                categoria.setNombre(rs.getString(idx));
                listaCategoria.add(categoria);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listaCategoria;

    }

    /**
     * Devuelve Optional de categoria con el ID dado.
     */
    @Override
    public Optional<Categoria> find(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT * FROM categoria WHERE idCategoria = ?");

            int idx =  1;
            ps.setInt(idx, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Categoria categoria = new Categoria();
                idx = 1;
                categoria.setIdCategoria(rs.getInt(idx++));
                categoria.setNombre(rs.getString(idx));

                return Optional.of(categoria);
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
     * Actualiza categoria con campos del bean categoria según ID del mismo.
     */
    @Override
    public void update(Categoria categoria) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("UPDATE categoria SET nombre = ? WHERE idCategoria = ?");
            int idx = 1;
            ps.setString(idx++, categoria.getNombre());
            ps.setInt(idx, categoria.getIdCategoria());

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Update de categoria con 0 registros actualizados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    /**
     * Borra categoria con ID proporcionado.
     */
    @Override
    public void delete(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("DELETE FROM categoria WHERE idCategoria = ?");
            int idx = 1;
            ps.setInt(idx, id);

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Delete de categoria con 0 registros eliminados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }


}
