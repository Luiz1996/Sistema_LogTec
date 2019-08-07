package model;

/**
 *
 * @author luiz.pereira
 */
public class Consulta {
    int pk1;
    int pk2;
    int pk3;
    int pk4;
    String tableName;
    String operation;
    int transactionId;
    String dml;
    String userName;
    String clientAdress;
    String systime;
    String columnName;
    String oldValue;
    String newValue;

    public Consulta(int pk1, int pk2, int pk3, int pk4, String tableName, String operation, int transactionId, String dml, String userName, String clientAdress, String systime, String columnName, String oldValue, String newValue) {
        this.pk1 = pk1;
        this.pk2 = pk2;
        this.pk3 = pk3;
        this.pk4 = pk4;
        this.tableName = tableName;
        this.operation = operation;
        this.transactionId = transactionId;
        this.dml = dml;
        this.userName = userName;
        this.clientAdress = clientAdress;
        this.systime = systime;
        this.columnName = columnName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public Consulta(){
        
    }
    
    public int getPk1() {
        return pk1;
    }

    public void setPk1(int pk1) {
        this.pk1 = pk1;
    }

    public int getPk2() {
        return pk2;
    }

    public void setPk2(int pk2) {
        this.pk2 = pk2;
    }

    public int getPk3() {
        return pk3;
    }

    public void setPk3(int pk3) {
        this.pk3 = pk3;
    }

    public int getPk4() {
        return pk4;
    }

    public void setPk4(int pk4) {
        this.pk4 = pk4;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getDml() {
        return dml;
    }

    public void setDml(String dml) {
        this.dml = dml;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClientAdress() {
        return clientAdress;
    }

    public void setClientAdress(String clientAdress) {
        this.clientAdress = clientAdress;
    }

    public String getSystime() {
        return systime;
    }

    public void setSystime(String systime) {
        this.systime = systime;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }   
}
