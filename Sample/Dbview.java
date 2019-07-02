import java.sql.*;

public class Dbview {
    public static void main(String[] args) {
        try {
            // 接続の準備
            String url = "jdbc:derby:stagedb;create=true";
            String usr = "";
            String pw = "";

            // データベースへの接続
            Connection cn = DriverManager.getConnection(url, usr, pw);

            // 問い合わせの準備
            DatabaseMetaData dm = cn.getMetaData();
            ResultSet tb = dm.getTables(null, null, "ステージ1", null);
            System.out.println(tb.next());
            System.out.println(tb);

            Statement st = cn.createStatement();

            // sqlの発行
            String qry3 = "SELECT * FROM ステージ1";

            // 問い合わせ
            ResultSet rs = st.executeQuery(qry3);

            // データの取得
            ResultSetMetaData rm = rs.getMetaData();
            int cnum = rm.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= cnum; i++) {
                    System.out.print(rm.getColumnName(i) + ":" + rs.getObject(i) + " ");
                }
                System.out.println("");
            }

            // 接続のクローズ
            rs.close();
            st.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}