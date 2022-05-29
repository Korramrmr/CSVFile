package mainpackage;

import csvpackage.CSV;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Вы не указали название файлов.");
        } else if (args.length == 1) {
            new CSV().writeToFile(args[0], null);
            System.out.println("Необходимо указать имя выходного файла.");
        } else if (args.length == 2) {
            new CSV().writeToFile(args[0], args[1]);
        } else {
            System.out.println("Вы указали больше двух файлов.");
        }

    }

}


