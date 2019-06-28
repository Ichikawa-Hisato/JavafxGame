package mybeans;
import java.util.*;
import java.io.*;
import java.sql.*;

public class StageDataBean implements Serializable{
    private ArrayList<String> colname;
    private ArrayList<ArrayList> data;

    public CarDBBean()
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
            ResultSet tb = dm.getTables(null, null, "ステージ", null);

            Statement st = cn.createStatement();

            String qry1 = "CREATE TABLE ステージ(data1 int NOT NULL, data2 int NOT NULL, data3 int NOT NULL, data4 int NOT NULL, data5 int NOT NULL,
                                                data6 int NOT NULL, data7 int NOT NULL, data8 int NOT NULL, data9 int NOT NULL, data10 int NOT NULL,
                                                data11 int NOT NULL, data12 int NOT NULL, data13 int NOT NULL, data14 int NOT NULL, data15 int NOT NULL,
                                                data16 int NOT NULL, data17 int NOT NULL, data18 int NOT NULL, data19 int NOT NULL, data20 int NOT NULL,)";
            String[] qry2 = {"INSERT INTO ステージ VALUES (2, '乗用車')",
                             "INSERT INTO ステージ VALUES (3, 'オープンカー')",
                             "INSERT INTO ステージ VALUES (4, 'トラック')"};
            String qry3 = "SELECT * FROM ステージ";

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

}