import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class V1 {
    private JComboBox comboBox2Color;
    private JButton buscarButton;
    private JButton actualizarButton;
    private JButton agregarButton;
    private JTextField modelo;
    private JComboBox comboBox1Anio;
    private JTextField tipo;
    private JTextField propietario;
    private JTextField matricula;
    private JButton eliminarButton;
    private JPanel p1;
    PreparedStatement pd;
    public static Connection getConecction() {
        Connection cn = null;
        String base = "vehiculo"; //Sombre de la BD
        String url = "jdbc:mysql://localhost:3306/" + base; //Direccion, puerto y tipo BD
        String user = "root"; //Usuario
        String pass = "0986167219"; //Contraseña
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url,user,pass);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return cn;
    }
    public V1() {
        //Combo box
        Connection cn;
        try {
            cn = getConecction();
            Statement consultaAnio = cn.createStatement();
            ResultSet resultadoAnio = consultaAnio.executeQuery("Select * from anioD");
            while (resultadoAnio.next()) {
                comboBox1Anio.addItem(resultadoAnio.getString(1));
            }
            //Cargar colores
            Statement consultaReceta = cn.createStatement();
            ResultSet resultadoReceta = consultaReceta.executeQuery("Select * from colores");
            while (resultadoReceta.next()) {
                comboBox2Color.addItem(resultadoReceta.getString(1));
            }
            cn.close();
        } catch (HeadlessException | SQLException f) {
            System.err.println(f);
        }
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection cn;
                try {
                    cn = getConecction();
                    pd = cn.prepareStatement("Insert into datosVehiculo VALUES (?,?,?,?,?,?)");
                    pd.setString(1, matricula.getText());
                    pd.setString(2, tipo.getText());
                    pd.setString(3, propietario.getText());
                    pd.setString(4, modelo.getText());
                    pd.setString(5, comboBox1Anio.getSelectedItem().toString());
                    pd.setString(6, comboBox2Color.getSelectedItem().toString());
                    System.out.println(pd);
                    int res = pd.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null,"Coche guardado con exito");
                    } else {
                        JOptionPane.showMessageDialog(null,"Error");
                    }
                    cn.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection cx;
                try{
                    cx = getConecction();
                    String qr = "select * from datosVehiculo  where matricula = "+ matricula.getText()+";";
                    Statement s = cx.createStatement();
                    ResultSet rs = s.executeQuery(qr);
                    System.out.println(rs);
                    while(rs.next()) {
                        tipo.setText(rs.getString(2));
                        propietario.setText(rs.getString(3));
                        modelo.setText(rs.getString(4));
                        comboBox1Anio.setSelectedItem(rs.getString(5));
                        comboBox2Color.setSelectedItem(rs.getString(6));
                    }
                    cx.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection ct;
                try {
                    String qr = "Update datosVehiculo set tipo = ?, propietario = ?, modelo = ?, anio = ?, color = ? where matricula = "+ matricula.getText();
                    ct = getConecction();
                    pd = ct.prepareStatement(qr);
                    pd.setString(1, tipo.getText());
                    pd.setString(2, propietario.getText());
                    pd.setString(3, modelo.getText());
                    pd.setString(4, comboBox1Anio.getSelectedItem().toString());
                    pd.setString(5, comboBox2Color.getSelectedItem().toString());
                    pd.executeUpdate();
                    System.out.println(pd);
                    int res = pd.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null,"Coche actualizado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null,"Error");
                    }
                    ct.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con;
                String id = matricula.getText();
                try {
                    con = getConecction();
                    pd = con.prepareStatement("DELETE FROM datosVehiculo WHERE matricula="+id);
                    pd.executeUpdate();
                    int res = pd.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null,"Error");
                    } else {
                        JOptionPane.showMessageDialog(null,"Coche eliminado con exito");
                    }
                    con.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }
    public static void main(String[] args) {
        JFrame fr = new JFrame("V1");
        fr.setContentPane(new V1().p1);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.pack();
        fr.setVisible(true);
        fr.setResizable(false);
        fr.setLocationRelativeTo(null);
    }

}
