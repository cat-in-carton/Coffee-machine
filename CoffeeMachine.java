package machine;

import java.util.Scanner;


public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String action = "";
        while (!action.equals("exit")) {
             if (wannaCoffee(action)) {
                 action = scanner.nextLine();
             }
        }
    }

    enum State {
        START,
        SELECT_ACTION,
        BUY,
        FILL,
        FILL_WATER,
        FILL_MILK,
        FILL_BEANS,
        FILL_CUPS
    }

    public static State state = State.START;
    public static int[] resources = new int[] {400, 540, 120, 9, 550};

    public static boolean wannaCoffee(String input) {
        //index0 - water, 1 - milk, 2 - beans, 3 - cups, 4 - bank
        switch (state) {
            case START:            //первый запуск - "главное меню"
                System.out.println("Write action (buy, remaining, fill, take, exit):");
                state = State.SELECT_ACTION;
                return true;
            case SELECT_ACTION:            //выбор действия в "главном меню"
                switch (input) {
                    case "buy":
                        state = State.BUY;
                        buy();
                        return true;
                    case "fill":
                        state = State.FILL;
                        return false;
                    case "take":
                        take();
                        resources[4] = 0;
                        state = State.START;
                        return false;
                    case "remaining":
                        information(resources);
                        state = State.START;
                        return false;
                    case "exit":
                        return false;
                    default:
                        System.out.println("Mistake! Re-enter: ");
                        state = State.SELECT_ACTION;
                        return true;
                }
            case BUY:            //покупка кофе
                switch (input) {
                    case "back":
                        state = State.START;
                        return false;
                    case "1":
                        if (resources[0] >= 250) {
                            resources[0] -= 250;
                        } else {
                            System.out.println("Sorry, not enough water!");
                            state = State.START;
                            return false;
                        }
                        if (resources[2] >= 16) {
                            resources[2] -= 16;
                        } else {
                            System.out.println("Sorry, not enough coffee beans!");
                            state = State.START;
                            return false;
                        }
                        resources[4] += 4;
                        resources[3]--;
                        System.out.println("I have enough resources, making you a coffee!");
                        state = State.START;
                        return false;
                    case "2":
                        if (resources[0] >= 350) {
                            resources[0] -= 350;
                        } else {
                            System.out.println("Sorry, not enough water!");
                            state = State.START;
                            return false;
                        }
                        if (resources[1] >= 75) {
                            resources[1] -= 75;
                        } else {
                            System.out.println("Sorry, not enough milk!");
                            state = State.START;
                            return false;
                        }
                        if (resources[2] >= 20) {
                            resources[2] -= 20;
                        } else {
                            System.out.println("Sorry, not enough coffee beans!");
                            state = State.START;
                            return false;
                        }
                        resources[4] += 7;
                        resources[3]--;
                        System.out.println("I have enough resources, making you a coffee!");
                        state = State.START;
                        return false;
                    case "3":
                        if (resources[0] >= 200) {
                            resources[0] -= 200;
                        } else {
                            System.out.println("Sorry, not enough water!");
                            state = State.START;
                            return false;
                        }
                        if (resources[1] >= 100) {
                            resources[1] -= 100;
                        } else {
                            System.out.println("Sorry, not enough milk!");
                            state = State.START;
                            return false;
                        }
                        if (resources[2] >= 12) {
                            resources[2] -= 12;
                        } else {
                            System.out.println("Sorry, not enough coffee beans!");
                            state = State.START;
                            return false;
                        }
                        resources[4] += 6;
                        resources [3]--;
                        System.out.println("I have enough resources, making you a coffee!");
                        state = State.START;
                        return false;
                }
            case FILL:        //восполнение ресурсов
                System.out.println("Write how many ml of water do you want to add:");
                state = State.FILL_WATER;
                return true;
            case FILL_WATER:
                fillWater(input);
                state = State.FILL_MILK;
                System.out.println("Write how many ml of milk do you want to add:");
                return true;
            case FILL_MILK:
                fillMilk(input);
                state = State.FILL_BEANS;
                System.out.println("Write how many grams of coffee beans do you want to add:");
                return true;
            case FILL_BEANS:
                fillBeans(input);
                state = State.FILL_CUPS;
                System.out.println("Write how many disposable cups of coffee do you want to add");
                return true;
            case FILL_CUPS:
                fillCups(input);
                System.out.println();
                state = State.START;
                return false;
        }
        return false;
    }


    public static void information(int[] a) {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.printf("%d of water%n%d of milk%n%d of coffee beans%n%d " +
                "of disposable cups%n%d of money%n%n", a[0], a[1], a[2], a[3], a[4]);
    }

    public static void buy() {
        System.out.println("What do you want to buy? 1 - espresso, " +
                "2 - latte, 3 - cappuccino, back - to main menu:");
    }

    public static void fillWater(String amount) {
        resources[0] += Integer.parseInt(amount);
    }
    public static void fillMilk(String amount) {
        resources[1] += Integer.parseInt(amount);
    }
    public static void fillBeans(String amount) {
        resources[2] += Integer.parseInt(amount);
    }
    public static void fillCups(String amount) {
        resources[3] += Integer.parseInt(amount);
    }
    public static void take() {
        System.out.printf("I gave you $%d%n%n", resources[4]);
    }
}
