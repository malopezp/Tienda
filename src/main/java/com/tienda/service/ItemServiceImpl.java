package com.tienda.service;

import com.tienda.domain.Item;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public List<Item> getItems() {
        return listaItems;
    }

    @Override
    public Item getItem(Item item) {
        for (Item c : listaItems) {
            if (Objects.equals(c.getIdArticulo(), item.getIdArticulo())) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void save(Item item) {
        boolean existe = false;
        for (Item c : listaItems) {
            if (Objects.equals(c.getIdArticulo(), item.getIdArticulo())) {
                if (c.getCantidad() < item.getExistencias()) {
                    c.setCantidad(c.getCantidad() + 1);
                }
                existe = true;
                break;
            }
            if (!existe) {
                item.setCantidad(1);
                listaItems.add(item);
            }
        }
    }

    @Override
    public void delete(Item item) {
        var posicion = -1;
        var existe = false;
        for (Item c : listaItems) {
            ++posicion;
            if (Objects.equals(c.getIdArticulo(), item.getIdArticulo())) {
                existe = true;
                break;
            }
        }
        if (existe) {
            listaItems.remove(posicion);
        }
    }

    @Override
    public void actualiza(Item item) {
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdArticulo(), item.getIdArticulo())) {
                i.setCantidad(item.getCantidad());
                break;
            }
        }
    }

    @Override
    public void facturar() {
        for (Item i : listaItems) {
        }
        listaItems.clear();
    }

}
