package com.exepinero.service;

import com.exepinero.dto.ItemDRO;
import com.exepinero.model.Cotizacion;
import com.exepinero.model.Producto;
import com.exepinero.view.PanelLateral;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

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

        Cotizacion currentCotizacion = panelLateral.getCurrentCotizacion();

        // Safecheck nullpointer
        if(currentCotizacion == null) return;
        if(ubicacion == null) return;

        String nombreArchivo = currentCotizacion.getNombreCotizacion();
        String fullPath = ubicacion.concat("\\").concat(nombreArchivo).concat(".xlsx");
        List<Producto> productosCotizados = currentCotizacion.getProductosCotizados();

        int cantidadFilas = productosCotizados.size();

        try {
            File fileModelo = new File("P:\\Usuarios\\Exequiel\\AppCotizaciones\\modeloExportacion.xlsx");
            FileInputStream fis = new FileInputStream(fileModelo);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);

            XSSFCellStyle estiloTexto = wb.createCellStyle();
            XSSFCellStyle estiloNumero = wb.createCellStyle();

            XSSFFont fuente = wb.createFont();
            fuente.setFontName("Calibri");
            fuente.setBold(false);
            fuente.setFontHeightInPoints((short)9);
            estiloTexto.setFont(fuente);

            estiloNumero.setFont(fuente);


            //estilo1.setFillForegroundColor(IndexedColors.GREY_80_PERCENT.getIndex());

            //System.out.println(estilo1);
            //System.out.println(estilo2);

            //System.out.println(sheet);


            for(int i=0; i<cantidadFilas; i++){

                XSSFRow row = sheet.createRow(i+1);
                //row.setRowStyle(estilo1);
                row.createCell(0).setCellValue((productosCotizados.get(i).getNombreMonodrogaBuscada()));


                if(productosCotizados.get(i).isCompuesto()){
                    row.createCell(1).setCellValue("Si");
                } else{
                    row.createCell(1).setCellValue("No");
                }


                row.createCell(2).setCellValue(productosCotizados.get(i).getCodProdLowsedo());


                row.createCell(3).setCellValue(productosCotizados.get(i).getGTIN());


                row.createCell(4).setCellValue(productosCotizados.get(i).getNombreProducto()
                        .concat(" ")
                        .concat(productosCotizados.get(i).getNombrePresentacion()));

                row.createCell(5).setCellValue(productosCotizados.get(i).getNombreLab());

                row.createCell(6).setCellType(Cell.CELL_TYPE_NUMERIC);

                String precio = productosCotizados.get(i).getPrecio().replace(",",".");
                row.getCell(6).setCellValue(Double.parseDouble(precio));

                /**
                 * Aplicando estilos a las celdas de esta fila
                 */

                row.getCell(0).setCellStyle(estiloTexto);
                row.getCell(1).setCellStyle(estiloTexto);
                row.getCell(2).setCellStyle(estiloTexto);
                row.getCell(3).setCellStyle(estiloTexto);
                row.getCell(4).setCellStyle(estiloTexto);
                row.getCell(5).setCellStyle(estiloTexto);
                row.getCell(6).setCellStyle(estiloTexto);

            }





            OutputStream os = new FileOutputStream(new File(fullPath));
            wb.write(os);
            os.close();
            fis.close();

            JOptionPane.showMessageDialog(null,"    Exportación exitosa");



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
