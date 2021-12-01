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
import java.util.stream.Collectors;

public class DatosLaboratorios implements Runnable {

    private List<Laboratorio> laboratorios = new ArrayList<>();

    public DatosLaboratorios() {
    }

    public List<Laboratorio> getLaboratorios() {
        return laboratorios;
    }


    public void readData(){


        try {
            FileInputStream fis = new FileInputStream(new File("P:\\Usuarios\\Exequiel\\AppCotizaciones\\laboratorios.xlsx"));
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

    @Override
    public void run() {
        readData();
        laboratorios = laboratorios.stream().filter(p -> p.isActive()).collect(Collectors.toList());
    }
}
