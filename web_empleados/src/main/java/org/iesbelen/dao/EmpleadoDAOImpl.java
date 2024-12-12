package org.iesbelen.dao;

import org.iesbelen.model.Departamento;
import org.iesbelen.model.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmpleadoDAOImpl extends AbstractDAOImpl implements EmpleadoDAO {


    @Override
    public void create(Empleado empleado) {

    }

    @Override
    public List<Empleado> getAll() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Empleado> listEmpleado = new ArrayList<>();

        try {
            conn = connectDB();
            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM empleado");
            while (rs.next()) {
                Empleado empleado = new Empleado();
                int idx = 1;
                empleado.setCodigo(rs.getInt(idx++));
                empleado.setNif(rs.getString(idx++));
                empleado.setNombre(rs.getString(idx++));
                empleado.setApellido1(rs.getString(idx++));
                empleado.setApellido2(rs.getString(idx++));
                empleado.setCodigoDepartamento(rs.getInt(idx++));
                listEmpleado.add(empleado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listEmpleado;
    }

    @Override
    public Optional<Empleado> find(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT * FROM empleado WHERE codigo = ?");

            int idx =  1;
            ps.setInt(idx, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                idx = 1;
                Empleado empleado = new Empleado();
                empleado.setCodigo(rs.getInt(idx++));
                empleado.setNif(rs.getString(idx++));
                empleado.setNombre(rs.getString(idx++));
                empleado.setApellido1(rs.getString(idx++));
                empleado.setApellido2(rs.getString(idx++));
                empleado.setCodigoDepartamento(rs.getInt(idx++));

                return Optional.of(empleado);
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

    @Override
    public void update(Empleado empleado) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("UPDATE empleado SET nombre = ? , nif = ?, apellido1 = ?, apellido2 = ?, codigo_departamento = ?  WHERE codigo = ?");
            int idx = 1;
            ps.setString(idx++, empleado.getNombre());
            ps.setString(idx++, empleado.getNif());
            ps.setString(idx++, empleado.getApellido1());
            ps.setString(idx++, empleado.getApellido1());
            ps.setInt(idx++, empleado.getCodigoDepartamento());
            ps.setInt(idx, empleado.getCodigo());

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Update de usuario con 0 registros actualizados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public void delete(int id) {

    }
}
