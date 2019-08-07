/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author luiz.pereira
 */
public class ExcelController {
    final int LABEL = -1;
    JFileChooser selecArq;
    
    public void exportarExcel(boolean salvarArquivo, JTable tabela, File arq){
        Workbook wb;
        selecArq = new JFileChooser();
        
        if(salvarArquivo){
            selecArq.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
            selecArq.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));

            String nome_arquivo = "";
            if (selecArq.showDialog(null, "Exportar arquivo") == JFileChooser.APPROVE_OPTION) {
                if(selecArq.getFileFilter().getDescription().equals("Excel (*.xls)")){
                    nome_arquivo = (selecArq.getSelectedFile().getPath().concat(".xls")).trim();
                }else{
                    nome_arquivo = (selecArq.getSelectedFile().getPath().concat(".xlsx")).trim();
                }
            }
            arq = new File(nome_arquivo.trim());
        }

        int numLinha = tabela.getRowCount();
        int numColuna = tabela.getColumnCount();

        if (arq.getName().endsWith("xls")) {
            wb = new HSSFWorkbook();
        } else {
            wb = new XSSFWorkbook();
        }

        Sheet sh = wb.createSheet("Sistema LogTec");

        try {
            for (int i = LABEL; i < numLinha; i++) {
                Row linha = sh.createRow(i + 1);
                for (int j = 0; j < numColuna; j++) {

                    Cell celula = linha.createCell(j);
                    celula.setCellValue(String.valueOf("null"));

                    if (i == LABEL) {
                        celula.setCellValue(String.valueOf(tabela.getColumnName(j)));
                    } else {
                        celula.setCellValue(String.valueOf(String.valueOf(tabela.getValueAt(i, j))));
                    }
                    wb.write(new FileOutputStream(arq));
                }
            }
                  
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma falha ao criar o arquivo Excel.", "Falha ao criar arquivo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(salvarArquivo){
            JOptionPane.showMessageDialog(null, "Dados exportados com sucesso.", "Sucesso na exportação", JOptionPane.INFORMATION_MESSAGE);
        }
        System.gc();
    }
}
