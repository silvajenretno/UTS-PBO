package Entitas;
import java.sql.Date;
import Database.database;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import java.text.ParseException;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
/**
 *
 * @author Silva_Jen_Retno
 */
public class jadwalmemasak extends database {
    private int Id_Jadwal;
    private String Nama_Makanan;
    private String Jenis_Makanan;
    private String Hari_Memasak;
    private Date Tanggal;
    private int Budget;
    private String PENGGUNA_ID_User;
    
    private DefaultTableCellRenderer cellRenderer;

    public jadwalmemasak(int Id_Jadwal,String Nama_Makanan, String Jenis_Makanan, String Hari_Memasak, Date Tanggal, int Budget, String PENGGUNA_ID_User) {
        this.Id_Jadwal = Id_Jadwal;
        this.Nama_Makanan = Nama_Makanan;
        this.Jenis_Makanan = Jenis_Makanan;
        this.Hari_Memasak = Hari_Memasak;
        this.Tanggal = Tanggal;
        this.Budget = Budget;
        this.PENGGUNA_ID_User = PENGGUNA_ID_User;
    }
    
    public jadwalmemasak(String Nama_Makanan, String Jenis_Makanan, String Hari_Memasak, Date Tanggal, int Budget, String PENGGUNA_ID_User) {
        this(-1, Nama_Makanan, Jenis_Makanan, Hari_Memasak, Tanggal, Budget, PENGGUNA_ID_User);
    }
    
    
    private boolean idjadwalExists() {
        try {
            this.openConnection();
            String checkQuery = "SELECT COUNT(*) FROM jadwal_memasak WHERE Id_Jadwal = ?";
            this.preparedStatement = this.connection.prepareStatement(checkQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, Id_Jadwal);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; 
            }
        } catch (SQLException ex) {
            displayErrors(ex);
        }
        return false; 
    }
    

    
    public boolean tambahjadwalmemasak() {
        boolean isOperationSuccess = false;
        try {
            
            if (idjadwalExists()) {
            JOptionPane.showMessageDialog(null, "Data jadwal sudah ada.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return false;
            }
            
            this.openConnection();
            String query = "INSERT INTO jadwal_memasak (Nama_Makanan, Jenis_Makanan, Hari_Memasak, Tanggal, Budget, PENGGUNA_ID_User) VALUES (?, ?, ?, ?, ?, ?)";
            this.preparedStatement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, this.Nama_Makanan);
            preparedStatement.setString(2, this.Jenis_Makanan);
            preparedStatement.setString(3, this.Hari_Memasak);
            preparedStatement.setDate(4, this.Tanggal);
            preparedStatement.setInt(5, this.Budget);
            preparedStatement.setString(6, this.PENGGUNA_ID_User);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            return false;
        }
    }
    
    
    public boolean ubahjadwalmemasak() {
        try {
            openConnection();
            String query = "UPDATE `jadwal_memasak` SET `Nama_Makanan`=?,`Jenis_Makanan`=?,`Hari_Memasak`=?,"
                    + "`Tanggal`=?,`Budget`=?,`PENGGUNA_ID_User`=? WHERE Id_Jadwal = ?";
            this.preparedStatement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
 
            preparedStatement.setString(1, this.Nama_Makanan);
            preparedStatement.setString(2, this.Jenis_Makanan);
            preparedStatement.setString(3, this.Hari_Memasak);
            preparedStatement.setDate(4, this.Tanggal);
            preparedStatement.setInt(5, this.Budget);
            preparedStatement.setString(6, this.PENGGUNA_ID_User);
            preparedStatement.setInt(7, Id_Jadwal);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                // Notify that ID was not found
                JOptionPane.showMessageDialog(null, "ID Jadwal tidak ditemukan.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return false;
            } else {
                return true;  // Return true for successful update
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            return false;
        }
    }
    
    public boolean delete() {
        boolean isOperationSuccess = false;

        try {
            this.openConnection();

            String sql = "DELETE FROM jadwal_memasak WHERE Id_Jadwal = ?";
            this.preparedStatement = this.connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, this.Id_Jadwal);

            int result = this.preparedStatement.executeUpdate();

            isOperationSuccess = result > 0;
        } catch (SQLException ex) {
            this.displayErrors(ex);
        }
        return isOperationSuccess;
    }
    

    public int getId_Jadwal() {
        return Id_Jadwal;
    }

    public void setId_Jadwal(int Id_Jadwal) {
        this.Id_Jadwal = Id_Jadwal;
    }

    public String getNama_Makanan() {
        return Nama_Makanan;
    }

    public void setNama_Makanan(String Nama_Makanan) {
        this.Nama_Makanan = Nama_Makanan;
    }

    public String getJenis_Makanan() {
        return Jenis_Makanan;
    }

    public void setJenis_Makanan(String Jenis_Makanan) {
        this.Jenis_Makanan = Jenis_Makanan;
    }

    public String getHari_Memasak() {
        return Hari_Memasak;
    }

    public void setHari_Memasak(String Hari_Memasak) {
        this.Hari_Memasak = Hari_Memasak;
    }

    public Date getTanggal() {
        return Tanggal;
    }

    public void setTanggal(Date Tanggal) {
        this.Tanggal = Tanggal;
    }

    public int getBudget() {
        return Budget;
    }

    public void setBudget(int Budget) {
        this.Budget = Budget;
    }

    public String getPENGGUNA_ID_User() {
        return PENGGUNA_ID_User;
    }

    public void setPENGGUNA_ID_User(String PENGGUNA_ID_User) {
        this.PENGGUNA_ID_User = PENGGUNA_ID_User;
    }
    
    
}
