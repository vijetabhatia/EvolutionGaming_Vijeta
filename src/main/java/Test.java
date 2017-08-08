import language_pack.*;
import model.Ad;
import org.junit.Assert;
import web_driver_bindings.GlobalController;

import java.util.List;

public class Test {
    
    
    public static void main(String[] args) {
        try {
            //Open the browser and maximize it.
            //Open  ss.lv
            TestSteps.goToHomePage();
            System.out.println( " Successfully logged into the Home Page");

            //switch to Russian language.
            TestSteps.switchLanguage(Language.RU);

            System.out.println(" Successfully switched the language to Russia");
            //Go to the section “Электротехника”
            TestSteps.openHomePageMenu(MainMenu.ELECTRICAL);

            System.out.println("Successfully the electrical engineering section");
            //open search ('Поиск')
            TestSteps.openTopMenu(TopMenus.SEARCH);
            System.out.println(" Successfully clicked on search");
            
            //in Search enter the search phrase (eg. 'Computer') and select a different search parameters.
            //Click Search
            TestSteps.searchForElectronics("Компьютер", 1, 500, SubHeadingSearch.SELLING,
                    City.ALL, SearchPeriod.ALL, Sorting.PRICE);

            System.out.println("Successfully clicked on electronics");

            //Sort the results by price
//                TestSteps.sortByPrice();

            System.out.println(" Successfully sorted the price");
            //select option 'Продажа' in "Тип сделки" dropdown.
            TestSteps.searchOnSearchResults(null, null, null, TransactionType.SELL);

            System.out.println(" Successfully clicked");
            // Open “Расширенный поиск”. (advanced search)
            TestSteps.goToExtendedSearch();

            System.out.println(" Successfully clicked on advanced search ");

            //Enter search option price between 160 and 300.
            TestSteps.searchForElectronics(null, 160, 300, null, null, null, null);

            System.out.println(" Successfully searched price option between 160 and 200 ");

            //Choose at least 3 random ads.
            
            int adsToBookMarks = 3;
            // Press “Добавить выбранные в закладки”
            List<Ad> selectedAds = TestSteps.addToBookMarks(adsToBookMarks);
            System.out.println(selectedAds +"This is the list of selected ads");
            Assert.assertTrue(selectedAds.size() == adsToBookMarks);
            System.out.println(" Successfully added ads to the bookmarks");
            //Open “Закладки”
            TestSteps.openTopMenu(TopMenus.BOOKMARKS);
            TestSteps.adsMatchToBookmarked(selectedAds);
            Boolean bookmarkedMatchToSelected = TestSteps.adsMatchToBookmarked(selectedAds);
            Assert.assertTrue(bookmarkedMatchToSelected);
            System.out.println(" Successfully verified thats ads are getting matched with the bookmarks");


        } finally {
//            Close the browser
              GlobalController.closeBrowser();
        }
    }
}
