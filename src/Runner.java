import java.io.*;
import java.util.Scanner;

/**
 * Программа на вход (с консоли) запрашивает пути к файлу с номерами документов (источнику), к месту где находятся
 * файл-отчеты для валидных и не валидных номеров (если файла по указанному пути нет, тогда файл будет создан).
 *
 * Номера, из файла источника, проверяются на наличие префикса, затем на длину. Валидный номер записывается в файл-отчет
 * валидных номеров, невалидный - файл-отчет невалидных. Для каждого невалидного номера указывается причина в файл-отчете.
 */


public class Runner {

    public static void main(String[] args) {

        String pathToDocFile;
        String pathToValidDocReport;
        String pathToInValidDocReport;

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Введите путь к .txt файлу с номерами документов: ");
            pathToDocFile = sc.nextLine(); // "D:\\!Lera\\!Учеба\\Java\\Java. TeachMeSkills\\11\\testData\\docNumbers.txt";

            System.out.println("Введите путь к/для файл-отчета валидных номеров документов: ");
            pathToValidDocReport = sc.nextLine(); // "D:\\!Lera\\!Учеба\\Java\\Java. TeachMeSkills\\11\\testData\\validDocNumbersReport.txt";

            System.out.println("Введите путь к/для файл-отчета невалидных номеров документов: ");
            pathToInValidDocReport = sc.nextLine(); // "D:\\!Lera\\!Учеба\\Java\\Java. TeachMeSkills\\11\\testData\\inValidDocNumbersReport.txt";
        }

        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToDocFile));
                BufferedWriter bufferedWriterValidReport = new BufferedWriter(new FileWriter(pathToValidDocReport));
                BufferedWriter bufferedWriterInValidReport = new BufferedWriter(new FileWriter(pathToInValidDocReport));
        ) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                if (DocNumberValidation.isPrefixOfNumberValid(currentLine)) {
                    if (DocNumberValidation.isNumberLengthValid(currentLine)) {
                        bufferedWriterValidReport.write(currentLine);
                        bufferedWriterValidReport.write('\n');
                    } else {
                        bufferedWriterInValidReport.write(currentLine + " [длина номера не соответствует требованиям]");
                        bufferedWriterInValidReport.write('\n');
                    }

                } else {
                    bufferedWriterInValidReport.write(currentLine + " [префикс номера не соответствует требованиям]");
                    bufferedWriterInValidReport.write('\n');
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

