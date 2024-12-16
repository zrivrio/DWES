package org.iesbelen.dao;

import org.iesbelen.model.DetallePedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetallePedidoDAOImpl extends AbstractDAOImpl implements DetallePedidoDAO {

    @Override
    public synchronized void create(DetallePedido detallePedido) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("INSERT INTO detallePedido (idProducto, idPedido, cantidad) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setInt(idx++, detallePedido.getIdProducto());
            ps.setInt(idx++,detallePedido.getIdPedido());
            ps.setInt(idx, detallePedido.getCantidad());

            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de detalles pedido con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                detallePedido.setIdPedido(rsGenKeys.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }
    @Override
    public List<DetallePedido> getAll() {

        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<DetallePedido> listaDetallePedido = new ArrayList<>();

        try {
            conn = connectDB();

            // Se utiliza un objeto Statement dado que no hay parámetros en la consulta.
            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM detallePedido");
            while (rs.next()) {
                DetallePedido detallePedido = new DetallePedido();
                int idx = 1;
                detallePedido.setIdDetalle(rs.getInt(idx++));
                detallePedido.setIdProducto(rs.getInt(idx++));
                detallePedido.setIdPedido(rs.getInt(idx++));
                detallePedido.setCantidad(rs.getInt(idx));
                listaDetallePedido.add(detallePedido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listaDetallePedido;

    }

    /**
     * Devuelve Optional de pedido con el ID dado.
     */
    @Override
    public Optional<DetallePedido> find(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT * FROM detallePedido WHERE idDetalle = ?");

            int idx =  1;
            ps.setInt(idx, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                DetallePedido detallepedido = new DetallePedido();
                idx = 1;
                detallepedido.setIdDetalle(rs.getInt(idx++));
                detallepedido.setIdProducto(rs.getInt(idx++));
                detallepedido.setIdPedido(rs.getInt(idx++));
                detallepedido.setCantidad(rs.getInt(idx));

                return Optional.of(detallepedido);
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
     * Actualiza detalle con campos del bean detalle según ID del mismo.
     */
    @Override
    public void update(DetallePedido detallePedido) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("UPDATE detallePedido SET idProducto = ?, idPedido = ?, cantidad = ? WHERE idDetalle = ?");
            int idx = 1;
            ps.setInt(idx++, detallePedido.getIdProducto());
            ps.setInt(idx++, detallePedido.getIdPedido());
            ps.setInt(idx++, detallePedido.getCantidad());
            ps.setInt(idx, detallePedido.getIdDetalle());

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Update de detalle con 0 registros actualizados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    /**
     * Borra detalle con ID proporcionado.
     */
    @Override
    public void delete(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("DELETE FROM detallePedido WHERE idDetalle = ?");
            int idx = 1;
            ps.setInt(idx, id);

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Delete de detalle con 0 registros eliminados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }
}
