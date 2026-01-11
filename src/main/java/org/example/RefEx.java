/**
 * Дан  Java – класс:
 *
 * public class RefEx {
 *
 *     public enum DocumentType {
 *         XML, PDF, DOCX
 *     }
 *
 *     public static class Document {
 *         String id;
 *         DocumentType type;
 *         String content;
 *     }
 *
 *     public static class DocumentService {
 *
 *         public void process(Document[] d) {
 *             for (Document i : d) {
 *                 // Общая логика обработки документа
 *                 switch (i.type) {
 *                     case DocumentType.PDF: {
 *                         // Специфическая логика для обработки PDF
 *                     } break;
 *                     case DocumentType.DOCX: {
 *                         // Специфическая логика для обработки Word
 *                     } break;
 *                     case DocumentType.XML: {
 *                         // Специфическая логика для обработки XML
 *                     } break;
 *                 }
 *             }
 *         }
 *     }
 * }
 *
 * Необходимо осуществить рефакторинг  обработки документов
 */

package org.example;

//import java.util.HashMap;
//import java.util.Map;
//
//public class RefEx {
//
//    public enum DocumentType {
//        XML, PDF, DOCX
//    }
//
//    public static class Document {
//        String id;
//        DocumentType type;
//        String content;
//
//        public Document(String id, DocumentType type, String content) {
//            this.id = id;
//            this.type = type;
//            this.content = content;
//        }
//    }
//
//    public static class DocumentService {
//        private Map<DocumentType, DocumentProcessor> processors = new HashMap<>();
//
//        public DocumentService() {
//            processors.put(DocumentType.PDF, new PdfProcessor());
//            processors.put(DocumentType.DOCX, new DocxProcessor());
//            processors.put(DocumentType.XML, new XmlProcessor());
//        }
//
//        public void process(Document[] d) {
//            for (Document doc : d) {
//                // Общая логика обработки документа
//                System.out.println("Обработка документа ID: " + doc.id);
//
//                // Получаем соответствующий процессор по типу документа
//                DocumentProcessor processor = processors.get(doc.type);
//                if (processor != null) {
//                    processor.process(doc);
//                } else {
//                    System.out.println("Неизвестный тип документа: " + doc.type);
//                }
//            }
//        }
//    }
//
//    // Базовый интерфейс для обработчиков документов
//    public interface DocumentProcessor {
//        void process(Document document);
//    }
//
//    // Конкретные реализации обработчиков
//    public static class PdfProcessor implements DocumentProcessor {
//        @Override
//        public void process(Document document) {
//            // Специфическая логика для обработки PDF
//            System.out.println("Обработка PDF документа: " + document.content);
//            // Здесь будет реальная логика обработки PDF
//        }
//    }
//
//    public static class DocxProcessor implements DocumentProcessor {
//        @Override
//        public void process(Document document) {
//            // Специфическая логика для обработки Word
//            System.out.println("Обработка DOCX документа: " + document.content);
//            // Здесь будет реальная логика обработки DOCX
//        }
//    }
//
//    public static class XmlProcessor implements DocumentProcessor {
//        @Override
//        public void process(Document document) {
//            // Специфическая логика для обработки XML
//            System.out.println("Обработка XML документа: " + document.content);
//            // Здесь будет реальная логика обработки XML
//        }
//    }
//
//    // Пример использования
//    public static void main(String[] args) {
//        Document[] documents = {
//                new Document("1", DocumentType.PDF, "PDF content"),
//                new Document("2", DocumentType.DOCX, "Word content"),
//                new Document("3", DocumentType.XML, "XML content")
//        };
//
//        DocumentService service = new DocumentService();
//        service.process(documents);
//    }
//}

/**
 * Вот полное решение с реальными библиотеками обработки документов:
 */

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RefEx {

    public enum DocumentType {
        XML, PDF, DOCX
    }

    public static class Document {
        String id;
        DocumentType type;
        String content;
        byte[] binaryContent; // Для бинарных форматов

        public Document(String id, DocumentType type, String content) {
            this.id = id;
            this.type = type;
            this.content = content;
        }

        public Document(String id, DocumentType type, byte[] binaryContent) {
            this.id = id;
            this.type = type;
            this.binaryContent = binaryContent;
        }

        // Геттеры для доступа к полям
        public String getId() { return id; }
        public DocumentType getType() { return type; }
        public String getContent() { return content; }
        public byte[] getBinaryContent() { return binaryContent; }
    }

    public static class DocumentService {
        private Map<DocumentType, DocumentProcessor> processors = new HashMap<>();

        public DocumentService() {
            processors.put(DocumentType.PDF, new PdfProcessor());
            processors.put(DocumentType.DOCX, new DocxProcessor());
            processors.put(DocumentType.XML, new XmlProcessor());
        }

        public DocumentService(Map<DocumentType, DocumentProcessor> processors) {
            this.processors = processors;
        }

        public void process(Document[] documents) throws DocumentProcessingException {
            List<String> errors = new ArrayList<>();

            for (Document doc : documents) {
                try {
                    System.out.println("Обработка документа ID: " + doc.id);

                    // Проверка наличия данных
                    if ((doc.type == DocumentType.PDF || doc.type == DocumentType.DOCX)
                            && (doc.binaryContent == null || doc.binaryContent.length == 0)) {
                        throw new DocumentProcessingException(
                                "Документ типа " + doc.type + " должен содержать бинарные данные");
                    }

                    if (doc.type == DocumentType.XML &&
                            (doc.content == null || doc.content.trim().isEmpty()) &&
                            (doc.binaryContent == null || doc.binaryContent.length == 0)) {
                        throw new DocumentProcessingException("XML документ должен содержать содержимое");
                    }

                    DocumentProcessor processor = processors.get(doc.type);
                    if (processor != null) {
                        processor.process(doc);
                        System.out.println("✓ Документ ID: " + doc.id + " успешно обработан");
                    } else {
                        throw new DocumentProcessingException(
                                "Неизвестный тип документа: " + doc.type);
                    }
                } catch (DocumentProcessingException e) {
                    String errorMsg = "Ошибка при обработке документа " + doc.id + ": " + e.getMessage();
                    errors.add(errorMsg);
                    System.err.println(errorMsg);
                }
            }

            if (!errors.isEmpty()) {
                throw new DocumentProcessingException("Ошибки при обработке документов:\n" +
                        String.join("\n", errors));
            }
        }
    }

    // Базовый интерфейс для обработчиков документов
    public interface DocumentProcessor {
        void process(Document document) throws DocumentProcessingException;
    }

    // Обработчик PDF с использованием iTextPDF
    public static class PdfProcessor implements DocumentProcessor {
        @Override
        public void process(Document document) throws DocumentProcessingException {
            try {
                // Дополнительная проверка на валидность PDF
                if (!isValidPdfData(document.binaryContent)) {
                    throw new DocumentProcessingException("Невалидные данные PDF документа");
                }

                PdfReader reader = new PdfReader(document.binaryContent);

                // Извлечение текста из PDF
                StringBuilder textBuilder = new StringBuilder();
                int pages = reader.getNumberOfPages();

                for (int i = 1; i <= pages; i++) {
                    String pageText = PdfTextExtractor.getTextFromPage(reader, i);
                    textBuilder.append(pageText);
                }

                String extractedText = textBuilder.toString();

                // Получение метаданных
                String title = reader.getInfo().get("Title");
                String author = reader.getInfo().get("Author");
                int numberOfPages = pages;

                reader.close();

                // Сохранение результатов обработки
                savePdfProcessingResult(
                        document.id,
                        extractedText,
                        title,
                        author,
                        numberOfPages
                );

                System.out.println("PDF документ ID: " + document.id +
                        " обработан. Страниц: " + numberOfPages +
                        ", символов текста: " + extractedText.length());

            } catch (IOException e) {
                throw new DocumentProcessingException("Ошибка чтения PDF файла", e);
            }
        }

        private boolean isValidPdfData(byte[] data) {
            // Минимальная проверка: PDF должен начинаться с %PDF-
            if (data == null || data.length < 5) {
                return false;
            }
            String header = new String(data, 0, Math.min(data.length, 10), StandardCharsets.UTF_8);
            return header.contains("%PDF");
        }

        private void savePdfProcessingResult(String docId, String text,
                                             String title, String author, int pages) {
            // Реальная логика сохранения в БД или другом хранилище
            System.out.println("Сохранение результатов PDF обработки для документа: " + docId);
            System.out.println("  Заголовок: " + (title != null ? title : "не указан"));
            System.out.println("  Автор: " + (author != null ? author : "не указан"));
            System.out.println("  Текст (первые 100 символов): " +
                    (text.length() > 100 ? text.substring(0, 100) + "..." : text));
        }
    }

    // Обработчик DOCX с использованием Apache POI
    public static class DocxProcessor implements DocumentProcessor {
        @Override
        public void process(Document document) throws DocumentProcessingException {
            try (InputStream is = new ByteArrayInputStream(document.binaryContent);
                 XWPFDocument docx = new XWPFDocument(is);
                 XWPFWordExtractor extractor = new XWPFWordExtractor(docx)) {

                // Извлечение текста
                String text = extractor.getText();

                // Извлечение метаданных
                String title = docx.getProperties().getCoreProperties().getTitle();
                String creator = docx.getProperties().getCoreProperties().getCreator();
                int paragraphs = docx.getParagraphs().size();
                int tables = docx.getTables().size();

                // Сохранение результатов обработки
                saveDocxProcessingResult(
                        document.id,
                        text,
                        title,
                        creator,
                        paragraphs,
                        tables
                );

                System.out.println("DOCX документ ID: " + document.id +
                        " обработан. Параграфов: " + paragraphs +
                        ", таблиц: " + tables);

            } catch (IOException e) {
                throw new DocumentProcessingException("Ошибка чтения DOCX файла", e);
            } catch (Exception e) {
                throw new DocumentProcessingException("Невалидный формат DOCX документа", e);
            }
        }

        private void saveDocxProcessingResult(String docId, String text,
                                              String title, String creator,
                                              int paragraphs, int tables) {
            // Реальная логика сохранения
            System.out.println("Сохранение результатов DOCX обработки для документа: " + docId);
            System.out.println("  Заголовок: " + (title != null ? title : "не указан"));
            System.out.println("  Создатель: " + (creator != null ? creator : "не указан"));
            System.out.println("  Текст (первые 100 символов): " +
                    (text.length() > 100 ? text.substring(0, 100) + "..." : text));
        }
    }

    // Обработчик XML с использованием Jackson XML
    public static class XmlProcessor implements DocumentProcessor {
        private final XmlMapper xmlMapper = new XmlMapper();

        @Override
        public void process(Document document) throws DocumentProcessingException {
            try {
                String xmlContent = document.content;
                if (xmlContent == null && document.binaryContent != null) {
                    xmlContent = new String(document.binaryContent, StandardCharsets.UTF_8);
                }

                if (xmlContent == null || xmlContent.trim().isEmpty()) {
                    throw new DocumentProcessingException("XML документ должен содержать содержимое");
                }

                // Пример: парсинг XML в POJO объект
                XmlData xmlData = xmlMapper.readValue(xmlContent, XmlData.class);

                // Валидация XML структуры
                validateXmlData(xmlData);

                // Преобразование в JSON для хранения
                String jsonRepresentation = xmlMapper.writeValueAsString(xmlData);

                // Сохранение результатов обработки
                saveXmlProcessingResult(document.id, xmlData, jsonRepresentation);

                System.out.println("XML документ ID: " + document.id +
                        " обработан. Элементов: " + xmlData.getItems().size());

            } catch (IOException e) {
                throw new DocumentProcessingException("Ошибка парсинга XML", e);
            }
        }

        private void validateXmlData(XmlData xmlData) throws DocumentProcessingException {
            if (xmlData.getItems() == null || xmlData.getItems().isEmpty()) {
                throw new DocumentProcessingException("XML не содержит элементов");
            }
        }

        private void saveXmlProcessingResult(String docId, XmlData xmlData, String json) {
            // Реальная логика сохранения
            System.out.println("Сохранение результатов XML обработки для документа: " + docId);
            System.out.println("  Корневой элемент: " + xmlData.getRootElement());
            System.out.println("  Количество элементов: " + xmlData.getItems().size());
            System.out.println("  JSON представление (первые 200 символов): " +
                    (json.length() > 200 ? json.substring(0, 200) + "..." : json));
        }
    }

    // POJO класс для XML данных (пример)
    public static class XmlData {
        private String rootElement;
        private List<XmlItem> items;

        // Геттеры и сеттеры
        public String getRootElement() { return rootElement; }
        public void setRootElement(String rootElement) { this.rootElement = rootElement; }
        public List<XmlItem> getItems() { return items; }
        public void setItems(List<XmlItem> items) { this.items = items; }
    }

    public static class XmlItem {
        private String id;
        private String value;

        // Геттеры и сеттеры
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getValue() { return value; }
        public void setValue(String value) { this.value = value; }
    }

    // Кастомное исключение для обработки документов
    public static class DocumentProcessingException extends Exception {
        public DocumentProcessingException(String message) {
            super(message);
        }

        public DocumentProcessingException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // Вспомогательные методы для создания тестовых данных
    public static class TestDataGenerator {

        // Создание простого PDF в памяти (для тестирования)
        public static byte[] createSimplePdf(String text) throws Exception {
            try (java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream()) {
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                com.itextpdf.text.pdf.PdfWriter writer =
                        com.itextpdf.text.pdf.PdfWriter.getInstance(document, baos);
                document.open();
                document.add(new com.itextpdf.text.Paragraph(text));
                document.close();
                return baos.toByteArray();
            }
        }

        // Создание простого DOCX в памяти (для тестирования)
        public static byte[] createSimpleDocx(String text) throws Exception {
            try (java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
                 XWPFDocument document = new XWPFDocument()) {
                document.createParagraph().createRun().setText(text);
                document.write(baos);
                return baos.toByteArray();
            }
        }
    }

    // Пример использования с тестовыми данными
    public static void main(String[] args) {
        try {
            System.out.println("=== Тестирование системы обработки документов ===\n");

            // 1. Тестирование XML (работает с текстом)
            System.out.println("1. Тестирование XML документа:");
            Document xmlDoc = new Document(
                    "xml-1",
                    DocumentType.XML,
                    "<XmlData>" +
                            "<rootElement>Пример XML</rootElement>" +
                            "<items>" +
                            "<item><id>1</id><value>Первое значение</value></item>" +
                            "<item><id>2</id><value>Второе значение</value></item>" +
                            "<item><id>3</id><value>Третье значение</value></item>" +
                            "</items>" +
                            "</XmlData>"
            );

            // 2. Создание и тестирование PDF (используем тестовый PDF)
            System.out.println("\n2. Тестирование PDF документа:");
            byte[] testPdf = TestDataGenerator.createSimplePdf("Это тестовый PDF документ.\n" +
                    "Создан для демонстрации работы системы.\n" +
                    "Содержит несколько строк текста.");
            Document pdfDoc = new Document("pdf-1", DocumentType.PDF, testPdf);

            // 3. Создание и тестирование DOCX (используем тестовый DOCX)
            System.out.println("\n3. Тестирование DOCX документа:");
            byte[] testDocx = TestDataGenerator.createSimpleDocx("Это тестовый DOCX документ.\n" +
                    "Создан с помощью Apache POI.\n" +
                    "Может содержать форматированный текст.");
            Document docxDoc = new Document("docx-1", DocumentType.DOCX, testDocx);

            // Обработка всех документов
            Document[] documents = {xmlDoc, pdfDoc, docxDoc};
            DocumentService service = new DocumentService();
            service.process(documents);

            System.out.println("\n=== Все документы успешно обработаны ===");

        } catch (Exception e) {
            System.err.println("Ошибка в основном методе: " + e.getMessage());
            e.printStackTrace();
        }
    }
}