package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.util.BurgerValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/burgers")
@CrossOrigin(origins = "*")
public class BurgerController {

    private final BurgerDao burgerDao;

    @Autowired
    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }

    @GetMapping
    public List<Burger> findAll() {
        log.info("Tüm burgerlar getiriliyor...");
        return burgerDao.findAll();
    }

    @GetMapping("/{id}")
    public Burger findById(@PathVariable Long id) {
        log.info("Burger getiriliyor, ID: {}", id);
        BurgerValidation.validateId(id);
        Burger burger = burgerDao.findById(id);
        BurgerValidation.checkBurgerExists(burger, id);
        return burger;
    }

    @PostMapping
    public Burger save(@RequestBody Burger burger) {
        log.info("Yeni burger kaydediliyor: {}", burger.getName());
        BurgerValidation.validateBurger(burger);
        return burgerDao.save(burger);
    }

    @PutMapping("/{id}")
    public Burger update(@PathVariable Long id, @RequestBody Burger burger) {
        log.info("Burger güncelleniyor, ID: {}", id);
        BurgerValidation.validateId(id);
        BurgerValidation.validateBurger(burger);
        Burger existingBurger = burgerDao.findById(id);
        BurgerValidation.checkBurgerExists(existingBurger, id);
        burger.setId(id);
        return burgerDao.update(burger);
    }

    @DeleteMapping("/{id}")
    public Burger remove(@PathVariable Long id) {
        log.info("Burger siliniyor, ID: {}", id);
        BurgerValidation.validateId(id);
        Burger burger = burgerDao.findById(id);
        BurgerValidation.checkBurgerExists(burger, id);
        return burgerDao.remove(id);
    }

    @GetMapping("/findByPrice")
    public List<Burger> findByPrice(@RequestParam Double price) {
        log.info("Fiyata göre aranıyor, price > {}", price);
        BurgerValidation.validatePrice(price);
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/findByBreadType")
    public List<Burger> findByBreadType(@RequestParam BreadType breadType) {
        log.info("Ekmek tipine göre aranıyor: {}", breadType);
        return burgerDao.findByBreadType(breadType);
    }

    @GetMapping("/findByContent")
    public List<Burger> findByContent(@RequestParam String content) {
        log.info("İçeriğe göre aranıyor: {}", content);
        BurgerValidation.validateContent(content);
        return burgerDao.findByContent(content);
    }
}
//## Endpoint Özeti
//| HTTP | URL | Açıklama |
//        |------|-----|----------|
//        | GET | `/workintech/burgers` | Tüm burgerlar |
//        | GET | `/workintech/burgers/5` | ID=5 olan burger |
//        | POST | `/workintech/burgers` | Yeni burger ekle |
//        | PUT | `/workintech/burgers/5` | ID=5 olan burger'ı güncelle |
//        | DELETE | `/workintech/burgers/5` | ID=5 olan burger'ı sil |
//        | GET | `/workintech/burgers/findByPrice?price=50` | Fiyatı 50'den büyük olanlar |
//        | GET | `/workintech/burgers/findByBreadType?breadType=WRAP` | WRAP tipi olanlar |
//        | GET | `/workintech/burgers/findByContent?content=cheese` | İçeriğinde cheese olanlar |