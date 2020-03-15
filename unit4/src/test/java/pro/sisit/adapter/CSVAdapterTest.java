package pro.sisit.adapter;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pro.sisit.adapter.impl.CSVAdapter;
import pro.sisit.adapter.impl.CSVAdapterWrapper;
import pro.sisit.model.Animal;
import pro.sisit.model.AnimalOwner;
import pro.sisit.model.Author;
import pro.sisit.model.Book;


/* Проверяетcя:
Создание csv-файла и заполнение двумя объектами
Удаление csv-файла
Чтение строк в csv-файле по индексу
Добавление строки в csv-файле и соответствие её поданной на запись строки сущности
 */

public class CSVAdapterTest {

    @Before
    public void createFile() throws IOException {

        Book firstBook = new Book("Убик", "Филип Дик", "Научная фантастика", "978-5-699-97309-5");
        Book secondBook = new Book("Глуховский", "Будущее", "Научная фантастика ", "978-5-17-118366-0");

        Author firstAuthor = new Author("Альбер Камю", "Алжир");
        Author secondAuthor = new Author("Габриэль Гарсиа Маркес", "Колумбия");

        Animal firstAnimal = new Animal("Кит", "Кот", "Украина", "12 - 16 часов");
        Animal secondAnimal = new Animal("Дог", "Собака", "Англия", "12 - 14 часов");

        AnimalOwner firstAnimalOwner = new AnimalOwner("Никита", "Кит", "8-901-765-34-21");
        AnimalOwner secondAnimalOwner = new AnimalOwner("Василий", "Дог", "8-954-976-12-67");

        Path bookFilePath = Paths.get("bookFile.csv");
        File bookFile = bookFilePath.toFile();
        bookFile.createNewFile();

        try (
                BufferedReader bookReader = new BufferedReader(new FileReader(bookFile));
                BufferedWriter bookWriter = new BufferedWriter(new FileWriter(bookFile, true))) {

            CSVAdapterWrapper<Book> bookCSVAdapterWrapper = new CSVAdapterWrapper<>(
                    new CSVAdapter<>(Book.class, bookReader, bookWriter));

            bookCSVAdapterWrapper.append(firstBook);
            bookCSVAdapterWrapper.append(secondBook);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path authorFilePath = Paths.get("authorFile.csv");
        File authorFile = authorFilePath.toFile();
        authorFile.createNewFile();

        try (
                BufferedReader authorReader = new BufferedReader(new FileReader(authorFile));
                BufferedWriter authorWriter = new BufferedWriter(new FileWriter(authorFile, true))) {

            CSVAdapterWrapper<Author> authorCSVAdapterWrapper = new CSVAdapterWrapper<>(
                    new CSVAdapter<>(Author.class, authorReader, authorWriter));

            authorCSVAdapterWrapper.append(firstAuthor);
            authorCSVAdapterWrapper.append(secondAuthor);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Path animalFilePath = Paths.get("animalFile.csv");
        File animalFile = animalFilePath.toFile();
        animalFile.createNewFile();

        try (
                BufferedReader animalReader = new BufferedReader(new FileReader(animalFile));
                BufferedWriter animalWriter = new BufferedWriter(new FileWriter(animalFile, true))) {

            CSVAdapterWrapper<Animal> animalCSVAdapterWrapper = new CSVAdapterWrapper<>(
                    new CSVAdapter<>(Animal.class, animalReader, animalWriter));

            animalCSVAdapterWrapper.append(firstAnimal);
            animalCSVAdapterWrapper.append(secondAnimal);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path animalOwnerFilePath = Paths.get("animalOwnerFile.csv");
        File animalOwnerFile = animalOwnerFilePath.toFile();
        animalOwnerFile.createNewFile();

        try (
                BufferedReader animalOwnerReader = new BufferedReader(new FileReader(animalOwnerFile));
                BufferedWriter animalOwnerWriter = new BufferedWriter(new FileWriter(animalOwnerFile, true))) {

            CSVAdapterWrapper<AnimalOwner> animalOwnerCSVAdapterWrapper = new CSVAdapterWrapper<>(
                    new CSVAdapter<>(AnimalOwner.class, animalOwnerReader, animalOwnerWriter));

            animalOwnerCSVAdapterWrapper.append(firstAnimalOwner);
            animalOwnerCSVAdapterWrapper.append(secondAnimalOwner);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @After
    public void deleteFile() {
        Path bookFilePath = Paths.get("bookFile.csv");
        Boolean isBookFileDeleted = bookFilePath.toFile().delete();

        assertEquals(true, isBookFileDeleted);

        Path authorFilePath = Paths.get("authorFile.csv");
        Boolean isAuthorFileDeleted = authorFilePath.toFile().delete();

        assertEquals(true, isAuthorFileDeleted);

        Path animalFilePath = Paths.get("animalFile.csv");
        Boolean isAnimalFileDeleted = animalFilePath.toFile().delete();

        assertEquals(true, isAnimalFileDeleted);

        Path animalOwnerFilePath = Paths.get("animalOwnerFile.csv");
        Boolean isAnimalOwnerFileDeleted = animalOwnerFilePath.toFile().delete();

        assertEquals(true, isAnimalOwnerFileDeleted);
    }

    @Test
    public void testReadBook() throws IOException {

        Path bookFilePath = Paths.get("bookFile.csv");
        File bookFile = bookFilePath.toFile();

        BufferedReader bookReader = new BufferedReader(new FileReader(bookFile));
        BufferedWriter bookWriter = new BufferedWriter(new FileWriter(bookFile, true));

        CSVAdapterWrapper<Book> bookCSVAdapterWrapper = new CSVAdapterWrapper<>(
                new CSVAdapter<>(Book.class, bookReader, bookWriter));

        Book expectedBook1 = new Book("Убик", "Филип Дик", "Научная фантастика", "978-5-699-97309-5");
        Book actualBook1 = bookCSVAdapterWrapper.read(0);
        assertEquals(expectedBook1, actualBook1);

        Book expectedBook2 = new Book("Глуховский", "Будущее", "Научная фантастика", "978-5-17-118366-0");
        Book actualBook2 = bookCSVAdapterWrapper.read(1);
        assertEquals(actualBook2, expectedBook2);
    }


    @Test
    public void testReadAuthor() throws IOException {

        Path authorFilePath = Paths.get("authorFile.csv");
        File authorFile = authorFilePath.toFile();

        BufferedReader authorReader = new BufferedReader(new FileReader(authorFile));
        BufferedWriter authorWriter = new BufferedWriter(new FileWriter(authorFile, true));

        CSVAdapterWrapper<Author> authorCSVAdapterWrapper = new CSVAdapterWrapper<>(
                new CSVAdapter<>(Author.class, authorReader, authorWriter));


        Author expectedAuthor1 = new Author("Альбер Камю", "Алжир");
        Author actualAuthor1 = authorCSVAdapterWrapper.read(0);
        assertEquals(expectedAuthor1, actualAuthor1);

        Author expectedAuthor2 = new Author("Габриэль Гарсиа Маркес", "Колумбия");
        Author actualAuthor2 = authorCSVAdapterWrapper.read(1);
        assertEquals(expectedAuthor2, actualAuthor2);

    }

    @Test
    public void testReadAnimal() throws IOException {

        Path animalFilePath = Paths.get("animalFile.csv");
        File animalFile = animalFilePath.toFile();

        BufferedReader animalReader = new BufferedReader(new FileReader(animalFile));
        BufferedWriter animalWriter = new BufferedWriter(new FileWriter(animalFile, true));

        CSVAdapterWrapper<Animal> animalCSVAdapterWrapper = new CSVAdapterWrapper<>(
                new CSVAdapter<>(Animal.class, animalReader, animalWriter));

        Animal expectedAnimal1 = new Animal("Кит", "Кот", "Украина", "12 - 16 часов");
        Animal actualAnimal1 = animalCSVAdapterWrapper.read(0);
        assertEquals(expectedAnimal1, actualAnimal1);

        Animal expectedAnimal2 = new Animal("Дог", "Собака", "Англия", "12 - 14 часов");
        Animal actualAnimal2 = animalCSVAdapterWrapper.read(1);
        assertEquals(expectedAnimal2, actualAnimal2);
    }

    @Test
    public void testReadAnimalOwner() throws IOException {

        Path animalOwnerFilePath = Paths.get("animalOwnerFile.csv");
        File animalOwnerFile = animalOwnerFilePath.toFile();

        BufferedReader animalOwnerReader = new BufferedReader(new FileReader(animalOwnerFile));
        BufferedWriter animalOwnerWriter = new BufferedWriter(new FileWriter(animalOwnerFile, true));

        CSVAdapterWrapper<AnimalOwner> animalOwnerCSVAdapterWrapper = new CSVAdapterWrapper<>(
                new CSVAdapter<>(AnimalOwner.class, animalOwnerReader, animalOwnerWriter));

        AnimalOwner expectedAnimalOwner1 = new AnimalOwner("Никита", "Кит", "8-901-765-34-21");
        AnimalOwner actualAnimalOwner1 = animalOwnerCSVAdapterWrapper.read(0);
        assertEquals(expectedAnimalOwner1, actualAnimalOwner1);

        AnimalOwner expectedAnimalOwner2 = new AnimalOwner("Василий", "Дог", "8-954-976-12-67");
        AnimalOwner actualAnimalOwner2 = animalOwnerCSVAdapterWrapper.read(1);
        assertEquals(expectedAnimalOwner2, actualAnimalOwner2);

    }

    @Test
    public void testAppendBook() throws IOException {
        Path bookFilePath = Paths.get("bookFile.csv");
        File bookFile = bookFilePath.toFile();

        BufferedReader bookReader = new BufferedReader(new FileReader(bookFile));
        BufferedWriter bookWriter = new BufferedWriter(new FileWriter(bookFile, true));

        CSVAdapterWrapper<Book> bookCSVAdapterWrapper = new CSVAdapterWrapper<>(
                new CSVAdapter<>(Book.class, bookReader, bookWriter));

        Book newBook = new Book("Посторонний", "Альбер Камю", "Роман", "978-8-845-27763-4");

        int bookIndex = bookCSVAdapterWrapper.append(newBook);
        Book bookAtIndex = bookCSVAdapterWrapper.read(bookIndex);

        assertEquals(newBook, bookAtIndex);
    }

    @Test
    public void testAppendAuthor() throws IOException {
        Path authorFilePath = Paths.get("authorFile.csv");
        File authorFile = authorFilePath.toFile();

        BufferedReader authorReader = new BufferedReader(new FileReader(authorFile));
        BufferedWriter authorWriter = new BufferedWriter(new FileWriter(authorFile, true));

        CSVAdapterWrapper<Author> authorCSVAdapterWrapper = new CSVAdapterWrapper<>(
                new CSVAdapter<>(Author.class, authorReader, authorWriter));

        Author newAuthor = new Author("Дэниел Киз", "США");
        int authorIndex = authorCSVAdapterWrapper.append(newAuthor);
        Author authorAtIndex = authorCSVAdapterWrapper.read(authorIndex);

        assertEquals(newAuthor, authorAtIndex);
    }

    @Test
    public void testAppendAnimal() throws IOException {
        Path animalFilePath = Paths.get("animalFile.csv");
        File animalFile = animalFilePath.toFile();

        BufferedReader animalReader = new BufferedReader(new FileReader(animalFile));
        BufferedWriter animalWriter = new BufferedWriter(new FileWriter(animalFile, true));

        CSVAdapterWrapper<Animal> animalCSVAdapterWrapper = new CSVAdapterWrapper<>(
                new CSVAdapter<>(Animal.class, animalReader, animalWriter));

        Animal newAnimal = new Animal("Змея", "Анаконда", "Бразилия", "2 - 3 дня");
        int animalIndex = animalCSVAdapterWrapper.append(newAnimal);
        Animal animalAtIndex = animalCSVAdapterWrapper.read(animalIndex);

        assertEquals(newAnimal, animalAtIndex);
    }

    @Test
    public void testAppendAnimalOwner() throws IOException {
        Path animalOwnerFilePath = Paths.get("animalOwnerFile.csv");
        File animalOwnerFile = animalOwnerFilePath.toFile();

        BufferedReader animalOwnerReader = new BufferedReader(new FileReader(animalOwnerFile));
        BufferedWriter animalOwnerWriter = new BufferedWriter(new FileWriter(animalOwnerFile, true));

        CSVAdapterWrapper<AnimalOwner> animalOwnerCSVAdapterWrapper = new CSVAdapterWrapper<>(
                new CSVAdapter<>(AnimalOwner.class, animalOwnerReader, animalOwnerWriter));

        AnimalOwner newAnimalOwner = new AnimalOwner("Пауло", "Змея", "55-063-565-33-91");
        int animalOwnerIndex = animalOwnerCSVAdapterWrapper.append(newAnimalOwner);
        AnimalOwner animalOwnerAtIndex = animalOwnerCSVAdapterWrapper.read(animalOwnerIndex);

        assertEquals(newAnimalOwner, animalOwnerAtIndex);
    }
}
