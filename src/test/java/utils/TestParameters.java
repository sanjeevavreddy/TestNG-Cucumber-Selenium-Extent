package utils;

public class TestParameters {
    private static ThreadLocal<String> browser = new ThreadLocal<>();
    private static ThreadLocal<String> gridURL = new ThreadLocal<>();
    private static ThreadLocal<Integer> screeShotCount = ThreadLocal.withInitial(() -> 1);

    public static void setBrowser(String browserName) {
        browser.set(browserName);
    }

    public static String getBrowser() {
        return browser.get();
    }

    public static void setGridURL(String browserName) {
        gridURL.set(browserName);
    }

    public static String getGridURL() {
        return gridURL.get();
    }

    public static void setScreeShotCount(int count) {
        screeShotCount.set(count);
    }

    public static int getScreeShotCount() {
        return screeShotCount.get();
    }

}