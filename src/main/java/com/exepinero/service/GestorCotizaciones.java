package com.exepinero.service;

import com.exepinero.dto.ItemDRO;
import com.exepinero.model.Cotizacion;
import com.exepinero.model.Producto;
import com.exepinero.view.PanelLateral;
import org.apache.poi.ss.format.CellFormatter;
import org.apache.poi.ss.format.CellGeneralFormatter;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GestorCotizaciones {

    private JFileChooser fc;
    private PanelLateral panelLateral;


    /**
     * Constructor
     */
    public GestorCotizaciones(PanelLateral panelLateral){
        fc = new JFileChooser();
        this.panelLateral = panelLateral;
    }

    /**
     * Exporta a excel current coti
     */

    public void exportarCotizacion(String ubicacion){
        // TODO exporta cotizacion en current cotizacion

        Cotizacion currentCotizacion = panelLateral.getCurrentCotizacion();

        // Safecheck nullpointer
        if(currentCotizacion == null) return;

        String nombreArchivo = currentCotizacion.getNombreCotizacion();
        String fullPath = ubicacion.concat("\\").concat(nombreArchivo).concat(".xlsx");

        List<Producto> productosCotizados = currentCotizacion.getProductosCotizados();
        int cantidadFilas = productosCotizados.size();

        try {
            File fileModelo = new File("P:\\Usuarios\\Exequiel\\AppCotizaciones\\modeloExportacion.xlsx");
            FileInputStream fis = new FileInputStream(fileModelo);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);

            XSSFCellStyle estilo1 = sheet.getRow(1).getRowStyle();
            XSSFCellStyle estilo2 = sheet.getRow(2).getRowStyle();

            System.out.println(estilo1);
            System.out.println(estilo2);

            System.out.println(sheet);


            for(int i=0; i<cantidadFilas; i++){
                System.out.println(i);
                XSSFRow row = sheet.createRow(1);
                row.setRowStyle(estilo1);
                System.out.println(row);
                row.getCell(i+1).setCellValue((productosCotizados.get(i).getCodProducto()));
            }

            OutputStream os = new FileOutputStream(new File(fullPath));
            wb.write(os);
            os.close();



        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void agregaMonodroga(ItemDRO monodroga,List<Producto> productos){

        Cotizacion currentCotizacion = panelLateral.getCurrentCotizacion();
        // Safecheck nullpointer
        if(currentCotizacion == null) return;
        if(nullPointerSafeChecker(monodroga,productos)) return;

        boolean checkMonodroga = isMonodrogaInCurrentCotizacion(monodroga);

        if(!checkMonodroga){
            currentCotizacion.getMonodrogasCotizadas().add(monodroga);
            currentCotizacion.getProductosCotizados().addAll(productos);
            actualizaDisplayCotizacion();
        }

    }

    public void remueveMonodroga(ItemDRO monodroga,List<Producto> productos){

        Cotizacion currentCotizacion = panelLateral.getCurrentCotizacion();

        // Safecheck nullpointer
        if(currentCotizacion == null) return;

        // BORRAR
        System.out.println(monodroga.isCompuesto());
        System.out.println(monodroga.getNombreMonodroga());
        System.out.println(currentCotizacion.getProductosCotizados());
        System.out.println(productos.size());
        System.out.println(productos);
        productos.get(0).getNombreMonodrogaBuscada();
        System.out.println("Cantidad de items en prod cotizados: "+currentCotizacion.getProductosCotizados().size());


        if(nullPointerSafeChecker(monodroga,productos)) return;

        boolean checkMonodroga = isMonodrogaInCurrentCotizacion(monodroga);

        if(checkMonodroga){
            List<ItemDRO> monodrogas = currentCotizacion.getMonodrogasCotizadas();
            List<Producto> productosCotizados = currentCotizacion.getProductosCotizados();

            monodrogas.removeIf(itemDRO -> itemDRO.isCompuesto() == monodroga.isCompuesto() && itemDRO.getNombreMonodroga() == monodroga.getNombreMonodroga());
            productosCotizados.removeIf(p -> p.isCompuesto() == monodroga.isCompuesto() && p.getNombreMonodrogaBuscada() == monodroga.getNombreMonodroga());

            currentCotizacion.setMonodrogasCotizadas(monodrogas);
            currentCotizacion.setProductosCotizados(productosCotizados);

            actualizaDisplayCotizacion();
        }


    }


    public void actualizaDisplayCotizacion(){
        JTextArea itemsCotizacion = panelLateral.getItemsCotizacion();
        Cotizacion currentCotizacion = panelLateral.getCurrentCotizacion();
        itemsCotizacion.setEnabled(true);
        itemsCotizacion.setText(" Monodrogas cotizadas\n");

        List<ItemDRO> monodrogasCotizadas = currentCotizacion.getMonodrogasCotizadas();

        for(int i=0; i<monodrogasCotizadas.size(); i++){

            ItemDRO itemDRO = monodrogasCotizadas.get(i);

            itemsCotizacion.append(" "+(i+1)+"- ");
            itemsCotizacion.append(itemDRO.getNombreMonodroga());
            if(itemDRO.isCompuesto()){
                itemsCotizacion.append(" - C");
            }
            itemsCotizacion.append("\n");
        }
        itemsCotizacion.setEnabled(false);
    }

    public boolean isMonodrogaInCurrentCotizacion(ItemDRO monodroga){
        Cotizacion currentCotizacion = panelLateral.getCurrentCotizacion();

        Optional<ItemDRO> match = currentCotizacion.getMonodrogasCotizadas().stream()
                .filter(itemDRO -> itemDRO.isCompuesto() == monodroga.isCompuesto())
                .filter(itemDRO -> itemDRO.getNombreMonodroga() == monodroga.getNombreMonodroga())
                .findFirst();

        return match.isPresent();
    }


    public boolean nullPointerSafeChecker(ItemDRO monodrogas, List<Producto> productos){
        if(monodrogas == null){
            return true;
        } else if(productos.isEmpty()){
            return true;
        }
        return false;
    }


    //TODO Funcionalidad de abrir cotizacion
    public Cotizacion abrirCotizacion(String nombre){
        return null;
    }

}
