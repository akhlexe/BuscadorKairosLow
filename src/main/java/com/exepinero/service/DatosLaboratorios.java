package com.exepinero.service;

import com.exepinero.model.Laboratorio;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DatosLaboratorios {

    private List<Laboratorio> laboratorios = new ArrayList<>();

    public DatosLaboratorios() {

        readData();

    }

    public List<Laboratorio> getLaboratorios() {
        return laboratorios;
    }

    public void readData(){


        try {
            FileInputStream fis = new FileInputStream(new File("src/main/resources/excel/laboratorios.xlsx"));
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet hoja = wb.getSheetAt(0);
            int max_row = hoja.getPhysicalNumberOfRows();
            int max_col = 3;
            Iterator<Row> filas = hoja.iterator();

            // Salto los encabezados
            filas.next();

            while(filas.hasNext()){

                Row row = filas.next();
                String codigo = row.getCell(0).getStringCellValue();
                String laboratorio = row.getCell(1).getStringCellValue();
                String activo = row.getCell(2).getStringCellValue();

                Laboratorio labo = new Laboratorio();
                labo.setId(codigo);
                labo.setNombre(laboratorio);
                if("si".equalsIgnoreCase(activo)){
                    labo.setActive(true);
                }else if("no".equalsIgnoreCase(activo)){
                    labo.setActive(false);
                }else {
                    labo.setActive(false);
                }

                this.laboratorios.add(labo);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
//
//    public void saveData(){
//
//
//        Workbook wb = new XSSFWorkbook();
//        wb.createSheet("Laboratorios");
//        Sheet sheet = wb.getSheetAt(0);
//        for (int i=0; i<laboratorios.size(); i++){
//            Row row = sheet.createRow(i);
//
//            Cell celdaId = row.createCell(0,Cell.CELL_TYPE_STRING);
//            celdaId.setCellValue(laboratorios.get(i).getId());
//
//            Cell celdaLaboratorio = row.createCell(1,Cell.CELL_TYPE_STRING);
//            celdaLaboratorio.setCellValue(laboratorios.get(i).getNombre());
//
//            Cell celdaActivo = row.createCell(2,Cell.CELL_TYPE_STRING);
//            String activo = "";
//
//            if(laboratorios.get(i).isActive()){
//                activo = "SI";
//            } else {
//                activo = "NO";
//            }
//
//            celdaActivo.setCellValue(activo);
//        }
//
//
//        try {
//            wb.write(new FileOutputStream("test.xlsx",true));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
