package ru.job4j.tracker;
/**
 * Описание заявки пользователя
 * @author  Denis Krylov
 * @version $Id$
 */
public class Item {
    private String id;
    private String name;
    private String desc;
    private long created;
    private String comments;
    /**
     * Конструктор нового объекта
     * @param name имя заявки
     * @param desc описание
     //* @param created
     */
    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
        //this.created = created;
    }
    /**
     * Метод задает значение поля this.id по входящему параметру
     * @param id новый id
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Получить значение this.id
     */
    public String getId() {
        return this.id;
    }
    /**
     *Получить значение поля this.name
     */
    public String getName() {
        return this.name;
    }
    /**
     * Метод задает значение поля this.name по входящему параметру
     * @param name новый name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return this.desc;
    }
}
