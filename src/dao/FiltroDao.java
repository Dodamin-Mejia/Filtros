/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.sun.istack.internal.logging.Logger;
import conexion.Conexion;
import java.sql.SQLException;
import java.util.logging.Level;
import interfaces.metodos;
import modelo.Filtro;

/**
 *
 * @author LN710Q
 */
public class FiltroDao implements metodos<Filtro>{
    private static final String SQL_INSERT = "INSERT INTO filtros_aceite(codFiltro, marca, stock, existencia) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE filtros_aceite SET marca = ?, stock = ?, existencia = ? WHERE codFiltro = ?";
    private static final String SQL_DELETE = "DELETE FROM filtros_aceite WHERE codFiltro = ?";
    private static final String SQL_READ = "SELECT *FROM filtros_aceite WHERE codFiltro = ?";
    private static final String SQL_REDALL = "SELECT *FROM filtros_aceite";
    
    public static final Conexion con = Conexion.conectar();
    
    @Override
    public boolean create(Filtro g){
        preparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getCodigo());
            ps.setString(2, g.getMarca());
            ps.setInt(3, g.getStock);
            ps.setBoolean(4, true);
            if (ps.executeUpdate() > 0){
                return true;
            }            
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level1.SEVERE, null, ex);
        } finally{
            con.cerrarConexion();
        }
       return false;
    }
    
    @Override
    public boolean delete(Object key){
        preparedStatement ps;
        try{
            ps = con.getCNX().preparedStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            
            if(ps.executeUpdate() > 0){
                return true;
            }       
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).Log(Level.SEVERE, null, ex);    
    }finally{
            con.cerrarConexion();            
        }
        return false;
    }
    @Override
    public Filtro read(Object key){
        Filtro f = null;
        preparedStatement ps;
        ResultSet rs;
        
        try{
            ps = con.getCNX().preparedStatement(SQL_READ);
            PS.setString(1, key.toString());
            
            rs = ps.executeQwery();
            
            while(rs.next()){
                f = new Filtro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5));
            }
            rs.close();
        }catch (SQLException ex){
                System.out.println(ex.getMessage());
                Logger.getLogger(FiltroDao.class.getName().log(Level.SEVERE, null, ex));
        }finally{
            con.cerrarConexion();
        }
        return f;        
    }
    @Override
    public ArrayList<Filtro> readAll(){
        ArrayList<Filtro> all = new ArrayList();
        Statement s;
        RsultSet rs;
        try{
            s = con.getCnx().preapredStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);
            while(rs.next()){
                all.add(new Filtro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5)));
        }
            rs.close();
        }catch(SQLException ex){
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        return all;
    }
}