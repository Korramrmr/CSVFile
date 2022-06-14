package main;

import csv.CSV;

public final class Main {

    public static final int INPUT_FILE_CLI_ARGUMENT_INDEX = 0;
    public static final int OUTPUT_FILE_CLI_ARGUMENT_INDEX = 1;

    private static final int NULL_CLI_ARGUMENTS = 0;
    private static final int IN_INPUT_ARGUMENT_COUNT = 1;
    private static final int IN_INPUT_ADN_OUTPUT_ARGUMENTS_COUNT = 2;

    private Main() {
    }

    public static void main(final String[] args) {
        if (args.length == NULL_CLI_ARGUMENTS) {
            System.out.println("Вы не указали название файлов.");
        } else if (args.length == IN_INPUT_ARGUMENT_COUNT) {
            new CSV().writeToFile(args[INPUT_FILE_CLI_ARGUMENT_INDEX], null);
            System.out.println("Необходимо указать имя выходного файла.");
        } else if (args.length == IN_INPUT_ADN_OUTPUT_ARGUMENTS_COUNT) {
            new CSV().writeToFile(args[INPUT_FILE_CLI_ARGUMENT_INDEX], args[OUTPUT_FILE_CLI_ARGUMENT_INDEX]);
        } else {
            System.out.println("Вы указали больше двух файлов.");
        }
    }

}


