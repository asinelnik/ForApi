public class Phone {
    private Object phone;
    private String locale;

    public Phone(Object phone, String locale) {
        this.phone = phone;
        this.locale = locale;
    }

    public Object getPhone() {
        return phone;
    }

    public String getLocale() {
        return locale;
    }
}
