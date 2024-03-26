package ra.run;

import ra.entity.Categories;
import ra.entity.Product;

import java.util.Scanner;

public class ShopManagemnet {
    private static ProductManagement productManagement = new ProductManagement();
    private static CategoriesManagement categoriesManagement = new CategoriesManagement();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("******************SHOP MENU*******************\n" +
                    "1. Quản lý danh mục sản phẩm\n" +
                    "2. Quản lý sản phẩm\n" +
                    "3. Thoát");
            System.out.println("Nhập lụa chọn");
            switch (Byte.parseByte(scanner.nextLine())) {
                case 1:
                    categoriesManagement.displayCategoriesMenu();
                    break;
                case 2:
                    productManagement.displayProductMenu();
                    break;
                case 3:
                    System.out.println("Thoát");
                    System.exit(0);
                default:
                    System.out.println("Nhập lựa chọn sai, vui lòng nhập lại");
            }
        }
    }
}
