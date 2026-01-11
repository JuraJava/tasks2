/**
 * Дан Java - класс Ex
 * public class Ex {
 *     // Тег
 *     public static class Tag {
 *         String name;
 *
 *         public Tag(String name) {
 *             this.name = name;
 *         }
 *     }
 *
 *     // Книга
 *     public static class Book {
 *         String name;
 *         List<Tag> tags;
 *
 *         public Book(String name, List<Tag> tags) {
 *             this.name = name;
 *             this.tags = tags;
 *         }
 *     }
 *
 *     // Автор
 *     public static class Author {
 *         String id;
 *         String name;
 *         List<Book> books;
 *
 *         public Author(String id, String name, List<Book> books) {
 *             this.id = id;
 *             this.name = name;
 *             this.books = books;
 *         }
 *     }
 *
 *     public static List<Tag> getTagsOfAuthorsBooks(List<Author> authors, List<String> searchAuthorIds) {
 *         // TODO Реализовать
 *         return null;
 *     }
 *
 *     public static void main(String[] args) {
 *         Tag tag1 = new Tag("Tag_1");
 *         Tag tag2 = new Tag("Tag_2");
 *         Tag tag3 = new Tag("Tag_3");
 *         Tag tag4 = new Tag("Tag_4");
 *         Tag tag5 = new Tag("Tag_5");
 *         Tag tag6 = new Tag("Tag_6");
 *         Tag tag7 = new Tag("Tag_7");
 *
 *         Book book1 = new Book("Book_1", List.of(tag1));
 *         Book book2 = new Book("Book_2", List.of(tag2, tag3));
 *         Book book3 = new Book("Book_3", List.of(tag4, tag5));
 *         Book book4 = new Book("Book_4", List.of(tag6, tag7));
 *
 *         Author author1 = new Author("Author_1", "Ivanov", List.of(book1, book2));
 *         Author author2 = new Author("Author_2", "Petrov", List.of(book3));
 *         Author author3 = new Author("Author_3", "Sidorov", List.of(book4));
 *
 *         List<Author> allAuthors = List.of(author1, author2, author3);
 *         List<Tag> allTags = List.of(tag1, tag2, tag3, tag4, tag5, tag6, tag7);
 *
 *         List<String> searchAuthorIds = List.of("Author_1", "Author_3");
 *
 *         List<Tag> tags = getTagsOfAuthorsBooks(allAuthors, searchAuthorIds);
 *     }
 * }
 */

package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Ex {
    public static class Tag {
        String name;

        public Tag(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tag tag = (Tag) o;
            return Objects.equals(name, tag.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    public static class Book {
        String name;
        List<Tag> tags;

        public Book(String name, List<Tag> tags) {
            this.name = name;
            this.tags = tags;
        }
    }

    public static class Author {
        String id;
        String name;
        List<Book> books;

        public Author(String id, String name, List<Book> books) {
            this.id = id;
            this.name = name;
            this.books = books;
        }
    }

    /**
     * Возвращает список тегов книг авторов, указанных списком идентификаторов
     * @param authors список авторов
     * @param searchAuthorIds список идентификаторов авторов, теги книг которых необходимо получить
     * @return список тегов книг указанных авторов
     */
    public static List<Tag> getTagsOfAuthorsBooks(List<Author> authors, List<String> searchAuthorIds) {
        Set<Tag> resultTags = new HashSet<>();
        Set<String> searchIdsSet = new HashSet<>(searchAuthorIds);

        for (Author author : authors) {
            if (searchIdsSet.contains(author.id)) {
                for (Book book : author.books) {
                    if (book.tags != null) {
                        resultTags.addAll(book.tags);
                    }
                }
            }
        }

        return new ArrayList<>(resultTags);
    }

    public static void main(String[] args) {
        Tag tag1 = new Tag("Tag_1");
        Tag tag2 = new Tag("Tag_2");
        Tag tag3 = new Tag("Tag_3");
        Tag tag4 = new Tag("Tag_4");
        Tag tag5 = new Tag("Tag_5");
        Tag tag6 = new Tag("Tag_6");
        Tag tag7 = new Tag("Tag_7");

        Book book1 = new Book("Book_1", List.of(tag1));
        Book book2 = new Book("Book_2", List.of(tag2, tag3));
        Book book3 = new Book("Book_3", List.of(tag4, tag5));
        Book book4 = new Book("Book_4", List.of(tag6, tag7));

        Author author1 = new Author("Author_1", "Ivanov", List.of(book1, book2));
        Author author2 = new Author("Author_2", "Petrov", List.of(book3));
        Author author3 = new Author("Author_3", "Sidorov", List.of(book4));

        List<Author> allAuthors = List.of(author1, author2, author3);
        List<String> searchAuthorIds = List.of("Author_1", "Author_3");

        List<Tag> tags = getTagsOfAuthorsBooks(allAuthors, searchAuthorIds);

//        System.out.println("Найдено тегов: " + tags.size());
        System.out.println("Tags found: " + tags.size());
        for (Tag tag : tags) {
            System.out.println(tag.name);
        }
    }
}

