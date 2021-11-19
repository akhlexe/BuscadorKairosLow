package com.exepinero.service;

import com.exepinero.model.Laboratorio;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class DatosLaboratorios {

    private List<Laboratorio> laboratorios;

    public DatosLaboratorios() {
        CargaDatosLaboratorios loader = new CargaDatosLaboratorios();
        laboratorios = loader.loadTxtLaboratorios();
        saveData();
    }

    public void saveData(){

        Workbook wb = new XSSFWorkbook();
        wb.createSheet("Laboratorios");
        Sheet sheet = wb.getSheetAt(0);
        for (int i=0; i<laboratorios.size(); i++){
            Row row = sheet.createRow(i);

            Cell celdaId = row.createCell(0,Cell.CELL_TYPE_STRING);
            celdaId.setCellValue(laboratorios.get(i).getId());

            Cell celdaLaboratorio = row.createCell(1,Cell.CELL_TYPE_STRING);
            celdaLaboratorio.setCellValue(laboratorios.get(i).getNombre());

            Cell celdaActivo = row.createCell(2,Cell.CELL_TYPE_STRING);
            String activo = "";

            if(laboratorios.get(i).isActive()){
                activo = "SI";
            } else {
                activo = "NO";
            }

            celdaActivo.setCellValue(activo);
        }


        try {
            wb.write(new FileOutputStream("test.xlsx",true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
