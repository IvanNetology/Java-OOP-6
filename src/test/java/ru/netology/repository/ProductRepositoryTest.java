package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repo = new ProductRepository();
    private Product book1 = new Book(1, "Алгебра", 350, "Жилин Н.А");
    private Product book2 = new Book(2, "Химия", 500, "Непряев С.Л.");
    private Product smart1 = new Smartphone(3, "S21 Ultra", 80000, "Samsung");
    private Product smart2 = new Smartphone(4, "10X", 60000, "Apple");
    private Product book3 = new Book(2, "История", 350, "Капризов К.А");


    @Test
    public void saveOneBook() {
        repo.save(book1);

        Product[] expected = new Product[]{book1};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void saveOneSmart() {
        repo.save(smart1);

        Product[] expected = new Product[]{smart1};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void saveBookAndSmart() {
        repo.save(book1);
        repo.save(smart1);

        Product[] expected = new Product[]{book1, smart1};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void findZero() {
        repo.findAll();

        Product[] expected = new Product[]{};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void findOneBook() {
        repo.save(book1);

        repo.findAll();

        Product[] expected = new Product[]{book1};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void findOneSmart() {
        repo.save(smart1);

        repo.findAll();

        Product[] expected = new Product[]{smart1};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void findBookAndSmart() {
        repo.save(book1);
        repo.save(smart1);

        repo.findAll();

        Product[] expected = new Product[]{book1, smart1};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void removeBookById() {
        repo.save(book1);

        repo.removeById(1);

        Product[] expected = new Product[]{};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void removeSmartById() {
        repo.save(smart1);

        repo.removeById(3);

        Product[] expected = new Product[]{};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void removeBookAndSmartById() {
        repo.save(book1);
        repo.save(book2);
        repo.save(smart1);
        repo.save(smart2);

        repo.removeById(2);
        repo.removeById(3);

        Product[] expected = new Product[]{book1, smart2};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void removeNonId() {
        repo.save(book1);
        repo.save(book2);
        repo.save(smart1);
        repo.save(smart2);

        assertThrows(NotFoundException.class, () -> repo.removeById(7));
    }

    @Test
    public void saveSameId() {
        repo.save(book1);
        repo.save(book2);
        repo.save(smart2);

        assertThrows(AlreadyExistsException.class, () -> repo.save(book3));
    }
}