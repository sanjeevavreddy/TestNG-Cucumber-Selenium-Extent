package pages;

public class BasePage {

    public FillFormPage fillFormPage;

    public FillFormPage fillFormPage() {
        if (fillFormPage == null)
            fillFormPage = new FillFormPage();
        return fillFormPage;
    }

}
