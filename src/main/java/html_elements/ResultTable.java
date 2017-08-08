package html_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import web_driver_bindings.WebDriverBindings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ResultTable {
    
        private List<ResultRow> rowList = new ArrayList<>();
        protected WebDriverBindings driverBindings = new WebDriverBindings();
        private String cssSelector;
    
    
        public ResultTable(String cssSelector) {
            this.cssSelector = cssSelector;
        }
        
      
        
        public List<ResultRow> getSearchResults() {
            return Collections.unmodifiableList(rowList);
        }
        
        protected WebElement getTable() throws TimeoutException {
            return driverBindings.waitForElementVisibility(By.cssSelector(cssSelector));
            
        }
        
        protected WebElement getText(WebElement tableRow) {


//            String xpath = tableRow.getText(By.cssSelector("td.msg2"));
            String xpath1 = String.valueOf(tableRow.findElement(By.cssSelector("td.msg2")));

            System.out.println(" Printing the xpath for debugging" + xpath1);

            return tableRow.findElement(By.cssSelector("td.msg2"));
        }
        
        protected WebElement getSelector(WebElement tableRow) {
//            String testxPath= tableRow.getAttribute("Продажа");
//            System.out.println(" The desired xpath is" + testxPath);

            return tableRow.findElement(By.cssSelector("td.msga2.pp0 > input"));

//            return tableRow.findElement(By.id("c42192019"));

            //*[@id="c42192019"]
        }

        
        public void initializeTable() throws TimeoutException{
            List<ResultRow> searchResultRows = new ArrayList<>();
            WebElement table = getTable();
            List<WebElement> tableRows = table.findElements(By.cssSelector("tr"));
            for (WebElement tableRow: tableRows) {
                if (tableRow.getAttribute("id").equalsIgnoreCase("head_line")) continue;
                WebElement selector = getSelector(tableRow);
                System.out.println("Displaying the selector in this case" + selector);
                WebElement text = getText(tableRow);
                System.out.println("Displaying the text in this case" + text);
                searchResultRows.add(new ResultRow(selector, text));
            }
            rowList = searchResultRows;
            System.out.println(" Displaying the final rowlist " + rowList);
        }

        public WebElement getPriceColumn() throws TimeoutException {
            return getTable().findElement(By.cssSelector("tr#head_line > td.msg_column_td"));
        }
        
        
        
        public class ResultRow {
            
            private WebElement selector;
            private WebElement text;
            
            private ResultRow(WebElement selector, WebElement text) {
                this.selector = selector;
                this.text = text;
                
            }
            
            public WebElement getSelector() {
                return selector;
            }
            public WebElement getText() {
                return text;
            }
            
        }
        
    }

