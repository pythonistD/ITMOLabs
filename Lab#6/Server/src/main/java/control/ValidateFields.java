package control;


import MyExceptions.IncorrectIdException;
import model.DragonType;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Проверка полей объектов коллекции на правильность
 */
public class ValidateFields {
    private static long itrCounter = 0;

    public static long checkId(long id) throws IncorrectIdException {
        ValidateFields.itrCounter++;
        if (id != ValidateFields.itrCounter) {
            throw new IncorrectIdException("В исходном файле неправильно указан id\nНумеруйте элементы CSV файла начиная с 1", id);
        }

        return id;
    }

    public static String checkName(String field) throws IllegalArgumentException {
        if (field == null | field.trim().isEmpty()) {
            throw new IllegalArgumentException("Неправильно введено Name");
        }
        return field;
    }

    public static String checkNameInteractive(String promptToChange) {
        String inData;
        BufferedReader in = DataReader.getTreat();
        while (true) {
            try {
                inData = in.readLine();
                if (inData == null && inData.trim().isEmpty()) {
                    throw new IllegalArgumentException("Неправильно введено Name");
                }
            } catch (Exception e) {
                System.out.println("Данные введены некорректно" + "\n" + "Попробуйте ввести ещё раз");
                System.out.println(promptToChange);
                continue;
            }
            break;
        }
        return inData;
    }

    public static Long checkAge(String field) throws IllegalArgumentException, NullPointerException {
        long age = Long.parseLong(field);
        if (age <= 0) {
            throw new IllegalArgumentException("Неправильно введено Age");
        }
        return age;
    }

    public static Long checkAgeInteractive(String promptToChange) {
        Long t;
        String inData;
        BufferedReader in = DataReader.getTreat();
        while (true) {
            try {
                inData = in.readLine();
                t = Long.parseLong(inData);
                if (t <= 0) {
                    throw new IllegalArgumentException();

                }
            } catch (Exception e) {
                System.out.println("Данные введены некорректно" + "\n" + "Попробуйте ввести ещё раз");
                System.out.println(promptToChange);
                continue;
            }
            break;
        }
        return t;
    }

    public static Double checkWingSpan(String field) throws IllegalArgumentException, NullPointerException {
        double wingspan = Double.parseDouble(field);
        if (wingspan <= 0) {
            throw new IllegalArgumentException("Неправильно введено WingSpan");
        }
        return wingspan;
    }

    public static Double checkWingSpanInteractive(String promptToChange) {
        Double t = null;
        String inData;
        BufferedReader in = DataReader.getTreat();
        while (true) {
            try {
                inData = in.readLine();
                t = Double.parseDouble(inData);
                if (t <= 0) {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException | IOException e) {
                System.out.println("Данные введены некорректно" + "\n" + "Попробуйте ввести ещё раз");
                System.out.println(promptToChange);
                continue;
            }
            break;
        }
        return t;
    }

    public static boolean checkSpeaking(String field) throws NullPointerException {
        boolean speaking = Boolean.parseBoolean(field);
        return speaking;
    }

    public static Boolean checkSpeakingInteractive(String promptToChange) throws Exception {
        Boolean t = null;
        String inData;
        BufferedReader in = DataReader.getTreat();
        while (true) {
            try {
                inData = in.readLine();
                if(inData.equals("TRUE")){
                    t = true;
                }
                else if(inData.equals("FALSE")){
                    t = false;
                }else{
                    throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                System.out.println("Данные введены некорректно" + "\n" + "Попробуйте ввести ещё раз");
                System.out.println(promptToChange);
                continue;
            }
            break;
        }
        return t;
    }

    public static DragonType checkDragonTypeInteractive(String promptToChange) {
        DragonType t = null;
        String inData;
        BufferedReader in = DataReader.getTreat();
        while (true) {
            try {
                inData = in.readLine();
                t = DragonType.valueOf(inData);
            } catch (Exception e) {
                System.out.println("Данные введены некорректно" + "\n" + "Попробуйте ввести ещё раз");
                System.out.println(promptToChange);
                continue;
            }
            break;
        }
        return t;
    }
    public static Double checkX(String field) throws IllegalArgumentException {
        double x = Double.parseDouble(field);
        return x;
    }

    public static Double checkXInteractive(String promptToChange) {
        Double t = null;
        String inData;
        BufferedReader in = DataReader.getTreat();
        while (true) {
            try {
                inData = in.readLine();
                t = Double.parseDouble(inData);
            } catch (Exception e) {
                System.out.println("Данные введены некорректно" + "\n" + "Попробуйте ввести ещё раз");
                System.out.println(promptToChange);
                continue;
            }
            break;
        }
        return t;
    }
    public static Double checkY(String field) throws IllegalArgumentException {
        double y = Double.parseDouble(field);
        if (y <= -923) {
            throw new IllegalArgumentException("Неправильно введено Y");
        }
        return y;
    }

    public static Double checkYInteractive(String promptToChange) {
        Double t = null;
        String inData;
        BufferedReader in = DataReader.getTreat();
        while (true) {
            try {
                inData = in.readLine();
                t = Double.parseDouble(inData);
                if (t <= -923) {
                    throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                System.out.println("Данные введены некорректно" + "\n" + "Попробуйте ввести ещё раз");
                System.out.println(promptToChange);
                continue;
            }
            break;
        }
        return t;
    }
    public static Double checkTooth(String field) throws IllegalArgumentException {
        double toothcount = Double.parseDouble(field);
        return toothcount;
    }


    public static Double checkToothCountInteractive(String promptToChange) {
        Double t = null;
        String inData;
        BufferedReader in = DataReader.getTreat();
        while (true) {
            try {
                inData = in.readLine();
                t = Double.parseDouble(inData);
            } catch (NullPointerException e) {
                t = null;
            } catch (Exception e) {
                System.out.println("Данные введены некорректно" + "\n" + "Попробуйте ввести ещё раз");
                System.out.println(promptToChange);
                continue;
            }
            break;
        }
        return t;
    }


}
