package com.workintech.s18d1.util;

import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import org.springframework.http.HttpStatus;

public class BurgerValidation {

    public static void validateBurger(Burger burger) {
        if (burger == null) {
            throw new BurgerException("Burger boş olamaz!", HttpStatus.BAD_REQUEST);
        }
        if (burger.getName() == null || burger.getName().isEmpty()) {
            throw new BurgerException("Burger ismi boş olamaz!", HttpStatus.BAD_REQUEST);
        }

        // *** GÜNCELLEME BURADA YAPILMALIDIR ***
        // Null kontrolü, negatif kontrolünden önce yapılmalıdır.
        if (burger.getPrice() == null) {
            throw new BurgerException("Burger fiyatı boş olamaz!", HttpStatus.BAD_REQUEST);
        }

        // Null kontrolü yapıldığı için, artık getPrice().doubleValue() hatası alınmaz.
        if (burger.getPrice() < 0) {
            throw new BurgerException("Fiyat negatif olamaz!", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateId(Long id) {
        if (id == null || id < 1) {
            throw new BurgerException("Geçersiz ID!", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validatePrice(Double price) {
        if (price == null || price < 0) {
            throw new BurgerException("Geçersiz fiyat!", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateContent(String content) {
        if (content == null || content.isEmpty()) {
            throw new BurgerException("Content boş olamaz!", HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkBurgerExists(Burger burger, Long id) {
        if (burger == null) {
            throw new BurgerException("ID: " + id + " ile burger bulunamadı!", HttpStatus.NOT_FOUND);
        }
    }
}