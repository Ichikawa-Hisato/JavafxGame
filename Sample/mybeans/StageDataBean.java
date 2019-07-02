package mybeans;
import java.util.*;
import java.io.*;
import java.sql.*;

public class StageDataBean implements Serializable{
    private ArrayList<String> colname;
    private ArrayList<ArrayList> data;

    public StageDataBean()
    {
        try{
            //接続の準備
            String url = "jdbc:derby:stagedb;create=true";
            String usr = "";
            String pw = "";

            //データベースへの接続
            Connection cn = DriverManager.getConnection(url, usr, pw);

            //問い合わせの準備
            DatabaseMetaData dm = cn.getMetaData();
            ResultSet tb = dm.getTables(null, null, "ステージ1", null);

            Statement st = cn.createStatement();

            String qry1 = "CREATE TABLE ステージ1(data1 int, data2 int, data3 int, data4 int, data5 int, data6 int, data7 int, data8 int, data9 int, data10 int, data11 int, data12 int, data13 int, data14 int, data15 int, data16 int, data17 int, data18 int, data19 int, data20 int)";
                                                  //data6 int, data7 int, data8 int, data9 int, data10 int,
                                                  //data11 int, data12 int, data13 int, data14 int, data15 int,
                                                  //data16 int, data17 int, data18 int, data19 int, data20 int)";
            String[] qry2 = {"INSERT INTO ステージ1 VALUES(1, 9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)",
                             "INSERT INTO ステージ1 VALUES(1, 0, 0, 1, 1, 8, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1)",
                             "INSERT INTO ステージ1 VALUES(1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1)",
                             "INSERT INTO ステージ1 VALUES(1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1)",
                             "INSERT INTO ステージ1 VALUES(1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1)",
                             "INSERT INTO ステージ1 VALUES(1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 2, 1)",
                             "INSERT INTO ステージ1 VALUES(1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)",
                             "INSERT INTO ステージ1 VALUES(1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)",
                             "INSERT INTO ステージ1 VALUES(1, 0, 0, 1, 0, 0, 0, 1, 1, 8, 1, 1, 1, 1, 0, 0, 0, 1, 0, 3)",
                             "INSERT INTO ステージ1 VALUES(1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1)",
                             "INSERT INTO ステージ1 VALUES(1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1)",
                             "INSERT INTO ステージ1 VALUES(1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1)",
                             "INSERT INTO ステージ1 VALUES(1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1)",
                             "INSERT INTO ステージ1 VALUES(1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1)",
                             "INSERT INTO ステージ1 VALUES(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)"};
            String qry3 = "SELECT * FROM ステージ1";


            if(!tb.next()){
                st.executeUpdate(qry1);
                for(int i=0; i<qry2.length; i++){
                    st.executeUpdate(qry2[i]);
                }
            }

            //問い合わせ
            ResultSet rs = st.executeQuery(qry3);

            //列数の取得
            ResultSetMetaData rm = rs.getMetaData();
            int cnum = rm.getColumnCount();
            colname = new ArrayList<String>(cnum);

            //列名の取得
            for(int i=1; i<=cnum; i++){
                colname.add(rm.getColumnName(i).toString());
            }

            //行の取得
            data = new ArrayList<ArrayList>(); 
            while(rs.next()){
                ArrayList<String> rowdata = new ArrayList<String>();
                for(int i=1; i<=cnum; i++){
                    rowdata.add(rs.getObject(i).toString());
                }
                data.add(rowdata);
            }

            //接続のクローズ
            rs.close();
            st.close();
            cn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList getData() 
    {
        return data;
    }
    public ArrayList getColname() 
    {
        return colname;
    }
}
