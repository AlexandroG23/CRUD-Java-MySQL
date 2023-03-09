/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_crud_mysql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Alexandro
 */
public class CAlumnos {

    int codigo;
    String nombreAlumno;
    String apellidoAlumno;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getApellidoAlumno() {
        return apellidoAlumno;
    }

    public void setApellidoAlumno(String apellidoAlumno) {
        this.apellidoAlumno = apellidoAlumno;
    }

    public void insertarAlumnos(JTextField paramNombres, JTextField paramApellidos) {
        setNombreAlumno(paramNombres.getText());
        setApellidoAlumno(paramApellidos.getText());

        // Para insertar los datos necesito la conexion
        CConexion objConexion = new CConexion();

        String consulta = "INSERT INTO Alumnos (nombres, apellidos) VALUES (?,?);";

        try {
            // Enlazamos la conexion con la consulta
            CallableStatement cs = objConexion.estableceConexion().prepareCall(consulta);

            cs.setString(1, getNombreAlumno());
            cs.setString(2, getApellidoAlumno());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Se inserto correctamente el alumno");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se inserto correctamente al alumno, error " + e.toString());
        }

    }

    public void mostrarAlumnos(JTable paramTablaTotalAlumnos) {

        CConexion objConexion = new CConexion();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<>(modelo);
        paramTablaTotalAlumnos.setRowSorter(OrdenarTabla);

        // Aqui estara mi consulta
        String sql = "";

        modelo.addColumn("id");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");

        paramTablaTotalAlumnos.setModel(modelo);

        sql = "SELECT * FROM Alumnos";

        String[] datos = new String[3];
        Statement st;

        try {
            st = objConexion.estableceConexion().createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);

                modelo.addRow(datos);
            }

            paramTablaTotalAlumnos.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros!, error: " + e.toString());
        }

    }

    public void selecionarAlumno(JTable paramTablaAlumnos, JTextField paramCodigo, JTextField paramNombres, JTextField paramApellidos) {
        try {
            // Recorremos el contador en este caso la fila
            int fila = paramTablaAlumnos.getSelectedRow();

            if (fila >= 0) {
                paramCodigo.setText((String) paramTablaAlumnos.getValueAt(fila, 0));
                paramNombres.setText((String) paramTablaAlumnos.getValueAt(fila, 1));
                paramApellidos.setText((String) paramTablaAlumnos.getValueAt(fila, 2));
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede seleccionar al alumno, error: " + e.toString());
        }
    }

    public void modificarAlumnos(JTextField paramCodigo, JTextField paramNombres, JTextField paramApellidos) {
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNombreAlumno(paramNombres.getText());
        setApellidoAlumno(paramApellidos.getText());

        CConexion objConexion = new CConexion();

        String consulta = "UPDATE Alumnos SET alumnos.nombres = ?, alumnos.apellidos = ? WHERE alumnos.id = ?;";

        try {
            CallableStatement cs = objConexion.estableceConexion().prepareCall(consulta);

            cs.setString(1, getNombreAlumno());
            cs.setString(2, getApellidoAlumno());
            cs.setInt(3, getCodigo());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Modificación exitosa");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se modificó, error: " + e.toString());
        }
    }

    public void eliminarAlumnos(JTextField paramCodigo) {
        setCodigo(Integer.parseInt(paramCodigo.getText()));

        CConexion objConexion = new CConexion();
        String consulta = "DELETE FROM Alumnos WHERE alumnos.id=?;";

        try {
            CallableStatement cs = objConexion.estableceConexion().prepareCall(consulta);
            cs.setInt(1, getCodigo());
            cs.execute();

            JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente el alumno");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se ha eliminado al alumno, error: "+e.toString());

        }

    }

}
