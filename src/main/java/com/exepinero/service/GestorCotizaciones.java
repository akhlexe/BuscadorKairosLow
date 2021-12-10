package com.exepinero.service;

import com.exepinero.dto.ItemDRO;
import com.exepinero.model.Cotizacion;
import com.exepinero.model.Producto;
import com.exepinero.view.PanelLateral;

import javax.swing.*;
import java.util.List;

public class GestorCotizaciones {

    private Cotizacion currentCotizacion = null;
    private JFileChooser fc;
    private PanelLateral panelLateral;

    /**
     * Constructor
     */
    public GestorCotizaciones(PanelLateral panelLateral){
        fc = new JFileChooser();
        panelLateral = panelLateral;
    }

    /**
     * Exporta a excel current coti
     */

    public void exportarCotizacion(){
        // TODO exporta cotizacion en current cotizacion
        // Safecheck nullpointer
        if(currentCotizacion == null) return;
    }

    public void agregaMonodroga(ItemDRO monodroga,List<Producto> productos){
        // Safecheck nullpointer
        if(currentCotizacion == null) return;
        currentCotizacion.getMonodrogasCotizadas().add(monodroga);
        currentCotizacion.getProductosCotizados().addAll(productos);
        actualizaDisplayCotizacion();
    }

    public Cotizacion abrirCotizacion(String nombre){
        return null;
    }

    public Cotizacion getCurrentCotizacion() {
        return currentCotizacion;
    }

    public void setCurrentCotizacion(Cotizacion currentCotizacion) {
        this.currentCotizacion = currentCotizacion;
    }

    public List<Producto> muestraProductosCotizacion(){
        return currentCotizacion.getProductosCotizados();
    }


    public void actualizaDisplayCotizacion(){
        JTextArea itemsCotizacion = panelLateral.getItemsCotizacion();
        itemsCotizacion.setEnabled(true);
        itemsCotizacion.setText(" Monodrogas cotizadas");

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
}
