package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repo = new ProductRepository();
    private ProductManager manager = new ProductManager(repo);
    private Product book1 = new Book(1, "Алгебра", 350, "Жилин Н.А");
    private Product book2 = new Book(2, "Химия", 500, "Непряев С.Л.");
    private Product smart1 = new Smartphone(3, "S21 Ultra", 80000, "Samsung");
    private Product smart2 = new Smartphone(4, "10X", 60000, "Apple");
    private Product book3 = new Book(5, "Алгебра", 450, "Суслов Р.В");

    @Test
    public void addBook() {
        manager.add(book1);

        Product[] expected = {book1};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void addSmart() {
        manager.add(smart1);

        Product[] expected = {smart1};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void addBookAndSmart() {
        manager.add(book1);
        manager.add(smart1);

        Product[] expected = {book1, smart1};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNoMatch() {
        manager.add(smart2);
        manager.add(book1);
        manager.add(smart1);
        manager.add(book2);

        String name = "Физика";

        Product[] expected = {};
        Product[] actual = manager.searchBy(name);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldOneMatch() {
        manager.add(smart2);
        manager.add(book1);
        manager.add(smart1);
        manager.add(book2);

        String name = "Алгебра";

        Product[] expected = {book1};
        Product[] actual = manager.searchBy(name);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldTwoMatch() {
        manager.add(smart2);
        manager.add(book1);
        manager.add(smart1);
        manager.add(book2);
        manager.add(book3);

        String name = "Алгебра";

        Product[] expected = {book1, book3};
        Product[] actual = manager.searchBy(name);
        assertArrayEquals(expected, actual);
    }
}