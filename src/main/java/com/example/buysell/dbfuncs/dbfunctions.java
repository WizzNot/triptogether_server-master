package com.example.buysell.dbfuncs;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;


public class dbfunctions {
    public static void write(String tb, String[] values) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:around.db");
        Statement cur = conn.createStatement();
        try {
            String val = "";
            for (String x : values) {
                val = val + x + "','";
            }
            val = val.substring(0, val.length() - 2);
            System.out.println("INSERT INTO " + tb + " VALUES('" + val + ")");
            cur.execute("INSERT INTO " + tb + " VALUES('" + val + ")");
            conn.close();
        }
        catch(Exception e)
        {
            conn.close();
            cur.close();
        }
    }
    public static String[] read(String tb, String[] col) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:around.db");
        Statement cur = conn.createStatement();
        try {
            String cols = "";
            for (String x : col) {
                cols = cols + x + ", ";
            }
            cols = cols.substring(0, cols.length() - 2);
            String req = "SELECT " + cols + " FROM " + tb;
            System.out.println(req);
            ResultSet otv = cur.executeQuery(req);
            int columns = otv.getMetaData().getColumnCount();
            String[] ret = new String[columns];
            for (int i = 0; i < columns; i++) {
                ret[i] = "";
            }
            while (otv.next()) {
                for (int i = 1; i <= columns; i++) {
                    ret[i - 1] += otv.getString(i) + ";";
                }
            }
            for (int i = 0; i < columns; i++) {
                if (ret[i].length() != 0) {
                    ret[i] = ret[i].substring(0, ret[i].length() - 1);
                }
            }
            conn.close();
            cur.close();
            return ret;
        }
        catch(Exception e)
        {
            conn.close();
            cur.close();
            return new String[]{"error"};
        }
    }
    public static String[] read_comments(String tb) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:around.db");
        Statement cur = conn.createStatement();
        try {
            String req = "SELECT comment, LENGTH(comment) FROM " + tb;
            ResultSet otv = cur.executeQuery(req);
            String[] ret = new String[2];
            ret[0] = "";
            ret[1] = "0;";
            int k = 0;
            while (otv.next()) {
                ret[0] += otv.getString(1);
                int z = otv.getInt(2);
                ret[1] += Integer.toString(z + k) + ";";
                k += z;
            }
            ret[1] = ret[1].substring(0, ret[1].length() - 1);
            System.out.println(ret[1]);
            System.out.println(ret[0]);
            conn.close();
            cur.close();
            return ret;
        }
        catch(Exception e)
        {
            conn.close();
            cur.close();
            e.printStackTrace();
            return new String[]{"error"};
        }
    }
    public static void updatewhere(String tb, String[] cols, String[] vals, String usl) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:around.db");
        Statement cur = conn.createStatement();
        try {
            String req = "";
            for (int i = 0; i < cols.length; i++) {
                req = req + cols[i] + "=" + "'" + vals[i] + "',";
            }
            req = req.substring(0, req.length() - 1);
            System.out.println("UPDATE " + tb + " SET " + req + " WHERE " + usl);
            cur.execute("UPDATE " + tb + " SET " + req + " WHERE " + usl);
            System.out.println("UPDATE " + tb + " SET " + req + " WHERE " + usl);
//        conn.commit();
            conn.close();
        }
        catch(Exception e)
        {
            conn.close();
            cur.close();
        }
    }
    public static void deletewhere(String tb, String usl) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:around.db");
        Statement cur = conn.createStatement();
        try {
            String req = "DELETE FROM " + tb + " WHERE " + usl;
            cur.execute(req);
            conn.close();
            cur.close();
        }
        catch(Exception e)
        {
            conn.close();
            cur.close();
        }
    }
    public static String[] readwhere(String tb, String[] col, String usl) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:around.db");
        Statement cur = conn.createStatement();
        try{
        String cols = "";
        for (String x:col) {
            cols = cols + x + ", ";
        }
        cols = cols.substring(0, cols.length()-2);
        String req = "SELECT " + cols + " FROM " + tb + " WHERE " + usl;
        ResultSet otv = cur.executeQuery(req);
        int columns = otv.getMetaData().getColumnCount();
        String[] ret = new String[columns];
        for(int i=0; i<columns; i++)
        {
            ret[i] = "";
        }
        while(otv.next())
        {
            for(int i=1; i<=columns; i++)
            {
                ret[i - 1] += otv.getString(i) + ";";
            }
        }
        for(int i=0; i<columns; i++)
        {
            if(ret[i].length() != 0) {
                ret[i] = ret[i].substring(0, ret[i].length() - 1);
            }
        }
        conn.close();
        cur.close();
        return ret;}
        catch(Exception e)
        {
            conn.close();
            cur.close();
            return new String[]{"error"};
        }
    }
    public static BufferedImage base64ToBufferedImage(String base64) throws IOException {
        Base64 base = new Base64();
        byte[] image = base.decode(base64.replace("data:image/png;base64,", ""));
        InputStream stream = new ByteArrayInputStream(image);
        BufferedImage bufferedImage = ImageIO.read(stream);
        return bufferedImage;
    }
    public static void saveBufferedimage(BufferedImage bi, String path){
        try {
            File outputfile = new File(path);
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            System.out.println("не удалось");
        }
    }
    static public void saveBase64txt(String base64, String path) {
        OutputStream os = null;
        try {
            PrintWriter out = new PrintWriter(path + ".txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            os = new FileOutputStream(new File(path + ".txt"));
            os.write(base64.getBytes(), 0, base64.length());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
    public static Date addHoursToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }
}
