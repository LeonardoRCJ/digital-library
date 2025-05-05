package tech.devleo.digital_library.entities.book;

import java.math.BigDecimal;

public record BookUpdateDTO(String title, String imageUrl, BigDecimal price) {
}
