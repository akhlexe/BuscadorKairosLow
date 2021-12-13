package com.exepinero.service;

import com.exepinero.dto.ItemDRO;
import com.exepinero.model.Cotizacion;
import com.exepinero.model.Producto;
import com.exepinero.view.PanelLateral;

import javax.swing.*;
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

    public void exportarCotizacion(){
        // TODO exporta cotizacion en current cotizacion
        // Safecheck nullpointer
        if(panelLateral.getCurrentCotizacion() == null) return;
    }

    public void agregaMonodroga(ItemDRO monodroga,List<Producto> productos){

        Cotizacion currentCotizacion = panelLateral.getCurrentCotizacion();
        // Safecheck nullpointer
        if(currentCotizacion == null) return;
        boolean checkMonodroga = isMonodrogaInCurrentCotizacion(monodroga);

        if(!checkMonodroga){
            currentCotizacion.getMonodrogasCotizadas().add(monodroga);
            currentCotizacion.getProductosCotizados().addAll(productos);
            actualizaDisplayCotizacion();
        }

    }

    public Cotizacion abrirCotizacion(String nombre){
        return null;
    }


    public List<Producto> muestraProductosCotizacion(){
        return panelLateral.getCurrentCotizacion().getProductosCotizados();
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

//        for(ItemDRO item:currentCotizacion.getMonodrogasCotizadas()){
//            if(item.isCompuesto() == monodroga.isCompuesto() && item.getNombreMonodroga() == monodroga.getNombreMonodroga()){
//                presente = true;
//            }
//        }
        Optional<ItemDRO> match = currentCotizacion.getMonodrogasCotizadas().stream()
                .filter(itemDRO -> itemDRO.isCompuesto() == monodroga.isCompuesto())
                .filter(itemDRO -> itemDRO.getNombreMonodroga() == monodroga.getNombreMonodroga())
                .findFirst();

        return match.isPresent();
    }
}
