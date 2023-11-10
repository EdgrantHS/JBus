package com.edgrantJBusRD.controller;

import com.edgrantJBusRD.Algorithm;
import com.edgrantJBusRD.dbjson.JsonTable;
import com.edgrantJBusRD.dbjson.Serializable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BasicGetController <T extends Serializable> {
    JsonTable<T> getJsonTable();

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public default List<T> getPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int pageSize
    ){
        return Algorithm.paginate(getJsonTable(), page, pageSize, e -> true);
    } // ini cek lagi nanti
    @GetMapping("/{id}")
    public default T getById(@PathVariable int id){
        return Algorithm.<T>find(getJsonTable(), e -> e.id == id);
    }
}
