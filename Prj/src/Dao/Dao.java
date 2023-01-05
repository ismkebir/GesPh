/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import java.util.List;

/**
 *
 * @author X
 */
public interface Dao <Table>{
    Table get(int id);
    List<Table> getAll();

    void save(Table t);

    void update(Table t,int id);

    void delete(int id);
    
}
