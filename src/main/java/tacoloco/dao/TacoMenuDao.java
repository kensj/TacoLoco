/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tacoloco.dao;
import org.springframework.data.repository.CrudRepository;

import tacoloco.entities.TacoMenu;

public interface TacoMenuDao extends CrudRepository<TacoMenu, Long> {
    TacoMenu findTacoMenuByName(String name);
}