/**
 * Selections.
 * @param <T> type.
 */
public class Selections<T> {

    private String key;
    private String message;
    private T returnValue;
    private Menu<T> subMenu;

    /**
     * Constructor.
     * @param key - key.
     * @param message - message.
     * @param returnValue - return value.
     */
    public Selections(String key, String message, T returnValue) {
        this.key = key;
        this.message = message;
        this.returnValue = returnValue;
        this.subMenu = null;
    }

    /**
     * constructor2.
     * @param key2 - key
     * @param message2 - message
     * @param subMenu2 - subMenu
     */
    public Selections(String key2, String message2, Menu<T> subMenu2) {
        this.key = key2;
        this.message = message2;
        this.subMenu = subMenu2;
        this.returnValue = null;
    }

    /**
     * getKey.
     * @return the key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * getMessage.
     * @return message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * getReturnValue.
     * @return value.
     */
    public T getReturnValue() {
        return this.returnValue;
    }

    /**
     * setSubMenu.
     * @return subMenu.
     */
    public Menu<T> getSubMenu() {
        return this.subMenu;
    }
}
