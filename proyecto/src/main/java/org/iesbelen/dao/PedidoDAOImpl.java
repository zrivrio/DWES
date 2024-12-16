package org.iesbelen.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.iesbelen.model.Pedido;

public class PedidoDAOImpl extends AbstractDAOImpl implements PedidoDAO {

    /**
     * Inserta en base de datos el nuevo pedido, actualizando el id en el bean pedido.
     */
    @Override
    public synchronized void create(Pedido pedido) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("INSERT INTO pedido (idUsuario, fechaPedido, estadoPedido) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setInt(idx++, pedido.getIdUsuario());
            ps.setDate(idx++,(Date) pedido.getFechaPedido());
            ps.setString(idx, pedido.getEstadoPedido());

            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de pedidos con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                pedido.setIdPedido(rsGenKeys.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    /**
     * Devuelve lista con todos los pedidos.
     */
    @Override
    public List<Pedido> getAll() {

        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Pedido> listaPedido = new ArrayList<>();

        try {
            conn = connectDB();

            // Se utiliza un objeto Statement dado que no hay parámetros en la consulta.
            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM pedido");
            while (rs.next()) {
                Pedido pedido = new Pedido();
                int idx = 1;
                pedido.setIdPedido(rs.getInt(idx++));
                pedido.setIdUsuario(rs.getInt(idx++));
                pedido.setFechaPedido(rs.getDate(idx++));
                pedido.setEstadoPedido(rs.getString(idx));
                listaPedido.add(pedido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listaPedido;

    }

    /**
     * Devuelve Optional de pedido con el ID dado.
     */
    @Override
    public Optional<Pedido> find(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT * FROM pedido WHERE idPedido = ?");

            int idx =  1;
            ps.setInt(idx, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Pedido pedido = new Pedido();
                idx = 1;
                pedido.setIdPedido(rs.getInt(idx++));
                pedido.setIdUsuario(rs.getInt(idx++));
                pedido.setFechaPedido(rs.getDate(idx++));
                pedido.setEstadoPedido(rs.getString(idx));

                return Optional.of(pedido);
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
     * Actualiza pedido con campos del bean pedido según ID del mismo.
     */
    @Override
    public void update(Pedido pedido) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("UPDATE pedido SET idUsuario = ?, fechaPedido = ?, estadoPedido = ? WHERE idPedido = ?");
            int idx = 1;
            ps.setInt(idx++, pedido.getIdUsuario());
            ps.setDate(idx++, (Date) pedido.getFechaPedido());
            ps.setString(idx++, pedido.getEstadoPedido());
            ps.setInt(idx, pedido.getIdPedido());

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Update de pedido con 0 registros actualizados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    /**
     * Borra pedido con ID proporcionado.
     */
    @Override
    public void delete(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("DELETE FROM pedido WHERE idPedido = ?");
            int idx = 1;
            ps.setInt(idx, id);

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Delete de pedido con 0 registros eliminados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }


}
