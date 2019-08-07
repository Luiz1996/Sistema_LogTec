package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author luiz.pereira
 */
public class ConsultaTableModel extends AbstractTableModel {

    private List<Consulta> dados = new ArrayList<Consulta>();
    private String[] colunas = {"PK1", "PK2", "PK3", "PK4", "TABLE", "OP", "TRANS_ID", "DML", "USER", "IP", "SYSTIME", "COLUMN", "OLD_VALUE", "NEW_VALUE"};

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {

        switch (coluna) {
            case 0:
                return dados.get(linha).getPk1();
            case 1:
                return dados.get(linha).getPk2();
            case 2:
                return dados.get(linha).getPk3();
            case 3:
                return dados.get(linha).getPk4();
            case 4:
                return dados.get(linha).getTableName();
            case 5:
                return dados.get(linha).getOperation();
            case 6:
                return dados.get(linha).getTransactionId();
            case 7:
                return dados.get(linha).getDml();
            case 8:
                return dados.get(linha).getUserName();
            case 9:
                return dados.get(linha).getClientAdress();
            case 10:
                return dados.get(linha).getSystime();
            case 11:
                return dados.get(linha).getColumnName();
            case 12:
                return dados.get(linha).getOldValue();
            case 13:
                return dados.get(linha).getNewValue();
        }

        return null;
    }
    
    public void addRow(Consulta cons){
        this.dados.add(cons);
        this.fireTableDataChanged();        
    }
    
    public void removeRow(int linha){ 
        this.dados.removeAll(dados);        
    }
}
