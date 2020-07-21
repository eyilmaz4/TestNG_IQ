package excelDriven;

public class ExcelOperations {
    public static void main(String[] args) {
        Xls_Reader reader= new Xls_Reader("C:\\Users\\12812\\TestNG_IQ\\ebay.xlsx");
        String firstname=reader.getCellData("ebayTest","FirstName",2);
        System.out.println(firstname);
        String lastname=reader.getCellData("ebayTest","LastName",2);
        System.out.println(lastname);

        String email=reader.getCellData("ebayTest","Email",2);
        System.out.println(email);
        String password=reader.getCellData("ebayTest","Password",2);
        System.out.println(password);
    }
}
