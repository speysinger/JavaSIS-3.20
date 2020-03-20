package pro.sisit.adapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    final private String bookFileName = "bookFile.csv";
    final private String authorFileName = "authorFile.csv";
    final private String animalFileName = "animalFile.csv";
    final private String animalOwnerFileName = "animalOwnerFile.csv";

    @Before
    public void createFile() {

        Path bookFilePath = Paths.get(bookFileName);
        File bookFile = bookFilePath.toFile();

        Path authorFilePath = Paths.get(authorFileName);
        File authorFile = authorFilePath.toFile();

        Path animalFilePath = Paths.get(animalFileName);
        File animalFile = animalFilePath.toFile();

        Path animalOwnerFilePath = Paths.get(animalOwnerFileName);
        File animalOwnerFile = animalOwnerFilePath.toFile();

        boolean bookFileCreated = false;
        boolean authorFileCreated = false;
        boolean animalFileCreated = false;
        boolean animalOwnerFileFileCreated = false;

        try {
            bookFileCreated = bookFile.createNewFile();
            authorFileCreated = authorFile.createNewFile();
            animalFileCreated = animalFile.createNewFile();
            animalOwnerFileFileCreated = animalOwnerFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка создания файла");
        }

        if (!bookFileCreated | !authorFileCreated | !animalFileCreated | !animalOwnerFileFileCreated) {
            throw new RuntimeException("Файл не был создан. Состояние файлов: " +
                    "book" + bookFileCreated +
                    "author" + authorFileCreated +
                    "animal" + animalFileCreated +
                    "animalOwner" + animalFileCreated);
        }

        fillFiles();
    }

    public void fillFiles() {
        try (FileWriter bookCsvWriter = new FileWriter(bookFileName);
             FileWriter authorCsvWriter = new FileWriter(authorFileName);
             FileWriter animalCsvWriter = new FileWriter(animalFileName);
             FileWriter animalOwnerWriter = new FileWriter(animalOwnerFileName)) {

            String firstBook = "Убик;Филип Дик;Научная фантастика;978-5-699-97309-5;\n";
            String secondBook = "Глуховский;Будущее;Научная фантастика;978-5-17-118366-0;";

            bookCsvWriter.append(firstBook);
            bookCsvWriter.append(secondBook);

            String firstAuthor = "Альбер Камю;Алжир;\n";
            String secondAuthor = "Габриэль Гарсиа Маркес;Колумбия;";

            authorCsvWriter.append(firstAuthor);
            authorCsvWriter.append(secondAuthor);

            String firstAnimal = "Кит;Кот;Украина;12 - 16 часов;\n";
            String secondAnimal = "Дог;Собака;Англия;12 - 14 часов;";

            animalCsvWriter.append(firstAnimal);
            animalCsvWriter.append(secondAnimal);

            String firstAnimalOwner = "Никита;Кит;8-901-765-34-21;\n";
            String secondAnimalOwner = "Василий;Дог;8-954-976-12-67;";

            animalOwnerWriter.append(firstAnimalOwner);
            animalOwnerWriter.append(secondAnimalOwner);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл;");
        }
    }

    @After
    public void deleteFile() {
        Path bookFilePath = Paths.get(bookFileName);
        boolean isBookFileDeleted = bookFilePath.toFile().delete();

        assertTrue(isBookFileDeleted);

        Path authorFilePath = Paths.get(authorFileName);
        boolean isAuthorFileDeleted = authorFilePath.toFile().delete();

        assertTrue(isAuthorFileDeleted);

        Path animalFilePath = Paths.get(animalFileName);
        boolean isAnimalFileDeleted = animalFilePath.toFile().delete();

        assertTrue(isAnimalFileDeleted);

        Path animalOwnerFilePath = Paths.get(animalOwnerFileName);
        boolean isAnimalOwnerFileDeleted = animalOwnerFilePath.toFile().delete();

        assertTrue(isAnimalOwnerFileDeleted);
    }

    @Test
    public void testReadBook() {

        Path bookFilePath = Paths.get(bookFileName);
        File bookFile = bookFilePath.toFile();

        try (
                BufferedReader bookReader = new BufferedReader(new FileReader(bookFile));
                BufferedWriter bookWriter = new BufferedWriter(new FileWriter(bookFile, true))) {
            CSVAdapterWrapper<Book> bookCSVAdapterWrapper = new CSVAdapterWrapper<>(
                    new CSVAdapter<>(Book.class, bookReader, bookWriter));

            Book expectedBook1 = new Book("Убик",
                    "Филип Дик",
                    "Научная фантастика",
                    "978-5-699-97309-5");
            Book actualBook1 = bookCSVAdapterWrapper.read(0);
            assertEquals(expectedBook1, actualBook1);

            Book expectedBook2 = new Book("Глуховский",
                    "Будущее",
                    "Научная фантастика",
                    "978-5-17-118366-0");
            Book actualBook2 = bookCSVAdapterWrapper.read(1);
            assertEquals(actualBook2, expectedBook2);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(unavailableFileExceptionText(bookFileName));

        } catch (IOException e) {
            throw new RuntimeException(creatingIoStreamsException(bookFileName));
        }
    }

    @Test
    public void testReadAuthor() {

        Path authorFilePath = Paths.get(authorFileName);
        File authorFile = authorFilePath.toFile();

        try (
                BufferedReader authorReader = new BufferedReader(new FileReader(authorFile));
                BufferedWriter authorWriter = new BufferedWriter(new FileWriter(authorFile, true))) {

            CSVAdapterWrapper<Author> authorCSVAdapterWrapper = new CSVAdapterWrapper<>(
                    new CSVAdapter<>(Author.class, authorReader, authorWriter));

            Author expectedAuthor1 = new Author("Альбер Камю", "Алжир");
            Author actualAuthor1 = authorCSVAdapterWrapper.read(0);
            assertEquals(expectedAuthor1, actualAuthor1);

            Author expectedAuthor2 = new Author("Габриэль Гарсиа Маркес", "Колумбия");
            Author actualAuthor2 = authorCSVAdapterWrapper.read(1);
            assertEquals(expectedAuthor2, actualAuthor2);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(unavailableFileExceptionText(authorFileName));
        } catch (IOException e) {
            throw new RuntimeException(creatingIoStreamsException(authorFileName));
        }

    }

    @Test
    public void testReadAnimal() {

        Path animalFilePath = Paths.get(animalFileName);
        File animalFile = animalFilePath.toFile();

        try (
                BufferedReader animalReader = new BufferedReader(new FileReader(animalFile));
                BufferedWriter animalWriter = new BufferedWriter(new FileWriter(animalFile, true))) {

            CSVAdapterWrapper<Animal> animalCSVAdapterWrapper = new CSVAdapterWrapper<>(
                    new CSVAdapter<>(Animal.class, animalReader, animalWriter));

            Animal expectedAnimal1 = new Animal("Кит",
                    "Кот",
                    "Украина",
                    "12 - 16 часов");
            Animal actualAnimal1 = animalCSVAdapterWrapper.read(0);
            assertEquals(expectedAnimal1, actualAnimal1);

            Animal expectedAnimal2 = new Animal("Дог",
                    "Собака",
                    "Англия",
                    "12 - 14 часов");
            Animal actualAnimal2 = animalCSVAdapterWrapper.read(1);
            assertEquals(expectedAnimal2, actualAnimal2);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(unavailableFileExceptionText(animalFileName));
        } catch (IOException e) {
            throw new RuntimeException(creatingIoStreamsException(animalFileName));
        }
    }

    @Test
    public void testReadAnimalOwner() {

        Path animalOwnerFilePath = Paths.get(animalOwnerFileName);
        File animalOwnerFile = animalOwnerFilePath.toFile();

        try (
                BufferedReader animalOwnerReader = new BufferedReader(new FileReader(animalOwnerFile));
                BufferedWriter animalOwnerWriter = new BufferedWriter(new FileWriter(animalOwnerFile, true))) {

            CSVAdapterWrapper<AnimalOwner> animalOwnerCSVAdapterWrapper = new CSVAdapterWrapper<>(
                    new CSVAdapter<>(AnimalOwner.class, animalOwnerReader, animalOwnerWriter));

            AnimalOwner expectedAnimalOwner1 = new AnimalOwner("Никита",
                    "Кит",
                    "8-901-765-34-21");
            AnimalOwner actualAnimalOwner1 = animalOwnerCSVAdapterWrapper.read(0);
            assertEquals(expectedAnimalOwner1, actualAnimalOwner1);

            AnimalOwner expectedAnimalOwner2 = new AnimalOwner("Василий",
                    "Дог",
                    "8-954-976-12-67");
            AnimalOwner actualAnimalOwner2 = animalOwnerCSVAdapterWrapper.read(1);
            assertEquals(expectedAnimalOwner2, actualAnimalOwner2);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(unavailableFileExceptionText(animalOwnerFileName));
        } catch (IOException e) {
            throw new RuntimeException(creatingIoStreamsException(animalOwnerFileName));
        }

    }

    @Test
    public void testAppendBook() {
        Path bookFilePath = Paths.get(bookFileName);
        File bookFile = bookFilePath.toFile();

        try (
                BufferedReader bookReader = new BufferedReader(new FileReader(bookFile));
                BufferedWriter bookWriter = new BufferedWriter(new FileWriter(bookFile, true))) {

            CSVAdapterWrapper<Book> bookCSVAdapterWrapper = new CSVAdapterWrapper<>(
                    new CSVAdapter<>(Book.class, bookReader, bookWriter));

            Book newBook = new Book("Посторонний",
                    "Альбер Камю",
                    "Роман",
                    "978-8-845-27763-4");

            int bookIndex = bookCSVAdapterWrapper.append(newBook);
            Book bookAtIndex = bookCSVAdapterWrapper.read(bookIndex);

            assertEquals(newBook, bookAtIndex);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(unavailableFileExceptionText(bookFileName));
        } catch (IOException e) {
            throw new RuntimeException(creatingIoStreamsException(bookFileName));
        }
    }

    @Test
    public void testAppendAuthor() {
        Path authorFilePath = Paths.get(authorFileName);
        File authorFile = authorFilePath.toFile();

        try (
                BufferedReader authorReader = new BufferedReader(new FileReader(authorFile));
                BufferedWriter authorWriter = new BufferedWriter(new FileWriter(authorFile, true))) {

            CSVAdapterWrapper<Author> authorCSVAdapterWrapper = new CSVAdapterWrapper<>(
                    new CSVAdapter<>(Author.class, authorReader, authorWriter));

            Author newAuthor = new Author("Дэниел Киз", "США");
            int authorIndex = authorCSVAdapterWrapper.append(newAuthor);
            Author authorAtIndex = authorCSVAdapterWrapper.read(authorIndex);

            assertEquals(newAuthor, authorAtIndex);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(unavailableFileExceptionText(authorFileName));
        } catch (IOException e) {
            throw new RuntimeException(creatingIoStreamsException(authorFileName));
        }
    }

    @Test
    public void testAppendAnimal() {
        Path animalFilePath = Paths.get(animalFileName);
        File animalFile = animalFilePath.toFile();

        try (
                BufferedReader animalReader = new BufferedReader(new FileReader(animalFile));
                BufferedWriter animalWriter = new BufferedWriter(new FileWriter(animalFile, true))) {

            CSVAdapterWrapper<Animal> animalCSVAdapterWrapper = new CSVAdapterWrapper<>(
                    new CSVAdapter<>(Animal.class, animalReader, animalWriter));

            Animal newAnimal = new Animal("Змея",
                    "Анаконда",
                    "Бразилия",
                    "2 - 3 дня");
            int animalIndex = animalCSVAdapterWrapper.append(newAnimal);
            Animal animalAtIndex = animalCSVAdapterWrapper.read(animalIndex);

            assertEquals(newAnimal, animalAtIndex);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(unavailableFileExceptionText(animalFileName));
        } catch (IOException e) {
            throw new RuntimeException(creatingIoStreamsException(animalFileName));
        }
    }

    @Test
    public void testAppendAnimalOwner() {
        Path animalOwnerFilePath = Paths.get(animalOwnerFileName);
        File animalOwnerFile = animalOwnerFilePath.toFile();

        try (
                BufferedReader animalOwnerReader = new BufferedReader(new FileReader(animalOwnerFile));
                BufferedWriter animalOwnerWriter = new BufferedWriter(new FileWriter(animalOwnerFile, true))) {

            CSVAdapterWrapper<AnimalOwner> animalOwnerCSVAdapterWrapper = new CSVAdapterWrapper<>(
                    new CSVAdapter<>(AnimalOwner.class, animalOwnerReader, animalOwnerWriter));

            AnimalOwner newAnimalOwner = new AnimalOwner("Пауло",
                    "Змея",
                    "55-063-565-33-91");
            int animalOwnerIndex = animalOwnerCSVAdapterWrapper.append(newAnimalOwner);
            AnimalOwner animalOwnerAtIndex = animalOwnerCSVAdapterWrapper.read(animalOwnerIndex);

            assertEquals(newAnimalOwner, animalOwnerAtIndex);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(unavailableFileExceptionText(animalOwnerFileName));
        } catch (IOException e) {
            throw new RuntimeException(creatingIoStreamsException(animalOwnerFileName));
        }
    }

    public String unavailableFileExceptionText(String fileName) {
        return "Файл " + fileName + "недоступен";
    }

    public String creatingIoStreamsException(String fileName) {
        return "Невозможно создать IO потоки файла " + fileName;
    }
}
