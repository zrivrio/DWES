package org.iesbelen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.iesbelen.model.Producto;

public class ProductoDAOImpl extends AbstractDAOImpl implements ProductoDAO {

    /**
     * Inserta en base de datos el nuevo producto, actualizando el id en el bean producto.
     */
    @Override
    public synchronized void create(Producto producto) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("INSERT INTO producto (nombre, precio, descripcion, idCategoria, idArtista) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setString(idx++, producto.getNombre());
            ps.setDouble(idx++, producto.getPrecio());
            ps.setString(idx++, producto.getDescripcion());
            ps.setInt(idx++, producto.getIdCategoria());
            ps.setInt(idx, producto.getIdArtista());

            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de productos con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                producto.setIdProducto(rsGenKeys.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    /**
     * Devuelve lista con todos los productos.
     */
    @Override
    public List<Producto> getAll() {

        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Producto> listProducto = new ArrayList<>();

        try {
            conn = connectDB();

            // Se utiliza un objeto Statement dado que no hay parámetros en la consulta.
            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM producto");
            while (rs.next()) {
                Producto producto = new Producto();
                int idx = 1;
                producto.setIdProducto(rs.getInt(idx++));
                producto.setNombre(rs.getString(idx++));
                producto.setPrecio(rs.getDouble(idx++));
                producto.setDescripcion(rs.getString(idx++));
                producto.setIdCategoria(rs.getInt(idx++));
                producto.setIdArtista(rs.getInt(idx));
                listProducto.add(producto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listProducto;

    }

    /**
     * Devuelve Optional de producto con el ID dado.
     */
    @Override
    public Optional<Producto> find(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT * FROM producto WHERE idProducto = ?");
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Producto producto = new Producto();
                int idx = 1;

                producto.setIdProducto(rs.getInt(idx++));  // String field
                producto.setNombre(rs.getString(idx++));  // String field
                producto.setPrecio(rs.getDouble(idx++));  // Double field
                producto.setDescripcion(rs.getString(idx++));  // String field
                producto.setIdCategoria(rs.getInt(idx++));  // Int field
                producto.setIdArtista(rs.getInt(idx));  // Int field

                return Optional.of(producto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Class not found: " + e.getMessage());
        } finally {
            closeDb(conn, ps, rs);
        }

        return Optional.empty();
    }
    /**
     * Actualiza producto con campos del bean producto según ID del mismo.
     */
    @Override
    public void update(Producto producto) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("UPDATE producto SET nombre = ?, precio = ?, descripcion = ?, idCategoria = ?, idArtista = ? WHERE idProducto = ?");
            int idx = 1;
            ps.setString(idx++, producto.getNombre());
            ps.setDouble(idx++, producto.getPrecio());
            ps.setString(idx++, producto.getDescripcion());
            ps.setInt(idx++, producto.getIdCategoria());
            ps.setInt(idx++, producto.getIdArtista());
            ps.setInt(idx, producto.getIdProducto());

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Update de producto con 0 registros actualizados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    /**
     * Borra Producto con ID proporcionado.
     */
    @Override
    public void delete(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("DELETE FROM  WHERE idProducto = ?");
            int idx = 1;
            ps.setInt(idx, id);

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Delete de productos con 0 registros eliminados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }


}
