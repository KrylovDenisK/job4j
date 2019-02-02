package ru.job4j.tracker;

public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";
    /**
     * Константа меню для вывода всех заявок
     */
    private static final String SHOW = "1";
    /**
     * Константа меню для редактирования заявки
     */
    private static final String EDIT = "2";
    /**
     * Константа меню для удаления заявки
     */
    private static final String DELETE = "3";
    /**
     *Константа меню для поиска заявки по ID
     */
    private static final String FINDBYID = "4";
    /**
     * Константа меню для поиска заявки по имени
     */
    private static final String FINDBYNAME = "5";
    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
    /**
     * Получение данных от пользователя.
     */
    private final Input input;
    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;
    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }
    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW.equals(answer)) {
                this.showAllItems();
            } else if (EDIT.equals(answer)) {
                this.replaceItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FINDBYID.equals(answer)) {
                this.findItemById();
            } else if (FINDBYNAME.equals(answer)) {
                this.findItemsByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
                }
            }
        }
    /**
     * Удаление заявки
     */
    private void deleteItem() {
            System.out.println("--------------Удаление заявки-------------");
            String id = this.input.ask("Введите id заявки:");
            this.tracker.delete(id);
        }
    /**
     * Замена заявки по ID
     */
    private void replaceItem() {
            System.out.println("----------------Замена заявки-------------");
            String name = this.input.ask("Введите имя новой заявки :");
            String desc = this.input.ask("Введите описание новой заявки :");
            String id = this.input.ask("Введите id изменяемой заявки");
            Item item = new Item(name, desc);
            this.tracker.replace(id, item);
    }
    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void showAllItems() {
            System.out.println("-------------- Список заявок---------------");
            for (Item item : this.tracker.findAll()) {
                System.out.println("Нименование заявки: " + item.getName() + " ID заявки: " + item.getId());
            }

        }

    /**
     * Создание заявки
     */
    private void createItem() {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = this.input.ask("Введите имя заявки :");
            String desc = this.input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            this.tracker.add(item);
            System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
        }

    /**
     * Поиск заявки по ID
     */
    private void findItemById() {
            System.out.println("------------ Поиск заявки по ID --------------");
            String id = this.input.ask("Введите ID заявки :");
            Item item = this.tracker.findById(id);
            if (item != null) {
                System.out.println("Имя заявки: " + item.getName() + " Описание заявки: " + item.getDesc());
                } else {
                System.out.println("Заявка не найдена!!!");
            }
        }

    /**
     * Поиск заявки по имени
     */
    private void findItemsByName() {
            System.out.println("------------ Поиск заявок по имени --------------");
            String name = this.input.ask("Введите имя заявки :");
            for (Item item : this.tracker.findByName(name)) {
                System.out.println("Нименование заявки: " + item.getName() + " ID заявки: " + item.getId());
            }
        }

    /**
     * Вывод меню
     */
    private void showMenu() {
            StringBuilder menu = new StringBuilder();
            menu.append("Меню.\n");
            menu.append("0. Add new Item\n");
            menu.append("1. Show all items\n");
            menu.append("2. Edit item\n");
            menu.append("3. Delete item\n");
            menu.append("4. Find item by Id\n");
            menu.append("5. Find items by name\n");
            menu.append("6. Exit Program\n");
            System.out.println(menu);
        }

        /**
         * Запуск программы.
         */
        public static void main(String[] args) {
            new StartUI(new ConsoleInput(), new Tracker()).init();
        }
}
